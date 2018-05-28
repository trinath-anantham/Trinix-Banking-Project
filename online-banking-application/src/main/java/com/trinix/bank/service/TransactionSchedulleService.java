package com.trinix.bank.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.trinix.bank.entity.TransactionSchedule;
import com.trinix.bank.repository.TransactionSchedulingRepository;

@Service
public class TransactionSchedulleService {

	@Autowired
	TransactionSchedulingRepository transactionSchedulingRepository;

	public String saveSchedule(TransactionSchedule transactionSchedule) {
		transactionSchedulingRepository.save(transactionSchedule);
		return "Transaction saved successfully";
	}

}
