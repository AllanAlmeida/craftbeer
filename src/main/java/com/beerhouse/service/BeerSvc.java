package com.beerhouse.service;

import com.beerhouse.exception.BeerException;
import com.beerhouse.model.Beer;

public interface BeerSvc {
	
	Beer findById(Long id);
	
	Iterable<Beer> findAllBeer();
	
	void saveBeer(Beer beer) throws BeerException;

}
