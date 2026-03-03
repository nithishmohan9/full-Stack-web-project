package com.tap.lanch;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import com.daoimpl.MenuImpl;
import com.daoimpl.Order_itemImpl;
import com.daoimpl.OrdersImpl;
import com.daoimpl.RestaurantImpl;
import com.daoimpl.UserImpl;
import com.daomodel.Menu;
import com.daomodel.Order_item;
import com.daomodel.Orders;
import com.daomodel.Restaurant;
import com.daomodel.User;

public class GetDetails {
	
	public static void getAUser(){

		System.out.println("Enter the user id;");
		Scanner sc=new Scanner(System.in);
		int id=sc.nextInt();
		UserImpl Uimpl=new UserImpl();
		User user =Uimpl.getUser(id);
		System.out.println(user);

	}
	
	
	
	public static List<User> getAllUser(){

		List<User> U_list =new ArrayList<User>();
		UserImpl Uimpl=new UserImpl();
		List<User> user=Uimpl.getAllUser();
		U_list.addAll(user);
		return U_list;

	}
	
	
	
	public static void getARestaurant(){
		
	   System.out.println("Enter the restaurant id;");
	   Scanner sc=new Scanner(System.in);
	   int id=sc.nextInt();
	   RestaurantImpl Rimpl=new RestaurantImpl();
	   Restaurant restaurant = Rimpl.getRestaurant(id);
	   System.out.println(restaurant);	   
	}
	
	
	
	
	
	public static List<Restaurant>  getAllRestaurant(){
		RestaurantImpl Rimpl=new RestaurantImpl();
		 List<Restaurant>R_list=Rimpl.getAllRestaurant();
		 return R_list;
		
	}
	
	
	
public static void getAMenu(){
		
		System.out.println("Enter the menu id;");
		Scanner sc=new Scanner(System.in);
		int m_id=sc.nextInt();
		 MenuImpl Mimpl=new  MenuImpl();
		Menu  menu =Mimpl.getMenu(m_id);
		System.out.println(menu);
		
	}

public static void getAOrder(){
	
	System.out.println("Enter the order id;");
	Scanner sc=new Scanner(System.in);
	int o_id=sc.nextInt();
	 OrdersImpl Oimpl=new OrdersImpl();
	Orders   order =Oimpl.getOrder(o_id);
	System.out.println(order);
	
}



public static void getAOrder_item(){
	
	System.out.println("Enter the order_item id;");
	Scanner sc=new Scanner(System.in);
	int order_item_id=sc.nextInt();
	Order_itemImpl OIimpl=new Order_itemImpl();
	Order_item   order_item =OIimpl.getOrder_item(order_item_id);
	System.out.println(order_item);
	
}




	
	
	

	public static void main(String[] args) {
		
		
		
	//getARestaurant();
	 // List<Restaurant >res=	getAllRestaurant();
	   //System.out.println(res);
	 
		//getAUser();
	//	getAMenu();
		 List<User> res=getAllUser();
		 System.out.println(res);
		
	//	getAOrder();
	//	getAOrder_item();
		

	}

}
