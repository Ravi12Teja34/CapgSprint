package com.capg.onlineshopping.service;

import java.util.Objects;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.capg.onlineshopping.entity.AuthenticationToken;
import com.capg.onlineshopping.entity.User;
import com.capg.onlineshopping.exceptions.AuthenticationFailException;
import com.capg.onlineshopping.repository.AuthenticationTokenRepository;

@Service
public class AuthenticationTokenService {
	
	@Autowired
	AuthenticationTokenRepository authenticationTokenRepository;

	public void saveConfirmationToken(AuthenticationToken authenticationToken) {
        
        authenticationTokenRepository.save(authenticationToken);
    }

	public User getUser(String token)
	{
		final AuthenticationToken authenticationToken=authenticationTokenRepository.findByToken(token);
		if(Objects.isNull(authenticationToken))
		{
			return null;
		}
        return authenticationToken.getUser();
	}
	
	public void authenticate(String token) throws AuthenticationFailException
	{
		if(Objects.isNull(token))
		{
			throw new AuthenticationFailException("token not present");
		}
		if(Objects.isNull(getUser(token)))
		{
			throw new AuthenticationFailException("token not valid");
		}
	}
}
