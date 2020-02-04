package br.com.onganimal.app.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import br.com.onganimal.app.enumerated.Status;
import br.com.onganimal.app.model.Animal;
import br.com.onganimal.app.model.Owner;

public interface AnimalRepository extends JpaRepository<Animal, Long>{

	Page<Animal> findByStatusNot(Status status, Pageable pageable);
	Page<Animal> findByOwner(Owner owner, Pageable pageable);
	
	
}
