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

import java.time.LocalDateTime;

//code for initializing the Database, this includes creating the tables
//and adding the original 10 tuples into each table
//this originally had all of the functionality included but was separated to improve
//readability
public class InitDatabase {
	
	private Connection connect = null;
	private Statement statement = null;
	private PreparedStatement preparedStatement = null;

	public InitDatabase() {
		
	}
	
	/*---------------------------------------------------------------
	 * 
	 * PROJECT 1 CODE
	 * 
	 * --------------------------------------------------------------
	 */
	
protected void connect_function() throws SQLException {
	if(connect == null || connect.isClosed()) {
		try {
			Class.forName("com.mysql.jdbc.Driver");
		}
		catch (ClassNotFoundException e) {
			throw new SQLException(e);
		}
		//changed connect to match with what project said i should use
		connect = (Connection) DriverManager
				.getConnection("jdbc:mysql://127.0.0.1:3306/projectdb?"
	  			          + "user=root&password=Superiormelee98");
		System.out.println(connect);
	}
}

protected void disconnect() throws SQLException {
	if (connect != null && !connect.isClosed()) {
		connect.close();
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

//tables are not successfully dropping because there are foreign key constraints
//I just made separate tables for dropping
public void dropAllTables() throws SQLException {
	
	String dropReviewTable = "DROP TABLE IF EXISTS reviews;";
	String dropUsersTable = "DROP TABLE IF EXISTS users;";
	String dropItemTable = "DROP TABLE IF EXISTS item;";
	String dropFavoriteItemsTable = "DROP TABLE IF EXISTS favoriteItem;";
	String dropFavoriteUsersTable = "DROP TABLE IF EXISTS favoriteUser";
	
	connect_function();
	statement = (Statement)connect.createStatement();
	
	statement.executeUpdate(dropFavoriteItemsTable);
	statement.executeUpdate(dropFavoriteUsersTable);
	statement.executeUpdate(dropReviewTable);
	statement.executeUpdate(dropUsersTable);
	statement.executeUpdate(dropItemTable);

	connect.close();
}

/*-------------------------------------------------------------------
 * CREATING AND ADDING TUPLES
 * ------------------------------------------------------------------*/
public void createItemTable() throws SQLException {
	//the two statements required for making table
	connect_function();
	statement = (Statement)connect.createStatement();
	String createItemTable = "CREATE TABLE IF NOT EXISTS item" +
			//Project Part 2, editing statement so that AUTO_INCREMENT is added
			//Project Part 2, editing statement so that username is incorporated for all items
			"(username VARCHAR(50) NOT NULL, " + 
			"itemID INTEGER AUTO_INCREMENT, " +
			"itemTitle VARCHAR(50) NOT NULL," +
			"itemDescription VARCHAR(100), " +
			"itemDate DATE, " +
			"itemPrice DOUBLE NOT NULL, " +
			"itemCategory VARCHAR(50), " +
			"PRIMARY KEY (itemID));";
	try {
	statement.executeUpdate(createItemTable);
	}
	catch(SQLException e) {
	}
	finally {
		connect.close();
	}
}
public void addItems() throws SQLException {
	connect_function();
	statement = (Statement)connect.createStatement();
	String addItem1 = "INSERT INTO item VALUES('AA1','123456789','Cactus','Pointy Plant','1998-6-21','20.00','plants');";
	String addItem2 = "INSERT INTO item VALUES('AA2','987654321','Soccer Ball', 'A ball to kick','1997-06-09','3.00','outdoors');";
	String addItem3 = "INSERT INTO item VALUES('AA3','121212121','Video Game','New Video Game', '2019-01-01','60.00','electronics');";
	String addItem4 = "INSERT INTO item VALUES('AA4','123123123','Coffee Table','Coffee Table','2019-02-01','100.00','furniture');";
	String addItem5 = "INSERT INTO item VALUES('AA5','231455153','Couch','Furniture','2019-02-01','300.00','furniture');";
	String addItem6 = "INSERT INTO item VALUES('AA6','555555555','Nintendo Switch','Newest Nintendo System', '2019-04-20','300.00','electronics,video games');";
	String addItem7 = "INSERT INTO item VALUES('AA7','423465674','Milk','2% Milk','2005-05-23','2.00','food,produce');";
	String addItem8 = "INSERT INTO item VALUES('AA8','343242351','Roomba', 'Self Operating Vaccuum Cleaner','2019-12-2','3.00','electronics,home');";
	String addItem9 = "INSERT INTO item VALUES('AA9','654764567','Macbook Air','2017 Macbook Air', '2019-12-3','649.00','electronics,computers');";
	String addItem10 = "INSERT INTO item VALUES('AA10','235790456','Book','Book', '2019-01-01','10.00','reading');";		
	String addItem11 = "INSERT INTO item VALUES('AA2','434253123','Box','Box', '2019-01-01','10.00','reading');";	
	String addItem12 = "INSERT INTO item VALUES('AA2','345612345','Iphone X', 'Newest Iphone','2019-06-21','1000.00','mobile');";
	String addItem13 = "INSERT INTO item VALUES('AA2','574835261','40 Inch Smart Tv', 'Smart Television','2019-06-21','300.00','electronics');";
	
	
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
		statement.executeUpdate(addItem11);
		statement.executeUpdate(addItem12);
		statement.executeUpdate(addItem13);
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
	String createUserTable = "CREATE TABLE IF NOT EXISTS users" +
			//Project 3 -- edited users so username is added
			"(username VARCHAR(50) NOT NULL," + 
			"userID INTEGER AUTO_INCREMENT, " +
			"pass VARCHAR(20) NOT NULL, " +
			"firstName VARCHAR(50) NOT NULL, " +
			"lastName VARCHAR(50) NOT NULL, " +
			"email VARCHAR(50) NOT NULL, " +
			"gender VARCHAR(10) NOT NULL," +
			"age INTEGER NOT NULL, " +
			"UNIQUE (username), " +
			"PRIMARY KEY (userID));";
	
	try {
	statement = (Statement)connect.createStatement();
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
	String addUsers1 = "INSERT INTO users VALUES('AA1','12345','hello123','Aaron1','Espere1','example1@gmail.com', 'Male','21');";
	String addUsers2 = "INSERT INTO users VALUES('AA2','54321','goodbye123','Aaron2','Espere2','example2@gmail.com', 'Male','21');";
	String addUsers3 = "INSERT INTO users VALUES('AA3','12121','hewwo123','Aaron3','Espere3','example3@gmail.com', 'Male','21');";
	String addUsers4 = "INSERT INTO users VALUES('AA4','16436','trials123','Aaron4','Espere4','example4@gmail.com', 'Male','21');";
	String addUsers5 = "INSERT INTO users VALUES('AA5','89231','laptop123','Aaron5','Espere5','example5@gmail.com', 'Male','21');";
	String addUsers6 = "INSERT INTO users VALUES('AA6','00000','qwerty123','Aaron6','Espere6','example6@gmail.com', 'Male','21');";
	String addUsers7 = "INSERT INTO users VALUES('AA7','12456','1234553123','Aaron7','Espere7','example7@gmail.com', 'Male','21');";
	String addUsers8 = "INSERT INTO users VALUES('AA8','55555','Super123','Aaron8','Espere8','example8@gmail.com', 'Male','21');";
	String addUsers9 = "INSERT INTO users VALUES('AA9','19284','Dont123','Aaron9','Espere9','example9@gmail.com', 'Male','21');";
	String addUsers10 = "INSERT INTO users VALUES('AA10','11111','Secret123','Aaron10','Espere10','example10@gmail.com', 'Male','21');";
	String addUsers11 = "INSERT INTO users VALUES('root','00001','pass1234','Admin1','Admin1','admin@gmail.com','Male','30');";
			
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
		statement.executeUpdate(addUsers11);
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
	String createReviewTable = "CREATE TABLE IF NOT EXISTS reviews" +
			"(username VARCHAR(50) NOT NULL, " +
			"itemID INTEGER NOT NULL, " +
			"itemTitle VARCHAR(50) NOT NULL, " +
			"score VARCHAR(10) NOT NULL, " +
			"remark VARCHAR(100) NOT NULL," +
			"CONSTRAINT f_key_itemID FOREIGN KEY (itemID) REFERENCES item(itemID)," +
			"CONSTRAINT f_key_username FOREIGN KEY (username) REFERENCES users(username));";
	try {
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
	String addReviews1 = "INSERT INTO reviews VALUES('AA4','121212121','Video Game','Poor','Example Review');";
	String addReviews2 = "INSERT INTO reviews VALUES('AA4','555555555','Nintendo Switch','Excellent','Example Review');";
	String addReviews3 = "INSERT INTO reviews VALUES('AA2','654764567','Macbook Air','Excellent','Example Review');";
	String addReviews4 = "INSERT INTO reviews VALUES('AA2','123123123','Coffee Table','Excellent','Example Review');";
	String addReviews5 = "INSERT INTO reviews VALUES('AA9','987654321','Soccer Ball','Excellent','Example Review');";
	String addReviews6 = "INSERT INTO reviews VALUES('AA1','555555555','Nintendo Switch','Excellent','Example Review');";
	String addReviews7 = "INSERT INTO reviews VALUES('AA1','121212121','Video Game','Poor','Example Review');";
	String addReviews8 = "INSERT INTO reviews VALUES('AA6','231455153','Couch','Fair','Example Review');";
	String addReviews9 = "INSERT INTO reviews VALUES('AA3','423465674','Milk','Poor','SPOILED');";
	String addReviews10 = "INSERT INTO reviews VALUES('AA1','987654321','Soccer Ball','Excellent','Example Review');";
	String addReviews11 = "INSERT INTO reviews VALUES('AA10','555555555','Nintendo Switch','Excellent','Great System! My kids loved it.');";
	String addReviews12 = "INSERT INTO reviews VALUES('AA1','434253123','Box','Poor','Cannot live inside it');";
	String addReviews13 = "INSERT INTO reviews VALUES('AA3','555555555','Nintendo Switch','Excellent','Great System I get to play old games!');";
	String addReviews14 = "INSERT INTO reviews VALUES('AA7','123123123','Coffee Table','Poor','This table came with a broken leg, I am very unsatisfied');";
	
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
		statement.executeUpdate(addReviews11);
		statement.executeUpdate(addReviews12);
		statement.executeUpdate(addReviews13);
		statement.executeUpdate(addReviews14);
	}
	catch (SQLException e) {
		
	}
	finally {
		disconnect();
	}
}

/*-------------------------------------------------------------------------------
 * LISTING THE RESULTS OF THE TABLES
 --------------------------------------------------------------------------------*/
//I noticed this code was being used in multiple places throughout the project,
//So I refactored it into one spot
public item resultSetItem(ResultSet resultSet) throws SQLException {
	
	String username = resultSet.getString("username");
	int itemID = resultSet.getInt("itemID");
	String itemTitle = resultSet.getString("itemTitle");
	String itemDescription = resultSet.getString("itemDescription");
	String date = resultSet.getString("itemDate");
	double itemPrice = resultSet.getDouble("itemPrice");
	String itemCategory = resultSet.getString("itemCategory");
	
	item items = new item(username,itemID, itemTitle , itemDescription , date , itemPrice ,itemCategory);
	
	return items;
}

//this is for listing the items table
public List<item> listAllItems() throws SQLException{
	List<item> listItems = new ArrayList<item>();
	String sql = "SELECT * FROM item";
	connect_function();
	statement = (Statement) connect.createStatement();
	ResultSet resultSet = statement.executeQuery(sql);
	
	//listItems = resultListItem(resultSet);
	while(resultSet.next()) {
		listItems.add(resultSetItem(resultSet));
	}
	
	resultSet.close();
	statement.close();
	disconnect();
	return listItems;
}

public users resultSetUser(ResultSet resultSet) throws SQLException {
	
	int userID = resultSet.getInt("userID");
	String username = resultSet.getString("username");
	String pass = resultSet.getString("pass");
	String firstName = resultSet.getString("firstName");
	String lastName = resultSet.getString("lastName");
	String email = resultSet.getString("email");
	String gender = resultSet.getString("gender");
	int age = resultSet.getInt("age");
	
	users users = new users(userID, username, pass, firstName, lastName, email, gender,age);
	return users;
	
}

//this is for listing the users
public List<users> listAllUsers() throws SQLException{
	List<users> listUsers = new ArrayList<users>();
	String sql = "SELECT * FROM users";
	connect_function();
	statement = (Statement) connect.createStatement();
	ResultSet resultSet = statement.executeQuery(sql);
	
	while(resultSet.next()){
		listUsers.add(resultSetUser(resultSet));		
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
		
		String username = resultSet.getString("username");
		int itemID = resultSet.getInt("itemID");
		String itemTitle = resultSet.getString("itemTitle");
		String score = resultSet.getString("score");
		String remark = resultSet.getString("remark");
		
		reviews review = new reviews(username, itemID, itemTitle, score, remark);
		listReviews.add(review);		
	}
	resultSet.close();
	statement.close();
	disconnect();
	return listReviews;
}
/*-------------------------------------------------------------------------------
 * LOGIN OR REGISTER FUNCTIONS
 --------------------------------------------------------------------------------*/

//this function is for checking if the correct username and password was used for login
public boolean loginCheck(String email, String pass) throws SQLException {
	connect_function();
	statement = (Statement)connect.createStatement();
	String listUsers = "SELECT * FROM users";
	ResultSet resultSet = statement.executeQuery(listUsers);
	//if matches both given email and password, then return true, because successful log in
	while(resultSet.next()) {
		if(resultSet.getString("email").equals(email) 
		&& resultSet.getString("pass").equals(pass)) {
			return true;
		}
	}
	//if there isn't anything to return, then just return false
	return false;
}

//When you register, 
public boolean addOneUser(users user) throws SQLException {
	//adding a singular user, this is to add the user who registers onto the server
	connect_function();         
	String sql = "INSERT INTO users(username, pass, firstName, lastName, email, gender, age) VALUES (?,?,?,?,?,?,?)";
	preparedStatement = (PreparedStatement) connect.prepareStatement(sql);
	preparedStatement.setString(1, user.username);
	preparedStatement.setString(2, user.pass);
	preparedStatement.setString(3, user.firstName);
	preparedStatement.setString(4, user.lastName);
	preparedStatement.setString(5, user.email);
	preparedStatement.setString(6, user.gender);
	preparedStatement.setInt(7, user.age);
	
    boolean rowInserted = preparedStatement.executeUpdate() > 0;
    preparedStatement.close();
//    disconnect();
    return rowInserted;
}

//getting the user's ID and username
public int getCurrentID(String email, String pass) throws SQLException {
	
	connect_function();
	String sql = "SELECT * FROM users WHERE email = ? AND pass = ?";
	preparedStatement = (PreparedStatement) connect.prepareStatement(sql);
	preparedStatement.setString(1,email);
	preparedStatement.setString(2,pass);
	ResultSet resultSet = preparedStatement.executeQuery();
	//Should return one result, which is the one you need to grab the userID from
	resultSet.next();
	int userID = resultSet.getInt("userID");
	return userID;
}

//getting username
public String getCurrentUsername(String email, String pass) throws SQLException {
	connect_function();
	statement = (Statement)connect.createStatement();
	String sql = "SELECT * FROM users WHERE email = ? AND pass = ?";
	preparedStatement = (PreparedStatement) connect.prepareStatement(sql);
	preparedStatement.setString(1,email);
	preparedStatement.setString(2,pass);
	ResultSet resultSet = preparedStatement.executeQuery();
			resultSet.next();
			String username = resultSet.getString("username");
			return username;
		//}
//	}
	//return null;
}

/*-------------------------------------------------------------------------------
 * 
 * PROJECT PART 2 CODE
 * 
 --------------------------------------------------------------------------------*/

/*-------------------------------------------------------------------------------
 * REQUIREMENT 1 - INSERTION OF ITEM
 ------------------------------------------------------------------------------*/

//Project Part 2 Requirement 1 -- inserting an item
public boolean insertItem(item item) throws SQLException {
	
	//need to insert conditional which will limit the amount of times a person can post a review
	connect_function();         
	String sql = "INSERT INTO item(username, itemTitle, itemDescription, itemDate, itemPrice, itemCategory) VALUES (?,?,?,?,?,?)";
	preparedStatement = (PreparedStatement) connect.prepareStatement(sql);
	//Project 2 requirement for favorite users, need to associate each item with a user ID
	preparedStatement.setString(1, item.username);
	preparedStatement.setString(2, item.itemTitle);
	preparedStatement.setString(3, item.itemDescription);
	//gets the local time. When this function is called, that means the user has submitted an item
	//and will take the date of when that item was created
	preparedStatement.setDate(4,java.sql.Date.valueOf(java.time.LocalDate.now()));
	preparedStatement.setDouble(5,item.itemPrice);
	preparedStatement.setString(6,item.itemCategory);
	
  boolean rowInserted = preparedStatement.executeUpdate() > 0;
  preparedStatement.close();
  return rowInserted;
}

/*---------------------------------------------------------------------------------
 * REQUIREMENT 2 - SEARCH INTERFACE
 ----------------------------------------------------------------------------------*/
//Project Part 2 --> Search function which displays all items under a certain category
//This should be similar to the listItems function because instead of listing all items
//you are listing all items that match a specific category
//STILL WORKS CORRECTLY -- NO NEED TO CHANGE
public List<item> searchItem(String category) throws SQLException{
	
	List<item> listItems = new ArrayList<item>();
	String sql = "SELECT * FROM item";
	connect_function();
	statement = (Statement) connect.createStatement();
	ResultSet resultSet = statement.executeQuery(sql);
	
	while(resultSet.next()){
	//so scan all of the categories, and if one matches an item then add it onto the items
	//that will be listed
		
		//parse the string based on comma separation
		//I used comma separation because 
		String[] parsedCategories = resultSet.getString("itemCategory").split("\\s*,\\s*");
		
		//so use for loop to scan through parsed categories array
		for (int i = 0; i < parsedCategories.length; i++) {
			
			//if the category matches the parsed ones, then add that to the list
			//of items to display
			if(category.equals(parsedCategories[i])) {
				listItems.add(resultSetItem(resultSet));
				}	
		}
	}
	resultSet.close();
	statement.close();
	disconnect();
	return listItems;
}

/*-----------------------------------------------------------------------------------
 * REQUIREMENT 3 - WRITING A REVIEW
 ------------------------------------------------------------------------------------*/
public boolean insertReview(reviews reviews) throws SQLException{
	connect_function();         
	String sql = "INSERT INTO reviews(username, itemID, itemTitle, score, remark) "
			+ "VALUES(?,?,?,?,?)";
	
	preparedStatement = (PreparedStatement) connect.prepareStatement(sql);
	preparedStatement.setString(1, reviews.username);
	preparedStatement.setInt(2, reviews.itemID);
	preparedStatement.setString(3, reviews.itemTitle);
	preparedStatement.setString(4, reviews.score);
	preparedStatement.setString(5, reviews.remark);
//	preparedStatement.executeUpdate();
	
    boolean rowInserted = preparedStatement.executeUpdate() > 0;
    preparedStatement.close();
//    disconnect();
    return rowInserted;
}

//getting the item title because I can only pass the itemID;
public String getItemName(int itemID) throws SQLException {
	
	connect_function();
	String itemTitle;
	String sql = "SELECT * FROM item WHERE itemID = ?";
	preparedStatement = (PreparedStatement) connect.prepareStatement(sql);
	preparedStatement.setInt(1, itemID);
	ResultSet resultSet = preparedStatement.executeQuery();
	resultSet.next();
	itemTitle = resultSet.getString("itemTitle");
	return itemTitle;
}

public String checkSameUsername(int itemID) throws SQLException {
	
	connect_function();
	String sql = "SELECT * FROM item WHERE itemID = ?";
	preparedStatement = (PreparedStatement) connect.prepareStatement(sql);
	preparedStatement.setInt(1, itemID);
	ResultSet resultSet = preparedStatement.executeQuery();
	resultSet.next();
	String username = resultSet.getString("username");
	return username;
}

/*-------------------------------------------------------------------------------------
 * REQUIREMENT 4 - 
 * Implement the functionality of add/delete the favorite sellers list. 
 * One can also click on a seller in the list and browse his/her profile: name, items posted by the seller, etc.
 --------------------------------------------------------------------------------------*/
public void createFavoriteUsersTable() throws SQLException {
	connect_function();
	statement = (Statement)connect.createStatement();

	String createFavoriteUsersTable = "CREATE TABLE IF NOT EXISTS favoriteUser" +
			"(currentUserID INTEGER NOT NULL, " +
			"favoriteUserID INTEGER NOT NULL, " +
			"favoriteUserUsername VARCHAR(50) NOT NULL," +
			"CONSTRAINT f_key_currentUserID FOREIGN KEY (currentUserID) REFERENCES users(userID)," +
			"CONSTRAINT f_key_favoriteUserID FOREIGN KEY (favoriteUserID) REFERENCES users(userID)," +
			"CONSTRAINT f_key_favoriteUserUsername FOREIGN KEY (favoriteUserUsername) REFERENCES users(username)" +
			");";
	
	try {
		statement.executeUpdate(createFavoriteUsersTable);
	}
	
	catch(SQLException e) {
		
	}
	finally {
		connect.close();
	}
}

public void addFavoriteUsers() throws SQLException {
	connect_function();
	statement = (Statement)connect.createStatement();
	
	String addUser = "INSERT INTO favoriteUser VALUES('12345','54321','AA2');";
	String addUser2 = "INSERT INTO favoriteUser VALUES('12345','00000','AA6');";
	String addUser3 = "INSERT INTO favoriteUser VALUES('12121','00000','AA6');";
	String addUser4 = "INSERT INTO favoriteUser VALUES('89231','5555','AA8');";
	String addUser5 = "INSERT INTO favoriteUser VALUES('00000','54321','AA2');";
	String addUser6 = "INSERT INTO favoriteUser VALUES('00000','11111','AA10');";
	String addUser7 = "INSERT INTO favoriteUser VALUES('16436','19284','AA9');";
	String addUser8 = "INSERT INTO favoriteUser VALUES('12456','54321','AA2');";
	String addUser9 = "INSERT INTO favoriteUser VALUES('55555','54321','AA2');";
	String addUser10 = "INSERT INTO favoriteUser VALUES('12345','12456','AA7');";
	
	statement.executeUpdate(addUser);
	statement.executeUpdate(addUser2);
	statement.executeUpdate(addUser3);
	statement.executeUpdate(addUser4);
	statement.executeUpdate(addUser5);
	statement.executeUpdate(addUser6);
	statement.executeUpdate(addUser7);
	statement.executeUpdate(addUser8);
	statement.executeUpdate(addUser9);
	statement.executeUpdate(addUser10);
}

public String getfavUsername(int userID) throws SQLException {
	
	connect_function();
	String username;
	String sql = "SELECT * FROM users WHERE userID = ?";
	preparedStatement = (PreparedStatement) connect.prepareStatement(sql);
	preparedStatement.setInt(1, userID);
	ResultSet resultSet = preparedStatement.executeQuery();
	resultSet.next();
	username = resultSet.getString("username");
	return username;
}

public boolean addFavoriteUser(favoriteUser favUser) throws SQLException {
	connect_function();
	/*get the current UserID and the fav UserID and add to favoriteUser table */
	String sql = "INSERT INTO favoriteUser(currentUserID, favoriteUserID, favoriteUserUsername) VALUES (?,?,?)";
	preparedStatement = (PreparedStatement) connect.prepareStatement(sql);
	preparedStatement.setInt(1, favUser.userID);
	preparedStatement.setInt(2, favUser.favUserID);
	preparedStatement.setString(3, favUser.favUserUsername);
	boolean rowInserted = preparedStatement.executeUpdate() > 0;
	preparedStatement.close();
	return rowInserted;
}

public List<favoriteUser> listFavoriteUsers(int currentUserID) throws SQLException {
	
	connect_function();
	List<favoriteUser> favUsers = new ArrayList<favoriteUser>();
	String sql = "SELECT * FROM favoriteUser WHERE currentUserID = ?";
	preparedStatement = (PreparedStatement) connect.prepareStatement(sql);
	preparedStatement.setInt(1, currentUserID);
	ResultSet resultSet = preparedStatement.executeQuery();
	
	while(resultSet.next()) {
		
		int userID = resultSet.getInt("currentUserID");
		int favUserID = resultSet.getInt("favoriteUserID");
		String favUserUsername = resultSet.getString("favoriteUserUsername");
		favoriteUser fav = new favoriteUser(userID, favUserID, favUserUsername);
		favUsers.add(fav);
	}
	
	resultSet.close();
	statement.close();
	disconnect();
	return favUsers;
	
}

//Using SQL to handle the creation and deletion of favorite users/items,
//So I think I need to make a separate table in order to add and delete item ID's
public void createFavoriteItemsTable() throws SQLException {
	
	connect_function();
	statement = (Statement)connect.createStatement();
	String createFavoriteItemsTable = "CREATE TABLE IF NOT EXISTS favoriteItem" +
			
			//Since I am using a separate table, and am using itemID to DELETE the item
			//this table will need the userID (the current user) and the itemID(of the item
			//they selected)
			"(userID INTEGER NOT NULL, " +
			"itemID INTEGER NOT NULL, " +
			"CONSTRAINT f_key_favuserID FOREIGN KEY (userID) REFERENCES users(userID)," +
			"CONSTRAINT f_key_favitemID FOREIGN KEY (itemID) REFERENCES item(itemID)" +
			");";
	
	try {
	statement.executeUpdate(createFavoriteItemsTable);
	}
	
	catch(SQLException e) {
	}
	finally {
		connect.close();
	}
}

public boolean addFavoriteItem(favoriteItem favoriteItem) throws SQLException{
	connect_function();
	//get the current UserID and the itemID they selected and add to the 
	//favorite item table
	String sql = "INSERT INTO favoriteItem(userID, itemID) VALUES (?,?)";
	preparedStatement = (PreparedStatement) connect.prepareStatement(sql);
	preparedStatement.setInt(1, favoriteItem.userID);
	preparedStatement.setInt(2, favoriteItem.itemID);
	boolean rowInserted = preparedStatement.executeUpdate() > 0;
	preparedStatement.close();
	//return true and redirect with successful add message, or return false
	//if fails and redirect with error message
	return rowInserted;
}

public ArrayList<Integer> getUserFavItemID(int userID) throws SQLException {
	
	ArrayList<Integer> favItemID = new ArrayList<Integer>();
	connect_function();
	String sql = "SELECT * FROM favoriteItem WHERE userID = ?";
	preparedStatement = (PreparedStatement) connect.prepareStatement(sql);
	preparedStatement.setInt(1, userID);
	ResultSet resultSet = preparedStatement.executeQuery();
	while (resultSet.next()) {
		//get the Item ID's that are currently associated with the 
		//current user ID under favorites
		favItemID.add(resultSet.getInt("itemID"));
	}
	preparedStatement.close();
	resultSet.close();
	disconnect();
	
	return favItemID;
}

public String getUserName(int userID) throws SQLException {
	
	connect_function();
	String sql = "SELECT username FROM users WHERE userID = ?";
	preparedStatement = (PreparedStatement) connect.prepareStatement(sql);
	preparedStatement.setInt(1, userID);
	ResultSet resultSet = preparedStatement.executeQuery();
	resultSet.next();
	String username = resultSet.getString("username");
	return username;
}

public users getUserProfile(int userID) throws SQLException {
	
	connect_function();
	String sql = "SELECT * FROM users WHERE userID = ?";
	preparedStatement = (PreparedStatement) connect.prepareStatement(sql);
	preparedStatement.setInt(1, userID);
	ResultSet resultSet = preparedStatement.executeQuery();
	resultSet.next();
	users userprofile = resultSetUser(resultSet);
	
	return userprofile;
}

//listing items for a userProfile
public List<item> listUserItems(String username) throws SQLException {
		List<item> userItems = new ArrayList<item>();
		String sql = "SELECT * FROM item WHERE username = ?";
		connect_function();
		statement = (Statement) connect.createStatement();
		preparedStatement = (PreparedStatement) connect.prepareStatement(sql);
		preparedStatement.setString(1, username);
		ResultSet resultSet = preparedStatement.executeQuery();
		
		while(resultSet.next()) {
			userItems.add(resultSetItem(resultSet));
		}
		
		return userItems;
}

public List<reviews> listUserReviews(String userprofile) throws SQLException {
	List<reviews> userReviews = new ArrayList<reviews>();
	String sql = "SELECT * FROM reviews WHERE username = ?";
	connect_function();
	statement = (Statement) connect.createStatement();
	preparedStatement = (PreparedStatement) connect.prepareStatement(sql);
	preparedStatement.setString(1, userprofile);
	ResultSet resultSet = preparedStatement.executeQuery();
	while(resultSet.next()) {
		String username = resultSet.getString("username");
		int itemID = resultSet.getInt("itemID");
		String itemTitle = resultSet.getString("itemTitle");
		String score = resultSet.getString("score");
		String remark = resultSet.getString("remark");
		reviews review = new reviews(username, itemID, itemTitle, score, remark);
		userReviews.add(review);	
	}
	
	return userReviews;
}

public List<item> listFavoriteItems(ArrayList<Integer> favitemID) throws SQLException {
	
	List<item> favItems = new ArrayList<item>();
	String sql = "SELECT * FROM item";
	connect_function();
	statement = (Statement) connect.createStatement();
	ResultSet resultSet = statement.executeQuery(sql);
	
	while(resultSet.next()) {
		
		for(int i = 0; i < favitemID.size(); i++) {
			
			if(favitemID.get(i) == resultSet.getInt("itemID")) {
				//need to edit this to include the username that posted this item
				
				favItems.add(resultSetItem(resultSet));
				
				//since itemID's are unique you can go to the next iteration
				//this will somewhat shorten the realtime execution but this
				//is still O(n^4)
				continue;
			}
		}
	}
	resultSet.close();
	statement.close();
	disconnect();
	return favItems;
}

//still works correctly but need to sort by category
public List<item> sortExpensive() throws SQLException {
	
	List<item> sortExpensive = new ArrayList<item>();
	String sql = "SELECT * FROM item ORDER BY itemPrice DESC";
	connect_function();
	statement = (Statement) connect.createStatement();
	ResultSet resultSet = statement.executeQuery(sql);
	
	//this will go through the table and since they are sorted already
	//so just prints the items

	while(resultSet.next()) {
		sortExpensive.add(resultSetItem(resultSet));
	}
	
	resultSet.close();
	statement.close();
	disconnect();
	
	
	return sortExpensive;
}

/*----------------------------------------------------------------------------------
 * REQUIREMENT 5 -  Implement the functionality of add/delete the favorite items list.   
 * One can browse each item in the list easily.
 ----------------------------------------------------------------------------------*/
public boolean deleteUser(int favoriteUserID, int currentUserID) throws SQLException {
    String sql = "DELETE FROM favoriteUser WHERE favoriteUserID = ? AND currentUserID = ?";        
    connect_function();
     
    preparedStatement = (PreparedStatement) connect.prepareStatement(sql);
    preparedStatement.setInt(1, favoriteUserID);
    preparedStatement.setInt(2, currentUserID);
     
    boolean rowDeleted = preparedStatement.executeUpdate() > 0;
    preparedStatement.close();
//    disconnect();
    return rowDeleted;  
}

public boolean deleteItem(int itemID, int userID) throws SQLException {
    String sql = "DELETE FROM favoriteItem WHERE itemID = ? AND userID = ?";        
    connect_function();
     
    preparedStatement = (PreparedStatement) connect.prepareStatement(sql);
    preparedStatement.setInt(1, itemID);
    preparedStatement.setInt(2, userID);
     
    boolean rowDeleted = preparedStatement.executeUpdate() > 0;
    preparedStatement.close();
//    disconnect();
    return rowDeleted;  
}
/*---------------------------------------------------------------------------------
 * 
 * Project Part 3 Code
 * 
 ----------------------------------------------------------------------------------*/

/*THINGS I NEEDED TO FIX IN THE DEMONSTRATION
 * REQUIREMENT 1 
 * REQUIREMENT 3
 * REQUIREMENT 4
 * REQUIREMENT 10
 */

public boolean checkDuplicateUsername(String username) throws SQLException {
	
	String sql = "SELECT * FROM users";
	connect_function();
	statement = (Statement) connect.createStatement();
	ResultSet resultSet = statement.executeQuery(sql);
	
	while(resultSet.next()) {
		String currentUsername = resultSet.getString("username");
		if(currentUsername == username) {
			return true;
		}	
	}
	//if you get to this part, then the emails were not duplicates, which means that 
	return false;
}

public boolean checkDuplicateEmail(String email) throws SQLException {
	
	String sql = "SELECT * FROM users";
	connect_function();
	statement = (Statement) connect.createStatement();
	ResultSet resultSet = statement.executeQuery(sql);
	
	while(resultSet.next()) {
		String currentEmail = resultSet.getString("email");
		if(currentEmail == email) {
			return true;
		}
	}
	return false;
}
/*---------------------------------------------------------------
 * REQUIREMENT 2 -  List the users who posted at least two items that are posted on the same day, 
 * one has a category of X and another has a category of Y. In terms of user interface, 
 * you will implement two text fields, so that you can input one category into each text field 
 * and the search will return the user (or users) who (the same user) posted two different items
 *  on the same day, such that one item has a category in the first text field and the other 
 *  has a category in the second text field.
 ---------------------------------------------------------------*/

public List<users> sameDay(String category1, String category2) throws SQLException {
	List<users>listUsers = new ArrayList<users>();
	String sql = "SELECT DISTINCT u.* " +
			"FROM users u, item item1, item item2 " +
			"WHERE item1.itemID != item2.itemID AND item1.itemDate = item2.itemDate " +
			"AND item1.username = item2.username " +
			"AND item1.itemCategory = ? " +
			"AND item2.itemCategory = ? " +
			"AND item1.username = u.username;";
	connect_function();
	preparedStatement = (PreparedStatement) connect.prepareStatement(sql);
	preparedStatement.setString(1,category1);
	preparedStatement.setString(2,category2);
	ResultSet resultSet = preparedStatement.executeQuery();
	
	while(resultSet.next()){
		listUsers.add(resultSetUser(resultSet));		
	}
	resultSet.close();
	statement.close();
	disconnect();
	return listUsers;
}


/*----------------------------------------------------------------------------------
 * REQUIREMENT 3 - List all the items posted by user X, such that all the comments are �Excellent� or �good� for these items 
 * (in other words, these items must have comments but these items don�t have any other kinds of comments such as �bad� or �fair� comments).
 *  User X is arbitrary and will be determined by the instructor.
 ----------------------------------------------------------------------------------*/
public List<item> excellentGoodComments(String username) throws SQLException {
	List<item> excellentGood = new ArrayList<item>();
	//SQL to select the items that have only received a good or excellent review
	//Select the item where the username is selected
	//and the itemID is selected from the ones that are not in the ones where there are excellent and good remarks
	String sql = "SELECT * FROM item where username = ? AND itemID IN " +
			"(SELECT DISTINCT itemID FROM reviews WHERE itemID IN " +
			"(SELECT DISTINCT itemID from reviews " +
			"WHERE score = 'Excellent' OR score = 'Good'));";
	connect_function();
	preparedStatement = (PreparedStatement) connect.prepareStatement(sql);
	preparedStatement.setString(1,username);
	ResultSet resultSet = preparedStatement.executeQuery();
	
	while(resultSet.next()){
		excellentGood.add(resultSetItem(resultSet));
	}
	
	resultSet.close();
	statement.close();
	disconnect();
	return excellentGood;
}

/*-------------------------------------------------------------------------------
 * REQUIREMENT 4 - List the users who posted the most number of items since 5/1/2018 (inclusive); 
 * if there is a tie, list all the users who have a tie.
 --------------------------------------------------------------------------------*/
public List<users> postMostItems() throws SQLException {
	List<users>listUsers = new ArrayList<users>();
	String sql = "SELECT U.*, COUNT(*) as NUM FROM users U, item I WHERE I.itemDate >= '2018-05-01' AND U.username = I.username\r\n" + 
			"GROUP BY I.username ORDER BY NUM DESC";
	connect_function();
	statement = (Statement)connect.createStatement();
	ResultSet resultSet = statement.executeQuery(sql);
	
	int highestValue = 0; //get the highest value from going through the resultSet. 
	
	//in the case of a tie, if there is a value greater than or equal to the highest, it will get printed
	//on the first iteration, it will collect the highest value
	while(resultSet.next()){
		if(resultSet.getInt("NUM") >= highestValue) {
		listUsers.add(resultSetUser(resultSet));
		highestValue = resultSet.getInt("NUM");
		}
	}
	resultSet.close();
	statement.close();
	disconnect();
	return listUsers;
}

/*---------------------------------------------------------------------------------
 * REQUIREMENT 5 - List the other users who are favorited by both users X and Y. 
 * Usernames X and Y will be selected from dropdown menus by the instructor. 
 * In other words, the user (or users) C are the favorite for both X and Y.
 ----------------------------------------------------------------------------------*/
public List<favoriteUser> usersFavorited(int userID1, int userID2) throws SQLException {
	List<favoriteUser>listUsers = new ArrayList<favoriteUser>();
	String sql = "SELECT X.favoriteUserID, X.favoriteUserUsername FROM favoriteuser X, favoriteuser Y "
			+ "WHERE X.currentUserID = ? AND Y.currentUserID = ? AND X.favoriteUserID = Y.favoriteUserID;";
	connect_function();
	preparedStatement = (PreparedStatement) connect.prepareStatement(sql);
	preparedStatement.setInt(1,userID1);
	preparedStatement.setInt(2,userID2);
	ResultSet resultSet = preparedStatement.executeQuery();
	
	while(resultSet.next()){
		
		String favoriteUserUserName = resultSet.getString("favoriteUserUsername");
		int favoriteUserID = resultSet.getInt("favoriteUserID");
		
		favoriteUser favoriteUser = new favoriteUser(favoriteUserID,favoriteUserUserName);
		listUsers.add(favoriteUser);
	}
	return listUsers;
}


/*-------------------------------------------------------------------------------
 * REQUIREMENT 6 - Display all the users who never posted any �excellent� items: 
 * an item is excellent if at least three reviews are excellent. 
 --------------------------------------------------------------------------------*/
public List<users> noExcellentItems() throws SQLException {
	List<users>listUsers = new ArrayList<users>();
	String sql = "SELECT * FROM users WHERE username NOT IN( " +
				 "SELECT username FROM item WHERE itemID IN( " +
				 "SELECT itemID FROM reviews WHERE itemID IN( " +
				 "SELECT itemID FROM reviews WHERE score = 'Excellent' GROUP BY itemID, score HAVING COUNT(*) >=3)));";
			
	connect_function();
	statement = (Statement)connect.createStatement();
	ResultSet resultSet = statement.executeQuery(sql);
	
	while(resultSet.next()){
		listUsers.add(resultSetUser(resultSet));		
	}
	resultSet.close();
	statement.close();
	disconnect();
	return listUsers;
}
/*-------------------------------------------------------------------------------
 * REQUIREMENT 7 - Display all the users who never posted a �poor� review.
 --------------------------------------------------------------------------------*/
public List<users> pNoPoorReview() throws SQLException {
	List<users>listUsers = new ArrayList<users>();
	//When displaying users (and you're not admin so you don't need to see password) you probably
	//only need to show the username and the userID
	
	//so select the userID and the username users where they are not 
	//in the search where users have posted poor reviews
	String sql = "SELECT DISTINCT username, userID "
			+ "FROM users "
			+ "WHERE username NOT IN "
			+ "(SELECT U.username FROM users U, reviews R "
			+ "WHERE R.score = 'Poor' AND U.username = R.username);";
	connect_function();
	statement = (Statement)connect.createStatement();
	ResultSet resultSet = statement.executeQuery(sql);
	
	while(resultSet.next()){
		listUsers.add(resultSetUserPart3(resultSet));		
	}
	resultSet.close();
	statement.close();
	disconnect();
	return listUsers;
}
/*-------------------------------------------------------------------------------
 * REQUIREMENT 8 - Display all the users who posted some reviews but each of them is �poor�.
 --------------------------------------------------------------------------------*/
public List<users> pAllPoorReview() throws SQLException {
	List<users>listUsers = new ArrayList<users>();
	//When displaying users (and you're not admin so you don't need to see password) you probably
	//only need to show the username and the userID
	
	//so select the userID and the username users where they are not 
	//in the search where users have posted poor reviews
	//and then eliminate users that haven't posted a review
	String sql = "SELECT DISTINCT U.username, U.userID "
			+ "FROM users U "
			+ "LEFT JOIN reviews R ON R.username = U.username "
			+ "WHERE U.username NOT IN "
			+ "(SELECT R.username FROM users U, reviews R WHERE "
			+ "(R.score = 'Excellent' OR R.score = 'Good' OR R.score = 'Fair') "
			+ "AND U.username = R.username) AND R.username IS NOT NULL;";
	connect_function();
	statement = (Statement)connect.createStatement();
	ResultSet resultSet = statement.executeQuery(sql);
	
	while(resultSet.next()){
		listUsers.add(resultSetUserPart3(resultSet));		
	}
	resultSet.close();
	statement.close();
	disconnect();
	return listUsers;
}

/*-------------------------------------------------------------------------------
 * REQUIREMENT 9 - Display those users such that each item they posted so far never received any �poor� reviews. 
 * In other words, these users must have posted some items, however, 
 * these items have never received any poor review or have not received any review at all.
 --------------------------------------------------------------------------------*/
public List<users> rNoPoorReview() throws SQLException {
	List<users>listUsers = new ArrayList<users>();
	String sql = "SELECT DISTINCT U.username, U.userID "
			+ "FROM users U "
			+ "LEFT JOIN reviews R ON R.username = U.username "
			+ "LEFT JOIN item I ON I.username = U.username "
			+ "WHERE U.username NOT IN (SELECT I.username FROM item I, reviews R WHERE "
			+ "(R.score = 'Poor') AND I.itemID = R.itemID) AND I.username IS NOT NULL;";
	connect_function();
	statement = (Statement)connect.createStatement();
	ResultSet resultSet = statement.executeQuery(sql);
	
	while(resultSet.next()){
		listUsers.add(resultSetUserPart3(resultSet));		
	}
	resultSet.close();
	statement.close();
	disconnect();
	return listUsers;
}

/*-----------------------------------------------------------------------------
 * REQUIREMENT 10 - List a user pair (A, B) such that they always gave each other 
 * "excellent� review for every single item they posted. 
 ------------------------------------------------------------------------------*/
public List<users> userPairExcellent() throws SQLException {
	List<users>userPair = new ArrayList<users>();
	String sql = "SELECT A.username AS UserA, B.username AS UserB\r\n" + 
			"FROM\r\n" + 
			"(SELECT R.score, R.username, R.itemID\r\n" + 
			"FROM reviews R\r\n" + 
			"INNER JOIN item I ON I.itemID = R.itemID) A,\r\n" + 
			"(SELECT R.score, R.username, R.itemID\r\n" + 
			"FROM reviews R\r\n" + 
			"INNER JOIN item I ON I.itemID = R.itemID) B\r\n" + 
			"WHERE A.score = 'Excellent'";
	connect_function();
	statement = (Statement)connect.createStatement();
	ResultSet resultSet = statement.executeQuery(sql);
	while(resultSet.next()){
		userPair.add(resultSetUserPart3(resultSet));		
	}
	resultSet.close();
	statement.close();
	disconnect();
	return userPair;
}

public users resultSetUserPart3(ResultSet resultSet) throws SQLException {
	
	int userID = resultSet.getInt("userID");
	String username = resultSet.getString("username");
	
	users users = new users(username, userID);
	return users;
	
}
}