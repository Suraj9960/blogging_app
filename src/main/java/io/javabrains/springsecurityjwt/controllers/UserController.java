package io.javabrains.springsecurityjwt.controllers;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import io.javabrains.springsecurityjwt.payloads.ApiResponse;
import io.javabrains.springsecurityjwt.payloads.UserDto;
import io.javabrains.springsecurityjwt.serviceImpl.UserServiceImpl;



@RestController
@RequestMapping("/api/users")
public class UserController {

	@Autowired
	private UserServiceImpl userServiceImpl;
	
	/* Get Users By id  */

	@GetMapping("/id/{id}")
	public ResponseEntity<?> getUsersById(@PathVariable int id ) {

		UserDto createdUser = userServiceImpl.findById(id);

		return new ResponseEntity<>(createdUser, HttpStatus.FOUND);
	}

	/* Update  Users */

	@PutMapping("/id/{id}")
	public ResponseEntity<UserDto> updateUsers(@Valid  @RequestBody UserDto userDto , @PathVariable int id ) {
		UserDto userDto2 = userServiceImpl.findById(id);
		userDto2.setName(userDto.getName());
		userDto2.setEmail(userDto.getEmail());
		userDto2.setPassword(userDto.getPassword());
		userDto2.setAbout(userDto.getAbout());
		
		userServiceImpl.CreateUser(userDto2);
		

		return new ResponseEntity<>(userDto2,  HttpStatus.CREATED);
	}
	
	/* Delete  Users */
	@DeleteMapping("/id/{id}")
	public ResponseEntity<ApiResponse> DeleteUsers(@PathVariable int id) {
		userServiceImpl.deleteById(id);
		return new ResponseEntity<ApiResponse>(new ApiResponse("User Deleted Successfully..!",true) ,HttpStatus.OK);
	}

}
