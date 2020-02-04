package br.com.onganimal.app.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.onganimal.app.annotation.LoggedUser;
import br.com.onganimal.app.dto.FindedAnimalDTO;
import br.com.onganimal.app.model.FindedAnimal;
import br.com.onganimal.app.model.Owner;
import br.com.onganimal.app.service.FindedAnimalService;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;

@RestController
@RequestMapping("/api/finded-animals")
public class FindedAnimalController {
	
	@Autowired
	private FindedAnimalService findedAnimalService;
	
	@ApiOperation(value = "Endpoint responsável por marcar um animal como encontrado.")
	@ApiResponses(value = { @ApiResponse(code = 200, message = "Marca o aninal como encontrado.") })
	@PutMapping("/animal/{id}")
	public FindedAnimalDTO markFinded(@RequestBody FindedAnimalDTO findedAnimalDTO, @PathVariable("id") Long animalID) {
		findedAnimalService.markAnimalsAsFindend(findedAnimalDTO, animalID);
		
		return findedAnimalDTO;
	}
	
	@ApiOperation(value = "Endpoint responsável por marcar um animal como encontrado.")
	@ApiResponses(value = { @ApiResponse(code = 200, message = "Marca o aninal como encontrado.") })
	@GetMapping("/animal/{id}")
	public List<FindedAnimal> getAll(@PathVariable("id") Long id, @LoggedUser Owner owner) {
		return findedAnimalService.getAll(id, owner);
	}
	
}
