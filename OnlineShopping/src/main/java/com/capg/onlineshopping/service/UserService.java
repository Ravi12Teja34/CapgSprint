package com.capg.onlineshopping.service;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Objects;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.jackson.JacksonProperties.Datatype;
import org.springframework.stereotype.Service;

import com.capg.onlineshopping.dto.ResponseDto;
import com.capg.onlineshopping.dto.SignInResponseDto;
import com.capg.onlineshopping.dto.user.SignInDto;
import com.capg.onlineshopping.dto.user.SignUpDto;
import com.capg.onlineshopping.entity.AuthenticationToken;
import com.capg.onlineshopping.entity.User;
import com.capg.onlineshopping.exceptions.AuthenticationFailException;
import com.capg.onlineshopping.exceptions.CustomException;
import com.capg.onlineshopping.repository.UserRepository;

import jakarta.transaction.Transactional;

@Service
public class UserService {
	

	@Autowired
	UserRepository userRepository;
	
	@Autowired
	AuthenticationTokenService authenticationTokenService;

	

	@Transactional
	public ResponseDto signUp(SignUpDto signupDto) {
		
		if(Objects.nonNull(userRepository.findByEmail(signupDto.getEmail())))
		{
			throw new CustomException("User already present");
		}
		
		
		/*String encryptedpassword=signupDto.getPassword();
		try {
			encryptedpassword=hashPassword(signupDto.getPassword());
		}
		catch(NoSuchAlgorithmException e)
		{
			e.printStackTrace();
		}*/
		
		
		
		User user=new User(signupDto.getFirstName(),signupDto.getLastName(),signupDto.getEmail(),signupDto.getPassword());
		userRepository.save(user);
		
		AuthenticationToken authenticationToken = new AuthenticationToken(user);
		authenticationTokenService.saveConfirmationToken(authenticationToken);
		
		
		
		ResponseDto responseDto = new ResponseDto("success","sign up done successfully");
		return responseDto;
	}



	public SignInResponseDto signIn(SignInDto signInDto) {
		User user=userRepository.findByEmail(signInDto.getEmail());
		if(Objects.isNull(user))
		{
			throw new AuthenticationFailException("User is not valid");
		}
		
			if(!user.getPassword().equals(signInDto.getPassword()))
			{
				throw new AuthenticationFailException("Incorrect password");
			}
		return new SignInResponseDto("success","sign in successfully");
		
		
	
	}

	/*private String hashPassword(String password) throws NoSuchAlgorithmException {
		MessageDigest md=MessageDigest.getInstance("MD5");
		md.update(password.getBytes());
		byte[] digest = md.digest();
		String hash=DatatypeConverter
				.printHexBinary(digest).toUpperCase();
		return hash;
	}*/

}
