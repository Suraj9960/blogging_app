package io.javabrains.springsecurityjwt.entity;

import java.util.ArrayList;
import java.util.List;

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

@Entity(name = "categories")
@Getter
@Setter
@NoArgsConstructor
public class Category {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer category_id;
	private String title;
	private String description;
	
	@OneToMany(mappedBy = "category" , cascade = CascadeType.ALL, fetch = FetchType.LAZY)
	private List<Posts> posts = new ArrayList<>();
	

}
