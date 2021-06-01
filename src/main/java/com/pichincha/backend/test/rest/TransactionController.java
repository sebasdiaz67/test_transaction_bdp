package com.pichincha.backend.test.rest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.pichincha.backend.test.dto.NewTransactionDto;
import com.pichincha.backend.test.service.AccountService;

@RestController
@RequestMapping("/accounts")
public class TransactionController {

	private final AccountService aService;
	
	private final Logger log = LoggerFactory.getLogger(TransactionController.class);

	public TransactionController(AccountService accountService) {
		this.aService = accountService;
	}

	@GetMapping(value = "/{id}/transactions")
	public ResponseEntity<?> getTransactionsForAccount(@PathVariable Long id) {
		log.info("account id => " + id);
		return ResponseEntity.ok(aService.getTransactionsForAccount(id));
	}
	
	@PostMapping(value = "/{id}/transactions")
	public ResponseEntity<?> addTransaction(@PathVariable Long id, @RequestBody NewTransactionDto newTransactionDto) {
		log.info("account id => " + id);
		newTransactionDto.setAccountId(id);
		return ResponseEntity.status(HttpStatus.CREATED).body(aService.addTransaction(newTransactionDto));
	}
}
