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
import java.time.LocalDateTime;


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
	//int favUserID = 0;
	int userItem = -1;
	int userReview = -1;
	String currentUser = "";
	int currentReviewID = 0;
	int currentFavoriteUserID = 0;
	
	String localDate = java.time.LocalDate.now().toString();
	
	
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
        RequestDispatcher dispatcher;
        
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
        		break;
        	case "/register":
        		//When I register now, I need to check for matching password and duplicate email
        		register(request,response);
        		break;
        	case "/logout":
        		logout(request, response);
        		break;
        	case "/showQueries":
        		showAllInformation(request, response);
        		break;
        	case "/addToBlacklist":
        		addToBlacklist(request,response);
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
        	case "/part3_3":
        		listUsers(request, response);
        		dispatcher = request.getRequestDispatcher("part3_3.jsp");
        		dispatcher.forward(request, response);
        	case "/excellentGood":
        		excellentGood(request,response);
        		break;
        	case "/postMostItems":
        		postMostItems(request,response);
        		break;
        	case "/part3_5":
        		listUsers(request, response);
        		dispatcher = request.getRequestDispatcher("part3_5.jsp");
        		dispatcher.forward(request, response);
        		break;
        	case "/usersFavorited":
        		usersFavorited(request,response);
        		break;
        	case "/noExcellentItems":
        		noExcellentItems(request,response);
        		break;
        	case "/pNoPoorReview":
        		pNoPoorReview(request,response);
        		break;
        	case "/pAllPoorReview":
        		pAllPoorReview(request,response);
        		break;
        	case "/rNoPoorReview":
        		rNoPoorReview(request,response);
        		break;
        	case "/userPairExcellent":
        		userPairExcellent(request,response);
        		break;
        	}
        }
        catch (SQLException ex) {
            throw new ServletException(ex);
        }
    }
    
    /*---------------------------------------------------------------------------
     * 
     * PROJECT 1 FUNCTIONS
     * 
     --------------------------------------------------------------------------*/
    
    //I feel like this is working correctly
    private void initDatabase(HttpServletRequest request, HttpServletResponse response)
            throws SQLException, IOException, ServletException {
            	InitDatabase.createDatabase();
            	InitDatabase.dropAllTables();
            	//users should go before items if you want to reference users as a foreign key in items
            	InitDatabase.createUserTable();
            	InitDatabase.addUsers();	
            	InitDatabase.createItemTable();
            	InitDatabase.addItems();
            	InitDatabase.createReviewTable();
            	InitDatabase.addReviews();
            	InitDatabase.createFavoriteItemsTable();
            	InitDatabase.createFavoriteUsersTable();
            	InitDatabase.createBlacklist();
            	//InitDatabase.addFavoriteUsers();
            	//go to the welcome page -- which is where you have to login/signup
    			RequestDispatcher dispatcher = request.getRequestDispatcher("Welcome.jsp");       
                dispatcher.forward(request, response);
            }
    
    //noticing I'm calling the same functions over and over so I just condense them into one function
    private void showAllInformation(HttpServletRequest request, HttpServletResponse response)
    		throws SQLException, IOException, ServletException {
    		//show all of the information
    		listItem(request, response);
    		listUsers(request, response);
    		listReviews(request, response);
    }
    
    private void addToBlacklist(HttpServletRequest request, HttpServletResponse response)
    		throws SQLException, IOException, ServletException {
    	
    	String username = request.getParameter("username");
    	InitDatabase.insertBlacklist(username);
    	
    }
    
    //for inserting a singular item into the list
    private void insertItem(HttpServletRequest request, HttpServletResponse response)
    		throws SQLException, IOException, ServletException {
    	
    	//CHECK HOW MANY ITEMS THE USER HAS SUBMITTEED
    	//IF GOOD THEN PROCEED WITH THE INSERT ITEM FUNCTION
    	if(localDate == java.time.LocalDate.now().toString()) {
    		userItem++;
    	}
    	if(userItem <= 5) {
    		
    	//basically you get all the parameters that were added, and create a new item with it
    	//which then goes to insertItem which inserts the item into the SQL
    	String itemName = request.getParameter("itemName");
    	String description = request.getParameter("description");
    	String category = request.getParameter("category");
    	Double price = Double.valueOf(request.getParameter("price"));
    	
    	//had to create a new constructor in this case because the itemID will be autoincremented
    	//and the date will be added within the insert function
    	item insertItem = new item(currentUser, itemName, description, price, category);
    	
    	//indication if the item was successfully added
    	if(InitDatabase.insertItem(insertItem) == true) {
    		System.out.println("Successfully inserted");
    	}
    	else {
    		System.out.println("Item unsuccessful in inserting");
    		response.sendRedirect("initDatabase.jsp");
    	}
    	}
    	//User post limit reached -- print to the user that they have reached the post limit
    	else {
    		System.out.println("User Post Limit Reached");
    		response.sendRedirect("initDatabase.jsp");
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
    //SEARCH ITEM STILL WORKS CORRECTLY -- NO NEED TO CHANGE
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
    		InitDatabase.deleteItem(id, userID);
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
    		List<favoriteUser> favUsers = InitDatabase.listFavoriteUsers(userID);
    		request.setAttribute("favUsers", favUsers);
    		RequestDispatcher dispatcher = request.getRequestDispatcher("listFavoriteUsers.jsp");
    		dispatcher.forward(request, response);
    }
    
    private void deleteUser(HttpServletRequest request, HttpServletResponse response) 
    		throws SQLException, IOException, ServletException {
    		int id = Integer.parseInt(request.getParameter("favUserID"));
    		InitDatabase.deleteUser(id, userID);
    }
    
    private void listUsers(HttpServletRequest request, HttpServletResponse response)
            throws SQLException, IOException, ServletException {
            	List<users> listUsers = InitDatabase.listAllUsers();
            	request.setAttribute("listUsers", listUsers);
            }
    
    private void addFavoriteUser(HttpServletRequest request, HttpServletResponse response) 
    		throws SQLException, IOException, ServletException {
    	
    		int favUserID = Integer.parseInt(request.getParameter("userID"));
    		String favUserUserName = InitDatabase.getfavUsername(favUserID);
    		favoriteUser addFavUser = new favoriteUser(userID, favUserID, favUserUserName);
    		
    		if(InitDatabase.addFavoriteUser(addFavUser) == true) {
    			System.out.println("Successfully Inserted");
    		}
    }
    
    private void displayUser(HttpServletRequest request, HttpServletResponse response)
    		throws SQLException, IOException, ServletException {
    	int favUserID = Integer.parseInt(request.getParameter("favUserID"));
    	String username = InitDatabase.getUserName(favUserID);
    	users userprofile = InitDatabase.getUserProfile(favUserID);
    	request.setAttribute("listUsers", userprofile);    	
    	List<item> userItems = InitDatabase.listUserItems(username);
    	request.setAttribute("listItem", userItems);
    	List<reviews> userReviews = InitDatabase.listUserReviews(username);
    	request.setAttribute("listReviews", userReviews);
    	RequestDispatcher dispatcher = request.getRequestDispatcher("userProfile.jsp");
    	dispatcher.forward(request, response);
    }
    
    //this is the last function when initializing the database, so it forwards the page to initDatabase.jsp
    private void listReviews(HttpServletRequest request, HttpServletResponse response)
            throws SQLException, IOException, ServletException {
            	List<reviews> listReviews = InitDatabase.listAllReviews();
            	request.setAttribute("listReviews", listReviews);
            	
                
                if(currentUser.equals("root")) {
                	RequestDispatcher dispatcher = request.getRequestDispatcher("adminInitDatabase.jsp");
                	dispatcher.forward(request, response);
                }
                else {
                	RequestDispatcher dispatcher = request.getRequestDispatcher("initDatabase.jsp");       
                	dispatcher.forward(request, response);
                }
            }
    
    private void reviewID(HttpServletRequest request, HttpServletResponse response) 
    		throws SQLException, IOException, ServletException {
    	
    		currentReviewID = Integer.parseInt(request.getParameter("itemID"));
    		String compare = InitDatabase.checkSameUsername(currentReviewID);
    		if(compare == currentUser) {
    			RequestDispatcher dispatcher = request.getRequestDispatcher("initDatabase.jsp");
    			dispatcher.forward(request, response);
    		}
    		else {
    		RequestDispatcher dispatcher = request.getRequestDispatcher("writeReview.jsp");
    		dispatcher.forward(request, response);
    		}
    }
    
    private void addReview(HttpServletRequest request, HttpServletResponse response) 
    		throws SQLException, IOException, ServletException {
    	    	
    		if(localDate == java.time.LocalDate.now().toString()) {
    			userReview++;
    		}
    	
    		if(userReview <= 5) {
    		String score = request.getParameter("remark");
    		String remark = request.getParameter("review");
    		int itemID = currentReviewID;
    		String itemTitle = InitDatabase.getItemName(itemID);
    		
    		reviews addReview = new reviews(currentUser, itemID, itemTitle, score, remark);
    		
    		InitDatabase.insertReview(addReview);
    		}
    		else {
    			System.out.println("User Review Limit Reached");
        		RequestDispatcher dispatcher = request.getRequestDispatcher("initDatabase.jsp");
        		dispatcher.forward(request, response);
    		}
    }
        
    private void login(HttpServletRequest request, HttpServletResponse response)
    		throws SQLException, IOException, ServletException {
    		//Most websites have email to log in so I use email only   	
    		// Grab the information from the login in page
    		String email = request.getParameter("email");
			String pass = request.getParameter("password");
			//This checks if the login and the password is sending correctly.
			
			if(InitDatabase.loginCheck(email, pass) == true) {
				userID = InitDatabase.getCurrentID(email, pass);
				currentUser = InitDatabase.getCurrentUsername(email, pass);
				//if successfully logged in, then you should be redirected to showing all information
				//if not then it fails and should be redirected back into login page.
				if(InitDatabase.checkBlacklist(currentUser) == true) {
					RequestDispatcher dispatcher = request.getRequestDispatcher("login.jsp");
					dispatcher.forward(request, response);
				}
				else {
				showAllInformation(request, response);
				}
			}
			//login check fails -- so redirect back to the index page AS A TEST
			else  {
				RequestDispatcher dispatcher = request.getRequestDispatcher("login.jsp");
				dispatcher.forward(request, response);
			}

    }
    
    private void register(HttpServletRequest request, HttpServletResponse response)
    		throws SQLException, IOException, ServletException {
    	
			RequestDispatcher dispatcher; 
    		//for registering the user
    		String username = request.getParameter("username");
    		String email = request.getParameter("email");
    		String password = request.getParameter("password");
    		String password2 = request.getParameter("password2");
    		String firstName = request.getParameter("firstName");
    		String lastName = request.getParameter("lastName");
    		String gender = request.getParameter("gender");
    		int age = Integer.parseInt(request.getParameter("age"));
    		
    		if(!(password.equals(password2))) {
    			dispatcher = request.getRequestDispatcher("register.jsp");
        		dispatcher.forward(request, response);
    		}

    		else if(duplicateUsername(username) == true) {
    			dispatcher = request.getRequestDispatcher("register.jsp");
        		dispatcher.forward(request, response);
    		}
    		
    		else if(duplicateEmail(email) == true) {
    			dispatcher = request.getRequestDispatcher("register.jsp");
    			dispatcher.forward(request, response);
    		}
    		
    		else {
    		//add the user to the database -- this is successfully working
    		users registerUser = new users(username, password, firstName, lastName, email, gender, age);
    		InitDatabase.addOneUser(registerUser);
    		
    		//get the userID and the username of the user
			userID = InitDatabase.getCurrentID(email, password);
    		currentUser = username;
    		
			System.out.print(currentUser);
			System.out.print(userID);
			showAllInformation(request, response);
    }
    }
    
    private void logout(HttpServletRequest request, HttpServletResponse response)
    		throws SQLException, IOException, ServletException {
    	RequestDispatcher dispatcher = request.getRequestDispatcher("Welcome.jsp");
    	dispatcher.forward(request, response);
    }
    
    /*-----------------------------------------------------------------------------------------------------------
     * 
     * PROJECT PART 3 CODE
     * 
     -----------------------------------------------------------------------------------------------------------*/
    public boolean duplicateUsername(String username) throws SQLException{
    	
    	if(InitDatabase.checkDuplicateUsername(username) == true) {
    		//if returns true then return to register because emails do not match
    		return true;
    	}
    		//do nothing if false, this means it is a unique email
    		return false;
        }
    
    public boolean duplicateEmail(String email) throws SQLException {
    	
    	if(InitDatabase.checkDuplicateEmail(email) == true) {
    		return true;
    	}
    	else return false;
    }
    
    //Project Part 2 - sorting the most expensive items in a category
    public void sortExpensive(HttpServletRequest request, HttpServletResponse response)
    		throws SQLException, IOException, ServletException {
    	List<item> sortExpensive = InitDatabase.sortExpensive();
    	request.setAttribute("listItem", sortExpensive);
    }
    

    
    public void sameDay(HttpServletRequest request, HttpServletResponse response)
        	throws SQLException, IOException, ServletException {

    	String category1 = request.getParameter("category1");
    	String category2 = request.getParameter("category2");
    	List<users> sameDay = InitDatabase.sameDay(category1, category2);
    	request.setAttribute("listUsers", sameDay);
    	RequestDispatcher dispatcher = request.getRequestDispatcher("part3_2.jsp");
    	dispatcher.forward(request, response);
    }
    
    public void excellentGood(HttpServletRequest request, HttpServletResponse response)
        	throws SQLException, IOException, ServletException {
    	
    	String username = request.getParameter("usernameX");
    	List<item> excellentGood = InitDatabase.excellentGoodComments(username);
    	request.setAttribute("listItem", excellentGood);
    	listUsers(request, response);
    	RequestDispatcher dispatcher = request.getRequestDispatcher("part3_3.jsp");
    	dispatcher.forward(request, response);
    }
    
    public void postMostItems(HttpServletRequest request, HttpServletResponse response)
        	throws SQLException, IOException, ServletException {
    	List<users> postMostItems = InitDatabase.postMostItems();
    	request.setAttribute("listUsers", postMostItems);
    	RequestDispatcher dispatcher = request.getRequestDispatcher("part3_4.jsp");
    	dispatcher.forward(request, response);
    }
    
    public void usersFavorited(HttpServletRequest request, HttpServletResponse response)
        	throws SQLException, IOException, ServletException {
    	int user1 = Integer.valueOf(request.getParameter("usernameX"));
    	int user2 = Integer.valueOf(request.getParameter("usernameY"));
    	List<favoriteUser> usersFavorited = InitDatabase.usersFavorited(user1, user2);
    	request.setAttribute("listFavUsers", usersFavorited);
    	RequestDispatcher dispatcher = request.getRequestDispatcher("part3_5.jsp");
    	dispatcher.forward(request, response);
    }
    
    public void noExcellentItems(HttpServletRequest request, HttpServletResponse response)
        	throws SQLException, IOException, ServletException {
    	
    	List<users> noExcellentItems = InitDatabase.noExcellentItems();
    	request.setAttribute("listUsers", noExcellentItems);
    	RequestDispatcher dispatcher = request.getRequestDispatcher("part3_6.jsp");
    	dispatcher.forward(request, response);
    	
    }
    
    public void pNoPoorReview(HttpServletRequest request, HttpServletResponse response)
        	throws SQLException, IOException, ServletException {
    	List<users> listUsers = InitDatabase.pNoPoorReview();
    	request.setAttribute("listUsers", listUsers);
    	RequestDispatcher dispatcher = request.getRequestDispatcher("part3_7.jsp");
    	dispatcher.forward(request, response);
    }
    
    public void pAllPoorReview(HttpServletRequest request, HttpServletResponse response)
        	throws SQLException, IOException, ServletException {
    	List<users> listUsers = InitDatabase.pAllPoorReview();
    	request.setAttribute("listUsers", listUsers);
    	RequestDispatcher dispatcher = request.getRequestDispatcher("part3_8.jsp");
    	dispatcher.forward(request, response);
    }
    
    public void rNoPoorReview(HttpServletRequest request, HttpServletResponse response)
        	throws SQLException, IOException, ServletException {
    	List<users> listUsers = InitDatabase.rNoPoorReview();
    	request.setAttribute("listUsers", listUsers);
    	RequestDispatcher dispatcher = request.getRequestDispatcher("part3_9.jsp");
    	dispatcher.forward(request, response);
    }
    
    public void userPairExcellent(HttpServletRequest request, HttpServletResponse response)
        	throws SQLException, IOException, ServletException {
    	List<userPair> userPair = InitDatabase.userPairExcellent();
    	request.setAttribute("userPair", userPair);
    	RequestDispatcher dispatcher = request.getRequestDispatcher("part3_10.jsp");
    	dispatcher.forward(request, response);
    }
}
