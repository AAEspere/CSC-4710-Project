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

//this is a DAO for the objects. created to separate the code between the items, users, and reviews

public class itemDAO {
	
	private Connection connect = null;
	private Statement statement = null;
	private PreparedStatement preparedStatement = null;
	private ResultSet resultSet = null;
	InitDatabase connection = new InitDatabase();
	
	//this function will most likely get moved. I will most likely create a DAO for customers, items, and reviewers
	public void addItems() throws SQLException {
		connection.connect_function();
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
			connection.disconnect();
		}	
	}
	

}
