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
import org.springframework.web.client.RestTemplate;

import com.pichincha.backend.test.dto.NewTransactionDto;
import com.pichincha.backend.test.service.AccountService;

@RestController
@RequestMapping("/accounts")
public class TransactionController{

	private final AccountService aService;
	
	private final Logger log = LoggerFactory.getLogger(TransactionController.class);	

	public TransactionController(AccountService accountService) {
		this.aService = accountService;
	}

	@GetMapping(value = "/transactions")
	public ResponseEntity<?> getTransactionsForAccount(@RequestBody NewTransactionDto newTransactionDto) {
		log.info("account id => " + newTransactionDto.getAccountId());
		return ResponseEntity.ok(aService.getTransactionsForAccount(newTransactionDto.getAccountId()));
	}
	
	@PostMapping(value = "/{id}/transactions")
	public ResponseEntity<?> addTransaction(@PathVariable Long id, @RequestBody NewTransactionDto newTransactionDto) {
		log.info("account id => " + id);
		newTransactionDto.setAccountId(id);
		try {
			return ResponseEntity.status(HttpStatus.CREATED).body(aService.addTransaction(newTransactionDto));
		} catch (Exception e) {
			log.error("error =>" + e.getMessage());
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body(e.getMessage());
		}
	}
	
	@GetMapping(value = "/transactions/ext")
	public ResponseEntity<?> getTransactionsExt() {		
		RestTemplate restTemplate = new RestTemplate();
		String apiExt  = "https://mocki.io/v1/370615e7-b5bb-4947-baa9-5b0df2349850";
		ResponseEntity<String> response  = restTemplate.getForEntity(apiExt, String.class);
		return ResponseEntity.status(HttpStatus.OK).body(response.getBody());
	}

}
