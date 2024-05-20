package io.javabrains.springsecurityjwt.util;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import io.javabrains.springsecurityjwt.entity.Users;
import io.javabrains.springsecurityjwt.repository.UserRepository;


@Service
public class MyUserDetailsService implements UserDetailsService {

	@Autowired
	private UserRepository repository;

	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		
		Users user = repository.findByEmail(username);
		
		if(user != null) {
			UserDetails userDetails = org.springframework.security.core.userdetails.User.builder().username(user.getEmail())
			              .password(user.getPassword())
			              .roles(user.getRole())
			              .build();
			return userDetails;
		}
		
		throw new UsernameNotFoundException("User not found : "+username);
	}
}