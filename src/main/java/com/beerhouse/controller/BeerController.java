package com.beerhouse.controller;

import static com.beerhouse.util.Util.logger;
import static com.beerhouse.util.Util.loggerBeer;
import static com.beerhouse.util.Util.loggerPatch;
import static com.beerhouse.util.Util.validate;
import static com.beerhouse.util.Util.toLong;

import java.util.List;
import java.util.Map;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.PropertySource;
import org.springframework.core.env.Environment;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.beerhouse.enums.Messages;
import com.beerhouse.exception.BeerException;
import com.beerhouse.model.Beer;
import com.beerhouse.service.BeerSvcImpl;

@RestController
@RequestMapping("/beers")
@PropertySource({ "classpath:beer-msg.yml" })
public class BeerController {
	
	@Autowired
	Environment env;
	
	@Autowired
	private BeerSvcImpl beerSvc;
	
	@GetMapping
    public ResponseEntity<List<Beer>> listAllBeers() {
        
		List<Beer> beers = beerSvc.findAllBeer();
        return new ResponseEntity<List<Beer>>(beers, HttpStatus.OK);
    }
	
	@PostMapping
    public ResponseEntity<Beer> createNewBeer(@Valid @RequestBody Beer body){
       
		try {
			beerSvc.saveBeer(body);
			
		} catch (BeerException beerx) {
			body = loggerBeer(Messages.SAVE, beerx, env);
		}
		
		return new ResponseEntity<Beer>(body, validate(HttpStatus.CREATED, body));
    }
	
	@GetMapping("/{id}")
    public ResponseEntity<Beer> findBeer(@PathVariable("id") String id) {
        
		Beer beer = this.beerSvc.findById(toLong(id));
		return new ResponseEntity<Beer>(beer,validate(HttpStatus.OK, beer));
    }
	
	@PutMapping("/{id}")
	public ResponseEntity<?> updateAllDataBeer(@Valid @RequestBody Beer body, @PathVariable("id") String id) {
		
		try {
			beerSvc.saveBeer(body, toLong(id));
			
		} catch (BeerException beerx) {
			body = loggerBeer(Messages.PUT, beerx, env);
		}
		return new ResponseEntity<Beer>(validate(HttpStatus.OK, body));
	}
	
	@PatchMapping("/{id}")
	public ResponseEntity<?> updateSomeDataBeer(@RequestBody Map<String, Object> body, @PathVariable("id") String id) {
		
		try {
			beerSvc.saveBeer(body, toLong(id));
			
		} catch (BeerException beerx) {
			body = loggerPatch(Messages.PATCH, beerx, env);
		}
		return new ResponseEntity<Beer>(validate(HttpStatus.OK, body));
	}
	
	@DeleteMapping("/{id}")
	public ResponseEntity<?> deleteBeer(@PathVariable("id") String id) {
		
		try {
			beerSvc.deleteBeer(toLong(id));
			
		} catch (BeerException beerx) {
			id = (String) logger(Messages.DELETE, beerx, env);
		}
		return new ResponseEntity<Beer>(validate(HttpStatus.NO_CONTENT, id));
	}
}
