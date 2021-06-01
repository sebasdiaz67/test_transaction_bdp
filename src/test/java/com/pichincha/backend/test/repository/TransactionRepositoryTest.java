package com.pichincha.backend.test.repository;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.test.context.junit4.SpringRunner;

import com.pichincha.backend.test.model.Transaction;

@RunWith(SpringRunner.class)
@DataJpaTest()
public class TransactionRepositoryTest {

	@Autowired
	protected TransactionRepository transactionRepository;
	
	@Test
	public void shouldExistingTransactionByComment() {
		Transaction transaction = transactionRepository.findByComment("TRA-001");
		
		assertEquals(transaction.getComment(), "TRA-001");
	}
	
	@Test
	public void shouldBeNullWhenFindTransactionByNoExistingComment() {
		Transaction transaction = transactionRepository.findByComment("TRA-123");
		
		assertThat(transaction).isNull();
	}
	
	@Test
	public void shouldDeleteWhenTransactionIdExists() {
		Long id = 1L;
		transactionRepository.deleteById(id);
		
		Transaction transaction = transactionRepository.findById(id).orElse(null);
		
		assertThat(transaction).isNull();
	}
	
	@Test
	public void shouldNoDeleteWhenTransactionIdNotExists() {
		Long id = 101L;
		try {
			transactionRepository.deleteById(id);
		} catch (EmptyResultDataAccessException e) {
			assertThat(e).isNotNull();
		}
	}
}
