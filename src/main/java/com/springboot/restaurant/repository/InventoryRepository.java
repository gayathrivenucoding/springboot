package com.springboot.restaurant.repository;

import java.time.LocalDate;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import org.springframework.web.bind.annotation.RequestParam;
import com.springboot.restaurant.entity.InventoryEntity;


@Repository
public interface InventoryRepository extends JpaRepository<InventoryEntity, Integer> {
	
	    @Query("SELECT i FROM InventoryEntity i " +
	            "JOIN FETCH i.mealEntity " +
	            "JOIN FETCH i.tableEntity " +
	            "WHERE i.inventoryDate = :date " +
	            "AND i.mealEntity.mealType = :mealType " +
	            "AND i.availableSeats > 0")
	    List<InventoryEntity> findAvailableSlotsByCheckAvailability(@RequestParam("date") LocalDate date, @RequestParam("mealType") String mealType);

	    
	}

	