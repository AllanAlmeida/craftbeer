package com.beerhouse;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.patch;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.setup.MockMvcBuilders.standaloneSetup;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.MockMvc;

import com.beerhouse.builder.BeerBuilder;
import com.beerhouse.controller.BeerController;
import com.beerhouse.model.Beer;
import com.fasterxml.jackson.databind.ObjectMapper;

@RunWith(SpringRunner.class)	
@SpringBootTest(classes = Application.class)
@WebAppConfiguration
@AutoConfigureMockMvc
@ContextConfiguration
public class BeerControllerTest {
	
	@Autowired
	private MockMvc mockMvc;
	
	@Autowired
	private BeerController beerController;
	
	@Before
	public void setUp() throws Exception {
		mockMvc = standaloneSetup(beerController).build();
		
		this.mockMvc.perform(post("/beers")
		  		  .content(asJsonString(haveABeer(null)))
		  		  .contentType(MediaType.APPLICATION_JSON)
				      .accept(MediaType.APPLICATION_JSON));
	}
  
	@Test
 	public void new_beer_OK() throws Exception {
  	
		this.mockMvc.perform(post("/beers")
		  		  .content(asJsonString(haveABeer(null)))
		  		  .contentType(MediaType.APPLICATION_JSON)
				      .accept(MediaType.APPLICATION_JSON))
		
				  .andExpect(status().is2xxSuccessful());
 	}
	
	@Test
 	public void find_beer_OK() throws Exception {
  	
		this.mockMvc.perform(get("/beers/1")
	  		  .content(asJsonString(haveABeer(null)))
	  		  .contentType(MediaType.APPLICATION_JSON)
			      .accept(MediaType.APPLICATION_JSON))
		
			  .andExpect(status().is2xxSuccessful());
 	}
	
	@Test
 	public void find_all_beers_OK() throws Exception {
  	
		this.mockMvc.perform(get("/beers")
	  		  .contentType(MediaType.APPLICATION_JSON)
			      .accept(MediaType.APPLICATION_JSON))
		
			  .andExpect(status().is2xxSuccessful());
 	}
	
	@Test
 	public void put_beers_OK() throws Exception {
  	
		this.mockMvc.perform(put("/beers/1")
			  .content(asJsonString(haveABeer("CraftBarley")))	
	  		  .contentType(MediaType.APPLICATION_JSON)
			      .accept(MediaType.APPLICATION_JSON))
		
			  .andExpect(status().is2xxSuccessful());
 	}
	
	@Test
 	public void patch_beers_OK() throws Exception {
  	
		this.mockMvc.perform(patch("/beers/1")
			  .content(asJsonString(haveABeer(null)))	
	  		  .contentType(MediaType.APPLICATION_JSON)
			      .accept(MediaType.APPLICATION_JSON))
		
			  .andExpect(status().is2xxSuccessful());
 	}
	
	@Test
 	public void delete_beers_OK() throws Exception {
  	
		this.mockMvc.perform(patch("/beers/1")
			  .content(asJsonString(haveABeer(null)))	
			  .contentType(MediaType.APPLICATION_JSON)
			      .accept(MediaType.APPLICATION_JSON))
		
			  .andExpect(status().is2xxSuccessful());
 	}
	
 	private Beer haveABeer(String updateName) {
 		
 		boolean isNameBeer = updateName!=null && !updateName.isEmpty();
 		
 		return new BeerBuilder()
				.name(!isNameBeer ? "BetterBeer" : updateName)
				.alcoholContent("Max")
				.category("Yellow Feel")
				.ingredients("Main barley")
				.price(199.99D)
			.build();
 	}

 	private static String asJsonString(final Object obj) throws Exception {
        return new ObjectMapper().writeValueAsString(obj);
    }
}
