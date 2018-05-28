package com.trinix.bank.entity;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection = "account_counter")
public class AccountCounter {

	@Id
	private String id;
	private long sequenceId;

	public String getId() {
		return this.id;
	}

	public void setId(String argId) {
		this.id = argId;
	}

	public long getSequenceId() {
		return this.sequenceId;
	}

	public void setSequenceId(long argSequenceId) {
		this.sequenceId = argSequenceId;
	}

}
