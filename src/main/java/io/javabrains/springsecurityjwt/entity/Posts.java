package io.javabrains.springsecurityjwt.entity;

import java.sql.Date;
import java.util.HashSet;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.validation.constraints.NotEmpty;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Getter
@Setter
@NoArgsConstructor
public class Posts {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer postId;
	
	@NotEmpty(message = "Title Must Contain at least 2 letters !! ")
	private String title;
	
	@NotEmpty(message = "Content cannot be null !! ")
	private String content;
	
	private String imagename;
	
	private Date addDate;
	
	@ManyToOne
	private Category category;
	
	@ManyToOne
	private Users user;
	
	@OneToMany(mappedBy = "posts",cascade = CascadeType.ALL, fetch = FetchType.LAZY)
	private Set<Comments> comments = new HashSet<>();

}
