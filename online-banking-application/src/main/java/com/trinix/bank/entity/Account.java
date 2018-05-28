package com.trinix.bank.entity;

import java.util.List;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.index.CompoundIndex;
import org.springframework.data.mongodb.core.index.CompoundIndexes;
import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection = "accounts")
@CompoundIndexes(value = {
		@CompoundIndex(name = "acc_id", def = "{ 'accountId': 1 }", background = true, unique = true) })
public class Account {

	@Id
	private String id;
	private String accountId;

	@NotNull(message = "ifsc Code is mandatory")
	private String ifscCode;

	@NotNull(message = "branch name is mandatory")
	private String branchName;

	private String accountType;

	@Min(value = 0)
	private double amount;
	
	private Customer customer;
	private List<CustomerPayee> payees;

	public String getAccountId() {
		return this.accountId;
	}

	public void setAccountId(String argAccountId) {
		this.accountId = argAccountId;
	}

	public String getIfscCode() {
		return this.ifscCode;
	}

	public void setIfscCode(String argIfscCode) {
		this.ifscCode = argIfscCode;
	}

	public String getBranchName() {
		return this.branchName;
	}

	public void setBranchName(String argBranchName) {
		this.branchName = argBranchName;
	}

	public String getAccountType() {
		return this.accountType;
	}

	public void setAccountType(String argAccountType) {
		this.accountType = argAccountType;
	}

	public double getAmount() {
		return this.amount;
	}

	public void setAmount(double argAmount) {
		this.amount = argAmount;
	}

	public Customer getCustomer() {
		return customer;
	}

	public void setCustomer(Customer argCustomer) {
		this.customer = argCustomer;
	}

	public List<CustomerPayee> getPayees() {
		return payees;
	}

	public void setPayees(List<CustomerPayee> argPayees) {
		this.payees = argPayees;
	}

}
