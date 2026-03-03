package com.daomodel;

public class Restaurant {
	private  	int restaurant_id;
	private  	String name;
	private  	String  address;
	private  	String  phone;
	private  	String  rating;
	private  	String cusine_type;
	private  	String  isActive;
	private 	String estimated_time_arival;
	private  int user_id;
	private  String  image_path;

	
	public Restaurant() {
		// TODO Auto-generated constructor stub
	}


	public int getrestaurant_id() {
		return restaurant_id;
	}


	public void setrestaurant_id(int restaurant_id) {
		this.restaurant_id = restaurant_id;
	}


	public String getname() {
		return name;
	}


	public void setName(String name) {
		this.name = name;
	}

	public String getaddress() {
		return address;
	}

	
	public void setaddress(String address) {
		this.address = address;
	}

	public String getphone() {
		return phone;
	}

	public void setphone(String phone) {
		this.phone = phone;
	}

	public String getrating() {
		return rating;
	}

	public void setrating(String rating) {
		this.rating = rating;
	}

	public String getcusine_type() {
		return cusine_type;
	}
	
	public void setcusine_type(String cusine_type) {
		this.cusine_type = cusine_type;
	}

	public String getisActive() {
		return isActive;
	}

	public void setisActive(String isActive) {
		this.isActive = isActive;
	}
	public String getestimated_time_arival() {
		return estimated_time_arival;
	}

	public void setestimated_time_arival(String estimated_time_arival) {
		this.estimated_time_arival = estimated_time_arival;
	}

	public int getuser_id() {
		return user_id;
	}

	public void setuser_id(int user_id) {
		this.user_id = user_id;
	}

	public String getimage_path() {
		return image_path;
	}


	public void setimage_path(String image_path) {
		this.image_path = image_path;
	}



	public Restaurant(int restaurant_id, String name, String address, String phone, String rating, String cusine_Type,
			String isActive, String estimated_time_arival, int user_id, String image_path) {
		super();
		this.	restaurant_id = restaurant_id;
		this.	name = name;
		this.	address = address;
		this.	phone = phone;
		this.	rating = rating;
		this.	cusine_type = cusine_Type;
		this.	isActive = isActive;
		this.estimated_time_arival = estimated_time_arival;
		this.user_id = user_id;
		this.image_path = image_path;
	}


	@Override
	public String toString() {
		return "Restaurant [Restaurant_ID=" + restaurant_id + ", Name=" + name + ", address=" + address + ", phone="
				+ phone + ", rating=" + rating + ", cusine_type=" + cusine_type + ", isActive=" + isActive
				+ ", estimated_time_arival=" + estimated_time_arival + ", user_id=" + user_id + ", image_path="
				+ image_path + "]";
	}
	
	
	

}

