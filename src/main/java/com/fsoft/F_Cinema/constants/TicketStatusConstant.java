package com.fsoft.F_Cinema.constants;

import lombok.Getter;

@Getter
public enum TicketStatusConstant {

	BOOKED("BOOKED", 1), 
	CHECKED("CHECKED", 2), 
	AVAILABLE("AVAILABLE", 3);

	private final String key;
	private final Integer value;

	TicketStatusConstant(String key, Integer value) {
		this.key = key;
		this.value = value;
	}

}
