package com.springboot.restaurant.service;
import org.modelmapper.ModelMapper;
import java.time.LocalDate;
import java.util.*;
import java.util.stream.Collectors;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import com.springboot.restaurant.entity.*;
import com.springboot.restaurant.exception.*;
import com.springboot.restaurant.repository.*;
import com.springboot.restaurant.vo.*;

@Service
public class RestaurantService {
	
	@Autowired
	private InventoryRepository inventoryRepository;
	@Autowired
	private BookingRepository bookingRepository;
	@Autowired
	private RestaurantRepository restaurantRepository;
	@Autowired
	private MealRepository mealRepository;
	@Autowired
	private TableRepository tableRepository;
	@Autowired
	private UserRepository userRepository;
	private final ModelMapper modelMapper;
	

	public RestaurantService(UserRepository userRepository, InventoryRepository inventoryRepository,
			BookingRepository bookingRepository, RestaurantRepository restaurantRepository,
			MealRepository mealRepository, TableRepository tableRepository,ModelMapper modelMapper) {

		this.inventoryRepository = inventoryRepository;
		this.bookingRepository = bookingRepository;
		this.restaurantRepository = restaurantRepository;
		this.mealRepository = mealRepository;
		this.tableRepository = tableRepository;
		this.userRepository = userRepository;
		this.modelMapper = modelMapper;
		}
	
// Meal
	
	public String addMeal(MealEntity mealEntity) {
		MealEntity existingMeal = mealRepository.findByMealType(mealEntity.getMealType());
		if (existingMeal == null) {
            mealRepository.save(mealEntity);
            return "Meal added successfully";
            } else {
            	throw new BadRequestException("MealType already exists", HttpStatus.BAD_REQUEST.value());
            	}
		}
	
	public String deleteMeal(int mealId) {
		mealRepository.deleteById(mealId);
		return "Meal successfully deleted";
		}

	public List<MealEntity> getAllMeals() {
		return mealRepository.findAll();
		}
	
//Restaurant

	public String addResturant(RestaurantEntity restaurantEntity) {
		RestaurantEntity existingRestaurant = restaurantRepository.findByRestaurantName(restaurantEntity.getRestaurantName());
		if (existingRestaurant == null) {
			restaurantRepository.save(restaurantEntity);
			return "Restaurant added successfully";
			} else {
				throw new BadRequestException("Restaurant already exists", HttpStatus.BAD_REQUEST.value());
				}
		}

	public String deleteRestaurant(int restaurantId) {
		restaurantRepository.deleteById(restaurantId);
		return "Restaurant successfully deleted";
		}

	public List<RestaurantEntity> getAllRestaurants() {
		return restaurantRepository.findAll();
		}

//Table 

	public String addTable(TableVo tableVo) {
		RestaurantEntity restaurantEntity = restaurantRepository.findByRestaurantName(tableVo.getRestaurantName());
		if (restaurantEntity == null) {
            throw new BadRequestException("Invalid restaurant name", HttpStatus.BAD_REQUEST.value());
        }
		TableEntity tableEntity = new TableEntity();
		tableEntity.setRestaurantDetails(restaurantEntity);
		tableEntity.setTableName(tableVo.getTableName());
		tableEntity.setTableCapacity(tableVo.getTableCapacity());
		tableRepository.save(tableEntity);
		return "Table added succesfully";
	}

	public String deleteTable(int tableId) {
		tableRepository.deleteById(tableId);
		return "Table deleted successfully";
	}

	public List<TableEntity> getAllTables() {
		return tableRepository.findAll();
	}

//Inventory 

	public String addInventory(InventoryVo inventoryVo) {
		if (inventoryVo.getStartingDate().isBefore(LocalDate.now())||inventoryVo.getEndingDate().isBefore(LocalDate.now())) {
		    throw new BadRequestException("Date cannot be in the past", HttpStatus.BAD_REQUEST.value());
		} else {
			final LocalDate startDate = inventoryVo.getStartingDate();
		    final LocalDate endDate = inventoryVo.getEndingDate();
		    List<MealEntity> meals = mealRepository.findAll();
		    List<TableEntity> tables = tableRepository.findAll();
		    List<InventoryEntity> inventories = inventoryRepository.findAll();
		    List<LocalDate> inventoryDates = inventories.stream().map(InventoryEntity::getInventoryDate).collect(Collectors.toList());
		    
		    if (inventoryDates.contains(startDate) || inventoryDates.contains(endDate)) {
	            throw new BadRequestException("Date already exists", HttpStatus.BAD_REQUEST.value());
	        } else {
	        	for (LocalDate inventoryDate = startDate; inventoryDate.isBefore(endDate.plusDays(1)); inventoryDate = inventoryDate.plusDays(1)) {
	        		for (TableEntity tableEntity : tables) {
	        			for (MealEntity mealEntity : meals) {
	        				
	        				InventoryEntity inventoryEntity = new InventoryEntity();
	        				inventoryEntity.setInventoryDate(inventoryDate);
						    inventoryEntity.setTableDetails(tableEntity);
						    inventoryEntity.setAvailableSeats(tableEntity.getTableCapacity());
						    inventoryEntity.setMealDetails(mealEntity);
						    inventoryRepository.save(inventoryEntity);
						    }
	        			}
	        		}
	        	return "Inventory added successfully";
	        	}
		}
		}

