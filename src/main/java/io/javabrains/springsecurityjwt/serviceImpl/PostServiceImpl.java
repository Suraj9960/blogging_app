package io.javabrains.springsecurityjwt.serviceImpl;


import java.sql.Date;
import java.util.List;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import io.javabrains.springsecurityjwt.entity.Category;
import io.javabrains.springsecurityjwt.entity.Posts;
import io.javabrains.springsecurityjwt.entity.Users;
import io.javabrains.springsecurityjwt.exceptions.ResourceNotFoundException;
import io.javabrains.springsecurityjwt.payloads.PostDto;
import io.javabrains.springsecurityjwt.payloads.PostResponse;
import io.javabrains.springsecurityjwt.repository.CategoryRepository;
import io.javabrains.springsecurityjwt.repository.PostRepository;
import io.javabrains.springsecurityjwt.repository.UserRepository;
import io.javabrains.springsecurityjwt.serviceDao.PostService;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;

@Service
public class PostServiceImpl implements PostService {

	@Autowired
	private PostRepository repository;

	@Autowired
	private UserRepository userRepository;

	@Autowired
	private CategoryRepository categoryRepository;

	@Autowired
	private ModelMapper modelMapper;

	@Override
	public PostDto createPostDto(PostDto postDto, Integer userId, Integer categoryId) {

		Posts posts = modelMapper.map(postDto, Posts.class);

		Users user = userRepository.findById(userId)
				.orElseThrow(() -> new ResourceNotFoundException("User", "userId", userId));

		Category category = categoryRepository.findById(categoryId)
				.orElseThrow(() -> new ResourceNotFoundException("Category", "categoryId", categoryId));

		posts.setImagename("default.png");
		posts.setAddDate(new Date(categoryId));
		posts.setCategory(category);
		posts.setUser(user);

		Posts Savedposts = repository.save(posts);

		return modelMapper.map(Savedposts, PostDto.class);
	}

	@Override
	public PostDto updatePostDto(PostDto postDto, Integer postId) {

		Posts posts = repository.findById(postId)
				.orElseThrow(() -> new ResourceNotFoundException("Posts", "id", postId));

		posts.setTitle(postDto.getTitle());
		posts.setContent(postDto.getContent());
		posts.setImagename(postDto.getImagename());
		
		Posts updatedPosts =  repository.save(posts);
		
		return modelMapper.map(updatedPosts,PostDto.class);
	}

	@Override
	public PostDto findById(Integer postId) {
		Posts posts = repository.findById(postId)
				.orElseThrow(() -> new ResourceNotFoundException("Post", "id", postId));

		return modelMapper.map(posts, PostDto.class);
	}

	@Override
	public void deleteById(Integer postId) {
		Posts posts = repository.findById(postId)
				.orElseThrow(() -> new ResourceNotFoundException("Post", "id", postId));

		repository.delete(posts);

	}

	@Override
	public List<PostDto> findAllByUser(Integer userId) {
		
		Users user = userRepository.findById(userId).orElseThrow(() ->  new ResourceNotFoundException("User", "id", userId));
		
		List<Posts> posts = repository.findByUser(user);
		
		List<PostDto> postDtos =  posts.stream().map(post -> modelMapper.map(post, PostDto.class)).collect(Collectors.toList());

		return postDtos;
	}

	@Override
	public List<PostDto> findAllByCategory(Integer categoryId) {

		Category category = categoryRepository.findById(categoryId)
				.orElseThrow(() -> new ResourceNotFoundException("Category", "id", categoryId));

		List<Posts> posts = repository.findByCategory(category);
		
		List<PostDto> postDtos =  posts.stream().map(post -> modelMapper.map(post, PostDto.class)).collect(Collectors.toList());

		return postDtos;
	}

	@Override
	public List<PostDto> searchPosts(String keyword) {
		List<Posts> posts = repository.findByTitle("%" + keyword + "%");
		
		List<PostDto> postDtos = posts.stream().map( post -> modelMapper.map(post,PostDto.class)).collect(Collectors.toList());
		
		return postDtos;
	}

	@Override
	public PostResponse getAllPosts(Integer pageNumber , Integer pageSize , String sortBy) {
		
		Pageable pageable =  PageRequest.of(pageNumber, pageSize, Sort.by(sortBy));
        Page <Posts> postsPage = repository.findAll(pageable);
		
		List<PostDto> postDtos = postsPage.stream().map( post -> modelMapper.map(post,PostDto.class)).collect(Collectors.toList());
		
		PostResponse postResponse = new PostResponse();
		
		postResponse.setContent(postDtos);
		
		postResponse.setPageNumber(postsPage.getNumber());
		postResponse.setPageSize(postsPage.getSize());
		postResponse.setTotalElements(postsPage.getTotalElements());
		postResponse.setTotalPages(postsPage.getTotalPages());
		postResponse.setLastPage(postsPage.isLast());
		
		return postResponse;
	}

}
