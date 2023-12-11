package com.capg.onlineshopping.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.capg.onlineshopping.entity.AuthenticationToken;
import com.capg.onlineshopping.entity.User;

@Repository
public interface AuthenticationTokenRepository extends JpaRepository<AuthenticationToken, Integer> {

	AuthenticationToken findByToken(String token);
}
