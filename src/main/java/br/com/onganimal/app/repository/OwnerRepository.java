package br.com.onganimal.app.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import br.com.onganimal.app.model.Owner;

public interface OwnerRepository extends JpaRepository<Owner, Long> {
	
	Owner findByEmail(String email);
}
