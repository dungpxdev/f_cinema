package com.fsoft.F_Cinema.constants;

import lombok.Getter;

@Getter
public enum ApiResponseConstant {

	DATA("data", 1),
	RESPONSE("response", 2);

	private final String key;
	private final Integer value;

	ApiResponseConstant(String key, Integer value) {
		this.key = key;
		this.value = value;
	}

}
