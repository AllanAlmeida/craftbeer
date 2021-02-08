package com.beerhouse.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

import com.beerhouse.jwt.UserCredentials;

/**
* Usado para obter o token do JWT
*/
@RestController
public class LoginController {

	@PostMapping("/tokenizer")
	public ResponseEntity<String> tokenizer(UserCredentials user) {
        return new ResponseEntity<String>(HttpStatus.OK);
    }

}
