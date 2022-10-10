package com.ltw.blog.controllers;

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

import com.ltw.blog.entities.Category;
import com.ltw.blog.payload.ApiResponse;
import com.ltw.blog.payload.CategoryDto;
import com.ltw.blog.services.CategoryService;

@RestController
@RequestMapping("/api/categories")
public class CategoryController {
	@Autowired
	private CategoryService categoryService;
	
	@PostMapping("/add")
	public ResponseEntity<CategoryDto> createCategory(@Valid @RequestBody CategoryDto categoryDto){
		CategoryDto createCategory = this.categoryService.createCategory(categoryDto);
		return new ResponseEntity<CategoryDto>(createCategory,HttpStatus.CREATED);
	}
	
	@PutMapping("/update/{catId}")
	public ResponseEntity<CategoryDto> updateCategory(@Valid @RequestBody CategoryDto categoryDto,
			@PathVariable("catId") Integer catId){
			CategoryDto updateCat= this.categoryService.updateCategory(categoryDto, catId);
			return new ResponseEntity<CategoryDto>(updateCat,HttpStatus.OK);
	}
	
	@DeleteMapping("delete/{catId}")
	public ResponseEntity<ApiResponse> deleteCategory(@PathVariable("catId") Integer catId){
		this.categoryService.deleteCategory(catId);
		return new ResponseEntity<ApiResponse>(new ApiResponse("Category is delete succefully",true),HttpStatus.OK);
	}
	
	@GetMapping("{catId}")
	public ResponseEntity<CategoryDto> getCategory(@PathVariable("catId") Integer catId){
		CategoryDto catDto= this.categoryService.getCategory(catId);
		return new ResponseEntity<CategoryDto>(catDto, HttpStatus.OK);
	}
	
	@GetMapping("/")
	public ResponseEntity<List<CategoryDto>> getAllCategory(){
		List<CategoryDto> listCat= this.categoryService.getAllCategory();
		return new ResponseEntity<List<CategoryDto>>(listCat, HttpStatus.OK);
	}

}
