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
/*public InitDatabase() throws SQLException{
	//connect to server
	connect_function();
	
	//drop and create database
	createDatabase();
	
	//drop and create item table
	createItemTable();
	
	//drop and create user table
	createUserTable();
	
	//add items
	addItems();
	
	//add the users
	addUsers();
} */
	
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
	  			          + "user=root&password=Superiormelee98");
		System.out.println(connect);
	}
}

//create the table for items
public void createItemTable() throws SQLException {
	//the two statements required for making table
	connect_function();
	statement = (Statement)connect.createStatement();
	String dropItemTable = "DROP TABLE IF EXISTS item";
	String createItemTable = "CREATE TABLE IF NOT EXISTS item" +
			"(itemID INTEGER, " +
			"itemTitle VARCHAR(50)," +
			"itemDescription VARCHAR(100), " +
			"itemDate DATE, " +
			"itemPrice INTEGER, " +
			"PRIMARY KEY (itemID)";
	
	try {
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
	connect_function();
	statement = (Statement)connect.createStatement();
	String addItem = "INSERT INTO item VALUES('123456789','Cactus','Pointy Plant','06/21/1998','Plants');"
			+"INSERT INTO item VALUES('987654321','Soccer Ball', 'A ball to kick','06/09/1997','Outdoors');"
			+"INSERT INTO item VALUES('121212121','Video Game','New Video Game', '01/01/2019','Electronics');"
			+"INSERT INTO item VALUES('123123123','Couch','Lounge Furniture','02/01/2019, 'Furniture');"
			+"INSERT INTO item VALUES('987987987','Spear','A thrusting weapon','03/01/2019,'Outdoors');"
			+"INSERT INTO item VALUES('678543987','Macbook','Apple Computer','06/21/1998','Electronics');"
			+"INSERT INTO item VALUES('444444444','Headphones', 'Headphones','06/10/2018','Electronics');"
			+"INSERT INTO item VALUES('000000001','Table','Dining Table', '05/22/2017','Furniture');"
			+"INSERT INTO item VALUES('666666666','Bible','Holy Book for Christians','12/25/2018, 'Books');"
			+"INSERT INTO item VALUES('101010101','Phone','Phone','10/10/2019,'Electronics');"
			;
	
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
	connect_function();
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
		disconnect();
	}
}
//Part 1 Requires About 10 tuples, so I just input 10 user stuff to show on the table
public void addUsers() throws SQLException {
	connect_function();
	//adding the items
	String addUsers = "INSERT INTO users VALUES('12345','hello123','Aaron1','Espere1','example1@gmail.com', 'Male','21');"
			+"INSERT INTO users VALUES('54321','goodbye123','Aaron2','Espere2','example2@gmail.com', 'Male','21');"
			+"INSERT INTO users VALUES('12121','hewwo123','Aaron3','Espere3','example3@gmail.com', 'Male','21');"
			+"INSERT INTO users VALUES('16436','trials123','Aaron4','Espere4','example4@gmail.com', 'Male','21');"
			+"INSERT INTO users VALUES('89231','laptop123','Aaron5','Espere5','example5@gmail.com', 'Male','21');"
			+"INSERT INTO users VALUES('00000','qwerty123','Aaron6','Espere6','example6@gmail.com', 'Male','21');"
			+"INSERT INTO users VALUES('12456','1234553123','Aaron7','Espere7','example7@gmail.com', 'Male','21');"
			+"INSERT INTO users VALUES('55555','Super123','Aaron8','Espere8','example8@gmail.com', 'Male','21');"
			+"INSERT INTO users VALUES('19284','Dont123','Aaron9','Espere9','example9@gmail.com', 'Male','21');"
			+"INSERT INTO users VALUES('11111','Secret123','Aaron10','Espere10','example10@gmail.com', 'Male','21');"
			;
	try {
		statement.executeUpdate(addUsers);
	}
	catch (SQLException e) {
		
	}
	finally {
		disconnect();
	}
}

public void addOneUser(String username, String password, String firstName, String lastName, String gender, String age) throws SQLException {
	//adding a singular user, this is to add the user who registers onto the server
	connect_function();
	String addUser = "INSERT INTO user VALUES('" + username + "','"
			+ password + "','"
			+ firstName + "','"
			+ lastName + "','"
			+ gender + "','"
			+ age + "');";
	try {
		statement.executeUpdate(addUser);
	}
	catch (SQLException e) {	
	}
	finally {
		disconnect();
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