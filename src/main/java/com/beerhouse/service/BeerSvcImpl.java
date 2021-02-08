package com.beerhouse.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.beerhouse.exception.BeerException;
import com.beerhouse.model.Beer;
import com.beerhouse.repository.BeerRepository;

@Service
public class BeerSvcImpl implements BeerSvc {

	@Autowired
	private BeerRepository beerRepository;
	
	@Override
	public Beer findById(Long id) {
		
		Optional<Beer> beerOptional = beerRepository.findById(id);
		
		if (beerOptional.isPresent()) {
			return beerOptional.get();
		} 
		return null;
	}

	@Override
	public List<Beer> findAllBeer() {
		return (List<Beer>) beerRepository.findAll();
	}

	@Override
	public void saveBeer(Beer beer) throws BeerException {
		beerRepository.save(beer);
	}
}
