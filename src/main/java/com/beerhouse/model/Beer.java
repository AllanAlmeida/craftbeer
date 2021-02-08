package com.beerhouse.model;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "beer")
@Getter @Setter @NoArgsConstructor
public class Beer implements Serializable {

	private static final long serialVersionUID = -8023088477220190632L;
	
	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE)
	@Column(name = "beer_id")
	private Long id;
	
	@NotEmpty @NotNull(message = "Name cannot be null")
	private String name;
	
	@NotEmpty @NotNull(message = "Ingredients cannot be null")
	private String ingredients;
	
	@NotEmpty @NotNull(message = "AlcoholContent cannot be null")
	private String alcoholContent;
	
	@NotNull(message = "Price cannot be null")
	private Double price;
	
	@NotEmpty @NotNull(message = "Category cannot be null")
	private String category;
	
}
