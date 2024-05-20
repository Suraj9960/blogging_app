package io.javabrains.springsecurityjwt.payloads;

import javax.validation.constraints.NotEmpty;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class CommentDto {
	
	@NotEmpty
	private Integer comentId;
	
	private String content;

}
