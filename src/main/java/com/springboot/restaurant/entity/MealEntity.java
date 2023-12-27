package com.springboot.restaurant.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name = "meals_table")
public class MealEntity {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private int mealId;
	private String mealType;

	public MealEntity() {

	}

	public MealEntity(String mealType) {
		this.mealType = mealType;
	}

	public int getMealId() {
		return mealId;
	}

	public void setMealId(int mealId) {
		this.mealId = mealId;
	}

	public String getMealType() {
		return mealType;
	}

	public void setMealType(String mealType) {
		this.mealType = mealType;
	}

}
