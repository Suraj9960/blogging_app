package io.javabrains.springsecurityjwt.serviceImpl;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import io.javabrains.springsecurityjwt.entity.Comments;
import io.javabrains.springsecurityjwt.entity.Posts;
import io.javabrains.springsecurityjwt.entity.Users;
import io.javabrains.springsecurityjwt.exceptions.ResourceNotFoundException;
import io.javabrains.springsecurityjwt.payloads.CommentDto;
import io.javabrains.springsecurityjwt.repository.CommentsRepository;
import io.javabrains.springsecurityjwt.repository.PostRepository;
import io.javabrains.springsecurityjwt.repository.UserRepository;
import io.javabrains.springsecurityjwt.serviceDao.CommentService;


@Service
public class CommentServiceImpl implements CommentService {
	
	@Autowired
	private CommentsRepository repository;
	
	@Autowired
	private UserRepository userRepository;
	
	@Autowired
	private PostRepository postRepository;
	
	
	@Autowired
	private ModelMapper modelMapper;

	@Override
	public CommentDto createComment(CommentDto commentDto, Integer userId, Integer posId) {
		
		Comments comment = modelMapper.map(commentDto, Comments.class);

		Users user = userRepository.findById(userId)
				.orElseThrow(() -> new ResourceNotFoundException("User", "userId", userId));

		Posts posts = postRepository.findById(posId)
				.orElseThrow(() -> new ResourceNotFoundException("Post", "postId", posId));

		comment.setContent(commentDto.getContent());
		comment.setPosts(posts);
		comment.setUser(user);
		
		Comments Savedcomment = repository.save(comment);

		return modelMapper.map(Savedcomment, CommentDto.class);

	}

	@Override
	public CommentDto updateComment(CommentDto commentDto, Integer commentId) {
		

		Comments comment = repository.findById(commentId)
				.orElseThrow(() -> new ResourceNotFoundException("Comments", "id", commentId));
		
		comment.setContent(commentDto.getContent());
		
		Comments updatedComments =  repository.save(comment);
		
		return modelMapper.map(updatedComments,CommentDto.class);
	}

	@Override
	public void deleteComment(Integer commentId) {
		Comments comments = repository.findById(commentId).orElseThrow(() -> new ResourceNotFoundException("Comment", "id", commentId));
		repository.delete(comments);
		
		
	}

	@Override
	public CommentDto getById(Integer commentId) {
		Comments comments = repository.findById(commentId).orElseThrow(() -> new ResourceNotFoundException("Comment", "id", commentId));
		
		return modelMapper.map(comments, CommentDto.class);
	}
}
