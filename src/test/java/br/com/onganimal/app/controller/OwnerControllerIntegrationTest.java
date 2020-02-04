package br.com.onganimal.app.controller;

import static org.hamcrest.CoreMatchers.is;
import static org.mockito.Matchers.any;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.util.Arrays;
import java.util.List;

import org.junit.Before;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.web.PageableHandlerMethodArgumentResolver;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import br.com.onganimal.app.AbstractIntegrationTest;
import br.com.onganimal.app.WebTestUtils;
import br.com.onganimal.app.dto.AnimalDTO;
import br.com.onganimal.app.model.Animal;
import br.com.onganimal.app.repository.AnimalRepository;
import br.com.onganimal.app.repository.OwnerRepository;
import br.com.onganimal.app.service.AnimalService;
import br.com.onganimal.app.service.OwnerService;

@WebMvcTest(OwnerController.class)
public class OwnerControllerIntegrationTest extends AbstractIntegrationTest {
	
	@Autowired
	private OwnerController ownerController;
	
	@MockBean
	private OwnerService ownerService;
	
	@MockBean
	private OwnerRepository ownerRepository;
	
	@MockBean
	private AnimalService animalService;
	
	@MockBean
	private AnimalRepository animalRepository;
	
	
	@Before
    public void setUp() {
		this.mvc = MockMvcBuilders.standaloneSetup(ownerController)
	            .setCustomArgumentResolvers(new PageableHandlerMethodArgumentResolver())
	            .build();
    }
	
	@Test
	public void getAll_should_return_all_animals_by_owener() throws Exception {
		Animal animal = new Animal();
		animal.setId(1L);
	 
		Animal animal1 = new Animal();
		animal1.setId(2L);
		
		Animal animal2 = new Animal();
		animal2.setId(3L);
		
	    List<Animal> allAnimals = Arrays.asList(animal, animal1, animal2);
	    
	    Page<Animal> page = new PageImpl<>(allAnimals);
	    
	    when(animalService.getAll(any(), any())).thenReturn(page);
	 
	    mvc.perform(get("/owners/animals?page=0&size=3")
	      .contentType(MediaType.APPLICATION_JSON))
	      .andExpect(status().isOk())
	      .andExpect(jsonPath("$.totalElements", is(3)));
	}
	
	@Test
	public void get_should_return_one_animal()
	  throws Exception {
	     
		Animal animal = new Animal();
		animal.setId(1L);
	    
	    when(animalService.getById(any())).thenReturn(animal);
	 
	    mvc.perform(get("/owners/animal/1")
	      .contentType(MediaType.APPLICATION_JSON))
	      .andExpect(status().isOk())
	      .andExpect(jsonPath("$.id", is(1)));
	}
	
	@Test
	public void save_should_save_and_return_animal()
	  throws Exception {
	     
		Animal animal = new Animal();
		animal.setId(1L);
	    
	    when(animalService.save(any())).thenReturn(animal);
	 
	    mvc.perform(post("/owners/animal")
	      .contentType(MediaType.APPLICATION_JSON)
	      .content(WebTestUtils.convertObjectToJsonBytes(new AnimalDTO())))
	      .andExpect(status().isOk())
	      .andExpect(jsonPath("$.id", is(1)));
	}
	
	@Test
	public void update_should_update_and_return_animal()
	  throws Exception {
	     
		Animal animal = new Animal();
		animal.setId(1L);
	    
		when(animalService.getById(any())).thenReturn(animal);
	    when(animalService.save(any())).thenReturn(animal);
	 
	    mvc.perform(put("/owners/animal/1")
	      .contentType(MediaType.APPLICATION_JSON)
	      .content(WebTestUtils.convertObjectToJsonBytes(animal)))
	      .andExpect(status().isOk())
	      .andExpect(jsonPath("$.id", is(1)));
	}
	
	@Test
	public void delete_should_delete_animal()
	  throws Exception {
		
	    mvc.perform(delete("/owners/animal/1")
	      .contentType(MediaType.APPLICATION_JSON))
	      .andExpect(status().isOk());
	}
}
