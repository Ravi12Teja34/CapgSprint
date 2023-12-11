package com.capg.onlineshopping.entity;

import java.util.Date;

import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;

@Entity
@Table(name="wishlist")
public class WishList {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;
	
	@OneToOne(targetEntity=User.class,fetch=FetchType.EAGER)
	@JoinColumn(name="user_id")
	private User user;
	
	private Date createdDate;
	
	@ManyToOne
	@JoinColumn(name="product_id")
	private Product product;

	public WishList(Integer id, User user, Date createdDate, Product product) {
		super();
		this.id = id;
		this.user = user;
		this.createdDate = createdDate;
		this.product = product;
	}

	public WishList() {
		super();
		// TODO Auto-generated constructor stub
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	public Date getCreatedDate() {
		return createdDate;
	}

	public void setCreatedDate(Date createdDate) {
		this.createdDate = createdDate;
	}

	public Product getProduct() {
		return product;
	}

	public void setProduct(Product product) {
		this.product = product;
	}

	public WishList(User user, Product product) {
		super();
		this.user = user;
		this.product = product;
		this.createdDate=new Date();
	}
	
	
	
	

}
