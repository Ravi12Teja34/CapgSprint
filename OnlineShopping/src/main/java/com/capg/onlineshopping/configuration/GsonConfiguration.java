package com.capg.onlineshopping.configuration;

import java.net.Proxy;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.capg.onlineshopping.CustomProxyTypeAdapter;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

@Configuration
public class GsonConfiguration {
	
    @Bean
    public Gson gson() {
    	return new GsonBuilder()
    	        .registerTypeAdapter(Proxy.class, new CustomProxyTypeAdapter())
    	        .create();
    }
    
    

}
