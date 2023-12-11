package com.capg.onlineshopping.controller;

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
import com.capg.onlineshopping.dto.cart.AddToCartDto;
import com.capg.onlineshopping.dto.cart.CartDto;
import com.capg.onlineshopping.entity.User;
import com.capg.onlineshopping.service.AuthenticationTokenService;
import com.capg.onlineshopping.service.CartService;

@RestController
@RequestMapping("/cart")
public class CartController {
	
	@Autowired
	private CartService cartService;
	
	@Autowired
	private AuthenticationTokenService authenticationTokenService;
	
	@PostMapping("/add/{token}")
	public ResponseEntity<ApiResponse> addToCart(@RequestBody AddToCartDto addToCartDto, @PathVariable("token") String token)
	{
		authenticationTokenService.authenticate(token);
		
		User user= authenticationTokenService.getUser(token);
		
		cartService.addToCart(addToCartDto, user);
		return new ResponseEntity<>(new ApiResponse(true, "Added to cart"),HttpStatus.CREATED);
	}
	
	@GetMapping("/{token}")
	public ResponseEntity<CartDto> getCartItems(@PathVariable("token") String token)
	{
        authenticationTokenService.authenticate(token);
		
		User user= authenticationTokenService.getUser(token);
		
		CartDto cartDto=cartService.listCartItems(user);
		
		return new ResponseEntity<>(cartDto,HttpStatus.OK);
	}
	
	@DeleteMapping("/delete/{cartItemId}/{token}")
	public ResponseEntity<ApiResponse> deleteCartItem(@PathVariable("cartItemId") Integer cartItemId, @PathVariable("token") String token)
	{
        authenticationTokenService.authenticate(token);
		
		User user= authenticationTokenService.getUser(token);
		
		cartService.deleteCartItem(cartItemId,user);
		
		return new ResponseEntity<>(new ApiResponse(true, "Item has been removed"),HttpStatus.OK);

	}

}
