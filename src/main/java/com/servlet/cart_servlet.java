package com.servlet;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

import java.io.IOException;
import com.daoimpl.MenuImpl;
import com.daoimpl.cartImpl;
import com.daomodel.Menu;
import com.daomodel.cart_item;

/**
 * Servlet implementation class cart_servlet
 */
@WebServlet("/cart_servlet")
public class cart_servlet extends HttpServlet {

	protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		HttpSession session = request.getSession();
		cartImpl  cart = (cartImpl)session.getAttribute("cart");


		Integer   current_restaurant_id = (Integer)session.getAttribute("restaurant_id");
		int new_restaurant_id = Integer.parseInt(request.getParameter("restaurant_id"));

		if(cart == null || current_restaurant_id != new_restaurant_id) {
			cart = new cartImpl();
			session.setAttribute("cart", cart);
			session.setAttribute("restaurant_id",new_restaurant_id);

		}

		String action = request.getParameter("action");

		if("add".equals(action)) {
			addItemToCart(request,cart);
		}
		else if("update".equals(action)) {
			updateItemToCart(request, cart);

		}
		else if("remove".equals(action)){
			deleteItemToCart(request, cart);
		}


		response.sendRedirect("cart.jsp");

	}

	private void addItemToCart(HttpServletRequest request, cartImpl cart) {
		// TODO Auto-generated method stub

		int  menu_id=Integer.parseInt(request.getParameter("menu_id"));
		int  quantity =Integer.parseInt( request.getParameter("quantity"));


		MenuImpl mImpl = new MenuImpl();
		Menu menu_item = mImpl.getMenu(menu_id);


		if(menu_item !=null) {
			cart_item Item = new cart_item(

					menu_item.getMenu_id(),
					menu_item.getRestaurant_id(),
					menu_item.getItem_name(),
					menu_item.getPrice(),
					quantity);


			cart.addToCart(Item);

		}

	}



	private void updateItemToCart(HttpServletRequest request,cartImpl cart){

		int  menu_id = Integer.parseInt(request.getParameter("menu_id"));
		int  quantity = Integer.parseInt(request.getParameter("quantity"));
		cart.updateTheCart(menu_id, quantity);


	}

	private void deleteItemToCart(HttpServletRequest request, cartImpl cart){
		int menu_id = Integer.parseInt(request.getParameter("menu_id"));
		cart.deleteTheCart(menu_id);


	}

}














