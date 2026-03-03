package com.daomodel;

public class cart_item {
	
	int menu_id;
	int restaurant_id;
	String item_name;
	int price;
	int quantity;
	
	public cart_item() {
		// TODO Auto-generated constructor stub
	}

	public cart_item(int menu_id, int restaurant_id, String item_name, int price, int quantity) {
		super();
		this.menu_id = menu_id;
		this.restaurant_id = restaurant_id;
		this.item_name = item_name;
		this.price = price;
		this.quantity = quantity;
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

	public int getPrice() {
		return price;
	}

	public void setPrice(int price) {
		this.price = price;
	}

	public int getQuantity() {
		return quantity;
	}

	public void setQuantity(int quantity) {
		this.quantity = quantity;
	}

	@Override
	public String toString() {
		return "cart_item [menu_id=" + menu_id + ", restaurant_id=" + restaurant_id + ", item_name=" + item_name
				+ ", price=" + price + ", quantity=" + quantity + "]";
	}
	
}

