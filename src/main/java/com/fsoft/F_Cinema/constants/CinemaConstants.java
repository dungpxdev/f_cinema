package com.fsoft.F_Cinema.constants;

import lombok.Getter;

@Getter
public enum CinemaConstants {
	
	ACTIVE("ACTIVE", 1),
	DEACTIVE("DEACTIVE", 2);

	private final String key;
	private final Integer value;

	CinemaConstants(String key, Integer value) {
		this.key = key;
		this.value = value;
	}

}
