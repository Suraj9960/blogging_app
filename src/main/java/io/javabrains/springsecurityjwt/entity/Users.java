package io.javabrains.springsecurityjwt.entity;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity(name = "users")
@Getter
@Setter
@NoArgsConstructor
public class Users {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;
	private String name;
	private String email;
	private String password;
	private String about;
	private String role;
	
	@OneToMany(mappedBy = "user" , cascade = CascadeType.ALL, fetch = FetchType.LAZY)
	private List<Posts> posts = new ArrayList<>();
	
	
	@OneToMany(mappedBy = "user" , cascade = CascadeType.ALL, fetch = FetchType.LAZY)
	private Set<Comments> comments = new HashSet<>();
	

}
