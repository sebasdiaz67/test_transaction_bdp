package com.pichincha.backend.test.service;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.hasSize;
import static org.hamcrest.Matchers.notNullValue;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;

import java.time.LocalDateTime;
import java.util.List;

import org.hamcrest.Matchers;
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
public class AccountServiceTest {

	@Autowired
	AccountRepository accountRepository;

	@Autowired
	AccountService accountService;
	

	@Test
	public void shouldReturnCreatedAccount() {
		LocalDateTime creationDate = LocalDateTime.of(2018, 5, 20, 20, 51, 16);
		Account account = UtilClass.createObjectAccountTest(creationDate);
		accountRepository.save(account);

		AccountDto accountDto = accountService.getAccount(account.getId());
		

		assertNotNull("Account shouldn't be null", accountDto);
		assertThat(accountDto.getType(), equalTo("Test Type"));
		assertThat(accountDto.getNumber(), equalTo("Test Number"));
		assertThat(accountDto.getCreationDate(), equalTo(creationDate));
	}

	@Test
	public void shouldReturnNullForNotExistingAccount() {

		AccountDto accountDto = accountService.getAccount(0L);
		assertNull(accountDto);
	}


	@Test
	public void shouldAddTransaction() {
		Account account = createTestAccount();

		NewTransactionDto newTransactionDto = UtilClass.createTransaction(account.getId(), "Comment", "Type");
		Long transactionId = accountService.addTransaction(newTransactionDto);

		assertThat("Transaction id shouldn't be null", transactionId, notNullValue());
	}

	private Account createTestAccount() {
		LocalDateTime creationDate = LocalDateTime.of(2018, 5, 20, 20, 51, 16);
		Account account = UtilClass.createObjectAccountTest(creationDate);
		accountRepository.save(account);
		return account;
	}

	@Test
	public void shouldReturnAddedTransaction() {
		Account account = createTestAccount();

		NewTransactionDto newTransactionDto = UtilClass.createTransaction(account.getId(), "Comment", "Type");

		accountService.addTransaction(newTransactionDto);

		List<TransactionDto> transactions = accountService.getTransactionsForAccount(account.getId());

		assertThat("There should be one transaction", transactions, hasSize(1));
		assertThat(transactions.get(0).getType(), Matchers.equalTo("Type"));
		assertThat(transactions.get(0).getComment(), Matchers.equalTo("Comment"));
	}
	
	
}
