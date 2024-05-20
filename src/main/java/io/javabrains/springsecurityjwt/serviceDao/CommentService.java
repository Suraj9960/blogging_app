package io.javabrains.springsecurityjwt.serviceDao;

import io.javabrains.springsecurityjwt.payloads.CommentDto;

public interface CommentService {

	public CommentDto createComment(CommentDto commentDto, Integer userId, Integer posId);

	public CommentDto updateComment(CommentDto commentDto, Integer commentId);

	public void deleteComment(Integer commentId);

	public CommentDto getById(Integer commentId);

}
