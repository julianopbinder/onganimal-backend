package br.com.onganimal.app.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import br.com.onganimal.app.enumerated.Status;
import br.com.onganimal.app.model.Animal;
import br.com.onganimal.app.model.Owner;
import br.com.onganimal.app.repository.AnimalRepository;

@Service
public class AnimalService {

	@Autowired
	private AnimalRepository animalRepository;
	
	public Page<Animal> getAll(Pageable pageable) {
		return animalRepository.findByStatusNot(Status.FINDED, pageable);
	}
	
	public Page<Animal> getAll(Owner owner, Pageable pageable) {
		return animalRepository.findByOwner(owner, pageable);
	}
	
	public Animal getById(Long id) {
		return animalRepository.findOne(id);
	}
	
	public Animal save(Animal animal) {
		return animalRepository.save(animal);
	}
	
	public void delete(Long id) {
		animalRepository.delete(id);
	}
	
}
