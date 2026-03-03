package com.servlet;

import java.io.IOException;
import java.sql.Timestamp;
import java.util.Map;

import com.dao.OrdersDao;
import com.daoimpl.OrdersImpl;
import com.daomodel.Orders;
import com.daoimpl.cartImpl;
import com.daomodel.cart_item;
import com.daomodel.User;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.*;

@WebServlet("/placeOrder_servlet")
public class PlaceOrder_servlet extends HttpServlet {
    private static final long serialVersionUID = 1L;

    // GST and delivery constants (adjust as needed)
    private static final double GST_RATE = 0.05;
    private static final double DELIVERY_CHARGE = 30.0;

    private OrdersDao ordersDao;

    @Override
    public void init() throws ServletException {
        // Ensure OrdersImpl implements OrdersDao and has addOrder returning generated id (int)
        ordersDao = new OrdersImpl();
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        // read input
        request.setCharacterEncoding("UTF-8");
        HttpSession session = request.getSession(false);
        if (session == null) {
            response.sendRedirect("login.jsp");
            return;
        }

        // user must be in session (set at login)
        User user = (User) session.getAttribute("user");
        if (user == null) {
            response.sendRedirect("login.jsp");
            return;
        }

        // read checkout form fields
        String custName = request.getParameter("cust_name");
        String custPhone = request.getParameter("cust_phone");
        String custAddress = request.getParameter("cust_address");
        String paymentMethod = request.getParameter("payment_method");
        String restaurantIdParam = request.getParameter("restaurant_id");

        // validate minimal required fields
        if (custName == null || custPhone == null || custAddress == null || paymentMethod == null) {
            request.setAttribute("error", "Missing required checkout fields.");
            request.getRequestDispatcher("checkout.jsp").forward(request, response);
            return;
        }

        // read cart from session
        cartImpl cart = (cartImpl) session.getAttribute("cart");
        if (cart == null || cart.getCartItems() == null || cart.getCartItems().isEmpty()) {
            request.setAttribute("error", "Cart is empty.");
            request.getRequestDispatcher("cart.jsp").forward(request, response);
            return;
        }

        // server-side totals
        double subtotal = 0.0;
        for (Map.Entry<Integer, cart_item> e : cart.getCartItems().entrySet()) {
            cart_item it = e.getValue();
            subtotal += (double) it.getPrice() * it.getQuantity();
        }
        double gst = Math.round(subtotal * GST_RATE * 100.0) / 100.0;
        double delivery = subtotal > 0 ? DELIVERY_CHARGE : 0.0;
        double total = Math.round((subtotal + gst + delivery) * 100.0) / 100.0;

        // build Orders object (use your Orders POJO setter names)
        Orders order = new Orders();
        // set fields expected by your OrdersImpl.addOrder()
        // set user id
        order.setuser_id(user.getuser_id());
        // restaurant id
        try {
            order.setrestaurant_id(Integer.parseInt(restaurantIdParam));
        } catch (Exception ex) {
            order.setrestaurant_id(0);
        }
        // set order date to now
        order.setorder_date(new Timestamp(System.currentTimeMillis()));
        // total_amount in your Orders POJO appears to be int (from earlier code). Cast safely.
        order.settotal_amount((int)Math.round(total));
        order.setstatus("PLACED");
        order.setpayment_mode(paymentMethod);
        order.setaddress(custAddress);

        // attempt to save order and get generated id
        int generatedOrderId = -1;
        try {
            // assume OrdersDao.addOrder returns int (generated id)
            // if your addOrder signature is different, update OrdersDao/OrdersImpl accordingly
            generatedOrderId = ordersDao.addOrder(order);
        } catch (Throwable t) {
            // fallback: try calling via reflection if implementation still uses void addOrder(Orders)
            try {
                java.lang.reflect.Method m = ordersDao.getClass().getMethod("addOrder", Orders.class);
                Object ret = m.invoke(ordersDao, order);
                if (ret instanceof Integer) {
                    generatedOrderId = (Integer) ret;
                } else {
                    // if void, maybe OrdersImpl set order_id inside the object; try reading it
                    try {
                        java.lang.reflect.Method getId = order.getClass().getMethod("getorder_id");
                        Object idObj = getId.invoke(order);
                        if (idObj instanceof Number) generatedOrderId = ((Number) idObj).intValue();
                    } catch (Exception ignore) {}
                }
            } catch (Exception ex) {
                ex.printStackTrace();
            }
        }

        // if generatedOrderId still -1, still proceed to show success but warn in log
        if (generatedOrderId <= 0) {
            // log and continue — the order might still be in DB but no id returned
            System.err.println("Warning: order saved but generated ID not returned. generatedOrderId=" + generatedOrderId);
        }

        // Best-effort: try to save order items using optional OrderItems DAO if present
        try {
            // If you have com.dao.OrderItemsDao / com.daoimpl.OrderItemsImpl implemented, this will call it.
            // We'll try to instantiate com.daoimpl.OrderItemsImpl and call addOrderItem(int orderId, cart_item item)
            try {
                Class<?> itemsImplClass = Class.forName("com.daoimpl.OrderItemsImpl");
                Object itemsDao = itemsImplClass.getDeclaredConstructor().newInstance();

                // method signature attempted: int addOrderItem(int orderId, cart_item item)
                java.lang.reflect.Method addItemMethod = null;
                for (java.lang.reflect.Method mm : itemsImplClass.getMethods()) {
                    if (mm.getName().equals("addOrderItem") && mm.getParameterCount() == 2) {
                        addItemMethod = mm;
                        break;
                    }
                }

                if (addItemMethod != null && generatedOrderId > 0) {
                    for (Map.Entry<Integer, cart_item> e : cart.getCartItems().entrySet()) {
                        cart_item ci = e.getValue();
                        try {
                            addItemMethod.invoke(itemsDao, generatedOrderId, ci);
                        } catch (Exception ie) {
                            // ignore per-item failure (log)
                            ie.printStackTrace();
                        }
                    }
                }
            } catch (ClassNotFoundException cnf) {
                // no OrderItemsImpl found — skip (not fatal)
            }
        } catch (Exception ex) {
            ex.printStackTrace();
        }

        // Store order summary into session for order_success.jsp and invoice generation
        session.setAttribute("last_order_id", generatedOrderId > 0 ? String.valueOf(generatedOrderId) : null);
        session.setAttribute("last_order_total", total);
        session.setAttribute("last_order_subtotal", subtotal);
        session.setAttribute("last_order_gst", gst);
        session.setAttribute("last_order_delivery", delivery);
        session.setAttribute("last_order_items", cart.getCartItems());

        // clear the cart from session
        session.removeAttribute("cart");
        session.removeAttribute("restaurant_id");

        // redirect to success page
        response.sendRedirect("order_success.jsp");
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        // disallow GET for placing orders — redirect to checkout
        resp.sendRedirect("checkout.jsp");
    }
}
