package com.daoimpl;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;
import com.dao.OrdersDao;
import com.daomodel.Orders;

public class OrdersImpl implements OrdersDao {
	
	
	private static String url="jdbc:mysql://localhost:3306/fooddata";
	private static  String username="root";
	private static String password="2004";
	static String query1="INSERT INTO `orders` (`user_id`,`restaurant_id`,`order_date`,`total_amount`,`status`,`payment_mode`,`address`) " +
            "VALUES (?, ?, ?, ?, ?, ?,?)";
	static String query2="UPDATE `orders` SET `user_id` = ?,`restaurant_id` = ?,`order_date` = ?,` total_amount` = ?, " +
            "`status` = ?,`payment_mode` = ? WHERE `order_id` = ?";
	static String query3= "DELETE FROM `orders` WHERE `order_id` = ?";
	static String query4= "SELECT * FROM orders WHERE `order_id` = ?";
	String query5="SELECT * FROM orders";
	String query6= "SELECT * FROM orders WHERE `user_id` = ? ORDER BY order_date DESC";
			 	                 
	
	static Orders order;
	int  oi;
	
	@Override
	public int addOrder(Orders order) {
		// TODO Auto-generated method stub
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
		try {
			Connection con=	DriverManager.getConnection(url,username,password);
		//	 PreparedStatement pstmt=con.prepareStatement(query1);
			 PreparedStatement pstmt=con.prepareStatement(query1,PreparedStatement.RETURN_GENERATED_KEYS);
			 pstmt.setInt(1, order.getuser_id());
			 pstmt.setInt(2, order.getrestaurant_id());
			 pstmt.setTimestamp(3, order.getorder_date());
			 pstmt.setInt(4,order.gettotal_amount());
			 pstmt.setString(5, order.getstatus());
			 pstmt.setString(6,order.getpayment_mode());
			 pstmt.setString(7, order.getaddress());
			int res =pstmt.executeUpdate();
			ResultSet generatedKeys = pstmt.getGeneratedKeys();
			
			while(generatedKeys.next()) {
				oi=generatedKeys.getInt(1);
			}
			System.out.println("Orders details are added successfully");
			
			
			 
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		}
		
			
		catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
			
		return oi;
				
	}
	@Override
	public void updateOrder(Orders order) {
		// TODO Auto-generated method stub
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
			try {
				Connection con=DriverManager.getConnection(url, username,password);
				PreparedStatement pstmt=con.prepareStatement(query2);
				pstmt.setInt(1, order.getuser_id());
				pstmt.setInt(2, order.getrestaurant_id());
				pstmt.setTimestamp(3, order.getorder_date());
				pstmt.setInt(4, order.gettotal_amount());
				pstmt.setString(5, order.getstatus());
				pstmt.setString(6, order.getpayment_mode());
				pstmt.setInt(7, order.getorder_id());
				pstmt.setString(8, order.getaddress());
			int res=pstmt.executeUpdate();
			System.out.println("Orders details are updated Sucessfully");
				
				
				
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
	public void deleteOrder(int Order_id) {
		// TODO Auto-generated method stub
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
			try {
				Connection con=DriverManager.getConnection(url,username,password);
				 PreparedStatement pstmt=con.prepareStatement(query3);
				 pstmt.setInt(1, Order_id);
				 int res=pstmt.executeUpdate();
				System.out.println("Order details are deleted Sucessfully");
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
	public Orders getOrder(int Order_id) {
		// TODO Auto-generated method stub
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
		try {
			Connection con=	DriverManager.getConnection(url,username,password);
			 PreparedStatement pstmt=con.prepareStatement(query4);
			 pstmt.setInt(1,Order_id);
			  ResultSet res=pstmt.executeQuery();
			  while(res.next()) {
				  int O_id=res.getInt(1);
				  int U_id=res.getInt(2);
				  int R_id=res.getInt(3);
				  Timestamp  Order_date=res.getTimestamp(4);
				  int total_amount =res.getInt(5);
				  String  status=res.getString(6);
				  String  pay_mode=res.getString(7);
				  String Address=res.getString(8);
				  
				order  =new Orders(O_id, U_id, R_id, Order_date, total_amount, status, pay_mode,Address);
				  
				  
			  }
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return order;
	}
	@Override
	public  List<Orders> getAllOrder() {
		// TODO Auto-generated method stub
	List<Orders> list=new ArrayList<Orders>();
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
		try {
			Connection con=	DriverManager.getConnection(url, username, password);
			 PreparedStatement pstmt=con.prepareStatement(query5);
			ResultSet res= pstmt.executeQuery();
			 while(res.next()) {
				  int O_id=res.getInt(1);
				  int U_id=res.getInt(2);
				  int R_id=res.getInt(3);
				  Timestamp  Order_date=res.getTimestamp(4);
				  int total_amount =res.getInt(5);
				  String  status=res.getString(6);
				  String  pay_mode=res.getString(7);
				  String Address=res.getString(8);
				  
				order  =new Orders(O_id, U_id, R_id, Order_date, total_amount, status, pay_mode,Address);
				list.add(order);
				  
				  
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
	
	
	public List<Orders> getOrdersByUserId(int user_id) {
	    List<Orders> list = new ArrayList<>();
	    
	    
	    try {
			Class.forName("com.mysql.cj.jdbc.Driver");
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	    
	    

	   

	    try (Connection con = DriverManager.getConnection(url,username,password);
	         PreparedStatement ps = con.prepareStatement(query6)) {

	        ps.setInt(1, user_id);

	        try (ResultSet rs = ps.executeQuery()) {
	            while (rs.next()) {
	                Orders o = new Orders();
	                o.setorder_id(rs.getInt("order_id"));
	                o.setuser_id(rs.getInt("user_id"));
	                o.setrestaurant_id(rs.getInt("restaurant_id"));
	                o.setorder_date(rs.getTimestamp("order_date"));
	                o.settotal_amount(rs.getInt("total_amount"));
	                o.setstatus(rs.getString("status"));
	                o.setpayment_mode(rs.getString("payment_mode"));
	                o.setaddress(rs.getString("Address"));

	                list.add(o);
	            }
	        }

	    } catch (Exception e) {
	        e.printStackTrace();
	    }

	    return list;
	}

	
	
	
	
	
	
	
	
	

	
}

