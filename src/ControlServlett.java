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
    
    //these are for showing the results of the table
    private void listItem(HttpServletRequest request, HttpServletResponse response)
            throws SQLException, IOException, ServletException {
            	List<item> listItem = InitDatabase.listAllItems();
            	request.setAttribute("listItem", listItem);
            	
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
    		RequestDispatcher dispatcher;
    		String username = request.getParameter("username");
    		String password = request.getParameter("password");
    		String firstName = request.getParameter("firstName");
    		String lastName = request.getParameter("lastName");
    		String gender = request.getParameter("gender");
    		int age = Integer.parseInt(request.getParameter("age"));
    		
    		//add the user to the database
    		//InitDatabase.addOneUser(username, password, firstName, lastName, gender, age);
    		
    		/*try {
    			//InitDatabase.addOneUser(username,password,firstName,lastName,gender,age);
    		} catch (SQLException e) {
    			// TODO Auto-generated catch block
    			e.printStackTrace();
    		}*/
    		//add the name to the users table in the database
    		usersession = request.getSession();
    		usersession.setAttribute("currentUser", username);
    		response.sendRedirect("Welcome.jsp");
    		
    }
}
