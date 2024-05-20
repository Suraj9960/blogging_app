package io.javabrains.springsecurityjwt.serviceDao;

import java.util.List;

import io.javabrains.springsecurityjwt.payloads.PostDto;
import io.javabrains.springsecurityjwt.payloads.PostResponse;


public interface PostService {
	
	public PostResponse getAllPosts(Integer pageNumber , Integer pageSize , String sortBy);
	
	public PostDto createPostDto(PostDto postDto , Integer userId , Integer categoryId);
	
	public PostDto updatePostDto(PostDto postDto , Integer postId);
	
	public PostDto findById(Integer postId);
	
	public void deleteById(Integer postId);
	
	
	public List<PostDto> findAllByUser(Integer userId);
	
	public List<PostDto> findAllByCategory(Integer categoryId);
	
	public List<PostDto> searchPosts(String keyword);

}
