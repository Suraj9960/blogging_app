package io.javabrains.springsecurityjwt.serviceDao;

import java.util.List;

import io.javabrains.springsecurityjwt.payloads.UserDto;


public interface UserService {
	public List<UserDto> getAlUsers();
	
	public UserDto CreateUser(UserDto userDto);
	
	public UserDto findById(Integer id);
	
	public void deleteById(Integer id);
	
	public UserDto updateUser(UserDto userDto , Integer id);
	
	
	

}
