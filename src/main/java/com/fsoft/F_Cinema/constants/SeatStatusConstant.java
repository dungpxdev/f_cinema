package com.fsoft.F_Cinema.constants;

import lombok.Getter;

@Getter
public enum SeatStatusConstant {

	SELECTED("SELECTED", 0), 
	AVAILABLE("AVAILABLE", 1), 
	OCCUPIED("OCCUPIED", 2);

	private final String key;
	private final Integer value;

	SeatStatusConstant(String key, Integer value) {
		this.key = key;
		this.value = value;
	}

}
