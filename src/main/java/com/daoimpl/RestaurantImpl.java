package com.daoimpl;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

import com.dao.RestaurantDao;
import com.daomodel.Restaurant;


public class RestaurantImpl implements RestaurantDao{
	
	private static String url="jdbc:mysql://localhost:3306/fooddata";
	private static  String username="root";
	private static String password="2004";
	static String query1="INSERT into `restaurant`(`restaurant_id`, `name`, `address`,` phone`, `rating`, `cusine_type`,` isActive`,` estimated_time_arival`, `user_id`,` image_path`) VALUES(?,?,?,?,?,?,?,?,?,?)";	
	static  String query2 = "UPDATE `restaurant` SET `name`=?, `address`=?,` phone`=?, `rating`=?, " +
             "`cusine_type`=?,` isActive`=?,` estimated_time_arival`=?,` user_id`=?, `image_path`=? WHERE `restaurant_id`=?";
    static String query3= "DELETE FROM `restaurant` WHERE `restaurant_id`=?";
    static String query4 = "SELECT * FROM `restaurant` WHERE `restaurant_id`=?";
    static String query5 = "SELECT * FROM `restaurant`";

	private static Restaurant restaurant;
	

	@Override
	public void addRestaurant(Restaurant restauarant) {
		// TODO Auto-generated method stub
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
			try {
				Connection con=DriverManager.getConnection(url,username,password);
				 PreparedStatement pstmt=con.prepareStatement(query1);
				 pstmt.setInt(1, restaurant.getrestaurant_id());
				 pstmt.setString (2, restaurant.getname());
				 pstmt.setString(3, restaurant.getaddress());
				 pstmt.setString(4, restaurant.getphone());
				 pstmt.setString(5, restaurant.getrating());
				 pstmt.setString(6, restaurant.getcusine_type());
				 pstmt.setString(7, restaurant.getisActive());
				 pstmt.setString(8, restaurant.getestimated_time_arival());
				 pstmt.setInt(9, restaurant.getuser_id());
				 pstmt.setString(10, restaurant.getimage_path());
				int res= pstmt.executeUpdate();
				System.out.println(res);
				 System.out.println("restaurant details added Succeessfully");
				
				
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
	}

	@Override
	public void updateRestaurant(Restaurant restaurant) {
		// TODO Auto-generated method stub
		
		
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
			
			try {
				Connection con=DriverManager.getConnection(url,username,password);
				PreparedStatement pstmt=con.prepareStatement(query2);
				 pstmt.setInt(1, restaurant.getrestaurant_id());
				 pstmt.setString (2, restaurant.getname());
				 pstmt.setString(3, restaurant.getaddress());
				 pstmt.setString(4, restaurant.getphone());
				 pstmt.setString(5, restaurant.getrating());
				 pstmt.setString(6, restaurant.getcusine_type());
				 pstmt.setString(7, restaurant.getisActive());
				 pstmt.setString(8, restaurant.getestimated_time_arival());
				 pstmt.setInt(9, restaurant.getuser_id());
				 pstmt.setString(10, restaurant.getimage_path());
				int res= pstmt.executeUpdate();
				System.out.println(res);
				 System.out.println("restaurant details updated Succeessfully");
				
				
				
				
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
		
	@Override
	public void deleteRestaurant(int Restaurant_ID) {
		// TODO Auto-generated method stub
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
			try {
				Connection con=DriverManager.getConnection(url,username,password);
				 PreparedStatement pstmt=con.prepareStatement(query3);
				 pstmt.setInt(1,Restaurant_ID );
				int res= pstmt.executeUpdate();
				System.out.println(res);
				 System.out.println("Restaurant id details deleted Suceessfully");
				 
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}

	@Override
	public Restaurant  getRestaurant(int Restaurant_ID) {
		// TODO Auto-generated method stub
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
			try {
				Connection con=DriverManager.getConnection(url,username,password);
				 PreparedStatement pstmt=con.prepareStatement(query4);
				 pstmt.setInt(1, Restaurant_ID);
				ResultSet res= pstmt.executeQuery();
				 while(res.next()){
					  int R_id=res.getInt(1);
					   String Name=res.getString(2);
					   String Address=res.getString(3);
					   String  Phone=res.getString(4);
					   String Rating=res.getString(5);
					   String Cusine_Type=res.getString(6);
					   String IsActive=res.getString(7);
					   String Estimated_Time_Arival=res.getString(8);
					   int  User_id=res.getInt(9);
					   String  Image_Path=res.getString(10);
					   
					   
					   restaurant  =new Restaurant(R_id, Name, Address, Phone, Rating, Cusine_Type, IsActive, Estimated_Time_Arival,  User_id,  Image_Path);
					 				  
					
				 }
				
				
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return restaurant ;
	}



	@Override
	public List<Restaurant> getAllRestaurant() {
		
		 List<Restaurant> list=new ArrayList<Restaurant>();
		 

		
		// TODO Auto-generated method stub
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
			
			try {
				Connection con=DriverManager.getConnection(url,username,password);
				 PreparedStatement pstmt=con.prepareStatement(query5);
				ResultSet res= pstmt.executeQuery();
				 while(res.next()){
					  int R_id=res.getInt(1);
					   String Name=res.getString(2);
					   String Address=res.getString(3);
					   String  Phone=res.getString(4);
					   String Rating=res.getString(5);
					   String Cusine_Type=res.getString(6);
					   String IsActive=res.getString(7);
					   String Estimated_Time_Arival=res.getString(8);
					   int  User_id=res.getInt(9);
					   String  Image_Path=res.getString(10);
					   restaurant  =new Restaurant(R_id,Name,Address, Phone,Rating,Cusine_Type, IsActive,Estimated_Time_Arival,User_id,Image_Path);
					   list.add(restaurant);
					
				 }
				
			} catch (SQLException e) {
				e.printStackTrace();
			}
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
		return list;
	}

}

