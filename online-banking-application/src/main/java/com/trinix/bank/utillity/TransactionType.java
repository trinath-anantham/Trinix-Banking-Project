package com.trinix.bank.utillity;

public enum TransactionType {

	DEPOSIT("DEPOSIT"), WITHDRAW("WITHDRAW"), TRANSFER("TRANSFER");

	private String name;

	private TransactionType(String name) {
		this.setName(name);
	}

	public static TransactionType fromString(String name) {
		if (("DEPOSIT").equals(name)) {
			return DEPOSIT;
		}
		if (("WITHDRAW").equals(name)) {
			return WITHDRAW;
		}
		if (("TRANSFER").equals(name)) {
			return TRANSFER;
		}
		return null;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}
}
