/*package com.capg.onlineshopping;

import java.net.Proxy;

import com.google.gson.ExclusionStrategy;
import com.google.gson.FieldAttributes;

public class ProxyExclusionStrategy implements ExclusionStrategy  {
	
	@Override
    public boolean shouldSkipField(FieldAttributes f) {
        return f.getDeclaringClass() == Proxy.class && f.getName().equals("type");
    }

    @Override
    public boolean shouldSkipClass(Class<?> clazz) {
        return false;
    }

}*/
