package io.javabrains.springsecurityjwt.controllers;

import java.io.IOException;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import io.javabrains.springsecurityjwt.config.AppContrant;
import io.javabrains.springsecurityjwt.payloads.ApiResponse;
import io.javabrains.springsecurityjwt.payloads.PostDto;
import io.javabrains.springsecurityjwt.payloads.PostResponse;
import io.javabrains.springsecurityjwt.serviceImpl.FileServiceImpl;
import io.javabrains.springsecurityjwt.serviceImpl.PostServiceImpl;

@RestController
@RequestMapping("/api")
public class PostController {

	@Autowired
	private PostServiceImpl postServiceImpl;
	
	@Autowired
	private FileServiceImpl fileServiceImpl;
	
	@Value("${project.image}")
	private String path;

	@PostMapping("/user/{userId}/category/{categoryId}")
	public ResponseEntity<PostDto> createPosts(@RequestBody PostDto postDto, @PathVariable Integer userId,
			@PathVariable Integer categoryId) {
		PostDto createdPost = postServiceImpl.createPostDto(postDto, userId, categoryId);

		return new ResponseEntity<>(createdPost, HttpStatus.CREATED);
	}

	@PutMapping("/post/{postId}")
	public ResponseEntity<PostDto> updatePosts(@RequestBody PostDto postDto, @PathVariable Integer postId) {

		PostDto postDto2 = postServiceImpl.updatePostDto(postDto, postId);

		return new ResponseEntity<PostDto>(postDto2, HttpStatus.OK);
	}

	@GetMapping("/user/{userId}")
	public ResponseEntity<?> getPostsByUser(@PathVariable Integer userId) {

		List<PostDto> posts = postServiceImpl.findAllByUser(userId);

		if (posts != null && !posts.isEmpty()) {
			return new ResponseEntity<>(posts, HttpStatus.OK);
		}

		return new ResponseEntity<>(HttpStatus.NOT_FOUND);
	}

	@GetMapping("/category/{categoryId}")
	public ResponseEntity<?> getPostsByCategory(@PathVariable Integer categoryId) {

		List<PostDto> posts = postServiceImpl.findAllByCategory(categoryId);

		if (posts != null && !posts.isEmpty()) {
			return new ResponseEntity<>(posts, HttpStatus.OK);
		}

		return new ResponseEntity<>(HttpStatus.NOT_FOUND);
	}

	@GetMapping("/post/{postId}")
	public ResponseEntity<?> getPostsById(@PathVariable Integer postId) {

		PostDto postDto = postServiceImpl.findById(postId);

		return new ResponseEntity<>(postDto, HttpStatus.OK);
	}

	@DeleteMapping("/post/{postId}")
	public ResponseEntity<ApiResponse> DeletePost(@PathVariable Integer postId) {

		postServiceImpl.deleteById(postId);

		return new ResponseEntity<ApiResponse>(new ApiResponse("Post Deleted Successfully..!", true), HttpStatus.OK);
	}

	@GetMapping
	public ResponseEntity<?> getAllPosts(
			@RequestParam(value = "pageNumber", defaultValue = AppContrant.PAGE_NUMBER, required = false) Integer pageNumber,
			@RequestParam(value = "pageSize", defaultValue = AppContrant.PAGE_SIZE, required = false) Integer pageSize,
			@RequestParam(value = "sortBy", defaultValue = AppContrant.SORT_BY, required = false) String sortBy) {

		PostResponse postDto = postServiceImpl.getAllPosts(pageNumber, pageSize, sortBy);

		if (postDto != null) {
			return new ResponseEntity<>(postDto, HttpStatus.OK);
		}

		return new ResponseEntity<>(postDto, HttpStatus.NOT_FOUND);
	}

	// searching api

	@GetMapping("/title/{key}")
	public ResponseEntity<?> searching(@PathVariable("key") String key) {

		List<PostDto> postDtos  =  postServiceImpl.searchPosts(key);
		
		if(!postDtos.isEmpty() && postDtos != null) {
			return new ResponseEntity<>(postDtos , HttpStatus.OK);
		}
		
		return new ResponseEntity<>(postDtos , HttpStatus.NOT_FOUND);

		
	}
	
	// uploading the images
	
	@PostMapping("/upload/image/post/{postId}")
	public ResponseEntity<PostDto> uploadImage(@RequestParam("image") MultipartFile image , @PathVariable Integer postId) throws IOException{
		
		PostDto postDto = postServiceImpl.findById(postId);
		
		String filename =  fileServiceImpl.uploadImage(path, image);
		 
		postDto.setImagename(filename);
		
		PostDto updatedPostDto =  postServiceImpl.updatePostDto(postDto, postId);
		
		return new ResponseEntity<PostDto>(updatedPostDto, HttpStatus.OK);
	}

}
