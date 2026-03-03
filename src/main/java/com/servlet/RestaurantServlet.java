package com.servlet;

import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import com.daoimpl.RestaurantImpl;

import com.daomodel.Restaurant;


@WebServlet("/getAllRestaurant")
public class RestaurantServlet extends HttpServlet {
	
 
	private String restaurant;

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	 
		// create the object
		 RestaurantImpl Rimpl=new RestaurantImpl();
		//call the get all restaurants
		
	List<Restaurant > R_list= Rimpl.getAllRestaurant();
		
		// set the values 
		request.setAttribute("Restaurant_list", R_list);
		//call the servlets
		RequestDispatcher	rd=request.getRequestDispatcher("Restaurant.jsp");
		rd.forward(request, response);
				 
	}

}


