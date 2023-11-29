package com.adityarai1.blog.services;

import java.util.List;

import com.adityarai1.blog.payloads.CategoryDto;

public interface CategoryService 
{
    // create , update , delete, get, getAll
	
	CategoryDto createCategory(CategoryDto categoryDto);
	
	CategoryDto updateCategory(CategoryDto categoryDto, Integer categoryId );
	
	void deleteCategory (Integer categoryId);
	
	CategoryDto getCategory(Integer categoryId);
	
	List<CategoryDto> getCategories();
	
	
}
