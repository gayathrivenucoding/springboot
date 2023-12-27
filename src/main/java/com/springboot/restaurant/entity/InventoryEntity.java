package com.springboot.restaurant.entity;

import java.time.LocalDate;

import jakarta.persistence.*;

@Entity
@Table(name = "inventorys_table")
public class InventoryEntity {
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private int inventoryId;
	@ManyToOne
    @JoinColumn(name = "table_id")
	private TableEntity tableEntity;
	private int availableSeats;
	@ManyToOne
    @JoinColumn(name = "meal_id")
	private MealEntity mealEntity;
	private LocalDate inventoryDate;

	public InventoryEntity() {

	}
	public InventoryEntity(TableEntity tableEntity, int availableSeats, MealEntity mealEntity, LocalDate inventoryDate) {
		this.tableEntity = tableEntity;
		this.availableSeats = availableSeats;
		this.mealEntity = mealEntity;
		this.inventoryDate = inventoryDate;
	}
	
	public int getInventoryId() {
		return inventoryId;
	}
	public void setInventoryId(int inventoryId) {
		this.inventoryId = inventoryId;
	}
	public TableEntity getTableDetails() {
		return tableEntity;
	}
	public void setTableDetails(TableEntity tableEntity) {
		this.tableEntity = tableEntity;
	}
	public int getAvailableSeats() {
		return availableSeats;
	}
	public void setAvailableSeats(int availableSeats) {
		this.availableSeats = availableSeats;
	}
	public MealEntity getMealDetails() {
		return mealEntity;
	}
	public void setMealDetails(MealEntity mealEntity) {
		this.mealEntity = mealEntity;
	}
	public LocalDate getInventoryDate() {
		return inventoryDate;
	}
	public void setInventoryDate(LocalDate inventoryDate) {
		this.inventoryDate = inventoryDate;
	}

}
