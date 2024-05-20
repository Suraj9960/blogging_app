package io.javabrains.springsecurityjwt.controllers;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import io.javabrains.springsecurityjwt.payloads.UserDto;
import io.javabrains.springsecurityjwt.serviceImpl.UserServiceImpl;


@RestController
@RequestMapping("/public")
public class Controller {
	
	@Autowired
	private PasswordEncoder passwordEncoder;

	@Autowired
	private UserServiceImpl userServiceImpl;

	/* Get All Users */

	@GetMapping
	public ResponseEntity<?> getAllUsers() {
		List<UserDto> userDtos = userServiceImpl.getAlUsers();

		if (userDtos != null && !userDtos.isEmpty()) {
			return new ResponseEntity<>(userDtos, HttpStatus.OK);
		}

		return new ResponseEntity<>(HttpStatus.NOT_FOUND);

	}

	/* Create New Users */

	@PostMapping("/create_user")
	public ResponseEntity<?> createUser(@Valid @RequestBody UserDto userDto) {
		
		userDto.setPassword(passwordEncoder.encode(userDto.getPassword()));
		userDto.setRole(userDto.getRole());

		UserDto createdUser = userServiceImpl.CreateUser(userDto);
		
		System.out.println(userDto.getRole());
		System.out.println(userDto.getPassword());

		return new ResponseEntity<>(createdUser, HttpStatus.CREATED);
	}

}
