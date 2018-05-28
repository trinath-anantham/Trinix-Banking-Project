package com.trinix.bank.dao;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.trinix.bank.entity.Account;
import com.trinix.bank.repository.AccountsRepository;

@Component
public class AccountsDao {

	@Autowired
	AccountsRepository accountsRepository;

	public List<Account> getAccount() {
		return accountsRepository.findAll();
	}

	public Account createAccount(Account account) {
		return accountsRepository.save(account);
	}

	public Account depositAmount(String argAccountId, Double argAmount) {
		return null;
	}
}
