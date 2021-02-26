package com.fsoft.F_Cinema.utils;

import org.springframework.stereotype.Component;

@Component
public class StringUltis {

	/**
	 * find next character in alphabet
	 * 
	 * @param <String> single character of String
	 * @return <String> next single character of input
	 * @throws Exception
	 */
	public String getNextCharacter(String str) throws Exception {
		if (str.split("").length > 1)
			throw new Exception("Input string wrong format");

		char character = str.charAt(0);
		int asciivalue = character;
		if (asciivalue > 122 || asciivalue < 65) {
			if (asciivalue < 97 && asciivalue > 90) {
				throw new Exception("Input string wrong format");
			}
		}

		++asciivalue;

		return String.valueOf((char) asciivalue).toUpperCase();
	}

}
