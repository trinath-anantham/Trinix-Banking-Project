package com.trinix.bank.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.trinix.bank.entity.TransactionSummary;
import com.trinix.bank.repository.TransactionRepository;
import com.trinix.bank.utillity.TransactionType;

@Service
public class TransactionalService {

	@Autowired
	TransactionRepository transactionRepository;

	public String saveTransaction(String fromAccountId, String toAccountId, Double amount,
			TransactionType transactionType) {
		TransactionSummary transactionSummary = new TransactionSummary(fromAccountId, toAccountId, amount,
				transactionType.getName());
		transactionSummary = transactionRepository.save(transactionSummary);
		return prepareTransactionSummary(transactionSummary);
	}

	public String prepareTransactionSummary(TransactionSummary transactionSummary) {
		String messsage = "";
		if (transactionSummary.getTypeOfTransaction().equals(TransactionType.DEPOSIT.getName())) {
			messsage = "Transaction Id : " + transactionSummary.getTransactionId()
					+ ", Amount was deposited to account " + transactionSummary.getFromAccountId() + " with amount : "
					+ transactionSummary.getAmount();
		} else if (transactionSummary.getTypeOfTransaction().equals(TransactionType.WITHDRAW.getName())) {
			messsage = "Transaction Id : " + transactionSummary.getTransactionId()
					+ ", Amount was withdrawed from account " + transactionSummary.getFromAccountId()
					+ " with amount : " + transactionSummary.getAmount();
		} else if (transactionSummary.getTypeOfTransaction().equals(TransactionType.TRANSFER.getName())) {
			messsage = "Transaction Id : " + transactionSummary.getTransactionId()
					+ ", Amount was transfered from account " + transactionSummary.getFromAccountId() + "to account "
					+ transactionSummary.getToAccountId() + " with amount :  " + transactionSummary.getAmount();
		}
		return messsage;
	}

	public List<TransactionSummary> getAllTransactionSummary(String argAccountId) {
		return transactionRepository.findByFromAccountId(argAccountId);
	}
}
