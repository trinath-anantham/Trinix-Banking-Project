package com.trinix.bank.entity;

import javax.validation.constraints.NotNull;

public class CustomerPayee {

	@NotNull(message = "account id is mandatory")
	private String accountId;
	
	@NotNull(message = "ifsc Code is mandatory")
	private String ifscCode;
	
	private String nickName;

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

	public String getNickName() {
		return this.nickName;
	}

	public void setNickName(String argNickName) {
		this.nickName = argNickName;
	}

}
