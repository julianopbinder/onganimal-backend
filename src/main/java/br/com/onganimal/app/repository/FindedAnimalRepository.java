package br.com.onganimal.app.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import br.com.onganimal.app.model.FindedAnimal;
import br.com.onganimal.app.model.Owner;

public interface FindedAnimalRepository extends JpaRepository<FindedAnimal, Long>{

	List<FindedAnimal> findByAnimalIdAndAnimalOwner(Long id, Owner owner);
	
}
