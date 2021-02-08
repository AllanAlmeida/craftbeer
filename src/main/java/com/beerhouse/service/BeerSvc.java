package com.beerhouse.service;

import java.util.Map;

import com.beerhouse.exception.BeerException;
import com.beerhouse.model.Beer;

public interface BeerSvc {
	
	Beer findById(Long id);
	
	Iterable<Beer> findAllBeer();
	
	void saveBeer(Beer beer) throws BeerException;
	
	void saveBeer(Beer beer, Long id) throws BeerException;
	
	void saveBeer(Map<String, Object> updates, Long id) throws BeerException;
	
	void deleteBeer(Long id) throws BeerException;

}
