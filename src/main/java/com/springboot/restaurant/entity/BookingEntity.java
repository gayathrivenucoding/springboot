package com.springboot.restaurant.entity;

import java.time.LocalDate;

import com.fasterxml.jackson.annotation.JsonIgnore;

import jakarta.persistence.*;

@Entity
@Table(name = "bookings_table")
public class BookingEntity {
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private int bookingId;
	@JsonIgnore
	@ManyToOne
	@JoinColumn(name = "user_id")
	private UserEntity userEntity;
	@JsonIgnore
	private long phoneNumber;
	private String restaurantName;
	private String mealType;
	private String tableName;
	private int noOfPerson;
	@JsonIgnore
	@ManyToOne
	@JoinColumn(name = "inventory_id")
	private InventoryEntity inventoryEntity;
	private LocalDate reservationDate;
	@JsonIgnore
	private String bookingStatus;
	
	public BookingEntity() {
		
	}
	public BookingEntity(UserEntity userEntity, long phoneNumber, String restaurantName, String mealType,
			int noOfPerson, InventoryEntity inventoryEntity, LocalDate reservationDate, String bookingStatus) {
		
		this.userEntity = userEntity;
		this.phoneNumber = phoneNumber;
		this.restaurantName = restaurantName;
		this.mealType = mealType;
		this.noOfPerson = noOfPerson;
		this.inventoryEntity = inventoryEntity;
		this.reservationDate = reservationDate;
		this.bookingStatus = bookingStatus;
	}
	public int getBookingId() {
		return bookingId;
	}
	public void setBookingId(int bookingId) {
		this.bookingId = bookingId;
	}
	public UserEntity getUserDetails() {
		return userEntity;
	}
	public void setUserDetails(UserEntity userEntity) {
		this.userEntity = userEntity;
	}
	public long getPhoneNumber() {
		return phoneNumber;
	}
	public void setPhoneNumber(long phoneNumber) {
		this.phoneNumber = phoneNumber;
	}
	public String getRestaurantName() {
		return restaurantName;
	}
	public void setRestaurantName(String restaurantName) {
		this.restaurantName = restaurantName;
	}
	public String getMealType() {
		return mealType;
	}
	public void setMealType(String mealType) {
		this.mealType = mealType;
	}
	public String getTableName() {
		return tableName;
	}
	public void setTableName(String tableName) {
		this.tableName = tableName;
	}
	public int getNoOfPerson() {
		return noOfPerson;
	}
	public void setNoOfPerson(int noOfPerson) {
		this.noOfPerson = noOfPerson;
	}
	public InventoryEntity getInventoryDetails() {
		return inventoryEntity;
	}
	public void setInventoryDetails(InventoryEntity inventoryEntity) {
		this.inventoryEntity = inventoryEntity;
	}
	public LocalDate getReservationDate() {
		return reservationDate;
	}
	public void setReservationDate(LocalDate reservationDate) {
		this.reservationDate = reservationDate;
	}
	public String getBookingStatus() {
		return bookingStatus;
	}
	public void setBookingStatus(String bookingStatus) {
		this.bookingStatus = bookingStatus;
	}
	
}
