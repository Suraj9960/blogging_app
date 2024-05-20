package io.javabrains.springsecurityjwt.entity;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity(name = "comments")
@Getter
@Setter
@NoArgsConstructor
public class Comments {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer comentId;
	
	private String content;
	
	@ManyToOne
	private Posts posts;
	
	@ManyToOne
	private Users user;
	
	

}
