package io.javabrains.springsecurityjwt.payloads;


import javax.validation.constraints.NotEmpty;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class CategoryDto {
	
	private Integer category_id;
	@NotEmpty(message = "Title Must have at least 10 characters !!")
	private String title;
	@NotEmpty(message = "Description cannot be empty !! ")
	private String description;

}
