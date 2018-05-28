package com.trinix.bank.entity;

import java.util.Date;

import javax.validation.constraints.NotNull;

public class Customer {

	private String customerId;

	@NotNull(message = "Customer Name is mandatory")
	private String customerName;

	private Date dob;

	@NotNull(message = "Mobile No is mandatory")
	private long mobileNo;
	
	private String address;

	public String getCustomerName() {
		return this.customerName;
	}

	public void setCustomerName(String argCustomerName) {
		this.customerName = argCustomerName;
	}

	public long getMobileNo() {
		return this.mobileNo;
	}

	public void setMobileNo(long argMobileNo) {
		this.mobileNo = argMobileNo;
	}

	public String getAddress() {
		return this.address;
	}

	public void setAddress(String argAddress) {
		this.address = argAddress;
	}

	public Date getDob() {
		return dob;
	}

	public void setDob(Date argDob) {
		this.dob = argDob;
	}

	public String getCustomerId() {
		return this.customerId;
	}

	public void setCustomerId(String argCustomerId) {
		this.customerId = argCustomerId;
	}

}
