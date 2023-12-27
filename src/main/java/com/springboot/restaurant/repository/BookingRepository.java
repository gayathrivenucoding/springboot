package com.springboot.restaurant.repository;


import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import com.springboot.restaurant.entity.BookingEntity;

@Repository
public interface BookingRepository extends JpaRepository<BookingEntity, Integer>{
	
	@Query("SELECT b FROM BookingEntity b WHERE b.userEntity.userId = :userId")
    List<BookingEntity> findBookingbyUserId(@Param("userId") int userId);

}
