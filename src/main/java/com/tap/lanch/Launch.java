package com.tap.lanch;
import java.util.List;
import java.util.Scanner;

import com.daoimpl.UserImpl;
import com.daomodel.User;

public class Launch {

	private static UserImpl UserDao;
	private static int user;

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Scanner scanner = new Scanner(System.in);
		System.out.println("ENTER THE ID");
		int user_id=scanner.nextInt();
		System.out.println("ENTER THE NAME");
		String name = scanner.next();
		System.out.println("ENTER THE USER_NAME");
		String user_name = scanner.next();
		System.out.println("ENTER THE PASSWORD");
		String password = scanner.next();
		System.out.println("ENTER THE E_MAIL");
		String email = scanner.next();
		System.out.println("ENTER THE PHONE");
		String phone = scanner.next();
		System.out.println("ENTER THE ADDRESS");
		String address = scanner.next();
		System.out.println("ENTER THE ROLE");
		String role = scanner.next();


		//add the user details
	//	User U_details = new User (user_id,name,user_name,password,email,phone,address,role);
		//UserImpl impl =new UserImpl();
	//impl.addUser(U_details);


		// get the user_details
		//	User user= impl.getUser(user_id);
		//System.out.print(user);


		// update the user_details

		//  user.setPassword("sanjay3204");
		//  impl.updateuser(user);
		// System.out.print(user);


		// get all the user_details

	//	 List<User>list=impl.getAllUser();
		// for(User user1: list ) {
		 //System.out.println(user1);
	//}//

	// delete the user_details
	//impl.deleteUser(user_id);




	}


}









