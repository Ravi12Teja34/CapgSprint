package com.capg.onlineshopping.service;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.capg.onlineshopping.dto.cart.AddToCartDto;
import com.capg.onlineshopping.dto.cart.CartDto;
import com.capg.onlineshopping.dto.cart.CartItemDto;
import com.capg.onlineshopping.entity.Cart;
import com.capg.onlineshopping.entity.Product;
import com.capg.onlineshopping.entity.User;
import com.capg.onlineshopping.exceptions.CustomException;
import com.capg.onlineshopping.repository.CartRepository;
import com.capg.onlineshopping.repository.ProductRepository;

@Service
public class CartService {
	
	@Autowired
	ProductService productService;
	
	@Autowired 
	CartRepository cartRepository;

	public void addToCart(AddToCartDto addToCartDto, User user) {
		
		Product product= productService.findById(addToCartDto.getProductId());
		
		Cart cart=new Cart();
		cart.setProduct(product);
		cart.setUser(user);
		cart.setQuantity(addToCartDto.getQuantity());
		cart.setCreatedDate(new Date());
		
		cartRepository.save(cart);
		
		
		
	}

	public CartDto listCartItems(User user) {
		
	 List<Cart> cartList= cartRepository.findAllByUserOrderByCreatedDateDesc(user);
	 List<CartItemDto> cartItems=new ArrayList<>();
	 double totalCost=0;
	 for(Cart cart:cartList)
	 {
		 CartItemDto cartItemDto = new CartItemDto(cart);
		 totalCost+=cartItemDto.getQuantity()*cart.getProduct().getPrice();
		 cartItems.add(cartItemDto);
	 }
	 CartDto cartDto = new CartDto();
	 cartDto.setTotalCost(totalCost);
	 cartDto.setCartItems(cartItems);
	 return cartDto;
		
	}

	public void deleteCartItem(Integer cartItemId, User user) {
		
		Optional<Cart> optionalCart=cartRepository.findById(cartItemId);
		if(optionalCart.isEmpty())
		{
			throw new CustomException("cart item id is invalid");
		}
		
		Cart cart=optionalCart.get();
		if(cart.getUser()!=user)
		{
			throw new CustomException("cart item doesnot belong to the user");
		}
		
		cartRepository.delete(cart);

		
	}

}
