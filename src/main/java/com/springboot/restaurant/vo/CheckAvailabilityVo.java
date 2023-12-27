package com.springboot.restaurant.vo;

import java.time.LocalDate;

public class CheckAvailabilityVo {
	
	private LocalDate date;
	private String mealType;
	
	public CheckAvailabilityVo() {
		
	}
	public CheckAvailabilityVo(LocalDate date, String mealType) {
		this.date = date;
		this.mealType = mealType;
	}
	
	public LocalDate getDate() {
		return date;
	}
	public void setDate(LocalDate date) {
		this.date = date;
	}
	public String getMealType() {
		return mealType;
	}
	public void setMealType(String mealType) {
		this.mealType = mealType;
	}
	
}
