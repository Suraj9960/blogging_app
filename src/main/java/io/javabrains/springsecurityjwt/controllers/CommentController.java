package io.javabrains.springsecurityjwt.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import io.javabrains.springsecurityjwt.payloads.ApiResponse;
import io.javabrains.springsecurityjwt.payloads.CommentDto;
import io.javabrains.springsecurityjwt.serviceImpl.CommentServiceImpl;


@RestController
@RequestMapping("/api/comments")
public class CommentController {

	@Autowired
	private CommentServiceImpl commentServiceImpl;

	@PostMapping("/user/{userId}/post/{postId}")
	public ResponseEntity<?> createComments(@RequestBody CommentDto commentDto, @PathVariable Integer postId,
			@PathVariable Integer userId) {

		CommentDto commentDto2 = commentServiceImpl.createComment(commentDto, userId, postId);

		return new ResponseEntity<>(commentDto2, HttpStatus.CREATED);
	}

	@PutMapping("/{commentId}")
	public ResponseEntity<?> createComments(@RequestBody CommentDto commentDto, @PathVariable Integer commentId) {

		CommentDto commentDto2 = commentServiceImpl.updateComment(commentDto, commentId);

		return new ResponseEntity<>(commentDto2, HttpStatus.FOUND);
	}

	@GetMapping("/find/{commentId}")
	public ResponseEntity<?> findComments(@PathVariable Integer commentId) {

		CommentDto commentDto2 = commentServiceImpl.getById(commentId);

		return new ResponseEntity<>(commentDto2, HttpStatus.OK);
	}

	@DeleteMapping("/delete/{commentId}")
	public ResponseEntity<ApiResponse> deleteComments(@PathVariable Integer commentId) {

		commentServiceImpl.deleteComment(commentId);

		return new ResponseEntity<ApiResponse>(new ApiResponse("Comment Deleted Successfully !! ",true), HttpStatus.CREATED);
	}

}
