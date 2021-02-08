package com.beerhouse.model;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

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
	private String name;
	private String ingredients;
	private String alcoholContent;
	private Float price;
	private String category;
	
}
