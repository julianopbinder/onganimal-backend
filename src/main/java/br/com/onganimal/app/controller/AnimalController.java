package br.com.onganimal.app.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.onganimal.app.model.Animal;
import br.com.onganimal.app.service.AnimalService;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;

@RestController
@RequestMapping("api/animals")
public class AnimalController {

	@Autowired
	private AnimalService animalService;
	
	@ApiOperation(value = "Endpoint responsável por retornar uma lista contendo todos os animais cadastrados que ainda não foram encontrados.")
	@ApiResponses(value = { @ApiResponse(code = 200, message = "Retorna lista contendo todos os animais cadastrados que ainda não foram encontrados.") })
	@GetMapping
	public Page<Animal> getAll(Pageable pageable) {
		return animalService.getAll(pageable);
	}
	
	@ApiOperation(value = "Endpoint responsável por retornar um animal pelo seu ID.")
	@ApiResponses(value = { @ApiResponse(code = 200, message = "Retorna um animal pelo seu ID.") })
	@GetMapping("/{id}")
	public Animal get(@PathVariable("id") Long id) {
		return animalService.getById(id);
	}
	
}
