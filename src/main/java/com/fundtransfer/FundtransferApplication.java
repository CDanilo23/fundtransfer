package com.fundtransfer;

import com.fundtransfer.model.dao.AccountRepository;
import com.fundtransfer.model.dao.AttemptRepository;
import com.fundtransfer.model.entity.AccountEntity;
import com.fundtransfer.model.entity.AttemptEntity;
import java.time.LocalDate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class FundtransferApplication implements CommandLineRunner {

	@Autowired
	private AttemptRepository attemptRepository;

	@Autowired
	private AccountRepository accountRepository;


	public static void main(String[] args) {
		SpringApplication.run(FundtransferApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception {
		/* Success Operation
		   1. stage with origin account with enough funds
		   2. account with two attempts
		*/
		AttemptEntity newAttemptEntity = new AttemptEntity();
		newAttemptEntity.setAttempts(2);
		newAttemptEntity.setLocalDate(LocalDate.now());
		newAttemptEntity.setOriginAccount("12345600");
		AttemptEntity attemptEntity = attemptRepository.save(newAttemptEntity);

		AccountEntity originAccountEntity = new AccountEntity();
		originAccountEntity.setNumAccount("12345600");
		originAccountEntity.setAccountBalance(10000L);
		accountRepository.save(originAccountEntity);

		AccountEntity destinationAccountEntity = new AccountEntity();
		destinationAccountEntity.setNumAccount("12345601");
		destinationAccountEntity.setAccountBalance(5000L);
		accountRepository.save(destinationAccountEntity);

		/* No Success Operation
		   stage with origin account with less funds
		*/
		AttemptEntity newAttemptEntity1 = new AttemptEntity();
		newAttemptEntity1.setAttempts(1);
		newAttemptEntity1.setLocalDate(LocalDate.now());
		newAttemptEntity1.setOriginAccount("12345700");
		attemptRepository.save(newAttemptEntity);

		AccountEntity withOutFunds = new AccountEntity();
		withOutFunds.setNumAccount("12345700");
		withOutFunds.setAccountBalance(1000L);
		accountRepository.save(withOutFunds);
	}
}
