package com.trinix.bank.service;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import com.trinix.bank.entity.TransactionSchedule;
import com.trinix.bank.repository.TransactionSchedulingRepository;

@Service
public class SchedulerService {

	@Autowired
	TransactionSchedulingRepository transactionSchedulingRepository;

	@Autowired
	AccountService accountService;

	@Scheduled(cron = "*/10 * * * * ?")
	public void scheduleTask() {
		List<TransactionSchedule> list = transactionSchedulingRepository.findByIsCompleted(false);
		list.parallelStream().forEach(transactionSchedule -> {
			if (!transactionSchedule.getTransactionDate().after(new Date())) {
				String msg = accountService.transferAmount(transactionSchedule.getToAccountId(),
						transactionSchedule.getFromAccountId(), transactionSchedule.getAmount());
				if (msg != null) {
					transactionSchedule.setCompleted(true);
					transactionSchedule.setStatus(msg);
					transactionSchedulingRepository.save(transactionSchedule);
				}
			}
		});
	}

}
