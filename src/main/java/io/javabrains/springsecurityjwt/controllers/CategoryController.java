package io.javabrains.springsecurityjwt.controllers;

import java.util.List;

import javax.validation.Valid;

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
import io.javabrains.springsecurityjwt.payloads.CategoryDto;
import io.javabrains.springsecurityjwt.serviceImpl.CategoryServiceImpl;


@RestController
@RequestMapping("/api/category")
public class CategoryController {

	@Autowired
	private CategoryServiceImpl categoryServiceImpl;

	@GetMapping
	public ResponseEntity<?> getAllCategories() {

		List<CategoryDto> categoryDtos = categoryServiceImpl.getAllCategories();

		if (categoryDtos != null && !categoryDtos.isEmpty()) {
			return new ResponseEntity<>(categoryDtos, HttpStatus.OK);
		}

		return new ResponseEntity<>(categoryDtos, HttpStatus.NOT_FOUND);
	}

	@GetMapping("/id/{categoryId}")
	public ResponseEntity<?> getAllCategoriesById(@PathVariable Integer categoryId) {

		CategoryDto categoryDto = categoryServiceImpl.findCategoryById(categoryId);

		return new ResponseEntity<>(categoryDto, HttpStatus.FOUND);
	}

	@PostMapping
	public ResponseEntity<?> createCategories(@Valid @RequestBody CategoryDto categoryDto) {

		CategoryDto SavedcategoryDto = categoryServiceImpl.createCategory(categoryDto);

		return new ResponseEntity<>(SavedcategoryDto, HttpStatus.CREATED);
	}

	@PutMapping("/id/{categoryId}")
	public ResponseEntity<?> updateCategories(@Valid @RequestBody CategoryDto categoryDto,
			@PathVariable Integer categoryId) {

		CategoryDto updatedcategoryDto = categoryServiceImpl.updateCategory(categoryDto, categoryId);

		return new ResponseEntity<>(updatedcategoryDto, HttpStatus.OK);
	}

	@DeleteMapping("/id/{categoryId}")
	public ResponseEntity<ApiResponse> deleteCategories( @PathVariable Integer categoryId) {
		
		categoryServiceImpl.deleteCategoryById(categoryId);

		return new ResponseEntity<ApiResponse>(new ApiResponse("Category Deleted Successfully..!",true), HttpStatus.OK);
	}

}
