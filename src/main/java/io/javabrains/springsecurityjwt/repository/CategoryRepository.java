package io.javabrains.springsecurityjwt.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import io.javabrains.springsecurityjwt.entity.Category;

public interface CategoryRepository extends JpaRepository<Category, Integer> {

}
