package com.pichincha.backend.test.rest;

import com.pichincha.backend.test.dto.AccountDto;
import com.pichincha.backend.test.dto.NewTransactionDto;
import com.pichincha.backend.test.service.AccountService;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/accounts")
public class AccountController {

	private final AccountService aService;
	
	private final Logger log = LoggerFactory.getLogger(AccountController.class);

	public AccountController(AccountService accountService) {
		this.aService = accountService;
	}

	@GetMapping(value = "/{id}")
	@ResponseStatus(HttpStatus.OK)
	public AccountDto getAccount(@PathVariable Long id) {
		return aService.getAccount(id);
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
