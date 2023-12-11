package com.capg.onlineshopping.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.capg.onlineshopping.dto.ProductDto;
import com.capg.onlineshopping.entity.Category;
import com.capg.onlineshopping.entity.Product;
import com.capg.onlineshopping.exceptions.ProductNotExistsException;
import com.capg.onlineshopping.repository.ProductRepository;

@Service
public class ProductService {
	
	@Autowired
	ProductRepository productRepository;
	
	public void createProduct(ProductDto productDto,Category category)
	{
		Product product=new Product();
		product.setName(productDto.getName());
		product.setDescription(productDto.getDescription());
		product.setPrice(productDto.getPrice());
		product.setCategory(category);
		
		productRepository.save(product);
	}
	
	public ProductDto getProductDto(Product product)
	{
		ProductDto productDto=new ProductDto();
		productDto.setName(product.getName());
		productDto.setDescription(product.getDescription());
		productDto.setPrice(product.getPrice());
		productDto.setId(product.getId());
		productDto.setCategoryId(product.getCategory().getId());
		return productDto;
	}
	
	public List<ProductDto> getAllProducts()
	{
		List<Product> allProducts= productRepository.findAll();
		List<ProductDto> productDtos=new ArrayList<>();
		for(Product product:allProducts)
		{
			productDtos.add(getProductDto(product));
		}
		return productDtos;
	}

	public void updateProduct(ProductDto productDto, Integer id) throws Exception {
		
		Optional<Product> optionalProduct=productRepository.findById(id);
		if(!optionalProduct.isPresent())
		{
			throw new Exception("product not present");
		}
		
		Product product=optionalProduct.get();
		product.setName(productDto.getName());
		product.setDescription(productDto.getDescription());
		product.setPrice(productDto.getPrice());
		
		productRepository.save(product);
		
		
	}

	public void deleteProduct(ProductDto productDto, Integer id) {
		productRepository.deleteById(id);
		
	}

	public Product findById(Integer productId)throws ProductNotExistsException {
		
		Optional<Product> optionalProduct=productRepository.findById(productId);
		if(optionalProduct.isEmpty())
		{
			throw new ProductNotExistsException("product is invalid:" +productId);
			
		}
		return optionalProduct.get();
		
		
		
	}

}
