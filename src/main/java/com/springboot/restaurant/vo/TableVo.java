package com.springboot.restaurant.vo;

public class TableVo {
	
	private String restaurantName;
	private String tableName;
	private int tableCapacity;

	public TableVo() {
	}
	public TableVo(String restaurantName, String tableName, int tableCapacity) {
		this.restaurantName = restaurantName;
		this.tableName = tableName;
		this.setTableCapacity(tableCapacity);
	}
	public String getRestaurantName() {
		return restaurantName;
	}
	public void setRestaurantName(String restaurantName) {
		this.restaurantName = restaurantName;
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
