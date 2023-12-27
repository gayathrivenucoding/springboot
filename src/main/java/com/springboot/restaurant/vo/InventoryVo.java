package com.springboot.restaurant.vo;

import java.time.LocalDate;

public class InventoryVo {
	
	private LocalDate startingDate;
	private LocalDate endingDate;
	
	public InventoryVo() {
		
	}
	public InventoryVo(LocalDate startingDate, LocalDate endingDate) {
		this.startingDate = startingDate;
		this.endingDate = endingDate;
	}
	
	public LocalDate getStartingDate() {
		return startingDate;
	}

	public void setStartingDate(LocalDate startingDate) {
		this.startingDate = startingDate;
	}

	public LocalDate getEndingDate() {
		return endingDate;
	}

	public void setEndingDate(LocalDate endingDate) {
		this.endingDate = endingDate;
	}
	
	
}




