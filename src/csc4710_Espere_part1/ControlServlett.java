package csc4710_Espere_part1;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.List;
 
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import java.sql.Connection;
import java.sql.Statement;
import java.sql.PreparedStatement;


public class ControlServlett extends HttpServlet{
	private static final long serialVersionUID = 1L;
	private InitDatabase InitDatabase;
	
	public void init(){
		try {
		InitDatabase = new InitDatabase();
		}
		catch(SQLException e) {
			
		}
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
            	listItem(request, response); 
            	listUsers(request,response);
        } 
        catch (SQLException ex) {
            throw new ServletException(ex);
        }
    }
    
    private void listItem(HttpServletRequest request, HttpServletResponse response)
            throws SQLException, IOException, ServletException {
            	List<item> listItem = InitDatabase.listAllItems();
            	request.setAttribute("listItem", listItem);
            	RequestDispatcher dispatcher = request.getRequestDispatcher("initDatabase.jsp");       
                dispatcher.forward(request, response);
            }
    private void listUsers(HttpServletRequest request, HttpServletResponse response)
            throws SQLException, IOException, ServletException {
            	List<users> listUsers = InitDatabase.listAllUsers();
            	request.setAttribute("listUsers", listUsers);
            	RequestDispatcher dispatcher = request.getRequestDispatcher("initDatabase.jsp");       
                dispatcher.forward(request, response);
            }
}
