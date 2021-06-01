package com.pichincha.backend.test.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class TransactionExtDto {
	
	private String amount;
	private String currency;
	private String description;

}
