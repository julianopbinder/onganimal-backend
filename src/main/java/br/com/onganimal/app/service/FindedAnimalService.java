package br.com.onganimal.app.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.onganimal.app.dto.FindedAnimalDTO;
import br.com.onganimal.app.enumerated.Status;
import br.com.onganimal.app.model.Animal;
import br.com.onganimal.app.model.FindedAnimal;
import br.com.onganimal.app.model.Owner;
import br.com.onganimal.app.repository.FindedAnimalRepository;

@Service
public class FindedAnimalService {

	@Autowired
	private FindedAnimalRepository findedAnimalRepository;
	
	@Autowired
	private AnimalService animalService;
	
	public FindedAnimal markAnimalsAsFindend(FindedAnimalDTO findedAnimalDTO, Long animalID) {
		FindedAnimal findedAnimal = new FindedAnimal();
		findedAnimal.setName(findedAnimalDTO.getName());
		findedAnimal.setPhone(findedAnimalDTO.getPhone());
		
		Animal animal = animalService.getById(animalID);
		
		if (animal == null) {
			
		}
		
		animal.setStatus(Status.REPORTED);
		animalService.save(animal);
		
		findedAnimal.setAnimal(animal);
		
		return findedAnimalRepository.save(findedAnimal);
		
	}
	
	public List<FindedAnimal> getAll(Long animalId, Owner owner) {
		return findedAnimalRepository.findByAnimalIdAndAnimalOwner(animalId, owner);
	}
	
}