	public List<InventoryEntity> getAllInventories() {
		return inventoryRepository.findAll();
		}

//Availability
	
	public List<AvailableSlotVo> getAvailableSlots(LocalDate date,String mealType) {
        List<InventoryEntity> availabilities = inventoryRepository.findAvailableSlotsByCheckAvailability(date,mealType);
        // Map entities to VOs using ModelMapper
        return availabilities.stream().map(entity -> modelMapper.map(entity, AvailableSlotVo.class)).collect(Collectors.toList());
        }



        
//	public List<AvailableSlotVo> getAvailableSlots(CheckAvailabilityVo checkAvailabilityVo) {
//
//		final LocalDate bookingDate = checkAvailabilityVo.getInventoryDate();
//		MealEntity mealEntity = mealRepository.findByMealType(checkAvailabilityVo.getMealType());
//		int mealId = mealEntity.getMealId();
//
//		List<InventoryEntity> inventories = inventoryRepository.findAll();
//		
//		List<AvailableSlotVo> availableSlots = new ArrayList<>();
//
//		for (InventoryEntity inventoryEntity : inventories) {
//
//			AvailableSlotVo availableSlot = new AvailableSlotVo();
//
//			if (inventoryEntity.getInventoryDate().equals(bookingDate)
//					&& inventoryEntity.getMealDetails().getMealId() == mealId
//					&& inventoryEntity.getAvailableSeats() > 0) {
//
//				availableSlot.setRestaurantName(
//						inventoryEntity.getTableDetails().getRestaurantDetails().getRestaurantName());
//
//				TableEntity table = tableRepository.findById(inventoryEntity.getTableDetails().getTableId()).get();
//
//				availableSlot.setInventoryId(inventoryEntity.getInventoryId());
//				availableSlot.setInventoryDate(bookingDate);
//				availableSlot.setMealType(mealEntity.getMealType());
//				availableSlot.setTableName(table.getTableName());
//				availableSlot.setAvailableSeats(inventoryEntity.getAvailableSeats());
//
//				availabilities.add(availableSlot);
//			}
//		}
//		return availabilities;
//	}

//Booking

	public String addBooking(int userId, BookingVo bookingVo) {
		if (userId <= 0 || !userRepository.existsById(userId)) {
	        throw new UnauthorizedException("Invalid user ID", HttpStatus.UNAUTHORIZED.value());
	    }
		InventoryEntity inventoryEntity = inventoryRepository.findById(bookingVo.getInventoryId()).get();
		if (inventoryEntity == null) {
	        throw new BadRequestException("Invalid inventory ID", HttpStatus.BAD_REQUEST.value());
	        }
		BookingEntity bookingEntity = new BookingEntity();
		if (bookingVo.getNoOfPerson() > inventoryEntity.getAvailableSeats()) {
	            throw new BadRequestException("Seats are not available for this count", HttpStatus.BAD_REQUEST.value());
	            }else {
	            	bookingEntity.setUserDetails(userRepository.findById(userId).get());
			        bookingEntity.setInventoryDetails(inventoryEntity);
			        bookingEntity.setPhoneNumber(bookingVo.getPhoneNumber());
			        bookingEntity.setRestaurantName(inventoryEntity.getTableDetails().getRestaurantDetails().getRestaurantName());
			        bookingEntity.setMealType(inventoryEntity.getMealDetails().getMealType());
			        bookingEntity.setTableName(inventoryEntity.getTableDetails().getTableName());
			        bookingEntity.setNoOfPerson(bookingVo.getNoOfPerson());
			        bookingEntity.setReservationDate(inventoryEntity.getInventoryDate());
			        bookingEntity.setBookingStatus("Booked");
			        bookingRepository.save(bookingEntity);
			        inventoryEntity.setAvailableSeats(inventoryEntity.getAvailableSeats() - bookingVo.getNoOfPerson());
		         	inventoryRepository.save(inventoryEntity);
		         	return "Booking Successful";
		         	}
		}
	
