package io.javabrains.springsecurityjwt.repository;

import java.util.Set;
import org.springframework.data.jpa.repository.JpaRepository;

import io.javabrains.springsecurityjwt.entity.Comments;
import io.javabrains.springsecurityjwt.entity.Posts;
import io.javabrains.springsecurityjwt.entity.Users;

public interface CommentsRepository extends JpaRepository<Comments, Integer> {

	public Set<Comments> findByPosts(Posts posts);

	public Set<Comments>findByUser(Users user);
}
