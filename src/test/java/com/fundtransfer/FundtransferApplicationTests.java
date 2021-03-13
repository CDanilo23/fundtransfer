package com.fundtransfer;

import com.fundtransfer.model.dao.AccountRepository;
import com.fundtransfer.model.dao.AttemptRepository;
import com.fundtransfer.model.entity.AccountEntity;
import com.fundtransfer.model.entity.AttemptEntity;
import java.time.LocalDate;
import java.util.Optional;
import org.junit.Assert;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest
class FundtransferApplicationTests {

	@Autowired
	private AccountRepository accountRepository;

	@Autowired
	private AttemptRepository attemptRepository;

	@Test
	public void givenAccountEntityRepository_whenSaveAndRetreiveEntity_thenOK(){

		AccountEntity newAccountEntity =  new AccountEntity();
		newAccountEntity.setAccountBalance(784512L);
		AccountEntity accountEntity = accountRepository.save(newAccountEntity);

		Optional<AccountEntity> accountFound = accountRepository.findById(accountEntity.getId());

		Assert.assertNotNull(accountFound);
		Assert.assertEquals(accountEntity.getAccountBalance(), accountFound.get().getAccountBalance());

	}

	@Test
	public void givenAccountAttemptRepository_saveEntity() {

		AttemptEntity newAttemptEntity = new AttemptEntity();
		newAttemptEntity.setAttempts(3);
		newAttemptEntity.setLocalDate(LocalDate.now());
		newAttemptEntity.setOriginAccount("12345600");
		AttemptEntity attemptEntity = attemptRepository.save(newAttemptEntity);

		Optional<AttemptEntity> accountFound = null;
		accountFound = attemptRepository.findById(attemptEntity.getId());
		Assert.assertNotNull(accountFound);

	}

}
