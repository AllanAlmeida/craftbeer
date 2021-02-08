package com.beerhouse.enums;

public enum Messages {
	
	SAVE("msg.beer.erro.save"),
	PUT("msg.beer.erro.put"),
	PATCH("msg.beer.erro.patch"),
	DELETE("msg.beer.erro.delete");
	
	public String code;
    
	Messages(String code) {
		this.code = code;
    }
	
	public String get(){
		return code;
	}
}
