package com.ltw.blog.services.impl;

import java.util.List;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ltw.blog.entities.Category;
import com.ltw.blog.exceptions.ResourceNotFoundException;
import com.ltw.blog.payload.CategoryDto;
import com.ltw.blog.repositories.CategoryRepo;
import com.ltw.blog.services.CategoryService;

@Service
public class CategoryServiceImpl implements CategoryService{
	
	@Autowired
	private CategoryRepo categoryRepo;
	@Autowired
	private ModelMapper modelMapper;

	@Override
	public CategoryDto createCategory(CategoryDto categoryDto) {
		Category cat=this.modelMapper.map(categoryDto, Category.class);
		Category addcat= this.categoryRepo.save(cat);
		return this.modelMapper.map(addcat, CategoryDto.class);
	}

	@Override
	public CategoryDto updateCategory(CategoryDto categoryDto, Integer categoryId) {
		Category cat = this.categoryRepo.findById(categoryId).orElseThrow(()
				->new ResourceNotFoundException("Category", "Id", categoryId));
		cat.setTitle(categoryDto.getTitle());
		cat.setDescription(categoryDto.getDescription());
		Category updateCat= this.categoryRepo.save(cat);
		
		return this.modelMapper.map(updateCat, CategoryDto.class);
	}

	@Override
	public void deleteCategory(Integer categoryId) {
		Category cat = this.categoryRepo.findById(categoryId).orElseThrow(()
				-> new ResourceNotFoundException("Category", "Id", categoryId));
		this.categoryRepo.delete(cat);
		
	}

	@Override
	public CategoryDto getCategory(Integer categoryId) {
		Category cat = this.categoryRepo.findById(categoryId).orElseThrow(()
				->new ResourceNotFoundException("Category", "Id", categoryId));
		return this.modelMapper.map(cat, CategoryDto.class);
	}

	@Override
	public List<CategoryDto> getAllCategory() {
		List<Category> listCat= this.categoryRepo.findAll();
		List<CategoryDto> listCatDto=listCat.stream().map((cat)
				->this.modelMapper.map(cat, CategoryDto.class)).collect(Collectors.toList());
		
		return listCatDto;
	}

}
