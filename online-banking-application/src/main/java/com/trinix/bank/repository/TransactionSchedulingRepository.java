package com.trinix.bank.repository;

import java.util.Date;
import java.util.List;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import com.trinix.bank.entity.TransactionSchedule;

@Repository
public interface TransactionSchedulingRepository extends MongoRepository<TransactionSchedule, String> {

	public List<TransactionSchedule> findByFromAccountId(String accountId);

	public List<TransactionSchedule> findByTransactionDateAndIsCompleted(Date date, boolean isCompleted);
	
	public List<TransactionSchedule> findByIsCompleted(boolean isCompleted);

}
