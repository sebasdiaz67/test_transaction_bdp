package com.pichincha.backend.test.service.util;

import java.time.LocalDateTime;

import com.pichincha.backend.test.dto.NewTransactionDto;
import com.pichincha.backend.test.model.Account;

public class UtilClass {

	public static Account createObjectAccountTest(LocalDateTime creationDate) {
		Account account = new Account();
		account.setNumber("Test Number");
		account.setType("Test type");
		account.setCreationDate(creationDate == null ? LocalDateTime.now() : creationDate);
		return account;
	}
	
	public static NewTransactionDto createTransaction(Long accountId, String comment, String type) {
		NewTransactionDto newTransaction = new NewTransactionDto();
		newTransaction.setComment(comment);
		newTransaction.setType(type);
		newTransaction.setAccountId(accountId);
		return newTransaction;
	}
}
