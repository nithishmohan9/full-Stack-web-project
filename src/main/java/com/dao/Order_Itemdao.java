package com.dao;

import java.util.List;

import com.daomodel.Order_item;
import com.daomodel.User;

public interface Order_Itemdao {

	void addOrder_item(Order_item order_item );
	void updateOrder_item( Order_item order_item);
	void deleteOrder_item( int order_item_id);
	Order_item getOrder_item(int  order_item_id);
	List< Order_item > getAllOrder_item();
}
