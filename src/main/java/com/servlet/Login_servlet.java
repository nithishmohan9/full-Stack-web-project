package com.servlet;

import java.io.IOException;

import com.daoimpl.UserImpl;
import com.daomodel.User;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

@WebServlet("/Login_servlet")
public class Login_servlet extends HttpServlet {

    private UserImpl userImpl;

    @Override
    protected void service(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {

        String email = req.getParameter("email");
        String password = req.getParameter("password");

        userImpl = new UserImpl();
        User user = userImpl.getUserByEmail(email);

        if (user == null) {
            req.setAttribute("isError", true);
            req.setAttribute("error", "Invalid Email / User Not Found");
            req.getRequestDispatcher("login.jsp").forward(req, resp);
            return;
        }

        if (user.getpassWord().equals(password)) {

            HttpSession session = req.getSession(true);
            session.setAttribute("user", user);

            System.out.println("[LOGIN] Logged-in User ID: " + user.getuser_id());

            // optional: prefill checkout form
            session.setAttribute("user_name", user.getname());
            session.setAttribute("user_phone", user.getphone());
            session.setAttribute("user_address", user.getaddress());

            resp.sendRedirect("getAllRestaurant");
        } else {
            req.setAttribute("isError", true);
            req.setAttribute("error", "Invalid Password");
            req.getRequestDispatcher("login.jsp").forward(req, resp);
        }
    }
}
