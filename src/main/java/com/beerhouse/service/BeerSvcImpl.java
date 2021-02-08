package com.beerhouse.service;

import java.util.List;
import java.util.Map;
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
	
	@Override
	public void saveBeer(Beer beer, Long id) throws BeerException {
		
		Optional<Beer> beerOpt = beerRepository.findById(id);
		
		if(!beerOpt.isPresent()) {
			throw new BeerException();
		}
		
		Beer updateBeer = beerOpt.get();
		
		updateBeer.setAlcoholContent(	beer.getAlcoholContent());
		updateBeer.setCategory(			beer.getCategory());
		updateBeer.setIngredients(		beer.getIngredients());
		updateBeer.setName(				beer.getName());
		updateBeer.setPrice(			beer.getPrice());
		
		beerRepository.save(updateBeer);
	}
	
	@Override
	public void saveBeer(Map<String, Object> updates, Long id) throws BeerException {
		
		Optional<Beer> beer = beerRepository.findById(id);
		
		if(!beer.isPresent()) {
			throw new BeerException();
		}
		
		Beer patchBeer = beer.get();
		
        updates.forEach(
            (update, value) -> {
                switch (update){
                    case "name": 			patchBeer.setName((String) value); break;
                    case "alcoholContent": 	patchBeer.setAlcoholContent((String) value); break;
                    case "category": 		patchBeer.setCategory((String) value); break;
                    case "ingredients": 	patchBeer.setIngredients((String) value); break;
                    case "price": 			patchBeer.setPrice((Double) value); break;
                }
            }
        );
		
		beerRepository.save(patchBeer);
	}
	
	@Override
	public void deleteBeer(Long id) throws BeerException {
		
		Optional<Beer> beer = beerRepository.findById(id);
		
		if(!beer.isPresent()) {
			throw new BeerException();
		}
		
		beerRepository.delete(beer.get());
	}
}
