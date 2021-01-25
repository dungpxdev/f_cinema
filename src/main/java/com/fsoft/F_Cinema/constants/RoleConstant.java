package com.fsoft.F_Cinema.constants;

import lombok.Getter;

/**
 * ENUM CONSTANTS
 * 
 * Definition of role constants
 * each value is key-value pair <String, Integer>
 */
@Getter
public enum RoleConstant {
	
	ADMIN("ADMIN", 1),
	SCHEDULE_MANAGER("SCHEDULE_MANAGER", 2),
	TICKET_MANAGER("TICKET_MANAGER", 3),
	MOVIE_CATEGORY_MANAGER("MOVIE_CATEGORY_MANAGER", 4),
	MOVIE_MANAGER("MOVIE_MANAGER", 5),
	ROOM_MANAGER("ROOM_MANAGER", 6),
	GUEST("GUEST", 7);
	
	private final String key;
	private final Integer value;

	RoleConstant(String key, Integer value) {
		this.key = key;
		this.value = value;
	}
	
}
