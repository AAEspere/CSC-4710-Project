//Aaron Espere gb1962

/* InitDatabase covers the creation of the tables. This will create 
 * database, create the tables, and list the information in the tables */
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
	//OLD INIT BUTTON
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

	public InitDatabase() {
		
	}
	
//initialize connection to the Server
protected void connect_function() throws SQLException {
	if(connect == null || connect.isClosed()) {
		try {
			Class.forName("com.mysql.jdbc.Driver");
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

//create the Database
public void createDatabase() {
	try {
		connect_function();
		String dropDataBase = "DROP DATABASE IF EXISTS projectdb";
		String createDataBase = "CREATE DATABASE IF NOT EXISTS projectdb";
		
		preparedStatement = connect.prepareStatement(dropDataBase);
		preparedStatement = connect.prepareStatement(createDataBase);
	}
	catch(SQLException e) {
		System.out.print("Exception: "+e+"\n");
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
			"itemTitle VARCHAR(50) NOT NULL," +
			"itemDescription VARCHAR(100), " +
			"itemDate VARCHAR(50), " +
			"itemPrice INTEGER NOT NULL, " +
			"itemCategory VARCHAR(50), " +
			"PRIMARY KEY (itemID));";
	
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
	String addItem1 = "INSERT INTO item VALUES('123456789','Cactus','Pointy Plant','06/21/1998','20.00','Plants');";
	String addItem2 = "INSERT INTO item VALUES('987654321','Soccer Ball', 'A ball to kick','06/09/1997','3.00','Outdoors');";
	String addItem3 = "INSERT INTO item VALUES('121212121','Video Game','New Video Game', '01/01/2019','60.00','Electronics');";
	String addItem4 = "INSERT INTO item VALUES('123123123','Couch','Furniture','02/01/2019','300.00','Furniture');";
	String addItem5 = "INSERT INTO item VALUES('231455153','Couch','Furniture','02/01/2019','300.00','Furniture');";
	String addItem6 = "INSERT INTO item VALUES('555555555','Video Game','New Video Game', '01/01/2019','60.00','Electronics');";
	String addItem7 = "INSERT INTO item VALUES('423465674','Cactus','Pointy Plant','06/21/1998','20.00','Plants');";
	String addItem8 = "INSERT INTO item VALUES('343242351','Soccer Ball', 'A ball to kick','06/09/1997','3.00','Outdoors');";
	String addItem9 = "INSERT INTO item VALUES('654764567','Video Game','New Video Game', '01/01/2019','60.00','Electronics');";
	String addItem10 = "INSERT INTO item VALUES('235790456','Video Game','New Video Game', '01/01/2019','60.00','Electronics');";
	
	
	//adding this code makes the tables stop appearing
	/*String addItem4 ="INSERT INTO item VALUES('123123123','Couch','Lounge Furniture','02/01/2019, '300.00', 'Furniture');";
	String addItem5 ="INSERT INTO item VALUES('987987987','Spear','A thrusting weapon','03/01/2019,'1000.00','Outdoors');";
	String addItem6 ="INSERT INTO item VALUES('678543987','Macbook','Apple Computer','06/21/1998','1000.00','Electronics');";
	String addItem7 ="INSERT INTO item VALUES('444444444','Headphones', 'Headphones','06/10/2018','200.00','Electronics');";
	String addItem8 ="INSERT INTO item VALUES('000000001','Table','Dining Table', '05/22/2017','100.00','Furniture');";
	String addItem9 ="INSERT INTO item VALUES('666666666','Bible','Holy Book for Christians','1.00','12/25/2018, 'Books');";
	String addItem10 ="INSERT INTO item VALUES('101010101','Phone','Phone','10/10/2019,'899.00','Electronics');";*/
			
	
	try {
		statement.executeUpdate(addItem1);
		statement.executeUpdate(addItem2);
		statement.executeUpdate(addItem3);
		statement.executeUpdate(addItem4);
		statement.executeUpdate(addItem5);
		statement.executeUpdate(addItem6);
		statement.executeUpdate(addItem7);
		statement.executeUpdate(addItem8);
		statement.executeUpdate(addItem9);
		statement.executeUpdate(addItem10);
	}
	catch (SQLException e) {
		
	}
	finally {
		disconnect();
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
			"firstName VARCHAR(50) NOT NULL, " +
			"lastName VARCHAR(50) NOT NULL, " +
			"email VARCHAR(50) NOT NULL, " +
			"gender VARCHAR(10) NOT NULL," +
			"age INTEGER NOT NULL, " +
			"PRIMARY KEY (userID));";
	
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
	statement = (Statement)connect.createStatement();
	//adding the items
	String addUsers1 = "INSERT INTO users VALUES('12345','hello123','Aaron1','Espere1','example1@gmail.com', 'Male','21');";
	String addUsers2 = "INSERT INTO users VALUES('54321','goodbye123','Aaron2','Espere2','example2@gmail.com', 'Male','21');";
	String addUsers3 = "INSERT INTO users VALUES('12121','hewwo123','Aaron3','Espere3','example3@gmail.com', 'Male','21');";
	String addUsers4 = "INSERT INTO users VALUES('16436','trials123','Aaron4','Espere4','example4@gmail.com', 'Male','21');";
	String addUsers5 = "INSERT INTO users VALUES('89231','laptop123','Aaron5','Espere5','example5@gmail.com', 'Male','21');";
	String addUsers6 = "INSERT INTO users VALUES('00000','qwerty123','Aaron6','Espere6','example6@gmail.com', 'Male','21');";
	String addUsers7 = "INSERT INTO users VALUES('12456','1234553123','Aaron7','Espere7','example7@gmail.com', 'Male','21');";
	String addUsers8 = "INSERT INTO users VALUES('55555','Super123','Aaron8','Espere8','example8@gmail.com', 'Male','21');";
	String addUsers9 = "INSERT INTO users VALUES('19284','Dont123','Aaron9','Espere9','example9@gmail.com', 'Male','21');";
	String addUsers10 = "INSERT INTO users VALUES('11111','Secret123','Aaron10','Espere10','example10@gmail.com', 'Male','21');";
			
	try {
		statement.executeUpdate(addUsers1);
		statement.executeUpdate(addUsers2);
		statement.executeUpdate(addUsers3);
		statement.executeUpdate(addUsers4);
		statement.executeUpdate(addUsers5);
		statement.executeUpdate(addUsers6);
		statement.executeUpdate(addUsers7);
		statement.executeUpdate(addUsers8);
		statement.executeUpdate(addUsers9);
		statement.executeUpdate(addUsers10);
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
	statement = (Statement)connect.createStatement();
	String addUser = "INSERT INTO users VALUES('" + username + "','"
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

public void createReviewTable() throws SQLException {
	connect_function();
	statement = (Statement)connect.createStatement();
	//the two statements required for making table
	String dropReviewTable = "DROP TABLE IF EXISTS reviews";
	String createReviewTable = "CREATE TABLE IF NOT EXISTS reviews" +
			"(itemID INTEGER, " +
			"userID INTEGER, " +
			"score VARCHAR(10) NOT NULL, " +
			"remark VARCHAR(100));";
			//"CONSTRAINT IID FOREIGN KEY (itemID) REFERENCES item(itemID));";
			//"CONSTRAINT UID FOREIGN KEY (userID) REFERENCES user(userID), " +
			//"PRIMARY KEY (itemID, userID));";
	
	try {
	statement.executeUpdate(dropReviewTable);
	statement.executeUpdate(createReviewTable);
	}
	catch (SQLException e) {
	}
	finally {
		disconnect();
	}
}

//this is for adding 10 tuples into the reviews table 
public void addReviews() throws SQLException {
	connect_function();
	statement = (Statement)connect.createStatement();
	String addReviews1 = "INSERT INTO reviews VALUES('123456789','12345','Poor','Example Review');";
	String addReviews2 = "INSERT INTO reviews VALUES('444444444','12345','Excellent','Example Review');";
	String addReviews3 = "INSERT INTO reviews VALUES('6666666665','12345','Fair','Example Review');";
	String addReviews4 = "INSERT INTO reviews VALUES('101010101','12345','Excellent','Example Review');";
	String addReviews5 = "INSERT INTO reviews VALUES('123456789','89231','Excellent','Example Review');";
	String addReviews6 = "INSERT INTO reviews VALUES('444444444','89231','Excellent','Example Review');";
	String addReviews7 = "INSERT INTO reviews VALUES('123456789','00000','Excellent','Example Review');";
	String addReviews8 = "INSERT INTO reviews VALUES('444444444','00000','Fair','Example Review');";
	String addReviews9 = "INSERT INTO reviews VALUES('123456789','11111','Fair','Example Review');";
	String addReviews10 = "INSERT INTO reviews VALUES('444444444','55555','Fair','Example Review');";
	
	try {
		statement.executeUpdate(addReviews1);
		statement.executeUpdate(addReviews2);
		statement.executeUpdate(addReviews3);
		statement.executeUpdate(addReviews4);
		statement.executeUpdate(addReviews5);
		statement.executeUpdate(addReviews6);
		statement.executeUpdate(addReviews7);
		statement.executeUpdate(addReviews8);
		statement.executeUpdate(addReviews9);
		statement.executeUpdate(addReviews10);
	}
	catch (SQLException e) {
		
	}
	finally {
		disconnect();
	}
}

//this function is for checking if the correct username and password was used for login
public boolean loginCheck(String username, String pass) throws SQLException {
	connect_function();
	statement = (Statement)connect.createStatement();
	String listUsers = "SELECT * FROM users";
	ResultSet resultSet = statement.executeQuery(listUsers);
	while(resultSet.next()) {
		if(resultSet.getString("username").equals(username) 
		&& resultSet.getString("password").contentEquals(pass)) {
			return true;
		}
	}
	return false;
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
		String date = resultSet.getString("itemDate");
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
	String sql = "SELECT * FROM users";
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
		
		users users = new users(userID, pass, firstName, lastName, email, gender,age);
		listUsers.add(users);		
	}
	resultSet.close();
	statement.close();
	disconnect();
	return listUsers;
}

//this is for listing the reviews
public List<reviews> listAllReviews() throws SQLException{
	List<reviews> listReviews = new ArrayList<reviews>();
	String sql = "SELECT * FROM reviews";
	connect_function();
	statement = (Statement) connect.createStatement();
	ResultSet resultSet = statement.executeQuery(sql);
	
	while(resultSet.next()){
	
		int itemID = resultSet.getInt("itemID");
		int userID = resultSet.getInt("userID");
		String score = resultSet.getString("score");
		String remark = resultSet.getString("remark");
		
		reviews review = new reviews(itemID, userID, score, remark);
		listReviews.add(review);		
	}
	resultSet.close();
	statement.close();
	disconnect();
	return listReviews;
}

protected void disconnect() throws SQLException {
	if (connect != null && !connect.isClosed()) {
		connect.close();
	}
}

}