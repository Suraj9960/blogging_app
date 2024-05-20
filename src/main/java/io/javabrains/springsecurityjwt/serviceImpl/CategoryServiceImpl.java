package io.javabrains.springsecurityjwt.serviceImpl;

import java.util.List;
import java.util.stream.Collectors;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import io.javabrains.springsecurityjwt.entity.Category;
import io.javabrains.springsecurityjwt.exceptions.ResourceNotFoundException;
import io.javabrains.springsecurityjwt.payloads.CategoryDto;
import io.javabrains.springsecurityjwt.repository.CategoryRepository;
import io.javabrains.springsecurityjwt.serviceDao.CategoryService;


@Service
public class CategoryServiceImpl implements CategoryService {
	
	@Autowired
	private CategoryRepository repository;

	@Override
	public List<CategoryDto> getAllCategories() {
		List<Category> categories = repository.findAll();
		
		List<CategoryDto>categoryDtos = categories.stream().map( categorie -> categoryToDto(categorie)).collect(Collectors.toList());
		
		return categoryDtos;
	}

	@Override
	public CategoryDto createCategory(CategoryDto categoryDto) {
		
		Category category = dtoToCategory(categoryDto);
		Category savedCategory = repository.save(category);
		
		return categoryToDto(savedCategory);
	}

	@Override
	public CategoryDto findCategoryById(Integer categoryId) {
		
		Category  category = repository.findById(categoryId).orElseThrow( () ->  new ResourceNotFoundException("Category", "id", categoryId));
		
		CategoryDto categoryDto = categoryToDto(category);
		
		return categoryDto;
	}

	@Override
	public void deleteCategoryById(Integer categoryId) {
		Category  category = repository.findById(categoryId).orElseThrow( () ->  new ResourceNotFoundException("Category", "id", categoryId));
		
		repository.delete(category);
		
	}

	@Override
	public CategoryDto updateCategory(CategoryDto categoryDto, Integer categoryId) {
		Category  category = repository.findById(categoryId).orElseThrow( () ->  new ResourceNotFoundException("Category", "id", categoryId));
		
		category.setTitle(categoryDto.getTitle());
		category.setDescription(categoryDto.getDescription());
		
		Category updatedCategory = repository.save(category);
		
		return categoryToDto(updatedCategory);
	}
	
	
	/* Converting categoryDto to Category*/
	
	public Category dtoToCategory(CategoryDto categoryDto) {
		Category category = new Category();
		category.setCategory_id(categoryDto.getCategory_id());
		category.setTitle(categoryDto.getTitle());
		category.setDescription(categoryDto.getDescription());
		
		return category;
	}
	
/* Converting  Category to CategoryDto */
	
	public CategoryDto categoryToDto(Category category) {
		CategoryDto categoryDto = new CategoryDto();
		categoryDto.setCategory_id(category.getCategory_id());
		categoryDto.setTitle(category.getTitle());
		categoryDto.setDescription(category.getDescription());
		
		return categoryDto;
	}


}
