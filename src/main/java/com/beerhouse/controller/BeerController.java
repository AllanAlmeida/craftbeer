package com.beerhouse.controller;

import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.PropertySource;
import org.springframework.core.env.Environment;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.beerhouse.exception.BeerException;
import com.beerhouse.model.Beer;
import com.beerhouse.service.BeerSvcImpl;

@RestController
@PropertySource({ "classpath:beer-msg.yml" })
public class BeerController {
	
	private final static Logger LOG = LogManager.getLogger();
	
	@Autowired
    Environment env;
	
	@Autowired
	private BeerSvcImpl beerSvc;
	
	@GetMapping(value = "/beers")
    public ResponseEntity<List<Beer>> listAllBeers() {
        
		List<Beer> beers = beerSvc.findAllBeer();
        return new ResponseEntity<List<Beer>>(beers, HttpStatus.OK);
    }
	
	@PostMapping(value = "/beers")
    public ResponseEntity<Beer> beerByName(@RequestBody Beer beer){
       
		try {
			beerSvc.saveBeer(beer);
		} catch (BeerException e) {
			beer = null;
			LOG.error(env.getProperty("msg.beer.erro.save"), e);
		}
		return new ResponseEntity<Beer>(beer, validate(beer));
    }
	
	private static HttpStatus validate(Beer beer) {
		return beer!=null ? HttpStatus.CREATED : HttpStatus.NOT_FOUND;
	}
}
