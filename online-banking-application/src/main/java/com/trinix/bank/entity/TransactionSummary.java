package com.trinix.bank.entity;

import java.util.Date;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection = "transactionSummary")
public class TransactionSummary {

	@Id
	private String transactionId;
	
	private Date dateOfTransaction;
	
	@NotNull(message = "from account id is mandatory")
	private String fromAccountId;
	
	@NotNull(message = "to account id is mandatory")
	private String toAccountId;
	
	@Min(0)
	private double amount;
	
	private String typeOfTransaction;

	public TransactionSummary() {
		super();
	}

	public TransactionSummary(String argFromAccountId, String argToAccountId, double argAmount,
			String argTypeOfTransaction) {
		this.dateOfTransaction = new Date();
		this.fromAccountId = argFromAccountId;
		this.toAccountId = argToAccountId;
		this.amount = argAmount;
		this.typeOfTransaction = argTypeOfTransaction;
	}

	public String getTransactionId() {
		return this.transactionId;
	}

	public void setTransactionId(String argTransactionId) {
		this.transactionId = argTransactionId;
	}

	public Date getDateOfTransaction() {
		return this.dateOfTransaction;
	}

	public void setDateOfTransaction(Date argDateOfTransaction) {
		this.dateOfTransaction = argDateOfTransaction;
	}

	public String getFromAccountId() {
		return this.fromAccountId;
	}

	public void setFromAccountId(String argFromAccountId) {
		this.fromAccountId = argFromAccountId;
	}

	public String getToAccountId() {
		return this.toAccountId;
	}

	public void setToAccountId(String argToAccountId) {
		this.toAccountId = argToAccountId;
	}

	public double getAmount() {
		return this.amount;
	}

	public void setAmount(double argAmount) {
		this.amount = argAmount;
	}

	public String getTypeOfTransaction() {
		return this.typeOfTransaction;
	}

	public void setTypeOfTransaction(String argTypeOfTransaction) {
		this.typeOfTransaction = argTypeOfTransaction;
	}

}
