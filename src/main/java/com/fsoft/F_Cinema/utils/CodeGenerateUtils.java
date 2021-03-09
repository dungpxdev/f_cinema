package com.fsoft.F_Cinema.utils;

import java.util.UUID;

import org.springframework.stereotype.Component;

@Component
public class CodeGenerateUtils {

	public String scheduleCodeGenerator(String cinemaCode, String roomCode, String movieCode) {
		return new StringBuilder(cinemaCode)
				.append(roomCode)
				.append(movieCode)
				.append(this.uniqueCodeGenerator()
						.toUpperCase())
				.toString();
	}

	public String uniqueCodeGenerator() {
		return UUID.randomUUID()
				.toString()
				.split("-")[0]
				.toUpperCase();
	}

}
