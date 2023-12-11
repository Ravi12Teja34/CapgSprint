package com.capg.onlineshopping.controller;

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

import com.capg.onlineshopping.common.ApiResponse;
import com.capg.onlineshopping.entity.Category;
import com.capg.onlineshopping.service.CategoryService;

@RestController
@RequestMapping("/category")
public class CategoryController {
	
	@Autowired
	CategoryService categoryService;
	
	@PostMapping("/create")
	public ResponseEntity<ApiResponse> createCategory(@RequestBody Category category)
	{
		categoryService.createCategory(category);
		return new ResponseEntity<>(new ApiResponse(true,"created new category"),HttpStatus.CREATED);
	}
	
	@DeleteMapping("/delete/{id}")
	public String deleteCategoryById(@PathVariable("id") Integer id)
	{
		categoryService.deleteCategoryById(id);
		return "Deleted successfully";
	}
	
	@GetMapping("/list")
	public List<Category> listAllCategories()
	{
		return categoryService.listAllCategories();
	}
	
	@PostMapping("/update/{id}")
    public ResponseEntity<ApiResponse> updateCategory(@PathVariable("id") Integer categoryId, @RequestBody Category updatedCategory) {
        if(!categoryService.findById(categoryId))
        {
    		return new ResponseEntity<>(new ApiResponse(false,"category does not exist"),HttpStatus.NOT_FOUND);

        }
		categoryService.updateCategory(categoryId, updatedCategory);
		return new ResponseEntity<>(new ApiResponse(true,"category has been updated"),HttpStatus.OK);

        
    }

}
