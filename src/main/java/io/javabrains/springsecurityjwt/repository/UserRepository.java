package io.javabrains.springsecurityjwt.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import io.javabrains.springsecurityjwt.entity.Users;


public interface UserRepository extends JpaRepository<Users, Integer> {
	public Users findByEmail(String email);

}
