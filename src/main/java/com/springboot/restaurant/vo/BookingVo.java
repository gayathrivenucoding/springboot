package com.springboot.restaurant.vo;

public class BookingVo {

	private int inventoryId;
	private int noOfPerson;
	private long phoneNumber;

	public BookingVo() {

	}

	public BookingVo(int inventoryId, int noOfPerson, long phoneNumber) {
		this.inventoryId = inventoryId;
		this.noOfPerson = noOfPerson;
		this.phoneNumber = phoneNumber;
	}

	public int getInventoryId() {
		return inventoryId;
	}
	public void setInventoryId(int inventoryId) {
		this.inventoryId = inventoryId;
	}
	public int getNoOfPerson() {
		return noOfPerson;
	}
	public void setNoOfPerson(int noOfPerson) {
		this.noOfPerson = noOfPerson;
	}
	public long getPhoneNumber() {
		return phoneNumber;
	}
	public void setPhoneNumber(long phoneNumber) {
		this.phoneNumber = phoneNumber;
	}
}
