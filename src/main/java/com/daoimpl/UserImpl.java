package com.daoimpl;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

import com.dao.UserDao;
import com.daomodel.User;

public class UserImpl implements UserDao {

    private static final String URL = "jdbc:mysql://localhost:3306/fooddata";
    private static final String USER = "root";
    private static final String PASSWORD = "2004";

    // QUERIES
    private static final String INSERT_USER =
            "INSERT INTO user (name, userName, passWord, email, phone, address, role, created_date, last_login_date) "
            + "VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?)";

    private static final String UPDATE_USER =
            "UPDATE user SET name=?, userName=?, passWord=?, email=?, phone=?, address=?, role=? WHERE user_id=?";

    private static final String DELETE_USER =
            "DELETE FROM user WHERE user_id=?";

    private static final String SELECT_BY_ID =
            "SELECT * FROM user WHERE user_id=?";

    private static final String SELECT_ALL =
            "SELECT * FROM user";

    private static final String SELECT_BY_EMAIL =
            "SELECT user_id, name, userName, passWord, email, phone, address, role, created_date, last_login_date "
            + "FROM user WHERE email=?";

    public UserImpl() {
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    // ----------------------------------------------------------
    // INSERT USER
    // ----------------------------------------------------------
    @Override
    public boolean addUser(User user) {

        try (Connection con = DriverManager.getConnection(URL, USER, PASSWORD);
             PreparedStatement ps = con.prepareStatement(INSERT_USER)) {

            ps.setString(1, user.getname());
            ps.setString(2, user.getuserName());
            ps.setString(3, user.getpassWord());
            ps.setString(4, user.getemail());
            ps.setString(5, user.getphone());
            ps.setString(6, user.getaddress());
            ps.setString(7, user.getrole());
            ps.setTimestamp(8, new Timestamp(System.currentTimeMillis()));
            ps.setTimestamp(9, new Timestamp(System.currentTimeMillis()));

            return ps.executeUpdate() > 0;

        } catch (Exception e) {
            e.printStackTrace();
        }

        return false;
    }

    // ----------------------------------------------------------
    // UPDATE USER
    // ----------------------------------------------------------
    @Override
    public void updateuser(User user) {

        try (Connection con = DriverManager.getConnection(URL, USER, PASSWORD);
             PreparedStatement ps = con.prepareStatement(UPDATE_USER)) {

            ps.setString(1, user.getname());
            ps.setString(2, user.getuserName());
            ps.setString(3, user.getpassWord());
            ps.setString(4, user.getemail());
            ps.setString(5, user.getphone());
            ps.setString(6, user.getaddress());
            ps.setString(7, user.getrole());
            ps.setInt(8, user.getuser_id());

            ps.executeUpdate();

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    // ----------------------------------------------------------
    // DELETE USER
    // ----------------------------------------------------------
    @Override
    public void deleteUser(int user_id) {
        try (Connection con = DriverManager.getConnection(URL, USER, PASSWORD);
             PreparedStatement ps = con.prepareStatement(DELETE_USER)) {

            ps.setInt(1, user_id);
            ps.executeUpdate();

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    // ----------------------------------------------------------
    // FETCH USER BY ID
    // ----------------------------------------------------------
    @Override
    public User getUser(int user_id) {
        User user = null;

        try (Connection con = DriverManager.getConnection(URL, USER, PASSWORD);
             PreparedStatement ps = con.prepareStatement(SELECT_BY_ID)) {

            ps.setInt(1, user_id);
            ResultSet rs = ps.executeQuery();

            if (rs.next()) {
                user = extractUser(rs);
            }

        } catch (Exception e) {
            e.printStackTrace();
        }

        return user;
    }

    // ----------------------------------------------------------
    // FETCH ALL USERS
    // ----------------------------------------------------------
    @Override
    public List<User> getAllUser() {

        List<User> list = new ArrayList<>();

        try (Connection con = DriverManager.getConnection(URL, USER, PASSWORD);
             PreparedStatement ps = con.prepareStatement(SELECT_ALL);
             ResultSet rs = ps.executeQuery()) {

            while (rs.next()) {
                list.add(extractUser(rs));
            }

        } catch (Exception e) {
            e.printStackTrace();
        }

        return list;
    }

    // ----------------------------------------------------------
    // FETCH USER BY EMAIL (USED FOR LOGIN)
    // ----------------------------------------------------------
    public User getUserByEmail(String email) {

        User user = null;

        try (Connection con = DriverManager.getConnection(URL, USER, PASSWORD);
             PreparedStatement ps = con.prepareStatement(SELECT_BY_EMAIL)) {

            ps.setString(1, email);

            ResultSet rs = ps.executeQuery();

            if (rs.next()) {
                user = extractUser(rs);
            }

        } catch (Exception e) {
            e.printStackTrace();
        }

        return user;
    }

    // ----------------------------------------------------------
    // HELPER: Convert ResultSet → User Object
    // ----------------------------------------------------------
    private User extractUser(ResultSet rs) throws SQLException {

        User user = new User();

        user.setuser_id(rs.getInt("user_id"));
        user.setname(rs.getString("name"));
        user.setuserName(rs.getString("userName"));
        user.setpassWord(rs.getString("passWord"));
        user.setemail(rs.getString("email"));
        user.setphone(rs.getString("phone"));
        user.setaddress(rs.getString("address"));
        user.setrole(rs.getString("role"));
        user.setcreated_date(rs.getTimestamp("created_date"));
        user.setlast_login_date(rs.getTimestamp("last_login_date"));

        return user;
    }

	@Override
	public User getUser(String userName) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public String login(String email) {
		// TODO Auto-generated method stub
		return null;
	}
}
