package com.capg.onlineshopping.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.capg.onlineshopping.common.ApiResponse;
import com.capg.onlineshopping.dto.ProductDto;
import com.capg.onlineshopping.entity.Product;
import com.capg.onlineshopping.entity.User;
import com.capg.onlineshopping.entity.WishList;
import com.capg.onlineshopping.service.AuthenticationTokenService;
import com.capg.onlineshopping.service.WishListService;

@RestController
@RequestMapping("/wishlist")
public class WishListController {
	
	@Autowired
	WishListService wishListService;
	
	@Autowired 
	AuthenticationTokenService authenticationTokenService;
	
	@PostMapping("/add/{token}")
	public ResponseEntity<ApiResponse> addToWishList(@RequestBody Product product,@PathVariable("token") String token)
	{
		authenticationTokenService.authenticate(token);
		
		User user= authenticationTokenService.getUser(token);
		
		WishList wishList = new WishList(user,product);
		
		wishListService.createWishList(wishList);
		
		return new ResponseEntity<>(new ApiResponse(true,"Added to wishlist"),HttpStatus.CREATED);
		
	}
	
	@GetMapping("/{token}")
	public ResponseEntity<List<ProductDto>> getWishList(@PathVariable("token") String token)
	{
        authenticationTokenService.authenticate(token);
		
		User user= authenticationTokenService.getUser(token);
		
		List<ProductDto> productDtos = wishListService.getWishListForUser(user);
		
		return new ResponseEntity<>(productDtos,HttpStatus.OK);
		
	}

}
