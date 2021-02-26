package com.fsoft.F_Cinema.constants;

import lombok.Getter;

@Getter
public enum SeatConstant {

	NUMBERSEATOFROW("12", 12);

	private final String key;
	private final Integer value;

	SeatConstant(String key, Integer value) {
		this.key = key;
		this.value = value;
	}

}
