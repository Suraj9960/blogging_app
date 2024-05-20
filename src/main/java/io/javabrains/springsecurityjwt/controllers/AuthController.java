package io.javabrains.springsecurityjwt.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import io.javabrains.springsecurityjwt.util.AuthenticationRequest;
import io.javabrains.springsecurityjwt.util.AuthenticationResponse;
import io.javabrains.springsecurityjwt.util.JwtUtil;


@RestController
@RequestMapping("/authenticate")
public class AuthController {
	
	@Autowired
	private JwtUtil jwtTokenHelper;
	
	@Autowired
	private UserDetailsService userDetailsService;
	
	@Autowired
	private AuthenticationManager authenticationManager;
	
	@PostMapping("/login")
	public ResponseEntity<AuthenticationResponse> createToken(@RequestBody AuthenticationRequest request){
		
		this.authenticate(request.getUsername() , request.getPassword());
		
		UserDetails userDetails =  userDetailsService.loadUserByUsername(request.getUsername());
		
		String token =  jwtTokenHelper.generateToken(userDetails);
		
		AuthenticationResponse response = new AuthenticationResponse();
		
		response.setToken(token);
		
		System.out.println("Token : "+ token );
		
		return new ResponseEntity<AuthenticationResponse>(response,HttpStatus.OK);
	}

	private void authenticate(String username, String password) {
		
		UsernamePasswordAuthenticationToken authenticationToken = 
				new UsernamePasswordAuthenticationToken(username, password);
		
		try {
			
			authenticationManager.authenticate(authenticationToken);
			
		}catch (BadCredentialsException e) {
			
			e.printStackTrace();
			System.out.println("Bad Credentials !! ");
		}
		
	}
	

}
