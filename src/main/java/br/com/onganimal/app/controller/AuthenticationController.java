package br.com.onganimal.app.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.DisabledException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import br.com.onganimal.app.dto.LoginDTO;
import br.com.onganimal.app.dto.LoginResponse;
import br.com.onganimal.app.model.Owner;
import br.com.onganimal.app.service.OwnerService;
import br.com.onganimal.app.service.UserService;
import br.com.onganimal.app.util.JwtTokenUtils;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;

@RestController
public class AuthenticationController {
	
	@Autowired
	private AuthenticationManager authenticationManager;
	
	@Autowired
	private UserService userService;
	
	@Autowired
	private OwnerService ownerService;
	
	@Autowired
	private PasswordEncoder passwordEncoder;
	
	@Autowired
	private JwtTokenUtils jwtTokenUtils;
	
	@ApiOperation(value = "Endpoint responsável por autenticar o usuário.")
	@ApiResponses(value = { @ApiResponse(code = 200, message = "Autentica o usuário.") })
	@PostMapping("/authenticate")
	public ResponseEntity<?> createAuthenticationToken(@RequestBody LoginDTO authenticationRequest) throws Exception {
		authenticate(authenticationRequest.getUsername(), authenticationRequest.getPassword());
		
		final UserDetails userDetails = userService.loadUserByUsername(authenticationRequest.getUsername());
		final String token = jwtTokenUtils.generateToken(userDetails);
		
		return ResponseEntity.ok(new LoginResponse(token));
	}
	
	@ApiOperation(value = "Endpoint responsável por registrar um novo usuário.")
	@ApiResponses(value = { @ApiResponse(code = 200, message = "Registra um novo usuário.") })
	@PostMapping("/register")
	public Owner register(@RequestBody Owner owner) {
		
		owner.setPassword(passwordEncoder.encode(owner.getPassword()));
		
		return this.ownerService.save(owner);
	}
				
	private void authenticate(String username, String password) throws Exception {
		try {
			authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(username, password));
		} catch (DisabledException e) {
			throw new Exception("USER_DISABLED", e);
		} catch (BadCredentialsException e) {
			throw new Exception("INVALID_CREDENTIALS", e);
		}
	}
	
}
