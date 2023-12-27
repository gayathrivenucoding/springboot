package com.springboot.restaurant.entity;

import jakarta.persistence.*;

@Entity
@Table(name = "restaurants_table")
public class RestaurantEntity {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private int restaurantId;
	private String restaurantName;
	
	public RestaurantEntity() {
		
	}
	public RestaurantEntity(String restaurantName) {
		this.restaurantName = restaurantName;
	}
	public int getRestaurantId() {
		return restaurantId;
	}
	public void setRestaurantId(int restaurantId) {
		this.restaurantId = restaurantId;
	}
	public String getRestaurantName() {
		return restaurantName;
	}
	public void setRestaurantName(String restaurantName) {
		this.restaurantName = restaurantName;
	}
	
	
	
}
