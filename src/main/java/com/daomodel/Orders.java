package com.daomodel;

import java.sql.Timestamp;

public class Orders {
	private  int order_id;
	private  int user_id;
	private  int restaurant_id;
	private  Timestamp order_date;
	private  int total_amount;
	private  String status;
	private  String payment_mode;
	private String address; 
	

	
	public Orders() {
	}
	

	public Orders(int order_id, int user_id, int restaurant_id, Timestamp order_date, int total_amount, String status,
			String payment_mode, String address) {
		super();
		this.order_id = order_id;
		this.user_id = user_id;
		this.restaurant_id = restaurant_id;
		this.order_date = order_date;
		this.total_amount = total_amount;
		this.status = status;
		this.payment_mode = payment_mode;
		this.address = address;
	}

	public int getorder_id() {
		return order_id;
	}


	public void setorder_id(int order_id) {
		this.order_id = order_id;
	}


	public int getuser_id() {
		return user_id;
	}


	public void setuser_id(int user_id) {
		this.user_id = user_id;
	}


	public int getrestaurant_id() {
		return restaurant_id;
	}


	public void setrestaurant_id(int restaurant_id) {
		this.restaurant_id = restaurant_id;
	}


	public Timestamp getorder_date() {
		return order_date;
	}


	public void setorder_date(Timestamp order_date) {
		this.order_date = order_date;
	}


	public int gettotal_amount() {
		return total_amount;
	}


	public void settotal_amount(int total_amount) {
		this.total_amount = total_amount;
	}


	public String getstatus() {
		return status;
	}


	public void setstatus(String status) {
		this.status = status;
	}


	public String getpayment_mode() {
		return payment_mode;
	}


	public void setpayment_mode(String payment_mode) {
		this.payment_mode = payment_mode;
	}
	
	public String getaddress() {
		return address;
	}

	public void setaddress(String address) {
		this.address = address;
	}

	@Override
	public String toString() {
		return "Orders [order_id=" + order_id + ", user_id=" + user_id + ", restaurant_id=" + restaurant_id
				+ ", order_date=" + order_date + ", total_amount=" + total_amount + ", status=" + status
				+ ", payment_mode=" + payment_mode + ", address=" + address + "]";
	}







	

	
	
	
	
	
	

}

