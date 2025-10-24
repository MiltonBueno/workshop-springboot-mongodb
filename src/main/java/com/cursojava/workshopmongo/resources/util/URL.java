package com.cursojava.workshopmongo.resources.util;

import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.time.Instant;
import java.time.LocalDate;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

public class URL {

	public static String decodeParam(String text) {
		try {
			return URLDecoder.decode(text, "UTF-8");
		} catch (UnsupportedEncodingException e) {
			return "";
		}
	}
	
	public static Instant convertDate(String textDate, Instant defaultValue) {
	    try {
	        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
	        LocalDate localDate = LocalDate.parse(textDate, formatter);

	        // início do dia seguinte → inclui todo o dia informado
	        return localDate.plusDays(1)
	                        .atStartOfDay(ZoneId.of("GMT"))
	                        .toInstant();
	    } catch (DateTimeParseException e) {
	        return defaultValue;
	    }
	}
}