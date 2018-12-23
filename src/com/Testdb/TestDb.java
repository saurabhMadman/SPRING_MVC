package com.Testdb;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class TestDb
 */
@WebServlet("/TestDb")
public class TestDb extends HttpServlet {
	private static final long serialVersionUID = 1L;


	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		String user="springstudent";
		String pass="springstudent";
		
		String jdbcUrl="jdbc:mysql://localhost:3306/web_customer_tracker?userSSL=fasle&ServerTimeZone=UTC";
	    String driver="com.mysql.jdbc.Driver";
	    
	   try
	   {
		Class.forName(driver);
	    PrintWriter wr = response.getWriter();
        
	    wr.print("Connectig" +jdbcUrl);
	    try(Connection con= DriverManager.getConnection(jdbcUrl,user,pass))
	    {
	    	wr.println("Connection Stablised"+"\n");
	    }catch(Exception ex)
	    {
	    	ex.printStackTrace();
	    	throw new ServletException();
	    	
	    }
		
	   } catch (ClassNotFoundException e){
	    	e.printStackTrace();
	   }
	   
		
		
		
		
	}

}
