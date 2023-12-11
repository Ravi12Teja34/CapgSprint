package com.capg.onlineshopping.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.capg.onlineshopping.entity.User;
import com.capg.onlineshopping.entity.WishList;

@Repository
 public interface WishListRepository extends JpaRepository<WishList, Integer> {
    
	List<WishList> findAllByUserOrderByCreatedDateDesc(User user);
}
