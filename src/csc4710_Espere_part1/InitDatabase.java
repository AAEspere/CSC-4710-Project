//Aaron Espere gb1962

/* InitDatabase covers the creation of the tables. This will create 
 * database, create the tables, and list the information in the tables */

package csc4710_Espere_part1;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Date;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

@WebServlet("/InitDatabase")

public class InitDatabase extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private Connection connect = null;
	private Statement statement = null;
	private PreparedStatement preparedStatement = null;
	private ResultSet resultSet = null;

	
public InitDatabase() {
	
}
	
//create the Database
public void createDatabase() {
	try {
		String dropDataBase = "DROP DATABASE IF EXISTS projectdb";
		String createDataBase = "CREATE DATABASE IF NOT EXISTS projectdb";
		
		preparedStatement = connect.prepareStatement(dropDataBase);
		preparedStatement = connect.prepareStatement(createDataBase);
	}
	catch(SQLException e) {
		System.out.print("Exception: "+e+"\n");
	}
	
}
	
	
//function that initializes both the tables, and adds tuples in them
//basically for the button
public void InitDatabaseButton() throws SQLException{
	//connect to server
	connect_function();
	
	//drop and create database
	createDatabase();
	
	//drop and create item table
	createItemTable();
	
	//list items in the Items Table
	listAllItems();
	
	//drop and create user table
	createUserTable();
	
	//list users in the Users Table
	listAllUsers();
}

//initialize connection to the Server
protected void connect_function() throws SQLException {
	if(connect == null || connect.isClosed()) {
		try {
			Class.forName("come.mysql.jdbc.Driver");
		}
		catch (ClassNotFoundException e) {
			throw new SQLException(e);
		}
		connect = (Connection) DriverManager
				.getConnection("jdbc:mysql://127.0.0.1:3306/projectdb?"
	  			          + "user=john&password=pass1234");
		System.out.println(connect);
	}
}

//create the table for items
public void createItemTable() throws SQLException {
	//the two statements required for making table
	String dropItemTable = "DROP TABLE IF EXISTS item";
	String createItemTable = "CREATE TABLE IF NOT EXISTS item" +
			"(itemID INTEGER, " +
			"itemDescription VARCHAR(100), " +
			"itemDate DATE, " +
			"itemPrice INTEGER, " +
			"PRIMARY KEY (itemID)";
	
	try {
	statement = (Statement)connect.createStatement();
	
	statement.executeUpdate(dropItemTable);
	statement.executeUpdate(createItemTable);
	}
	
	catch(SQLException e) {
	}
	finally {
		connect.close();
	}
}

//create the table for users
public void createUserTable() throws SQLException {
	//the two statements required for making table
	String dropUserTable = "DROP TABLE IF EXISTS users";
	String createUserTable = "CREATE TABLE IF NOT EXISTS users" +
			"(userID INTEGER, " +
			"pass VARCHAR(20) NOT NULL, " +
			"firstName VARCHAR(50), " +
			"lastName VARCHAR(50), " +
			"email VARCHAR(50) NOT NULL, " +
			"age INTEGER, " +
			"PRIMARY KEY (userID)";
	
	try {
	statement = (Statement)connect.createStatement();
	
	statement.executeUpdate(dropUserTable);
	statement.executeUpdate(createUserTable);

	}
	catch (SQLException e) {
	}
	finally {
		connect.close();
	}
}

//this is for listing the items table
public List<item> listAllItems() throws SQLException{
	List<item> listItems = new ArrayList<item>();
	String sql = "SELECT * FROM item";
	connect_function();
	statement = (Statement) connect.createStatement();
	ResultSet resultSet = statement.executeQuery(sql);
	
	while(resultSet.next()){
	
		int itemID = resultSet.getInt("itemID");
		String itemDescription = resultSet.getString("itemDescription");
		Date date = resultSet.getDate("date");
		int itemPrice = resultSet.getInt("itemPrice");
		String itemCategory = resultSet.getString("itemCategory");
		
		item items = new item(itemID,itemDescription,date,itemPrice,itemCategory);
		listItems.add(items);		
	}
	resultSet.close();
	statement.close();
	disconnect();
	return listItems;
}

//this is for listing the users
public List<users> listAllUsers() throws SQLException{
	List<users> listUsers = new ArrayList<users>();
	String sql = "SELECT * FROM item";
	connect_function();
	statement = (Statement) connect.createStatement();
	ResultSet resultSet = statement.executeQuery(sql);
	
	while(resultSet.next()){
	
		int userID = resultSet.getInt("userID");
		String pass = resultSet.getString("pass");
		String firstName = resultSet.getString("firstName");
		String lastName = resultSet.getString("lastName");
		String email = resultSet.getString("email");
		String gender = resultSet.getString("gender");
		int age = resultSet.getInt("age");
		
		users users = new users(userID, pass, firstName, lastName, email, gender,age);;
		listUsers.add(users);		
	}
	resultSet.close();
	statement.close();
	disconnect();
	return listUsers;
}

protected void disconnect() throws SQLException {
	if (connect != null && !connect.isClosed()) {
		connect.close();
	}
}

}