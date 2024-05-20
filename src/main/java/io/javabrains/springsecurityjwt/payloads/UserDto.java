package io.javabrains.springsecurityjwt.payloads;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class UserDto {
	private Integer id;
	@NotNull
	@Size(min = 4 , message = "Username must have 4 characters !! ")
	private String name;
	
	@Email
	@NotEmpty(message = "Email is not Valid !! ")
	private String email;
	
	@NotEmpty(message = "Password Cannot be null !! ")
	private String password;
	
	@NotEmpty(message = "Please Enter Something !! ")
	private String about;
	
	@NotEmpty(message =  "Role Must not empty !! ")
	private String role;
	
	
}