	public String addPersonToBooking(int userId,EditBookingVo editBookingVo) {
		 if (userId <= 0 || !userRepository.existsById(userId)) {
		        throw new UnauthorizedException("Invalid user ID", HttpStatus.UNAUTHORIZED.value());
		        }
		BookingEntity booked = bookingRepository.findById(editBookingVo.getBookingId()).orElseThrow(() -> new BadRequestException("Booking not found", HttpStatus.NOT_FOUND.value()));
        InventoryEntity pastInventory = inventoryRepository.findById(booked.getInventoryDetails().getInventoryId()).orElseThrow(() -> new BadRequestException("Inventory not found", HttpStatus.NOT_FOUND.value()));
        int seatsToAdd = editBookingVo.getAddPerson();
        if (pastInventory.getAvailableSeats() >= seatsToAdd) {
        	pastInventory.setAvailableSeats(pastInventory.getAvailableSeats() - seatsToAdd);
			booked.setNoOfPerson(booked.getNoOfPerson() + seatsToAdd);
            bookingRepository.save(booked);
			inventoryRepository.save(pastInventory);
            return "Booking updated successfully";
            } else {
            	throw new BadRequestException("Seats not available", HttpStatus.BAD_REQUEST.value());
            	}
        }

	public String cancelBooking(int userId, int bookingId) {
		if (userId <= 0 || !userRepository.existsById(userId)) {
	        throw new UnauthorizedException("Invalid user ID", HttpStatus.UNAUTHORIZED.value());
	    }
		List<BookingEntity> bookings = bookingRepository.findBookingbyUserId(userId);
		BookingEntity bookingEntity = bookingRepository.findById(bookingId).get();
	    if (bookingEntity.getUserDetails().getUserId()!= userId) {
	        throw new BadRequestException("Booking doesn't belong to the specified user", HttpStatus.BAD_REQUEST.value());
	    }
		if (bookings.isEmpty()) {
			throw new BadRequestException("User has no booking", HttpStatus.BAD_REQUEST.value());
			}
		if (bookingEntity == null || "Cancelled".equals(bookingEntity.getBookingStatus())) {
			throw new BadRequestException("Booking doesn't exist", HttpStatus.BAD_REQUEST.value());
			}else {
				bookingEntity.setBookingStatus("Cancelled");
			    bookingRepository.save(bookingEntity);
			    InventoryEntity inventoryEntity = inventoryRepository.findById(bookingEntity.getInventoryDetails().getInventoryId()).get();
			    inventoryEntity.setAvailableSeats(inventoryEntity.getAvailableSeats() + bookingEntity.getNoOfPerson());
			    inventoryRepository.save(inventoryEntity);
			    return "Booking cancelled successfully";
			    }
		}
	
		public Map<String, List<BookingEntity>> Bookings(int userId) {
			if (userId <= 0 || !userRepository.existsById(userId)) {
		        throw new UnauthorizedException("Invalid user ID", HttpStatus.UNAUTHORIZED.value());
		        }
		    Map<String, List<BookingEntity>> bookingStatus = new HashMap<>();
		    List<BookingEntity> bookings = bookingRepository.findBookingbyUserId(userId);
		    List<BookingEntity> pastBookings = new ArrayList<>();
		    List<BookingEntity> upcomingBookings = new ArrayList<>();
		    List<BookingEntity> cancelledBookings = new ArrayList<>();
		    for (BookingEntity bookingEntity : bookings) {
		        if (bookingEntity.getReservationDate().isAfter(LocalDate.now()) && !"Cancelled".equals(bookingEntity.getBookingStatus())) {
		            upcomingBookings.add(bookingEntity);
		        } else if (bookingEntity.getReservationDate().isBefore(LocalDate.now()) && !"Cancelled".equals(bookingEntity.getBookingStatus())) {
		            pastBookings.add(bookingEntity);
		        } else {
		            cancelledBookings.add(bookingEntity);
		        }
		    }
		    bookingStatus.put("Upcoming Bookings", upcomingBookings);
		    bookingStatus.put("Past Bookings", pastBookings);
		    bookingStatus.put("Cancelled Bookings", cancelledBookings);
		    return bookingStatus;
		}
}
