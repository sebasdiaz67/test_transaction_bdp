package com.pichincha.backend.test.service;

import static org.assertj.core.api.Assertions.assertThat;

import java.time.LocalDateTime;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import com.pichincha.backend.test.dto.NewTransactionDto;
import com.pichincha.backend.test.model.Account;
import com.pichincha.backend.test.repository.AccountRepository;

@RunWith(SpringRunner.class)
@SpringBootTest
public class TransactionServiceTest {
	
	@Autowired
	AccountRepository accountRepository;

	@Autowired
	AccountService accountService;
	
	@Test
	public void shouldReturnAddedTransaction() {
		NewTransactionDto newTransactionDto = new NewTransactionDto();
		newTransactionDto.setComment("Comment");
		newTransactionDto.setType("Type");	
		Account account = createTestAccount();
		newTransactionDto.setAccountId(account.getId());
		Long idTransaction = accountService.addTransaction(newTransactionDto);		
		assertThat(newTransactionDto.getAccountId()).isNotNull();
		assertThat(idTransaction).isGreaterThan(0L);
	}
	
	private Account createTestAccount() {
		Account account = new Account();
		account.setNumber("Test Number");
		account.setType("Test type");
		account.setCreationDate(LocalDateTime.now());
		accountRepository.save(account);
		return account;
	}
	
}
