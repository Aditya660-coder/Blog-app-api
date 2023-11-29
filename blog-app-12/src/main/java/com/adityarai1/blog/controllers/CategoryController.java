package com.adityarai1.blog.controllers;

import java.util.List;

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

import com.adityarai1.blog.payloads.ApiResponse;
import com.adityarai1.blog.payloads.CategoryDto;
import com.adityarai1.blog.services.CategoryService;

import jakarta.validation.Valid;


@RestController
@RequestMapping("/api/categories")
public class CategoryController 
{

	@Autowired
	private CategoryService categoryService;
	
	// Create Category
	@PostMapping("/")
	public ResponseEntity<CategoryDto> createCategory(@Valid @RequestBody CategoryDto cateDto)
	{
		CategoryDto createCate= this.categoryService.createCategory(cateDto);
		return new ResponseEntity<CategoryDto>(createCate,HttpStatus.CREATED);
	}
	
	//Update 
	@PutMapping("/{catId}")
	public ResponseEntity<CategoryDto> updateCategory(@Valid @RequestBody CategoryDto cateDto,@PathVariable("catId") Integer categoryId)
	{
		CategoryDto updateCate= this.categoryService.updateCategory(cateDto,categoryId);
		return new ResponseEntity<CategoryDto>(updateCate,HttpStatus.OK);
	}
	
	//delete
	@DeleteMapping("/{catId}")
	public ResponseEntity<ApiResponse> deleteCategory(@PathVariable Integer catId)
	{
		 this.categoryService.deleteCategory(catId);
		return new ResponseEntity<ApiResponse>(new ApiResponse("category is deleted succesfully", false),
				HttpStatus.OK);
	}
	
	//get single
	@GetMapping("/{catId}")
	public ResponseEntity<CategoryDto> getCategory(@PathVariable Integer catId)
	{
		CategoryDto categoryDto= this.categoryService.getCategory(catId);
		return new ResponseEntity<CategoryDto>(categoryDto,HttpStatus.OK);
	}
	
	//get all
	@GetMapping("/")
	public ResponseEntity<List<CategoryDto>> getCategories()
	{
		List<CategoryDto> categories= categoryService.getCategories();
		return ResponseEntity.ok(categories);
	}
	
	
}
