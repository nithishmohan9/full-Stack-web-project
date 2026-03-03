package com.daomodel;

public class Order_item {
	

	private	int order_item_id;
	private	int  order_id; 
	private 	int  menu_id;
	private int  quantity;
	private int total_price;
	

	public Order_item() {
		// TODO Auto-generated constructor stub
	}



	public Order_item( int order_id, int menu_id, int quantity, int total_price) {
		
		this.order_id = order_id;
		this.menu_id = menu_id;
		this.quantity = quantity;
		this.total_price = total_price;
	}



	public int getOrder_item_id() {
		return order_item_id;
	}



	public void setOrder_item_id(int order_item_id) {
		this.order_item_id = order_item_id;
	}



	public int getOrder_id() {
		return order_id;
	}



	public void setOrder_id(int order_id) {
		this.order_id = order_id;
	}



	public int getMenu_id() {
		return menu_id;
	}



	public void setMenu_id(int menu_id) {
		this.menu_id = menu_id;
	}



	public int getQuantity() {
		return quantity;
	}



	public void setQuantity(int quantity) {
		this.quantity = quantity;
	}



	public int getTotal_price() {
		return total_price;
	}



	public void setTotal_price(int total_price) {
		this.total_price = total_price;
	}



	@Override
	public String toString() {
		return "Order_item [order_item_id=" + order_item_id + ", order_id=" + order_id + ", menu_id=" + menu_id
				+ ", quantity=" + quantity + ", total_price=" + total_price + "]";
	}
	
	
	
	

}

