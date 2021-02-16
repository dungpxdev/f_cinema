package com.fsoft.F_Cinema.constants;

import lombok.Getter;

@Getter
public enum MovieGenreConstant {
	HORROR("HORROR", 1),
	SCI_FI("SCI_FI", 2),
	SPORTS("SPORTS", 3),
	WAR("WAR", 4),
	WESTERNS("WESTERNS", 5),
	COMEDY("COMEDY", 6),
	CRIME("CRIME", 7),
	ACTION("ACTION", 8),
	MELODRAMAS("MELODRAMAS", 9),
	MUSICALS("MUSICALS", 10),
	DRAMA("DRAMA", 11),
	FANTASY("FANTASY", 12),
	MYSTERY("MYSTERY", 13),
	THRILLER("THRILLER", 14),
	WESTERN("WESTERN", 15),
	COSTUME_DRAMAS("COSTUME_DRAMAS", 16),
	OTHERS("OTHERS", 17);

	private final String key;
	private final Integer value;

	MovieGenreConstant(String key, Integer value) {
		this.key = key;
		this.value = value;
	}

}
