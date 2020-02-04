package br.com.onganimal.app.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.onganimal.app.annotation.LoggedUser;
import br.com.onganimal.app.dto.AnimalDTO;
import br.com.onganimal.app.enumerated.Status;
import br.com.onganimal.app.model.Animal;
import br.com.onganimal.app.model.Owner;
import br.com.onganimal.app.service.AnimalService;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;

@RestController
@RequestMapping("/owners")
@CrossOrigin
public class OwnerController {
	
	@Autowired 
	private AnimalService animalService;
	
	@ApiOperation(value = "Endpoint responsável cadastrar um novo animal para um dono.")
	@ApiResponses(value = { @ApiResponse(code = 200, message = "Animal cadastrado com sucesso.") })
	@PostMapping("/animal")
	public Animal saveAnimal(@RequestBody AnimalDTO animalDTO, @LoggedUser Owner owner) {
		
		Animal animal = new Animal();
		animal.setAge(animalDTO.getAge());
		animal.setCity(animalDTO.getCity());
		animal.setExtraInfo(animalDTO.getExtraInfo());
		animal.setImage(animalDTO.getImage());
		animal.setName(animalDTO.getName());
		animal.setState(animalDTO.getState());
		animal.setOwner(owner);
		animal.setStatus(Status.LOST);
		
		return animalService.save(animal);
	}
	
	@ApiOperation(value = "Endpoint responsável por atualizar um novo animal.")
	@ApiResponses(value = { @ApiResponse(code = 200, message = "Animal atualizado com sucesso.") })
	@PutMapping("/animal/{id}")
	public Animal updateAnimal(@PathVariable("id") Long animalId, @RequestBody AnimalDTO animalDTO, @LoggedUser Owner owner) {
		
		Animal animal = animalService.getById(animalId);
		animal.setAge(animalDTO.getAge());
		animal.setCity(animalDTO.getCity());
		animal.setExtraInfo(animalDTO.getExtraInfo());
		animal.setImage(animalDTO.getImage());
		animal.setName(animalDTO.getName());
		animal.setState(animalDTO.getState());
		animal.setOwner(owner);
		animal.setStatus(animalDTO.getStatus());
		
		return animalService.save(animal);
	}
	
	@ApiOperation(value = "Endpoint responsável por deletar um animal.")
	@ApiResponses(value = { @ApiResponse(code = 200, message = "Animal deletado com sucesso.") })
	@DeleteMapping("/animal/{id}")
	public void deleteAnimal(@PathVariable("id") Long animalId, @LoggedUser Owner owner) {
		animalService.delete(animalId);
	}
	
	@ApiOperation(value = "Endpoint responsável por recuperar um animal.")
	@ApiResponses(value = { @ApiResponse(code = 200, message = "Animal recuperado.") })
	@GetMapping("/animal/{id}")
	public Animal getAnimal(@PathVariable("id") Long animalId, @LoggedUser Owner owner) {
		return animalService.getById(animalId);
	}
	
	@ApiOperation(value = "Endpoint responsável por recuperar todos os animais de um dono.")
	@ApiResponses(value = { @ApiResponse(code = 200, message = "Animais recuperados.") })
	@GetMapping("/animals")
	public Page<Animal> getAll(Pageable pageable, @LoggedUser Owner owner) {
		return animalService.getAll(owner, pageable);
	}
	
}
