package br.com.onganimal.app.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.onganimal.app.model.Owner;
import br.com.onganimal.app.repository.OwnerRepository;

@Service
public class OwnerService {

	@Autowired
	private OwnerRepository ownerRepository;
	
	public Owner getByEmail(String email) {
		return this.ownerRepository.findByEmail(email);
	}
	
	public Owner save(Owner owner) {
		return this.ownerRepository.save(owner);
	}
	
}
