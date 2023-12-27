package com.springboot.restaurant.entity;

import jakarta.persistence.*;

@Entity
@Table(name = "tables_table")
public class TableEntity {
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private int tableId;
	@ManyToOne
    @JoinColumn(name = "restaurant_id")
	private RestaurantEntity restaurantEntity;
	private String tableName;
	private int tableCapacity;	
	
	public TableEntity() {
		
	}

	public TableEntity(RestaurantEntity restaurantEntity, String tableName, int tableCapacity) {
		this.restaurantEntity = restaurantEntity;
		this.tableName = tableName;
		this.tableCapacity = tableCapacity;
	}

	public int getTableId() {
		return tableId;
	}

	public void setTableId(int tableId) {
		this.tableId = tableId;
	}

	public RestaurantEntity getRestaurantDetails() {
		return restaurantEntity;
	}

	public void setRestaurantDetails(RestaurantEntity restaurantEntity) {
		this.restaurantEntity = restaurantEntity;
	}

	public String getTableName() {
		return tableName;
	}

	public void setTableName(String tableName) {
		this.tableName = tableName;
	}

	public int getTableCapacity() {
		return tableCapacity;
	}

	public void setTableCapacity(int tableCapacity) {
		this.tableCapacity = tableCapacity;
	}

}
