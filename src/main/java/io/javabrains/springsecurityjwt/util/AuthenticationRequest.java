package io.javabrains.springsecurityjwt.util;

import lombok.Data;

@Data
public class AuthenticationRequest {

	private String username;
    private String password;

 }
