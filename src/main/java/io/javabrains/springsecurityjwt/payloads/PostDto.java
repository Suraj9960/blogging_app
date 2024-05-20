package io.javabrains.springsecurityjwt.payloads;

import java.sql.Date;
import java.util.HashSet;
import java.util.Set;

import javax.validation.constraints.NotEmpty;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class PostDto {

	@NotEmpty
	private Integer postId;
	@NotEmpty(message = "Title must have one letter !! ")
	private String title;
	@NotEmpty(message = "Content cannot be empty !! ")
	private String content;
	private String imagename;
	private Date addDate;

	private UserDto user;

	private CategoryDto category;
	
	private Set<CommentDto> comments = new HashSet<>();
}
