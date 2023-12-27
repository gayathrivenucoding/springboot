package com.springboot.restaurant.vo;

import java.time.LocalDate;

import com.springboot.restaurant.entity.MealEntity;
import com.springboot.restaurant.entity.TableEntity;


public class AvailableSlotVo {

	private int inventoryId;
	private LocalDate inventoryDate;
	private MealEntity mealEntity;
	private TableEntity tableEntity;
	private int availableSeats;

	public AvailableSlotVo() {

	}

	public AvailableSlotVo(int inventoryId, LocalDate inventoryDate, MealEntity mealEntity, TableEntity tableEntity ,String tableName,
			int availableSeats) {
		this.inventoryId = inventoryId;
		this.inventoryDate = inventoryDate;
		this.tableEntity = tableEntity;
		this.mealEntity = mealEntity;
		this.inventoryDate = inventoryDate;
		this.availableSeats = availableSeats;
	}

	public int getInventoryId() {
		return inventoryId;
	}

	public void setInventoryId(int inventoryId) {
		this.inventoryId = inventoryId;
	}

	public MealEntity getMealDetails() {
		return mealEntity;
	}
	public void setMealDetails(MealEntity mealEntity) {
		this.mealEntity = mealEntity;
	}
	public int getAvailableSeats() {
		return availableSeats;
	}

	public void setAvailableSeats(int availableSeats) {
		this.availableSeats = availableSeats;
	}
	public TableEntity getTableDetails() {
		return tableEntity;
	}
	public void setTableDetails(TableEntity tableEntity) {
		this.tableEntity = tableEntity;
	}

	public LocalDate getInventoryDate() {
		return inventoryDate;
	}

	public void setInventoryDate(LocalDate inventoryDate) {
		this.inventoryDate = inventoryDate;
	}
}
