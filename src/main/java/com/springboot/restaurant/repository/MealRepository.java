package com.springboot.restaurant.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import com.springboot.restaurant.entity.MealEntity;

@Repository
public interface MealRepository extends JpaRepository<MealEntity, Integer> {

	MealEntity findByMealType(String mealType);

}
