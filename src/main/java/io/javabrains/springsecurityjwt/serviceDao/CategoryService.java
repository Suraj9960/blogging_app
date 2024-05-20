package io.javabrains.springsecurityjwt.serviceDao;

import java.util.List;

import io.javabrains.springsecurityjwt.payloads.CategoryDto;



public interface CategoryService {

	public List<CategoryDto> getAllCategories();
	
	public CategoryDto createCategory(CategoryDto categoryDto);
	
	public CategoryDto findCategoryById(Integer categoryId);
	
	public void deleteCategoryById(Integer categoryId);
	
	public CategoryDto updateCategory(CategoryDto categoryDto , Integer categoryId);
}
