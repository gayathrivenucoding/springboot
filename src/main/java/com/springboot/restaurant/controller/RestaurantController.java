package com.springboot.restaurant.controller;
import java.time.LocalDate;
import java.util.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import com.springboot.restaurant.entity.*;
import com.springboot.restaurant.service.RestaurantService;
import com.springboot.restaurant.vo.*;

@RestController
@RequestMapping("/restaurant")
public class RestaurantController {

	@Autowired
	private RestaurantService restaurantService;

	public RestaurantController(RestaurantService restaurantService) {
		this.restaurantService = restaurantService;
	}

//Meal

	@PostMapping("/meal/add")
	public String addMeal(@RequestBody MealEntity mealEntity) {
		return restaurantService.addMeal(mealEntity);
	}

	@DeleteMapping("/meal/delete/{mealId}")
	public String deleteMeal(@PathVariable int mealId) {
		return restaurantService.deleteMeal(mealId);
	}

	@GetMapping("/meal/list")
	public List<MealEntity> getAllMeals() {
		return restaurantService.getAllMeals();
	}
//Restaurant

	@PostMapping("/restaurant/add")
	public String addRestaurant(@RequestBody RestaurantEntity restaurantEntity) {
		return restaurantService.addResturant(restaurantEntity);
	}

	@DeleteMapping("/restaurant/delete/{restaurantId}")
	public String deleteRestaurant(@PathVariable int restaurantId) {
		return restaurantService.deleteRestaurant(restaurantId);
	}

	@GetMapping("/restaurant/list")
	public List<RestaurantEntity> getAllRestaurants() {
		return restaurantService.getAllRestaurants();
	}

//Table

	@PostMapping("/table/add")
	public String addTable(@RequestBody TableVo tableVo) {
		return restaurantService.addTable(tableVo);
	}

	@DeleteMapping("/table/delete/{tableId}")
	public String deleteTable(@PathVariable int tableId) {
		return restaurantService.deleteTable(tableId);
	}

	@GetMapping("/table/list")
	public List<TableEntity> getAllTables() {
		return restaurantService.getAllTables();
	}

//Inventory

	@PostMapping("/inventory/add")
	public String addInventory(@RequestBody InventoryVo inventoryVo) {
		return restaurantService.addInventory(inventoryVo);
	}

	@GetMapping("/inventory/list")
	public List<InventoryEntity> viewInventory() {
		return restaurantService.getAllInventories();
	}

//Availability

	@GetMapping("/booking/availabilities")
	public List<AvailableSlotVo> getAvailableSlots(@RequestParam LocalDate date, @RequestParam String mealType) {
		return restaurantService.getAvailableSlots(date, mealType);
	}

//	@GetMapping("/booking/availabilities")
//	public List<AvailableSlotVo> availabilities(@RequestHeader("userId") int userId,
//			@RequestBody CheckAvailabilityVo checkAvailabilityVo) {
//		return restaurantService.availableSlots(checkAvailabilityVo);
//	}

//Booking

	@PostMapping("/booking/add")
	public String addBooking(@RequestHeader("userId") int userId, @RequestBody BookingVo bookingVo) {
		return restaurantService.addBooking(userId, bookingVo);
	}

	@PutMapping("/booking/edit/addPerson")
	public String addPersonInBooking(@RequestHeader("userId") int userId, @RequestBody EditBookingVo editBookingVo) {
		return restaurantService.addPersonToBooking(userId, editBookingVo);
	}

	@DeleteMapping("/booking/cancel/{bookingId}")
	public String cancelBooking(@RequestHeader("userId") int userId, @PathVariable int bookingId) {
		return restaurantService.cancelBooking(userId, bookingId);
	}

	@GetMapping("/booking/list")
	public Map<String, List<BookingEntity>> myBookings(@RequestHeader("userId") int userId) {
		return restaurantService.Bookings(userId);
	}
}
