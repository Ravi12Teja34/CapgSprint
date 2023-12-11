package com.capg.onlineshopping.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.capg.onlineshopping.common.ApiResponse;
import com.capg.onlineshopping.dto.checkout.CheckOutItemDto;
import com.capg.onlineshopping.dto.checkout.StripeResponse;
import com.capg.onlineshopping.service.AuthenticationTokenService;
import com.capg.onlineshopping.service.OrderService;
import com.stripe.exception.StripeException;
import com.stripe.model.checkout.Session;

@RestController
@RequestMapping("/order")
public class OrderController {
	
	@Autowired
	private AuthenticationTokenService authenticationTokenService;
	
	@Autowired
	private OrderService orderService;
	
	@PostMapping("/create-checkout-session")
	public ResponseEntity<StripeResponse> checkoutList(@RequestBody List<CheckOutItemDto> checkOutItemDtoList )throws StripeException
	{
		Session session = orderService.createSession(checkOutItemDtoList);
		
		StripeResponse stripeResponse = new StripeResponse(session.getId());
		return new ResponseEntity<>(stripeResponse,HttpStatus.OK);
	}
	

	
	
	

}
