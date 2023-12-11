package com.capg.onlineshopping.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.capg.onlineshopping.entity.Category;
import com.capg.onlineshopping.repository.CategoryRepository;

@Service
public class CategoryService {
	
	@Autowired
	CategoryRepository categoryRepository;
	
	public void createCategory(Category category)
	{
		categoryRepository.save(category);
	}
	
	public void deleteCategoryById(Integer id)
	{
		categoryRepository.deleteById(id);
	}
	
	public List<Category> listAllCategories()
	{
		return categoryRepository.findAll();
	}
	
	public void updateCategory(Integer categoryId,Category updatedCategory)
	{
		Optional<Category> category=categoryRepository.findById(categoryId);
		if (category.isPresent())
		{
			Category categoryy = category.get();
			categoryy.setCategoryName(updatedCategory.getCategoryName());
			categoryRepository.save(categoryy);
		}
		else
		{
			System.out.println("Id not found");
		}
		
	}

	public boolean findById(Integer categoryId) {

		return categoryRepository.findById(categoryId).isPresent();
	}

}
