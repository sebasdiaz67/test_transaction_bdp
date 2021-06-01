package com.pichincha.backend.test.rest;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.pichincha.backend.test.dto.AccountDto;
import com.pichincha.backend.test.service.AccountService;

@RestController
@RequestMapping("/accounts")
public class AccountController {

	private final AccountService aService;
	
	public AccountController(AccountService accountService) {
		this.aService = accountService;
	}

	@GetMapping(value = "/{id}")
	@ResponseStatus(HttpStatus.OK)
	public AccountDto getAccount(@PathVariable Long id) {
		return aService.getAccount(id);
	}

}
