package com.daomodel;

import java.sql.Timestamp;

public class User {
	
	private	  int user_id;
	private	 String name;
	private  String userName;
	private	 String passWord;
	private	 String email;
	private	 String phone;
	private String address;
	private	 String role;
	private	 Timestamp created_date;
	private	 Timestamp last_login_date;
	
	public User() {
		// TODO Auto-generated constructor stub
	}
	
	public User(int user_id, String name, String userName, String passWord, String email, String phone, String address,
			String role, Timestamp created_date, Timestamp last_login_date) {
		super();
		this.user_id = user_id;
		this.name = name;
		this.userName = userName;
		this.passWord = passWord;
		this.email = email;
		this.phone = phone;
		this.address = address;
		this.role = role;
		this.created_date = created_date;
		this.last_login_date = last_login_date;
	}

	public int getuser_id() {
		return user_id;
	}

	public void setuser_id(int user_id) {
		this.user_id = user_id;
	}

	public String getname() {
		return name;
	}

	public void setname(String name) {
		this.name = name;
	}

	public String getuserName() {
		return userName;
	}

	public void setuserName(String userName) {
		this.userName = userName;
	}

	public String getpassWord() {
		return passWord;
	}

	public void setpassWord(String passWord) {
		this.passWord = passWord;
	}

	public String getemail() {
		return email;
	}


	public void setemail(String email) {
		this.email = email;
	}


	public String getphone() {
		return phone;
	}

	public void setphone(String phone) {
		this.phone = phone;
	}

	public String getaddress() {
		return address;
	}

	public void setaddress(String address) {
		this.address = address;
	}

	public String getrole() {
		return role;
	}

	public void setrole(String role) {
		this.role = role;
	}

	public Timestamp getcreated_date() {
		return created_date;
	}

	public void setcreated_date(Timestamp created_date) {
		this.created_date = created_date;
	}

	public Timestamp getLast_login_date() {
		return last_login_date;
	}
	public void setlast_login_date(Timestamp last_login_date) {
		this.last_login_date = last_login_date;
	}

	public User(int user_id, String name, String user_name, String password, String email, String phone, String address,
			String role) {
		super();
		this.user_id = user_id;
		this.name = name;
		this.userName = user_name;
		this.passWord = password;
		this.email = email;
		this.phone = phone;
		this.address = address;
		this.role = role;
	}
	@Override
	public String toString() {
		return "User [user_id=" + user_id + ", name=" + name + ", user_name=" + userName + ", password=" + passWord
				+ ", email=" + email + ", phone=" + phone + ", address=" + address + ", role=" + role
				+ ", created_date=" + created_date + ", last_login_date=" + last_login_date + "]";
	}

}

