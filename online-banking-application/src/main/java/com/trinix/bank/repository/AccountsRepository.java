package com.trinix.bank.repository;

import java.util.List;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import com.trinix.bank.entity.Account;

@Repository
public interface AccountsRepository extends MongoRepository<Account, String> {

	public List<Account> findAll();

	public Account findByAccountId(String accountId);
	
	
}
