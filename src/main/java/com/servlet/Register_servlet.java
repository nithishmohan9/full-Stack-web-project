package com.servlet;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;

import javax.management.RuntimeErrorException;

import com.daoimpl.UserImpl;
import com.daomodel.User;

/**
 * Servlet implementation class Register_servlet
 */
@WebServlet("/Register_servlet")
public class Register_servlet extends HttpServlet {
	
	@Override
	protected void  service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		
		String n = request.getParameter("name");
		String u_name = request.getParameter("username");
		String email = request.getParameter("email");
		String p= request.getParameter("phone");
		String add= request.getParameter("address");
		String role= request.getParameter("role");
		String pass= request.getParameter("password");
		
				
		User user = new User();
		
		user.setname(n);
		user.setuserName(u_name);
		user.setemail(email);
		user.setphone(p);
		user.setaddress(add);
		user.setrole(role);
		user.setpassWord(pass);
		
		  if(pass==null || pass.isEmpty()) {
			  throw new RuntimeException("Paasword is missing");
		  }
		
		
		 UserImpl uimpl=new UserImpl();
		boolean result =  uimpl.addUser(user);
		 
		 if(result) {
			 response.sendRedirect("login.jsp");
		 }
		 else {
			 response.sendRedirect("Register.jsp?error=1");
			 
		 }
		 	
	}

}

