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
	
//initialize connection to the Server
	//I probably should separate the connection, the initializing of the Database
	//the functions on items, users, and reviews into separate files
	//So the readability of the code improves
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
//PROJECT PART 2: need to edit this and add a user section
//this will show what user posted what item.
public void createItemTable() throws SQLException {
	//the two statements required for making table
	connect_function();
	statement = (Statement)connect.createStatement();
	String dropItemTable = "DROP TABLE IF EXISTS item";
	String createItemTable = "CREATE TABLE IF NOT EXISTS item" +
			//Project Part 2, editing statement so that AUTO_INCREMENT is added
			//Project Part 2, editing statement so that username is incorporated for all items
			"(username VARCHAR(50) NOT NULL, " + 
			"itemID INTEGER AUTO_INCREMENT, " +
			"itemTitle VARCHAR(50) NOT NULL," +
			"itemDescription VARCHAR(100), " +
			"itemDate VARCHAR(50), " +
			"itemPrice DOUBLE NOT NULL, " +
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

//Project Part 1 just says to initialize tables with at least 10 tuples, creating 10 random items
//this is part of the database initialization
public void addItems() throws SQLException {
	connect_function();
	statement = (Statement)connect.createStatement();
	String addItem1 = "INSERT INTO item VALUES('AA1','123456789','Cactus','Pointy Plant','06/21/1998','20.00','Plants');";
	String addItem2 = "INSERT INTO item VALUES('AA2','987654321','Soccer Ball', 'A ball to kick','06/09/1997','3.00','Outdoors');";
	String addItem3 = "INSERT INTO item VALUES('AA3','121212121','Video Game','New Video Game', '01/01/2019','60.00','Electronics');";
	String addItem4 = "INSERT INTO item VALUES('AA4','123123123','Couch','Furniture','02/01/2019','300.00','Furniture');";
	String addItem5 = "INSERT INTO item VALUES('AA5','231455153','Couch','Furniture','02/01/2019','300.00','Furniture');";
	String addItem6 = "INSERT INTO item VALUES('AA6','555555555','Video Game','New Video Game', '01/01/2019','60.00','Electronics');";
	String addItem7 = "INSERT INTO item VALUES('AA7','423465674','Cactus','Pointy Plant','06/21/1998','20.00','Plants');";
	String addItem8 = "INSERT INTO item VALUES('AA8','343242351','Soccer Ball', 'A ball to kick','06/09/1997','3.00','Outdoors');";
	String addItem9 = "INSERT INTO item VALUES('AA9','654764567','Video Game','New Video Game', '01/01/2019','60.00','Electronics');";
	String addItem10 = "INSERT INTO item VALUES('AA10','235790456','Video Game','New Video Game', '01/01/2019','60.00','Electronics');";		
	
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


//Using SQL to handle the creation and deletion of favorite users/items,
//So I think I need to make a separate table in order to add and delete item ID's
public void createFavoriteItemsTable() throws SQLException {
	
	connect_function();
	statement = (Statement)connect.createStatement();
	String dropFavoriteItemsTable = "DROP TABLE IF EXISTS favoriteItem";
	String createFavoriteItemsTable = "CREATE TABLE IF NOT EXISTS favoriteItem" +
			
			//Since I am using a separate table, and am using itemID to DELETE the item
			//this table will need the userID (the current user) and the itemID(of the item
			//they selected)
			"(userID INTEGER NOT NULL, " +
			"itemID INTEGER NOT NULL, " +
			"PRIMARY KEY (userID, itemID));";
	
	try {
	statement.executeUpdate(dropFavoriteItemsTable);
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
	//return true and redirect with sucessful add message, or return false
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

//listing items for a userProfile
public List<item> listUserItems(int favUserID) throws SQLException {
		List<item> userItems = new ArrayList<item>();
		String sql = "SELECT * FROM item WHERE userID = ?";
		connect_function();
		statement = (Statement) connect.createStatement();
		preparedStatement = (PreparedStatement) connect.prepareStatement(sql);
		preparedStatement.setInt(1, favUserID);
		ResultSet resultSet = preparedStatement.executeQuery(sql);
		
		while(resultSet.next()) {
			userItems.add(resultSetItem(resultSet));
		}
		
		return userItems;
}

public List<item> listFavoriteItems(ArrayList<Integer> favitemID) throws SQLException {
	
	List<item> favItems = new ArrayList<item>();
	String sql = "SELECT * FROM item";
	connect_function();
	statement = (Statement) connect.createStatement();
	ResultSet resultSet = statement.executeQuery(sql);
	
	/*the way I have this implemented is a for loop within a while loop. I think this is bad
	 * practice and will have to be changed for Part 3...because the result makes this function
	 * extremely slow (I think O(n^4))*/
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
	preparedStatement.setString(4,java.time.LocalDate.now().toString());
	preparedStatement.setDouble(5,item.itemPrice);
	preparedStatement.setString(6,item.itemCategory);
//	preparedStatement.executeUpdate();
	
    boolean rowInserted = preparedStatement.executeUpdate() > 0;
    preparedStatement.close();
//    disconnect();
    return rowInserted;
	
	
}

//Project Part 2 --> Search function which displays all items under a certain category
//This should be similar to the listItems function because instead of listing all items
//you are listing all items that match a specific category
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

//create the table for users
public void createUserTable() throws SQLException {
	connect_function();
	//the two statements required for making table
	String dropUserTable = "DROP TABLE IF EXISTS users";
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
			"PRIMARY KEY (username, userID);";
	
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

public boolean addOneUser(users user) throws SQLException {
	//adding a singular user, this is to add the user who registers onto the server
	connect_function();         
	String sql = "INSERT INTO users(pass, firstName, lastName, email, gender, age) VALUES (?,?,?,?,?,?)";
	preparedStatement = (PreparedStatement) connect.prepareStatement(sql);
	preparedStatement.setString(1, user.pass);
	preparedStatement.setString(2, user.firstName);
	preparedStatement.setString(3, user.lastName);
	preparedStatement.setString(4, user.email);
	preparedStatement.setString(5, user.gender);
	preparedStatement.setInt(6, user.age);
	
    boolean rowInserted = preparedStatement.executeUpdate() > 0;
    preparedStatement.close();
//    disconnect();
    return rowInserted;
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
	//if you get to this part, then the emails were not duplicates, which means that 
	return false;
}

public void createFavoriteUsersTable() throws SQLException {
	connect_function();
	statement = (Statement)connect.createStatement();
	String dropFavoriteUsersTable = "DROP TABLE IF EXISTS favoriteUser";
	String createFavoriteUsersTable = "CREATE TABLE IF NOT EXISTS favoriteUser" +
	
			"(currentUserID INTEGER NOT NULL, " +
			"favoriteUserID INTEGER NOT NULL, " +
			"PRIMARY KEY (favoriteUserID));";
	
	try {
		statement.executeUpdate(dropFavoriteUsersTable);
		statement.executeUpdate(createFavoriteUsersTable);
	}
	
	catch(SQLException e) {
		
	}
	finally {
		connect.close();
	}
}

public boolean addFavoriteUser(favoriteUser favUser) throws SQLException {
	connect_function();
	/*get the current UserID and the fav UserID and add to favoriteUser table */
	String sql = "INSERT INTO favoriteUser(currentUserID, favoriteUserID) VALUES (?,?)";
	preparedStatement = (PreparedStatement) connect.prepareStatement(sql);
	preparedStatement.setInt(1, favUser.userID);
	preparedStatement.setInt(2, favUser.favUserID);
	boolean rowInserted = preparedStatement.executeUpdate() > 0;
	preparedStatement.close();
	return rowInserted;
}

public List<favoriteUser> listFavoriteUsers() throws SQLException {
	
	List<favoriteUser> favUsers = new ArrayList<favoriteUser>();
	String sql = "SELECT * FROM favoriteUser";
	connect_function();
	statement = (Statement) connect.createStatement();
	ResultSet resultSet = statement.executeQuery(sql);
	
	while(resultSet.next()) {
		
		int userID = resultSet.getInt("currentUserID");
		int favUserID = resultSet.getInt("favoriteUserID");
		favoriteUser fav = new favoriteUser(userID, favUserID);
		favUsers.add(fav);
	}
	
	resultSet.close();
	statement.close();
	disconnect();
	return favUsers;
	
}

//Project Part 2 - deleting a user. This will be used when adding and
//deleting users from the favorite user list
//I probably need to create a separate table to separate user and favorite user
//or else I will be permanently deleting the users 
public boolean deleteUser(int userID) throws SQLException {
    String sql = "DELETE FROM favoriteUser WHERE favoriteUserID = ?";        
    connect_function();
     
    preparedStatement = (PreparedStatement) connect.prepareStatement(sql);
    preparedStatement.setInt(1, userID);
     
    boolean rowDeleted = preparedStatement.executeUpdate() > 0;
    preparedStatement.close();
//    disconnect();
    return rowDeleted;  
}

public boolean deleteItem(int itemID) throws SQLException {
    String sql = "DELETE FROM favoriteItem WHERE itemID = ?";        
    connect_function();
     
    preparedStatement = (PreparedStatement) connect.prepareStatement(sql);
    preparedStatement.setInt(1, itemID);
     
    boolean rowDeleted = preparedStatement.executeUpdate() > 0;
    preparedStatement.close();
//    disconnect();
    return rowDeleted;  
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
			//"CONSTRAINT IID FOREIGN KEY (itemID) REFERENCES item(itemID);";
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

public boolean insertReview(reviews reviews) throws SQLException{
	connect_function();         
	String sql = "INSERT INTO reviews(itemID, userID, score, remark) "
			+ "VALUES(?,?,?,?)";
	
	preparedStatement = (PreparedStatement) connect.prepareStatement(sql);
	preparedStatement.setInt(1, reviews.itemID);
	preparedStatement.setInt(2, reviews.userID);
	preparedStatement.setString(3, reviews.score);
	preparedStatement.setString(4, reviews.remark);
//	preparedStatement.executeUpdate();
	
    boolean rowInserted = preparedStatement.executeUpdate() > 0;
    preparedStatement.close();
//    disconnect();
    return rowInserted;
}

//this function is for checking if the correct username and password was used for login
public boolean loginCheck(String email, String pass) throws SQLException {
	connect_function();
	statement = (Statement)connect.createStatement();
	String listUsers = "SELECT * FROM users";
	ResultSet resultSet = statement.executeQuery(listUsers);
	while(resultSet.next()) {
		if(resultSet.getString("email").equals(email) 
		&& resultSet.getString("pass").equals(pass)) {
			return true;
		}
	}
	return false;
}

public int getCurrentID(String email, String pass) throws SQLException {
	connect_function();
	statement = (Statement)connect.createStatement();
	String listUsers = "SELECT * FROM users";
	ResultSet resultSet = statement.executeQuery(listUsers);
	//if email and password equal the one entered, get the userID also. 
	while(resultSet.next()) {
		if(resultSet.getString("email").equals(email)
		&& resultSet.getString("pass").equals(pass)) {
			int userID = resultSet.getInt("userID");
			return userID;
		}
	}
	return 0;
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

//correct refactoring of getting all of the resultSet items into the new list
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
	
	item items = new item(username,itemID,itemTitle,itemDescription,date,itemPrice,itemCategory);
	
	return items;
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


//Project 3 Code Starts Here
public List<item> excellentGoodComments() throws SQLException {
	List<item> excellentGood = new ArrayList<item>();
	//SQL to select the items that have only received a good or excellent review
	//Select the item where the username is selected
	String sql = "SELECT * FROM item where username = ? AND itemID IN"
			//select the itemID from review table 
			+ "(SELECT DISTINCT itemID FROM review WHERE itemID NOT IN"
			+ "SELECT DISTINCT itemID from review"
			+ "WHERE remark = Excellent OR remark = Good";
	connect_function();
	statement = (Statement) connect.createStatement();
	ResultSet resultSet = statement.executeQuery(sql);
	
	while(resultSet.next()){
		excellentGood.add(resultSetItem(resultSet));
	}
	
	resultSet.close();
	statement.close();
	disconnect();
	return excellentGood;
}

}