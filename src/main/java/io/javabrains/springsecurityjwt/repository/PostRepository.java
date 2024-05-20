package io.javabrains.springsecurityjwt.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import io.javabrains.springsecurityjwt.entity.Category;
import io.javabrains.springsecurityjwt.entity.Posts;
import io.javabrains.springsecurityjwt.entity.Users;


public interface PostRepository extends JpaRepository<Posts, Integer> {

	public List<Posts> findByCategory(Category category);

	public List<Posts> findByUser(Users user);

	@Query("SELECT p FROM Posts p WHERE LOWER(p.title) LIKE LOWER(CONCAT('%', :key, '%'))")
	public List<Posts> findByTitle(@Param("key") String title);

}
