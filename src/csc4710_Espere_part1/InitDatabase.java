//Aaron Espere gb1962

/* InitDatabase covers the creation of the tables. This will create 
 * database, create the tables, and list the information in the tables */

package csc4710_Espere_part1;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Date;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class InitDatabase {
	
	private Connection connect = null;
	private Statement statement = null;
	private PreparedStatement preparedStatement = null;
	private ResultSet resultSet = null;

	//function that initializes both the tables, and adds tuples in them
	//basically for the button	
public InitDatabase() throws SQLException{
	//connect to server
	connect_function();
	
	//drop and create database
	createDatabase();
	
	//drop and create item table
	createItemTable();
	
	//add items
	addItems();
	
	//drop and create user table
	createUserTable();
	
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
			"itemTitle VARCHAR(50)," +
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

//Project Part 1 just says to initialize tables with at least 10 tuples, so I'm just adding
//10 random items. Will probably implement some way for user to input their own thing late
public void addItems() throws SQLException {
	statement = (Statement)connect.createStatement();
	
	String addItem = "INSERT INTO item VALUES('123456789','Cactus','A spiky plant','06/21/1998','plant'";
	
	try {
		statement.executeUpdate(addItem);
	}
	catch (SQLException e) {
		
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
		String itemTitle = resultSet.getString("itemTitle");
		String itemDescription = resultSet.getString("itemDescription");
		Date date = resultSet.getDate("date");
		int itemPrice = resultSet.getInt("itemPrice");
		String itemCategory = resultSet.getString("itemCategory");
		
		item items = new item(itemID,itemTitle,itemDescription,date,itemPrice,itemCategory);
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