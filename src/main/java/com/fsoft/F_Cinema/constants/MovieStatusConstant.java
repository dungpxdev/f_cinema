package com.fsoft.F_Cinema.constants;

import lombok.Getter;

@Getter
public enum MovieStatusConstant {

	DEACTIVE("DEACTIVE", 0),
	ACTIVE("ACTIVE", 1),
	WAITING("WAITING", 2),
	SUSPENDED("SUSPENDED", 3);

	private final String key;
	private final Integer value;

	MovieStatusConstant(String key, Integer value) {
		this.key = key;
		this.value = value;
	}
}
