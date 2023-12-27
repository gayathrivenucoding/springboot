package com.springboot.restaurant.vo;

public class EditBookingVo {
	
	private int bookingId;
	private int addPerson;
	
	public EditBookingVo() {

	}
	public EditBookingVo(int bookingId, int addPerson) {
		this.bookingId = bookingId;
		this.addPerson = addPerson;
	}

	public int getBookingId() {
		return bookingId;
	}

	public void setBookingId(int bookingId) {
		this.bookingId = bookingId;
	}

	public int getAddPerson() {
		return addPerson;
	}

	public void setAddPerson(int addPerson) {
		this.addPerson = addPerson;
	}
	
	
	
	
	
}
