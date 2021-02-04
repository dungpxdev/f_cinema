package com.fsoft.F_Cinema.constants;

import lombok.Getter;

@Getter
public enum GenderConstant {
	MALE("MALE", 1),
	FEMALE("FEMALE", 2),
	OTHERS("OTHERS", 3);

	private final String key;
	private final Integer value;

	GenderConstant(String key, Integer value) {
		this.key = key;
		this.value = value;
	}

}
