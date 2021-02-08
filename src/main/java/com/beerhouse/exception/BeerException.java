package com.beerhouse.exception;

public class BeerException  extends Exception {
		
	private static final long serialVersionUID = 1L;
	
	public BeerException() {
		super();
	}
	
	public BeerException(String message) {
		super(message);
	}

	public BeerException(String message, Exception e) {
		super(message, e);
	}
}
