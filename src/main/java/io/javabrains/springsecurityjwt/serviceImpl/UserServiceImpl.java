package io.javabrains.springsecurityjwt.serviceImpl;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import io.javabrains.springsecurityjwt.entity.Users;
import io.javabrains.springsecurityjwt.exceptions.ResourceNotFoundException;
import io.javabrains.springsecurityjwt.payloads.UserDto;
import io.javabrains.springsecurityjwt.repository.UserRepository;
import io.javabrains.springsecurityjwt.serviceDao.UserService;


@Service
public class UserServiceImpl implements UserService {
	@Autowired
	private UserRepository repository;

	/* Get All Users */
	
	@Override
	public List<UserDto> getAlUsers() {
		
		List<Users> users = repository.findAll();
		
		List<UserDto> userDtos = users.stream().map( user -> userToDto(user)).collect(Collectors.toList());
		
		return userDtos;
	}
	
	/* Create the  User */

	@Override
	public UserDto CreateUser(UserDto userDto) {
		
		Users user = dtoToUser(userDto);
		Users savedUser = repository.save(user);
		
		return this.userToDto(savedUser);
	}
	
	/* Find User By Id  */
	
	@Override
	public UserDto findById(Integer id) {
		
		Users  user = repository.findById(id).orElseThrow( () ->  new ResourceNotFoundException("User", "id", id));
		
		UserDto userDto = userToDto(user);
		
		return userDto;
	}
	
	/* Delete the  User */

	@Override
	public void deleteById(Integer id) {
		Users user = repository.findById(id).orElseThrow( () ->  new ResourceNotFoundException("User", "id", id));
		
		repository.delete(user);
	}
	
	
	/* Update The User */

	@Override
	public UserDto updateUser(UserDto userDto, Integer id) {
		
		Users  user = repository.findById(id).orElseThrow( () ->  new ResourceNotFoundException("User", "id", id));
		user.setName(userDto.getName());
		user.setEmail(userDto.getEmail());
		user.setPassword(userDto.getPassword());
		user.setAbout(userDto.getAbout());
		
		Users updatedUser = repository.save(user);
		
		
		return userToDto(updatedUser);
	}
	
	
	/* Converting UserDto to User */
	
	public  Users dtoToUser(UserDto userDto) {
		Users user = new Users();
		
		user.setId(userDto.getId());
		user.setName(userDto.getName());
		user.setEmail(userDto.getEmail());
		user.setPassword(userDto.getPassword());
		user.setAbout(userDto.getAbout());
		user.setRole(userDto.getRole());
		
		return user;
		
	}
	
	/* Converting User to UserDto */
	
	public UserDto userToDto(Users user) {
		UserDto userDto = new UserDto();
		userDto.setId(user.getId());
		userDto.setName(user.getName());
		userDto.setEmail(user.getEmail());
		userDto.setPassword(user.getPassword());
		userDto.setAbout(user.getAbout());
		userDto.setRole(user.getRole());
		
		return userDto;
	}
	
	

}
