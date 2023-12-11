package com.capg.onlineshopping.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.capg.onlineshopping.dto.ResponseDto;
import com.capg.onlineshopping.dto.SignInResponseDto;
import com.capg.onlineshopping.dto.user.SignInDto;
import com.capg.onlineshopping.dto.user.SignUpDto;
import com.capg.onlineshopping.service.UserService;

@RestController
@RequestMapping("/user")
public class UserController {
	
	@Autowired
	UserService userService;
	
	@PostMapping("/signup")
	public ResponseDto signUp(@RequestBody SignUpDto signupDto)
	{
		return userService.signUp(signupDto);
	}
	
	@PostMapping("/signin")
	public SignInResponseDto signIn(@RequestBody SignInDto signInDto)
	{
		return userService.signIn(signInDto);
	}

}
