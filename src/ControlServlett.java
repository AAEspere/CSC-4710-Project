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
import java.util.ArrayList;


@WebServlet(name = "/ControlServlett", urlPatterns = { "/"})

public class ControlServlett extends HttpServlet{
	private static final long serialVersionUID = 1L;
	public InitDatabase InitDatabase;
	public itemDAO itemDAO;
	public userDAO userDAO;
	public reviewDAO reviewDAO;
	
	private HttpSession usersession;
	//gets the current userID when the user logs in or signs up
	int userID = 0;
	int favUserID = 0;
	int userItem = 0;
	int userReview = 0;
	String currentUser;
	
	public void init(){
		InitDatabase = new InitDatabase();
		itemDAO = new itemDAO();
		userDAO = new userDAO();
		reviewDAO = new reviewDAO();
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
            	break;
        	case "/showTables":
            	showAllInformation(request, response);
        		break;
        	case "/login":
        		login(request,response);
        		showAllInformation(request, response);
        		break;
        	case "/register":
        		//When I register now, I need to check for matching password and duplicate email
        		matchingPassword(request,response);
        		duplicateUsername(request, response);
        		register(request,response);
        		showAllInformation(request, response);
        		break;
        	case "/showQueries":
        		showAllInformation(request, response);
        		break;
        	//Pertaining to Project Part 2
        	case "/insertItem":
        		insertItem(request,response);
        		showAllInformation(request, response);
        		break;
        	case "/searchItem":
        		searchItem(request, response);
        		break;
        	case "/reviewID":
        		reviewID(request, response);
        		break;
        	case "/addReview":
        		addReview(request, response);
        		showAllInformation(request, response);
        		break;
        	case "/sortExpensive":
        		sortExpensive(request, response);
        		listUsers(request, response);
        		listReviews(request, response);
        		break;
        	case "/addFavoriteItem":
        		addFavoriteItem(request, response);
        		showAllInformation(request, response);
        		break;
        	case "/addFavoriteUser":
        		addFavoriteUser(request, response);
        		showAllInformation(request, response);
        		break;
        	case "/displayFavoriteItem":
        		displayFavoriteItem(request, response);
        		break;
        	case "/displayFavoriteUser":
        		displayFavoriteUser(request, response);
        		break;
        	case "/deleteUser":
        		deleteUser(request, response);
        		displayFavoriteUser(request, response);
        		break;
        	case "/deleteItem":
        		deleteItem(request, response);
        		displayFavoriteItem(request,response);
        		break;		
        	case "/displayUser":
        		displayUser(request, response);
        		break;
        		
        		//Project 3 cases
        	case "/sameDay":
        		sameDay(request,response);
        		break;
        	case "/excellentGood":
        		excellentGood(request,response);
        		break;
        	case "postMostItems":
        		postMostItems(request,response);
        		break;
        	case "usersFavorited":
        		usersFavorited(request,response);
        		break;
        	case "noExcellentItems":
        		noExcellentItems(request,response);
        		break;
        	case "pNoPoorReview":
        		pNoPoorReview(request,response);
        		break;
        	case "pAllPoorReview":
        		pAllPoorReview(request,response);
        		break;
        	case "rNoPoorReview":
        		rNoPoorReview(request,response);
        		break;
        	case "userPairExcellent":
        		userPairExcellent(request,response);
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
            	InitDatabase.createFavoriteItemsTable();
            	InitDatabase.createFavoriteUsersTable();
    			RequestDispatcher dispatcher = request.getRequestDispatcher("Welcome.jsp");       
                dispatcher.forward(request, response);
            }
    
    //noticing I'm calling the same functions over and over so I just condense them into one function
    private void showAllInformation(HttpServletRequest request, HttpServletResponse response)
    		throws SQLException, IOException, ServletException {
    		listItem(request, response);
    		listUsers(request, response);
    		listReviews(request, response);
    }
    
