package com.trinix.bank.controller;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.trinix.bank.entity.Account;
import com.trinix.bank.entity.CustomerPayee;
import com.trinix.bank.entity.TransactionSchedule;
import com.trinix.bank.entity.TransactionSummary;
import com.trinix.bank.service.AccountService;

@Controller
@RequestMapping("/accounts")
public class AccountsController {

	@Autowired
	AccountService accountService;

	@GetMapping("/test")
	@ResponseBody
	private String test() {
		return "working";
	}

	@GetMapping("/getAccounts")
	@ResponseBody
	private List<Account> getAccounts() {
		return accountService.getAccounts();
	}

	@PostMapping("/createAccount")
	@ResponseBody
	private String createAccount(@RequestBody Account account) {
		return accountService.createAccount(account);
	}

	@GetMapping("/depositAmount/{accountId}/{amount}")
	@ResponseBody
	private String depositAmount(@PathVariable("accountId") String accountId, @PathVariable("amount") Double amount) {
		return accountService.depositAmount(accountId, amount);
	}

	@GetMapping("/withDrawAmount/{accountId}/{amount}")
	@ResponseBody
	private String withDrawAmount(@PathVariable("accountId") String accountId, @PathVariable("amount") Double amount) {
		return accountService.withDrawAmount(accountId, amount);
	}

	@PostMapping("/transferAmount/{toAccountId}/{fromAccountId}/{amount}")
	@ResponseBody
	private String transferAmount(@PathVariable("toAccountId") String toAccountId,
			@PathVariable("fromAccountId") String fromAccountId, @PathVariable("amount") Double amount) {
		return accountService.transferAmount(toAccountId, fromAccountId, amount);
	}

	@GetMapping("/getCurrentBalance/{accountId}")
	@ResponseBody
	private String getCurrentBalance(@PathVariable("accountId") String accountId) {
		return accountService.getCurrentBalance(accountId);
	}

	@GetMapping("/getAllTransactionSummary/{accountId}")
	@ResponseBody
	private List<TransactionSummary> getAllTransactionSummary(@PathVariable("accountId") String accountId) {
		return accountService.getAllTransactionSummary(accountId);
	}

	@PostMapping("/addPayeeAccount/{accountId}")
	@ResponseBody
	private String addPayeeAccount(@RequestBody CustomerPayee customerPayee,
			@PathVariable("accountId") String accountId) {
		return accountService.addPayeeAccount(accountId, customerPayee);
	}

	@GetMapping("/getPayees/{accountId}")
	@ResponseBody
	private List<CustomerPayee> getPayees(@PathVariable("accountId") String accountId) {
		return accountService.getPayees(accountId);
	}

	@DeleteMapping("/deletePayeeAccount/{accountId}/{payeeAccountId}")
	@ResponseBody
	private String deletePayeeAccount(@PathVariable("accountId") String accountId,
			@PathVariable("payeeAccountId") String payeeAccountId) {
		return accountService.deletePayeeAccount(accountId, payeeAccountId);
	}

	@PostMapping("/transferAmountOnSchedule")
	@ResponseBody
	private String transferAmountOnSchedule(@RequestBody TransactionSchedule transactionSchedule) {
		return accountService.transferAmountOnSchedule(transactionSchedule);
	}

	@GetMapping("/getBalanceForFutureDate/{accountId}/{date}")
	@ResponseBody
	private String getBalanceForFutureDate(@PathVariable("accountId") String accountId,
			@PathVariable("date") @DateTimeFormat(pattern = "yyyyMMdd") Date futureDate) {
		return accountService.getBalanceForFutureDate(accountId, futureDate);
	}

}
