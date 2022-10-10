package com.ltw.blog.services;

import java.util.List;

import com.ltw.blog.payload.CategoryDto;

public interface CategoryService {
	// create Category
	CategoryDto createCategory(CategoryDto categoryDto);
			
	// update Category
	CategoryDto updateCategory(CategoryDto categoryDto, Integer categoryId);

	// delete Category
	void deleteCategory(Integer categoryId);
	
	// get Category
	CategoryDto getCategory(Integer categoryId );

	// get All Category
	List<CategoryDto> getAllCategory();
}
