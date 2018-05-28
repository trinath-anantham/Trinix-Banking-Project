package com.trinix.bank.dao;

import static org.springframework.data.mongodb.core.FindAndModifyOptions.options;

import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoOperations;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.data.mongodb.core.query.Update;
import org.springframework.stereotype.Service;

import com.trinix.bank.entity.AccountCounter;
import com.trinix.bank.entity.CustomerCounter;

@Service
public class NextSequenceService {

	@Autowired
	private MongoOperations mongo;

	public static final String USER_ID_SEQUENCE_NAME = "sequence";
	public static final long ACCOUNT_CYCLE_ID = 53434125;
	public static final long CUSTOMER_CYCLE_ID = 353231;

	public String getNextSequence(String type) {
		if (type.equals("account")) {
			return getNextAccountIdSequence(USER_ID_SEQUENCE_NAME);
		} else if (type.equals("customer")) {
			return getNextCustomerIdSequence(USER_ID_SEQUENCE_NAME);
		}
		return UUID.randomUUID().toString();
	}

	private synchronized String getNextAccountIdSequence(String counterName) {
		Query query = new Query(Criteria.where("_id").is(counterName));
		Update update = new Update().inc("sequenceId", 1);
		AccountCounter counter = mongo.findAndModify(query, update, options().returnNew(true).upsert(true),
				AccountCounter.class);
		return ACCOUNT_CYCLE_ID + counter.getSequenceId() + "";
	}

	public synchronized String getNextCustomerIdSequence(String counterName) {
		Query query = new Query(Criteria.where("_id").is(counterName));
		Update update = new Update().inc("sequenceId", 1);
		CustomerCounter counter = mongo.findAndModify(query, update, options().returnNew(true).upsert(true),
				CustomerCounter.class);
		return CUSTOMER_CYCLE_ID + counter.getSequenceId() + "";
	}
}
