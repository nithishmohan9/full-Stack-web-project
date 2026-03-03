package com.daoimpl;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import com.dao.MenuDao;
import com.daomodel.Menu;
import com.daomodel.Restaurant;
import com.daomodel.User;

public class MenuImpl implements MenuDao{
	private static String url="jdbc:mysql://localhost:3306/fooddata";
	private static  String username="root";
	private static String password="2004";
	static String query1="INSERT into `menu`(`menu_id`,` restaurant_id`,` item_name`, `description`, `price`, `ratings`,` isAvailable`, `imagePath`) VALUES(?,?,?,?,?,?,?,?)";
	static   String query2 = "UPDATE `menu` SET `menu_id`=?, `restaurant_id`=?, `item_name`=?, ` description`=?, ` price`=?, `ratings`=?,`isAvailable`=?,`imagePath`=? WHERE `Menu_ID`=?";
	static String query3="DELETE FROM `menu` WHERE `menu_id`=?";
	static String query4="SELECT * FROM `menu` WHERE `menu_id`=?";
	static String query5="SELECT * FROM `menu`";
	String query6="SELECT * FROM `menu` WHERE `Restaurant_id` = ?";
	 static Menu menu;
	
	
	
	
	@Override
	public List <Menu> getAllMenusByRestaurantId(int Restaurant_id) {
		// TODO Auto-generated method stub
		
		List <Menu> menu_list=new ArrayList<>();
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
		 try {
			Connection con=	DriverManager.getConnection(url,username,password);
			PreparedStatement pstmt=con.prepareStatement (query6);
			pstmt.setInt(1, Restaurant_id);
			ResultSet res=pstmt.executeQuery();
			 while(res.next()){
				 int m_id=res.getInt(1);
				   int  R_id=res.getInt(2);
				   String item_name=res.getString(3);
				   String description=res.getString(4);
				   int  price=res.getInt(5);
				   String ratings=res.getString(6);
				   String isAvailable=res.getString(7);
				   String imagePath=res.getString(8);
				   
				  menu =new Menu(m_id,R_id,item_name,description,price,ratings,isAvailable,imagePath); 	
				  menu_list.add(menu);
				
			 }
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return menu_list;
		
	}

	@Override
	public void addMenu(Menu menu) {
		// TODO Auto-generated method stub
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
			try {
				Connection con=DriverManager.getConnection(url,username,password);
				 PreparedStatement pstmt=con.prepareStatement(query1);
				 pstmt.setInt(1, menu.getMenu_id());
				 pstmt.setInt (2, menu.getRestaurant_id());
				 pstmt.setString(3, menu.getItem_name());
				 pstmt.setString(4, menu.getDescription());
				 pstmt.setInt(5, menu. getPrice());
				 pstmt.setString(6, menu.getRatings());
				 pstmt.setString(7, menu.getIsAvailable());
				 pstmt.setString(8, menu.getImagePath());
				 int res= pstmt.executeUpdate();
				 System.out.println(res);
				 System.out.println("menu details added Succeessfully");
				
				
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
	public void updatemenu(Menu menu) {
		// TODO Auto-generated method stub
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
		try {
			Connection con=	DriverManager.getConnection(url,username,password);
			 PreparedStatement pstmt=con.prepareStatement(query2);
			 pstmt.setInt(1, menu.getMenu_id());
			 pstmt.setInt (2, menu.getRestaurant_id());
			 pstmt.setString(3, menu.getItem_name());
			 pstmt.setString(4, menu.getDescription());
			 pstmt.setInt(5, menu. getPrice());
			 pstmt.setString(6, menu.getRatings());
			 pstmt.setString(7, menu.getIsAvailable());
			 pstmt.setString(8, menu.getImagePath());
			int res= pstmt.executeUpdate();
			System.out.println(res);
			
	            System.out.println(" Menu updated successfully!");
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
	public void deleteMenu(int menu_id) {
		// TODO Auto-generated method stub
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
			try {
				Connection con=DriverManager.getConnection(url,username,password);
				PreparedStatement pstmt=con.prepareStatement(query3);
				 pstmt.setInt(1,menu_id );
				  int res=pstmt.executeUpdate();
				  System.out.println(res);
				  System.out.println("menu details deleted successfully");
				
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
	public Menu getMenu(int menu_id) {
		// TODO Auto-generated method stub
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
		try {
			Connection con=	DriverManager.getConnection(url,username,password);
			 PreparedStatement pstmt=con.prepareStatement(query4);
			 pstmt.setInt(1,menu_id);
			 ResultSet res=pstmt.executeQuery();
			 while(res.next()){
				 int m_id=res.getInt(1);
				   int  R_id=res.getInt(2);
				   String item_name=res.getString(3);
				   String description=res.getString(4);
				   int  price=res.getInt(5);
				   String ratings=res.getString(6);
				   String isAvailable=res.getString(7);
				   String imagePath=res.getString(8);
				   
				  menu =new Menu(m_id,R_id,item_name,description,price,ratings,isAvailable,imagePath); 				  
				
			 }
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return menu;
				
		
	}

	@Override
	public List<Menu> getAllMenu() {
		// TODO Auto-generated method stub
		
		List<Menu>list =new ArrayList<Menu>();
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
			 try {
				Connection con=DriverManager.getConnection(url,username,password);
				 PreparedStatement pstmt=con.prepareStatement(query5);
				 ResultSet res=pstmt.executeQuery();
				 while(res.next()){
					 int m_id=res.getInt(1);
					   int  R_id=res.getInt(2);
					   String item_name=res.getString(3);
					   String description=res.getString(4);
					   int  price=res.getInt(5);
					   String ratings=res.getString(6);
					   String isAvailable=res.getString(7);
					   String imagePath=res.getString(8);
					   
					  menu =new Menu(m_id,R_id,item_name,description,price,ratings,isAvailable,imagePath); 
					  list.add(menu);
					
				 }
							 
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return list;
	}

}

