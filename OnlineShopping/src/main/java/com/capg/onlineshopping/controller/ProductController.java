package com.capg.onlineshopping.controller;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.capg.onlineshopping.common.ApiResponse;
import com.capg.onlineshopping.dto.ProductDto;
import com.capg.onlineshopping.entity.Category;
import com.capg.onlineshopping.entity.Product;
import com.capg.onlineshopping.repository.CategoryRepository;
import com.capg.onlineshopping.repository.ProductRepository;
import com.capg.onlineshopping.service.ProductService;

@RestController
@RequestMapping("/product")
public class ProductController {
	
	@Autowired
	ProductService productService;
	
	@Autowired
	CategoryRepository categoryRepository;
	
	@Autowired
	ProductRepository productRepository;
	
	@PostMapping("/add")
    public ResponseEntity<ApiResponse> createProduct(@RequestBody ProductDto productDto)
	{
		Optional<Category> optionalCategory = categoryRepository.findById(productDto.getCategoryId());
		if(!optionalCategory.isPresent()) {
			
			return new ResponseEntity<ApiResponse>(new ApiResponse(false,"category doesnot exists"),HttpStatus.BAD_REQUEST);
			
		}
		productService.createProduct(productDto,optionalCategory.get());
		return new ResponseEntity<ApiResponse>(new ApiResponse(true,"product has been added"),HttpStatus.CREATED);
		
		
	}
	
	@GetMapping("/list")
	public ResponseEntity<List<ProductDto>> getAllProducts()
	{
		List<ProductDto> products=productService.getAllProducts();
		return new ResponseEntity<List<ProductDto>>(products,HttpStatus.OK);
	}
	
	@PostMapping("/update/{id}")
    public ResponseEntity<ApiResponse> updateProduct(@PathVariable("id") Integer id, @RequestBody ProductDto productDto) throws Exception
	{
		Optional<Product> optionalProduct = productRepository.findById(productDto.getId());
		if(!optionalProduct.isPresent()) {
			
			return new ResponseEntity<ApiResponse>(new ApiResponse(false,"product doesnot exists"),HttpStatus.BAD_REQUEST);
			
		}
		productService.updateProduct(productDto,id);
		return new ResponseEntity<ApiResponse>(new ApiResponse(true,"product has been updated"),HttpStatus.OK);
		
		
	}
	
	@DeleteMapping("/delete/{id}")
	public ResponseEntity<ApiResponse> deleteProduct(@PathVariable("id") Integer id, ProductDto productDto)
	{
		Optional<Product> optionalProduct = productRepository.findById(productDto.getId());
        if(!optionalProduct.isPresent()) {
			
			return new ResponseEntity<ApiResponse>(new ApiResponse(false,"product doesnot exists"),HttpStatus.BAD_REQUEST);
			
		}
		productService.deleteProduct(productDto,id);
		return new ResponseEntity<ApiResponse>(new ApiResponse(true,"product has been deleted"),HttpStatus.OK);


	}
	
	

}
