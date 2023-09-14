package com.model;

import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import javax.sql.DataSource;

import com.entity.User;

public class UsersModel {

	public List<User> listUsers(DataSource dataSource) {
		List<User> listUsers = new ArrayList<>();
		Connection connect = null;
		Statement stmt = null;
		ResultSet rs = null;
		try {
			connect = dataSource.getConnection();

			// Step 2 : Create a SQL statement string

			String query = "Select * from users";
			stmt = connect.createStatement();

			// Step 3 :

			rs = stmt.executeQuery(query);

			// Step 4 : Process the resultSet

			while (rs.next()) {
				listUsers.add(new User(rs.getInt("users_id"), rs.getString("username"), rs.getString("email")));

			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return listUsers;
	}

	public void addUser(DataSource dataSource, User newUser) {
		Connection connect = null;
		PreparedStatement ps = null;
		try {
			connect = dataSource.getConnection();
			String username = newUser.getUsername();
			String email = newUser.getEmail();
			String query = " insert into users(username,email) values(?,?)";
			ps = connect.prepareStatement(query);
			ps.setString(1, username);
			ps.setString(2, email);
		    ps.execute();
		} catch (SQLException e) {

			e.printStackTrace();
		
		}
		

	}

	public void updateUser(DataSource dataSource, User updatedUser) {
		Connection connect = null;
		PreparedStatement ps = null;
		try {
			connect = dataSource.getConnection();
			int usersId = updatedUser.getUsers_id();
			String username = updatedUser.getUsername();
			String email = updatedUser.getEmail();
			String query = " update users set username=?, email=? where(users_id=?)";
			ps = connect.prepareStatement(query);
			ps.setString(1, username);
			ps.setString(2, email);
			ps.setInt(3, usersId);
			ps.execute();
		} catch (SQLException e) {

			e.printStackTrace();
		
		}
		
		
	}

	

	public void deleteUser(DataSource dataSource, int usersID) {
		Connection connect = null;
		PreparedStatement statement = null;
		try {
			connect = dataSource.getConnection();
			String query = "delete from users where users_Id = ? ";
			statement = connect.prepareStatement(query);
			statement.setInt(1, usersID);
			statement.execute();	
		} catch (SQLException e) {
			e.printStackTrace();
			
		}
		
		
	}

}
