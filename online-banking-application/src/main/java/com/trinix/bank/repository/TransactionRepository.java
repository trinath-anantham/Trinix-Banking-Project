package com.trinix.bank.repository;

import java.util.List;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import com.trinix.bank.entity.TransactionSummary;

@Repository
public interface TransactionRepository extends MongoRepository<TransactionSummary, String> {

	public List<TransactionSummary> findByFromAccountId(String accountId);
	
}
