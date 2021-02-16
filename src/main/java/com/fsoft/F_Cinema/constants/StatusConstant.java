package com.fsoft.F_Cinema.constants;

import lombok.Getter;

@Getter
public enum StatusConstant {

	DEACTIVE("DEACTIVE", 0),
	ACTIVE("ACTIVE", 1),
	SUSPENDED("SUSPENDED", 2);

	private final String key;
	private final Integer value;

	StatusConstant(String key, Integer value) {
		this.key = key;
		this.value = value;
	}
}
