package com.dao;

import java.util.List;

import com.daomodel.User;

public interface UserDao {


	boolean addUser(User user);
	void updateuser(User user);
	void deleteUser( int id);
	User getUser(int user_id );
	User getUser(String userName);
	List<User > getAllUser();
	String login(String email);
	User getUserByEmail(String email);
}
