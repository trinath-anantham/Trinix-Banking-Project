package com.trinix.bank.service;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import com.trinix.bank.dao.AccountsDao;
import com.trinix.bank.dao.NextSequenceService;
import com.trinix.bank.entity.Account;
import com.trinix.bank.entity.CustomerPayee;
import com.trinix.bank.entity.TransactionSchedule;
import com.trinix.bank.entity.TransactionSummary;
import com.trinix.bank.repository.AccountsRepository;
import com.trinix.bank.utillity.TransactionType;

@Component
@Transactional
public class AccountService {

	@Autowired
	AccountsDao accountDao;

	@Autowired
	AccountsRepository accountsRepository;

	@Autowired
	NextSequenceService nextSequenceService;

	@Autowired
	TransactionalService transactionalService;

	@Autowired
	TransactionSchedulleService transactionSchedulleService;

	public List<Account> getAccounts() {
		return accountsRepository.findAll();
	}

	public String createAccount(Account argAccount) {
		argAccount.setAccountId(nextSequenceService.getNextSequence("account"));
		argAccount.getCustomer().setCustomerId(nextSequenceService.getNextSequence("customer"));
		argAccount.setPayees(new ArrayList<>());// Removes default payees
		Account newAccount = accountsRepository.save(argAccount);
		return transactionalService.saveTransaction(newAccount.getAccountId(), null, newAccount.getAmount(),
				TransactionType.DEPOSIT);
	}

	public String depositAmount(String argAccountId, Double argAmount) {
		Account account = accountsRepository.findByAccountId(argAccountId);
		if (argAmount < 0)
			return "Please Enter correct amount";
		if (account != null) {
			synchronized (account) {
				account.setAmount(account.getAmount() + argAmount);
				account = accountsRepository.save(account);
				return transactionalService.saveTransaction(account.getAccountId(), null, account.getAmount(),
						TransactionType.DEPOSIT);
			}
		}
		return "Account not found, please chek it once";
	}

	public String withDrawAmount(String argAccountId, Double argAmount) {
		Account account = accountsRepository.findByAccountId(argAccountId);
		if (argAmount < 0)
			return "Please Enter correct amount";
		if (account != null) {
			synchronized (account) {
				if (account.getAmount() < argAmount) {
					return "InSufficient balance";
				}
				account.setAmount(account.getAmount() - argAmount);
				account = accountsRepository.save(account);
				return transactionalService.saveTransaction(account.getAccountId(), null, account.getAmount(),
						TransactionType.WITHDRAW);
			}
		}
		return "Account not found, please chek it once";
	}

	@Transactional
	public String transferAmount(String toAccountId, String fromAccountId, Double argAmount) {
		if (toAccountId == fromAccountId)
			return "Please select different account id";
		String fromAccount = this.withDrawAmount(fromAccountId, argAmount);
		if (fromAccount != null && fromAccount.contains("Transaction Id ")) {
			String toAccount = this.depositAmount(toAccountId, argAmount);
			if (toAccount != null && toAccount.contains("Transaction Id ")) {
				return transactionalService.saveTransaction(fromAccountId, toAccountId, argAmount,
						TransactionType.DEPOSIT);
			} else {
				this.depositAmount(fromAccountId, argAmount);
			}
		}
		return "Error in Transaction";
	}

	public String getCurrentBalance(String argAccountId) {
		Account account = accountsRepository.findByAccountId(argAccountId);
		if (account != null) {
			return "Your current balance is :" + account.getAmount();
		}
		return "Invalid account number, please check once";
	}

	public List<TransactionSummary> getAllTransactionSummary(String argAccountId) {
		return transactionalService.getAllTransactionSummary(argAccountId);
	}

	public String addPayeeAccount(String argAccountId, CustomerPayee argCustomerPayee) {
		Account account = accountsRepository.findByAccountId(argAccountId);
		if (account != null && argCustomerPayee.getAccountId() != null && argCustomerPayee.getIfscCode() != null) {
			account.getPayees().add(argCustomerPayee);
			accountsRepository.save(account);
			return "Added payee successfully";
		}
		return "Invalid details, please check once";
	}

	public String deletePayeeAccount(String argAccountId, String payeeAccountId) {
		Account account = accountsRepository.findByAccountId(argAccountId);
		if (account != null) {
			List<CustomerPayee> list = account.getPayees().stream().filter(e -> e.getAccountId().equals(payeeAccountId))
					.collect(Collectors.toList());
			account.setPayees(list);
			accountsRepository.save(account);
			return "Added payee successfully";
		}
		return "Invalid account number, please check once";
	}

	public String transferAmountOnSchedule(TransactionSchedule transactionSchedule) {
		return transactionSchedulleService.saveSchedule(transactionSchedule);
	}

	public String getBalanceForFutureDate(String argAccountId, Date argFutureDate) {
		Account account = accountsRepository.findByAccountId(argAccountId);
		StringBuilder sb = new StringBuilder();
		if (account != null) {
			sb.append("Your current balance is :" + account.getAmount() + "\n");
			sb.append("Based on intrest rate 4% balance on :" + argFutureDate + " will be "
					+ this.calaculateIntrest(account.getAmount(), argFutureDate) + "\n");
		} else {
			sb.append("Invalid account number, please check once");
		}
		return sb.toString();
	}

	private Double calaculateIntrest(Double amount, Date date) {
		double timePeriod = this.daysBetween(date, new Date());
		return (4 * (timePeriod / 365) * amount) / 100 + amount;
	}

	private long daysBetween(Date one, Date two) {
		long difference = (one.getTime() - two.getTime()) / 86400000;
		return Math.abs(difference);
	}

	public List<CustomerPayee> getPayees(String argAccountId) {
		Account account = accountsRepository.findByAccountId(argAccountId);
		if (account != null) {
			return account.getPayees();
		}
		return null;
	}

}
