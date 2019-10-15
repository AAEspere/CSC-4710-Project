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

//this is for listing the items table
public List<item> listAllItems() throws SQLException{
	List<item> listItems = new ArrayList<item>();
	String sql = "SELECT * FROM item";
	connect_function();
	statement = (Statement) connect.createStatement();
	ResultSet resultSet = statement.executeQuery(sql);
	
	while(resultSet.next()){
	
		int itemID = resultSet.getInt("id");
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

protected void disconnect() throws SQLException {
	if (connect != null && !connect.isClosed()) {
		connect.close();
	}
}

}