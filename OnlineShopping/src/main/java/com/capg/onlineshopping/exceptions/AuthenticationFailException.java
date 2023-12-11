package com.capg.onlineshopping.exceptions;

public class AuthenticationFailException extends IllegalArgumentException {
	
	public AuthenticationFailException(String msg)
	{
		super(msg);
	}

}
