package com.springboot.restaurant.entity;

import jakarta.persistence.*;

@Entity
@Table(name = "users_table")
public class UserEntity {
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private int userId;
	private String userRole;
	private String userName;
	
	public UserEntity() {
	
	}

	public UserEntity(String userRole, String userName) {
		
		this.userRole = userRole;
		this.userName = userName;
	}

	public int getUserId() {
		return userId;
	}

	public void setUserId(int userId) {
		this.userId = userId;
	}

	public String getUserRole() {
		return userRole;
	}

	public void setUserRole(String userRole) {
		this.userRole = userRole;
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	@Override
	public String toString() {
		return "UserEntity [userId=" + userId + ", userRole=" + userRole + ", userName=" + userName + "]";
	}
	
}
