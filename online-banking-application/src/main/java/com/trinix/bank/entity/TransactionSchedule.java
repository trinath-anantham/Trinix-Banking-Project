package com.trinix.bank.entity;

import java.util.Date;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection = "transactionSchedule")
public class TransactionSchedule {

	@Id
	private String transactionScheduledId;
	private String toAccountId;
	private String fromAccountId;
	private Date transactionCreatedDate;
	private Date transactionDate;
	private double amount;
	private boolean isCompleted;
	private String status;

	public String getTransactionScheduledId() {
		return this.transactionScheduledId;
	}

	public void setTransactionScheduledId(String argTransactionScheduledId) {
		this.transactionScheduledId = argTransactionScheduledId;
	}

	public String getToAccountId() {
		return this.toAccountId;
	}

	public void setToAccountId(String argToAccountId) {
		this.toAccountId = argToAccountId;
	}

	public String getFromAccountId() {
		return this.fromAccountId;
	}

	public void setFromAccountId(String argFromAccountId) {
		this.fromAccountId = argFromAccountId;
	}

	public Date getTransactionCreatedDate() {
		return this.transactionCreatedDate;
	}

	public void setTransactionCreatedDate(Date argTransactionCreatedDate) {
		this.transactionCreatedDate = argTransactionCreatedDate;
	}

	public Date getTransactionDate() {
		return this.transactionDate;
	}

	public void setTransactionDate(Date argTransactionDate) {
		this.transactionDate = argTransactionDate;
	}

	public double getAmount() {
		return this.amount;
	}

	public void setAmount(double argAmount) {
		this.amount = argAmount;
	}

	public boolean isCompleted() {
		return this.isCompleted;
	}

	public void setCompleted(boolean argIsCompleted) {
		this.isCompleted = argIsCompleted;
	}

	public String getStatus() {
		return this.status;
	}

	public void setStatus(String argStatus) {
		this.status = argStatus;
	}

}
