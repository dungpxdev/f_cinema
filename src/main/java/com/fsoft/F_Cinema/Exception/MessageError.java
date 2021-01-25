package com.fsoft.F_Cinema.Exception;

import java.util.Date;
import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Data;

@AllArgsConstructor
@Data
public class MessageError {

	private Date timestamp;
	private String message;
	private List<String> errors;

}
