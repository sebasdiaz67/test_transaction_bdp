package com.pichincha.backend.test.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@Setter
@AllArgsConstructor
public class TransactionDto {

	private final Long id;

	private final String comment;

	private final String type;

	private final LocalDateTime creationDate;
	
}
