package com.fsoft.F_Cinema.constants;

import lombok.Getter;

@Getter
public enum ScheduleStatusConstant {
	
	WAITING("WAITING", 1),
	RUNNING("RUNNING", 2),
	SUSPENDED("SUSPENDED", 3),
	CANCELED("CANCELED", 4);

	private final String key;
	private final Integer value;

	ScheduleStatusConstant(String key, Integer value) {
		this.key = key;
		this.value = value;
	}

}
