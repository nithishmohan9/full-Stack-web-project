package com.daomodel;

public class Menu {


	private  	int menu_id;
	private  	int 	restaurant_id; 
	private  String item_name;
	private 	String  description;
	private 	int  price;
	private  	String  ratings;
	private  	String  isAvailable; 
	private  String imagePath;


	public Menu() {
		// TODO Auto-generated constructor stub
	}


	public Menu(int menu_id, int restaurant_id, String item_name, String description, int price, String ratings,
			String isAvailable, String imagePath) {

		this.menu_id = menu_id;
		this.restaurant_id = restaurant_id;
		this.item_name = item_name;
		this.description = description;
		this.price = price;
		this.ratings = ratings;
		this.isAvailable = isAvailable;
		this.imagePath = imagePath;
	}

	public int getMenu_id() {
		return menu_id;
	}


	public void setMenu_id(int menu_id) {
		this.menu_id = menu_id;
	}


	public int getRestaurant_id() {
		return restaurant_id;
	}


	public void setRestaurant_id(int restaurant_id) {
		this.restaurant_id = restaurant_id;
	}


	public String getItem_name() {
		return item_name;
	}


	public void setItem_name(String item_name) {
		this.item_name = item_name;
	}


	public String getDescription() {
		return description;
	}


	public void setDescription(String description) {
		this.description = description;
	}


	public int getPrice() {
		return price;
	}


	public void setPrice(int price) {
		this.price = price;
	}


	public String getRatings() {
		return ratings;
	}


	public void setRatings(String ratings) {
		this.ratings = ratings;
	}


	public String getIsAvailable() {
		return isAvailable;
	}


	public void setIsAvailable(String isAvailable) {
		this.isAvailable = isAvailable;
	}


	public String getImagePath() {
		return imagePath;
	}


	public void setImagePath(String imagePath) {
		this.imagePath = imagePath;
	}


	@Override
	public String toString() {
		return "Menu [menu_id=" + menu_id + ", restaurant_id=" + restaurant_id + ", item_name=" + item_name
				+ ", description=" + description + ", price=" + price + ", ratings=" + ratings + ", isAvailable="
				+ isAvailable + ", imagePath=" + imagePath + "]";
	}



}

