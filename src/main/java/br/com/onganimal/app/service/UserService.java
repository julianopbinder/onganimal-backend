package br.com.onganimal.app.service;

import java.util.ArrayList;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import br.com.onganimal.app.model.Owner;

@Service
public class UserService  implements UserDetailsService {
	
	@Autowired
	private OwnerService ownerService;

	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		Owner owner = this.ownerService.getByEmail(username);
		
		if (owner == null) {
			throw new UsernameNotFoundException("User not found with username: " + username);
		}
		
		return new User(owner.getEmail(), owner.getPassword(), new ArrayList<>());
	}
	
}
