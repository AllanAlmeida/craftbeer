package com.beerhouse.util;

import java.util.Map;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.core.env.Environment;
import org.springframework.http.HttpStatus;

import com.beerhouse.enums.Messages;
import com.beerhouse.exception.BeerException;
import com.beerhouse.model.Beer;

public class Util {
	
	private final static Logger LOG = LogManager.getLogger();
	
	public static Object logger(Messages messagesEnum, BeerException ex, Environment env) {
		makeLog(messagesEnum, ex, env);
		return null;
	}
	
	public static Beer loggerBeer(Messages messagesEnum, BeerException ex, Environment env) {
		makeLog(messagesEnum, ex, env);
		return null;
	}
	
	public static Map<String, Object> loggerPatch(Messages messagesEnum, BeerException ex, Environment env) {
		makeLog(messagesEnum, ex, env);
		return null;
	}
	
	public static HttpStatus validate(HttpStatus httpStatus, Object beerData) {
		return beerData!=null ? httpStatus : HttpStatus.NOT_FOUND;
	}
	
	public static Long toLong(String string) {
		return Long.valueOf(string);
	}
	
	private static void makeLog(Messages messagesEnum, BeerException ex, Environment env) {
		LOG.error(env.getProperty(messagesEnum.get()), ex);
	}
}
