package com.springboot.restaurant.repository;

import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import com.springboot.restaurant.entity.TableEntity;

@Repository
public interface TableRepository extends JpaRepository<TableEntity, Integer> {

	@Query("SELECT t FROM TableEntity t WHERE t.restaurantEntity.restaurantId = :restaurantId")
	List<TableEntity> findByRestaurantId(@Param("restaurantId") int restaurantId);

}
