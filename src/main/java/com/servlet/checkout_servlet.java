package com.servlet;


import java.io.IOException;

import com.daoimpl.Order_itemImpl;
import com.daoimpl.OrdersImpl;
import com.daoimpl.cartImpl;
import com.daomodel.Order_item;
import com.daomodel.Orders;
import com.daomodel.User;
import com.daomodel.cart_item;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

@WebServlet("/checkout_servlet")
public class checkout_servlet extends HttpServlet{
	OrdersImpl ordersImpl ;
	Order_itemImpl order_itemImpl ;
	@Override
		public void init() throws ServletException {
		ordersImpl = new OrdersImpl();
		order_itemImpl = new Order_itemImpl();
		}

@Override
protected void service(HttpServletRequest request, HttpServletResponse response)
        throws ServletException, IOException {

    HttpSession session = request.getSession(false);
    if (session == null || session.getAttribute("user") == null) {
        response.sendRedirect(request.getContextPath() + "/Login_servlet");
        return;
    }

    cartImpl cart = (cartImpl) session.getAttribute("cart");
    if (cart == null || cart.getCartItems().isEmpty()) {
        response.sendRedirect("cart.jsp");
        return;
    }

    final double GST_RATE = 0.05;
    final double DELIVERY_CHARGE = 30.0;

    int restaurantId = Integer.parseInt(request.getParameter("restaurant_id"));
    String paymentMethod = request.getParameter("payment_method");

    // PRICE CALCULATION
    double subtotal = 0.0;
    for (cart_item it : cart.getCartItems().values()) {
        subtotal += it.getPrice() * it.getQuantity();
    }
    double gst = subtotal * GST_RATE;
    double delivery = subtotal > 0 ? DELIVERY_CHARGE : 0.0;
    double total = subtotal + gst + delivery;

    // USER
    User user = (User) session.getAttribute("user");
    int userId = user.getuser_id();

    String userAddress = session.getAttribute("user_address") != null
            ? (String) session.getAttribute("user_address")
            : "";

    // INSERT ORDER
    Orders order = new Orders();
    order.setuser_id(userId);
    order.setrestaurant_id(restaurantId);
    order.setorder_date(new java.sql.Timestamp(System.currentTimeMillis()));
    order.settotal_amount((int) total);
    order.setstatus("Pending");
    order.setpayment_mode(paymentMethod);
    order.setaddress(userAddress);

    int orderId = 0;

    try {
        orderId = ordersImpl.addOrder(order);  // returns generated order_id

        // INSERT ORDER ITEMS -------------------------------------------
        Order_itemImpl itemDao = new Order_itemImpl();

        for (cart_item item : cart.getCartItems().values()) {
            Order_item oiBean = new Order_item();
            oiBean.setOrder_id(orderId);
            oiBean.setMenu_id(item.getMenu_id());  // Getter must match your cart_item
            oiBean.setQuantity(item.getQuantity());

            int totalPrice = (int)(item.getPrice() * item.getQuantity());
            oiBean.setTotal_price(totalPrice);

            itemDao.addOrder_item(oiBean);
        }

    } catch (Exception e) {
        e.printStackTrace();
        response.sendRedirect("cart.jsp");
        return;
    }

    // STORE FOR SUCCESS PAGE
    session.setAttribute("last_order_id", orderId);
    session.setAttribute("last_order_subtotal", subtotal);
    session.setAttribute("last_order_gst", gst);
    session.setAttribute("last_order_delivery", delivery);
    session.setAttribute("last_order_total", total);
    session.setAttribute("last_order_items", cart.getCartItems());


    // CLEAR CART
    session.removeAttribute("cart");


    response.sendRedirect("order_success.jsp");


}

}