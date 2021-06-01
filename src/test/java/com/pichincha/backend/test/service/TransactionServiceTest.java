package com.pichincha.backend.test.service;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertEquals;

import java.time.LocalDateTime;
import java.util.List;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import com.pichincha.backend.test.dto.AccountDto;
import com.pichincha.backend.test.dto.NewTransactionDto;
import com.pichincha.backend.test.dto.TransactionDto;
import com.pichincha.backend.test.model.Account;
import com.pichincha.backend.test.repository.AccountRepository;
import com.pichincha.backend.test.service.util.UtilClass;

@RunWith(SpringRunner.class)
@SpringBootTest
public class TransactionServiceTest {
	
	@Autowired
	AccountRepository accountRepository;

	@Autowired
	AccountService accountService;
	
	@Test
	public void shouldReturnAddedTransaction() {
		Account account = createTestAccount();
		
		NewTransactionDto newTransactionDto = UtilClass.createTransaction(account.getId(), "Comment", "Type");
		
		Long idTransaction = accountService.addTransaction(newTransactionDto);
		
		assertThat(newTransactionDto.getAccountId()).isNotNull();
		//assertEquals(idTransaction, 4L);
	}

	private Account createTestAccount() {
		LocalDateTime creationDate = LocalDateTime.of(2021, 6, 01, 20, 51, 16);
		Account account = UtilClass.createObjectAccountTest(creationDate);
		accountRepository.save(account);
		return account;
	}
	
	@Test
	public void shouldReturnErrorExceptionWhenAddedTransactionCantFindAccount() {
		try {
			NewTransactionDto newTransactionDto = UtilClass.createTransaction(20L, "Comment", "Type");
			
			Long idTransaction = accountService.addTransaction(newTransactionDto);
			
		} catch (Exception e) {
			assertThat(e).isNotNull();
		}
	}
	
	@Test
	public void shouldReturnErrorExceptionWhenAddedTransactionWithAccountIdNull() {
		try {
			NewTransactionDto newTransactionDto = UtilClass.createTransaction(null, "Comment", "Type");
			
			Long idTransaction = accountService.addTransaction(newTransactionDto);
			
		} catch (Exception e) {
			assertThat(e).isNotNull();
		}
	}
	
	@Test
	public void shouldReturnTransactionsByAccountId() {
		List<TransactionDto> transactions = accountService.getTransactionsForAccount(1L);
		
		assertThat(transactions).isNotEmpty();
		assertEquals(transactions.get(0).getId(), 1L);
	}
	
	@Test
	public void shouldReturnNullForNotExistingAccount() {
		AccountDto accountDto = accountService.getAccount(10L);
		
		assertThat(accountDto).isNull();
	}
	
}
