package com.pichincha.backend.test.rest;

import static org.hamcrest.Matchers.hasSize;
import static org.hamcrest.Matchers.is;
import static org.mockito.Mockito.when;
import static org.springframework.http.MediaType.APPLICATION_JSON_UTF8;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Logger;

import org.junit.Test;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.RestTemplate;

import com.pichincha.backend.test.dto.NewTransactionDto;
import com.pichincha.backend.test.dto.TransactionDto;
import com.pichincha.backend.test.service.util.UtilClass;

public class TransactionControllerTest extends AbstractControllerTest {

	private static final Logger log = Logger.getLogger(TransactionControllerTest.class.getName());

	@Test
	public void shouldReturnFoundTransactions() throws Exception {

		// given
		List<TransactionDto> transactions = new ArrayList<>();
		LocalDateTime creationDate = LocalDateTime.of(2018, 5, 20, 20, 51, 16);
		transactions.add(new TransactionDto(2L, "July payment", "Credit card payment", creationDate));

		// when
		when(accountService.getTransactionsForAccount(1L)).thenReturn(transactions);

		// then
		mockMvc.perform(get("/accounts/1/transactions").accept(MediaType.APPLICATION_JSON))
			.andExpect(status().isOk())
			.andExpect(jsonPath("$", hasSize(1)))
			.andExpect(jsonPath("$[0].id", is(2)))
			.andExpect(jsonPath("$[0].comment", is("July payment")))
			.andExpect(jsonPath("$[0].type", is("Credit card payment")))
			.andExpect(jsonPath("$[0].creationDate", is(creationDate.toString())));

	}

	@Test
	public void shouldAddTransaction() throws Exception {

		// given
		String transactionBody = "{\"comment\":\"Test comment\", \"type\":\"Credit card payment\"}";
		NewTransactionDto newTransaction = UtilClass.createTransaction(null, "Test comment", "Credit card payment");

		// when
		when(accountService.addTransaction(newTransaction)).thenReturn(1L);

		// then
		mockMvc.perform(post("/accounts/1/transactions")
			.content(transactionBody)
			.contentType(APPLICATION_JSON_UTF8)
			.accept(MediaType.APPLICATION_JSON))
			.andExpect(status().isCreated());
	}

	@Test    
	public void test_whenList() {
		RestTemplate rt = new RestTemplate();
		String apiExt  = "https://mocki.io/v1/370615e7-b5bb-4947-baa9-5b0df2349850";
		ResponseEntity<String> response = rt.getForEntity(apiExt, String.class);
		log.info(response.getBody());
	}
}