    //for inserting a singular item into the list
    private void insertItem(HttpServletRequest request, HttpServletResponse response)
    		throws SQLException, IOException, ServletException {
    	
    	//CHECK HOW MANY ITEMS THE USER HAS SUBMITTEED
    	//IF GOOD THEN PROCEED WITH THE INSERT ITEM FUNCTION
    	if(userItem <= 5) {
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
    		userItem++;
    	}
    	else {
    		System.out.println("Item unsuccessful in inserting");
    		response.sendRedirect("problemItem.jsp");
    	}
    	}
    	else {
    		System.out.println("User Post Limit Reached");
    	}
    	
    }
    
    private void addFavoriteItem(HttpServletRequest request, HttpServletResponse response)
    		throws SQLException, IOException, ServletException {
    			
    		//int userID = Integer.parseInt(request.getParameter("userID"));
    		int itemID = Integer.parseInt(request.getParameter("itemID"));
    		favoriteItem addFavItem = new favoriteItem(userID, itemID);
    		
    		if(InitDatabase.addFavoriteItem(addFavItem) == true) {
    			System.out.println("Successfully Inserted");
    		}
    		//else {
    			//System.out.println("Item unsuccessful in inserting");
    			//RequestDispatcher dispatcher = request.getRequestDispatcher("showQueries");
    			//dispatcher.forward(request, response);
    		//}
    		}
    
    //for searching items based on categories
    private void searchItem(HttpServletRequest request, HttpServletResponse response)
    		throws SQLException, IOException, ServletException {
    			String category = request.getParameter("category");
    			List<item> searchItem = InitDatabase.searchItem(category);
    			request.setAttribute("listItem", searchItem);
    			//after attribute is set redirect to the same page, but this time the results will be posted
            	RequestDispatcher dispatcher = request.getRequestDispatcher("searchItem.jsp");       
                dispatcher.forward(request, response);
    }
    
    private void deleteItem(HttpServletRequest request, HttpServletResponse response) 
    		throws SQLException, IOException, ServletException {
    		int id = Integer.parseInt(request.getParameter("itemID"));
    		InitDatabase.deleteItem(id);
    }
    
    //these are for showing the results of the table
    private void listItem(HttpServletRequest request, HttpServletResponse response)
            throws SQLException, IOException, ServletException {
            	List<item> listItem = InitDatabase.listAllItems();
            	request.setAttribute("listItem", listItem);
            }
    
    private void displayFavoriteItem(HttpServletRequest request, HttpServletResponse response) 
    		throws SQLException, IOException, ServletException{
    	
    		ArrayList<Integer> favItemID = InitDatabase.getUserFavItemID(userID);
    		List<item> favItems = InitDatabase.listFavoriteItems(favItemID);
    		request.setAttribute("favItems", favItems);
    		RequestDispatcher dispatcher = request.getRequestDispatcher("listFavoriteItems.jsp");
    		dispatcher.forward(request, response);
    }
    
    private void displayFavoriteUser(HttpServletRequest request, HttpServletResponse response) 
    		throws SQLException, IOException, ServletException {
    		List<favoriteUser> favUsers = InitDatabase.listFavoriteUsers();
    		request.setAttribute("favUsers", favUsers);
    		RequestDispatcher dispatcher = request.getRequestDispatcher("listFavoriteUsers.jsp");
    		dispatcher.forward(request, response);
    }
    
    private void deleteUser(HttpServletRequest request, HttpServletResponse response) 
    		throws SQLException, IOException, ServletException {
    		int id = Integer.parseInt(request.getParameter("favUserID"));
    		InitDatabase.deleteUser(id);
    }
    
    private void listUsers(HttpServletRequest request, HttpServletResponse response)
            throws SQLException, IOException, ServletException {
            	List<users> listUsers = InitDatabase.listAllUsers();
            	request.setAttribute("listUsers", listUsers);
            }
    
    private void addFavoriteUser(HttpServletRequest request, HttpServletResponse response) 
    		throws SQLException, IOException, ServletException {
    	
    		favUserID = Integer.parseInt(request.getParameter("userID"));
    		favoriteUser addFavUser = new favoriteUser(userID, favUserID);
    		
    		if(InitDatabase.addFavoriteUser(addFavUser) == true) {
    			System.out.println("Successfully Inserted");
    		}
    }
    
    private void displayUser(HttpServletRequest request, HttpServletResponse response)
    		throws SQLException, IOException, ServletException {
    	
    List<favoriteUser> userProfile;
    	int favUserID = Integer.parseInt(request.getParameter("favUserID"));
    	
    	
    }
    
    //this is the last function when initializing the database, so it forwards the page to initDatabase.jsp
    private void listReviews(HttpServletRequest request, HttpServletResponse response)
            throws SQLException, IOException, ServletException {
            	List<reviews> listReviews = InitDatabase.listAllReviews();
            	request.setAttribute("listReviews", listReviews);
            	RequestDispatcher dispatcher = request.getRequestDispatcher("initDatabase.jsp");       
                dispatcher.forward(request, response);
            }
    
    private void reviewID(HttpServletRequest request, HttpServletResponse response) 
    		throws SQLException, IOException, ServletException {
    	
    		//currentReviewID = Integer.parseInt(request.getParameter("itemID"));
    		RequestDispatcher dispatcher = request.getRequestDispatcher("writeReview.jsp");
    		dispatcher.forward(request, response);
    	
    }
    
    private void addReview(HttpServletRequest request, HttpServletResponse response) 
    		throws SQLException, IOException, ServletException {
    	
    		if(userReview <= 5) {
    		String score = request.getParameter("remark");
    		String remark = request.getParameter("review");
    		
    		//reviews addReview = new reviews(currentReviewID, userID, score, remark);
    		
    		//InitDatabase.insertReview(addReview);
    		userReview++;
    		}
    		else {
    			System.out.println("User Review Limit Reached");
    		}
    }
        
    private void login(HttpServletRequest request, HttpServletResponse response)
    		throws SQLException, IOException, ServletException {
    		//Most websites have email to log in so I use email only
    		String email = request.getParameter("email");
			String pass = request.getParameter("password");
			if(InitDatabase.loginCheck(email, pass) == true) {
				userID = InitDatabase.getCurrentID(email, pass);
				currentUser = InitDatabase.getCurrentUsername(email, pass);
				usersession = request.getSession();
				System.out.print(currentUser);
				System.out.print(userID);
			}

    }
    
    private void register(HttpServletRequest request, HttpServletResponse response)
    		throws SQLException, IOException, ServletException {
    	
    		//for registering the user
    		String username = request.getParameter("username");
    		String email = request.getParameter("email");
    		String password = request.getParameter("password");
    		String firstName = request.getParameter("firstName");
    		String lastName = request.getParameter("lastName");
    		String gender = request.getParameter("gender");
    		int age = Integer.parseInt(request.getParameter("age"));
    		
    		//add the user to the database
    		users registerUser = new users(username, password, firstName, lastName, email, gender, age);
    		InitDatabase.addOneUser(registerUser);
    		
    		//get the userID and the username of the user
    		userID = InitDatabase.getCurrentID(email, password);
    		usersession = request.getSession();
    		usersession.setAttribute("currentUser", username);
    		currentUser = (String)usersession.getAttribute("currentUser");
			System.out.print(currentUser);
			System.out.print(userID);
    }
    
    //Project Part 2 - sorting the most expensive items in a category
    public void sortExpensive(HttpServletRequest request, HttpServletResponse response)
    		throws SQLException, IOException, ServletException {
    	List<item> sortExpensive = InitDatabase.sortExpensive();
    	request.setAttribute("listItem", sortExpensive);
    }
    
    //Project Part 3
    public void matchingPassword(HttpServletRequest request, HttpServletResponse response)
    	throws SQLException, IOException, ServletException {
    	
    	String password1 = request.getParameter("password1");
    	String password2 = request.getParameter("password2");
    	if(password1 == password2) {
    		//do nothing if password1 is equal to password2 because there is no problem
    	}
    	else {
    		RequestDispatcher dispatcher = request.getRequestDispatcher("register.jsp");
    		dispatcher.forward(request, response);
    	}
    	
    }
    
    public void duplicateUsername(HttpServletRequest request, HttpServletResponse response)
        	throws SQLException, IOException, ServletException {
    	
    	String username = request.getParameter("username");
    	if(InitDatabase.checkDuplicateUsername(username) == true) {
    		//if returns true then return to register because emails do not match
    		RequestDispatcher dispatcher = request.getRequestDispatcher("register.jsp");
    		dispatcher.forward(request, response);
    	}
    	else {
    		//do nothing if false, this means it is a unique email
    	}
    	
        }
    
    public void sameDay(HttpServletRequest request, HttpServletResponse response)
        	throws SQLException, IOException, ServletException {
    	
    }
    
    public void excellentGood(HttpServletRequest request, HttpServletResponse response)
        	throws SQLException, IOException, ServletException {
    	
    }
    
    public void postMostItems(HttpServletRequest request, HttpServletResponse response)
        	throws SQLException, IOException, ServletException {
    	
    }
    
    public void usersFavorited(HttpServletRequest request, HttpServletResponse response)
        	throws SQLException, IOException, ServletException {
    	
    }
    
    public void noExcellentItems(HttpServletRequest request, HttpServletResponse response)
        	throws SQLException, IOException, ServletException {
    	
    }
    
    public void pNoPoorReview(HttpServletRequest request, HttpServletResponse response)
        	throws SQLException, IOException, ServletException {
    	
    }
    
    public void pAllPoorReview(HttpServletRequest request, HttpServletResponse response)
        	throws SQLException, IOException, ServletException {
    	
    }
    
    public void rNoPoorReview(HttpServletRequest request, HttpServletResponse response)
        	throws SQLException, IOException, ServletException {
    	
    }
    
    public void userPairExcellent(HttpServletRequest request, HttpServletResponse response)
        	throws SQLException, IOException, ServletException {
    	
    }
}
