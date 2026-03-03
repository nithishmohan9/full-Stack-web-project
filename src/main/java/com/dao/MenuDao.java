package com.dao;

import java.util.List;

import com.daomodel.Menu;


public interface MenuDao {
	List <Menu> getAllMenusByRestaurantId(int Restaurant_ID);
	void addMenu(Menu menu);
	void updatemenu(Menu menu);
	void deleteMenu( int menu_id);
	Menu getMenu(int  menu_id);
	List<Menu> getAllMenu();
}
