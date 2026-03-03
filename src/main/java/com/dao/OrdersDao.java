package com.dao;

import java.util.List;

import com.daomodel.Orders;
import com.daomodel.User;

public interface OrdersDao {

	int addOrder(Orders order );
	void updateOrder(Orders order );
	void deleteOrder( int Order_id);
	Orders getOrder(int Order_id);
	List<Orders> getAllOrder();
	List<Orders> getOrdersByUserId(int user_id);

	
	

}
