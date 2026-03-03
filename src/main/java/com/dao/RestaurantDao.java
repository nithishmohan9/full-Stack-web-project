package com.dao;

import java.util.List;

import com.daomodel.Restaurant;
import com.daomodel.User;

public interface RestaurantDao {
	void addRestaurant(Restaurant restaurant);
	void updateRestaurant(Restaurant restaurant);
	void deleteRestaurant( int Restaurant_ID);
	Restaurant  getRestaurant(int Restaurant_ID );
	List<Restaurant > getAllRestaurant();
}
