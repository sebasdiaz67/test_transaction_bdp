package com.pichincha.backend.test.service;


import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import com.pichincha.backend.test.dto.AccountDto;
import com.pichincha.backend.test.dto.NewTransactionDto;
import com.pichincha.backend.test.dto.TransactionDto;
import com.pichincha.backend.test.model.Account;
import com.pichincha.backend.test.model.Transaction;
import com.pichincha.backend.test.repository.AccountRepository;
import com.pichincha.backend.test.repository.TransactionRepository;

@Service
public class AccountService {

	private final AccountRepository accountRepository;
	private final TransactionRepository transactionRepository;
	
	private final Logger log = LoggerFactory.getLogger(AccountService.class);

	public AccountService(AccountRepository accountRepository, TransactionRepository transactionRepository) {
		this.accountRepository = accountRepository;
		this.transactionRepository = transactionRepository;
	}

	public AccountDto getAccount(Long id) {
		return accountRepository.findById(id)
			.map(account -> new AccountDto(account.getNumber(), account.getType(), account.getCreationDate()))
			.orElse(null);
	}

	/**
	 * Returns a list of all transactions for a account with passed id.
	 *
	 * @param accountId id of the account
	 * @return list of transactions sorted by creation date descending - most recent first
	 */
	public List<TransactionDto> getTransactionsForAccount(Long accountId) {
		return transactionRepository.getTransactionsByAccountId(accountId).stream()
				.map(transaction -> new TransactionDto(transaction.getId(), transaction.getComment(),
						transaction.getType(), transaction.getCreationDate()))
				.collect(Collectors.toList());
	}

	/**
	 * Creates a new transaction
	 *
	 * @param newTransactionDto data of new transaction
	 * @return id of the created transaction
	 * @throws IllegalArgumentException if there is no account for passed newTransactionDto.accountId
	 */
	public Long addTransaction(NewTransactionDto newTransactionDto) {
		if (null == newTransactionDto.getAccountId()) {
			log.error("account id cant be null");
			throw new IllegalArgumentException("Account ID shouldn't be null");
		}
		Account account = accountRepository.findById(newTransactionDto.getAccountId()).orElse(null);
		if (null == account) {
			log.error("account cant be null");
			throw new IllegalArgumentException("Account not found");
		}
		Transaction transaction = new Transaction();
		transaction.setAccount(account);
		transaction.setComment(newTransactionDto.getComment());
		transaction.setType(newTransactionDto.getType());
		transaction.setCreationDate(LocalDateTime.now());
		transactionRepository.save(transaction);
		return transaction.getId();
	}

}
