package com.dao;

import com.daomodel.cart_item;

public interface cartDao {
	
	
	 void addToCart(cart_item cartItem);
	 void updateTheCart(int menu_id,int quantity);
	 void  deleteTheCart(int menu_id);
	 

}
