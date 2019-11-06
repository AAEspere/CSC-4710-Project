import java.io.IOException;
import java.io.PrintWriter;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.List;
 
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import java.sql.Connection;
import java.sql.Statement;
import java.sql.PreparedStatement;
import javax.servlet.http.HttpSession;

@WebServlet(name = "/ControlServlett", urlPatterns = { "/"})

public class ControlServlett extends HttpServlet{
	private static final long serialVersionUID = 1L;
	public InitDatabase InitDatabase;
	private HttpSession usersession;
	
	public void init(){
		InitDatabase = new InitDatabase();
	}
	
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
    	doGet(request, response);
    }
    
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String action = request.getServletPath();
        System.out.println(action);
        
        try {
        	switch(action) {
        	case "/initDatabase":
        		initDatabase(request,response);
            	listItem(request, response); 
            	listUsers(request, response);
            	listReviews(request, response);
            	break;
        	case "/login":
        		login(request,response);
        		break;
        	case "/register":
        		register(request,response);
        		break;
        		
        		
        	//Pertaining to Project Part 2
        	case "/insertItem":
        		insertItem(request,response);
        		listItem(request, response);
        		listUsers(request, response);
        		listReviews(request, response);
        		break;
        	case "/searchItem":
        		searchItem(request, response);
        		break;
        	case "/addReview":
        		addReview(request, response);
        		break;
        	case "/sortExpensive":
        		sortExpensive(request, response);
        		listUsers(request, response);
        		listReviews(request, response);
        		break;
        	case "/favoriteItem":
        		break;
        	case "/favoriteUser":
        		break;
        	case "/deleteUser":
        		deleteUser(request, response);
        		break;
        	case "/deleteItem":
        		deleteItem(request, response);
        		break;
        		
        }
        }
        catch (SQLException ex) {
            throw new ServletException(ex);
        }
    }
    private void initDatabase(HttpServletRequest request, HttpServletResponse response)
            throws SQLException, IOException, ServletException {
            	InitDatabase.createDatabase();
            	InitDatabase.createItemTable();
            	InitDatabase.addItems();
            	InitDatabase.createUserTable();
            	InitDatabase.addUsers();	
            	InitDatabase.createReviewTable();
            	InitDatabase.addReviews();
            }
    
    //for inserting a singular item into the list
    private void insertItem(HttpServletRequest request, HttpServletResponse response)
    		throws SQLException, IOException, ServletException {
    	
    	//CHECK HOW MANY ITEMS THE USER HAS SUBMITTEED
    	//IF GOOD THEN PROCEED WITH THE INSERT ITEM FUNCTION
    	
    	//basically you get all the parameters that were added, and create a new item with it
    	//which then goes to insertItem which inserts the item into the SQL
    	String itemName = request.getParameter("itemName");
    	String description = request.getParameter("description");
    	String category = request.getParameter("category");
    	Double price = Double.valueOf(request.getParameter("price"));
    	
    	//had to create a new constructor in this case because the itemID will be autoincremented
    	//and the date will be added within the insert function
    	item insertItem = new item(itemName, description, price, category);
    	
    	//indication if the item was successfully added
    	if(InitDatabase.insertItem(insertItem) == true) {
    		System.out.println("Successfully inserted");
    	}
    	else {
    		System.out.println("Item unsuccessful in inserting");
    		response.sendRedirect("problemItem.jsp");
    	}
    	
    }
    
    //for searching items based on categories
    private void searchItem(HttpServletRequest request, HttpServletResponse response)
    		throws SQLException, IOException, ServletException {
    			String category = request.getParameter("category");
    			List<item> searchItem = InitDatabase.searchItem(category);
    			request.setAttribute("searchItem", searchItem);
    			
    			
    			//after attribute is set redirect to the same page, but this time the results will be posted
    			RequestDispatcher dispatcher = request.getRequestDispatcher("searchItem.jsp");       
                dispatcher.forward(request, response);
    	
    }
    
    private void deleteItem(HttpServletRequest request, HttpServletResponse response) 
    		throws SQLException, IOException, ServletException {
    		int id = Integer.parseInt(request.getParameter("itemID"));
    		InitDatabase.deleteUser(id);
    		RequestDispatcher dispatcher = request.getRequestDispatcher("listFavoriteItems.jsp");
    		dispatcher.forward(request, response);	
    }
    
    //these are for showing the results of the table
    private void listItem(HttpServletRequest request, HttpServletResponse response)
            throws SQLException, IOException, ServletException {
            	List<item> listItem = InitDatabase.listAllItems();
            	request.setAttribute("listItem", listItem);
            	
            }
    
    private void deleteUser(HttpServletRequest request, HttpServletResponse response) 
    		throws SQLException, IOException, ServletException {
    		int id = Integer.parseInt(request.getParameter("userID"));
    		InitDatabase.deleteUser(id);
    		RequestDispatcher dispatcher = request.getRequestDispatcher("listFavoriteUsers.jsp");
    		dispatcher.forward(request, response);	
    }
    
    private void listUsers(HttpServletRequest request, HttpServletResponse response)
            throws SQLException, IOException, ServletException {
            	List<users> listUsers = InitDatabase.listAllUsers();
            	request.setAttribute("listUsers", listUsers);
            }
    
    //this is the last function when initializing the database, so it forwards the page to initDatabase.jsp
    private void listReviews(HttpServletRequest request, HttpServletResponse response)
            throws SQLException, IOException, ServletException {
            	List<reviews> listReviews = InitDatabase.listAllReviews();
            	request.setAttribute("listReviews", listReviews);
            	RequestDispatcher dispatcher = request.getRequestDispatcher("initDatabase.jsp");       
                dispatcher.forward(request, response);
            }
    
    private void addReview(HttpServletRequest request, HttpServletResponse response) 
    		throws SQLException, IOException, ServletException {
    	
    		//I need to use parseInt in order to convert the ID's into integers
    		//needed to look this up
    		int itemID = Integer.parseInt(request.getParameter("itemID"));
    		int userID = Integer.parseInt(request.getParameter("userID"));
    		String score = request.getParameter("score");
    		String remark = request.getParameter("remark");
    		
    		reviews addReview = new reviews(itemID, userID, score, remark);
    		
    		InitDatabase.insertReview(addReview);
    }
        
    private void login(HttpServletRequest request, HttpServletResponse response)
    		throws SQLException, IOException, ServletException {
    		RequestDispatcher dispatcher;
			String username = request.getParameter("username");
			String pass = request.getParameter("pass");
			if(InitDatabase.loginCheck(username, pass) == true) {
				usersession = request.getSession();
				usersession.setAttribute("currentUser", username);
				dispatcher = request.getRequestDispatcher("Welcome.jsp");
				dispatcher.forward(request,response);
			}
			else {
				dispatcher = request.getRequestDispatcher("login.jsp");
				dispatcher.forward(request,response);
			}
    }
    
    private void register(HttpServletRequest request, HttpServletResponse response)
    		throws SQLException, IOException, ServletException {
    	
    		String username = request.getParameter("username");
    		String password = request.getParameter("password");
    		String firstName = request.getParameter("firstName");
    		String lastName = request.getParameter("lastName");
    		String gender = request.getParameter("gender");
    		int age = Integer.parseInt(request.getParameter("age"));
    		
    		//add the user to the database
    		users registerUser = new users(username, password, firstName, lastName, gender, age);
    		InitDatabase.addOneUser(registerUser);
    		
    		usersession = request.getSession();
    		usersession.setAttribute("currentUser", username);
    }
    
    //Project Part 2 - sorting the most expensive items in a category
    public void sortExpensive(HttpServletRequest request, HttpServletResponse response)
    		throws SQLException, IOException, ServletException {
    	List<item> sortExpensive = InitDatabase.sortExpensive();
    	request.setAttribute("listItem", sortExpensive);
    }
    
}
