package com.pichincha.backend.test.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.pichincha.backend.test.model.Transaction;

public interface TransactionRepository extends JpaRepository<Transaction, Long>{

	@Query("SELECT t FROM Transaction t WHERE t.account.id = ?1 ORDER BY creationDate DESC")
	List<Transaction> getTransactionsByAccountId(Long accountId);
	
	Transaction findByComment(String comment);
}
