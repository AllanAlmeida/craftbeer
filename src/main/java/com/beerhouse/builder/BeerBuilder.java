package com.beerhouse.builder;

import com.beerhouse.model.Beer;

public class BeerBuilder {
	
	Beer beer;
	
	BeerBuilder(){
		this.beer = new Beer();
	}
	
	public BeerBuilder name(String name) {
		beer.setName(name);
		return this;
	}
	
	public BeerBuilder ingredients(String ingredients) {
		beer.setIngredients(ingredients);
		return this;
	}
	
	public BeerBuilder alcoholContent(String alcoholContent) {
		beer.setAlcoholContent(alcoholContent);
		return this;
	}
	
	public BeerBuilder price(Double price) {
		beer.setPrice(price);
		return this;
	}
	
	public BeerBuilder category(String category) {
		beer.setCategory(category);
		return this;
	}
	
	public Beer build() {
		return beer;
	}
}
