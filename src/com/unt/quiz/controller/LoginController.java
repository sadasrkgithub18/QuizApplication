package com.unt.quiz.controller;

import java.io.IOException;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;


import db_connection.ConnectionProvider;

@WebServlet("/checkLogin")
public class LoginController extends HttpServlet {
	private static final long serialVersionUID = 1L;
   
    public LoginController() {
        super();
    }

	
	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		String username=request.getParameter("username");
		String password=request.getParameter("password");				
		Connection con=ConnectionProvider.getConnection();		
		ResultSet set=null;
		String id = "";
		int i=0;
		try
		{
		 Statement st=con.createStatement();
		 String sql = "Select * from  users where username='"+username+"' and password='"+password+"' ";
		 		System.out.println(sql);
		 set=st.executeQuery(sql);
		 while(set.next())
		 {
			 i=1;
			 id = set.getString("id");
System.out.println(" id of user is "+id);
		 }
		 if(i!=0)
		 {   HttpSession session=request.getSession();
		     session.setAttribute("user",username);
		     session.setAttribute("id",id);
			 RequestDispatcher rd=request.getRequestDispatcher("/WEB-INF/jsps/home.jsp");
			 rd.forward(request, response);
			 
		 }
		 else
		 {   request.setAttribute("errorMessage","Invalid username or password");
			 RequestDispatcher rd=request.getRequestDispatcher("/WEB-INF/jsps/logins.jsp");
			 rd.forward(request, response);
		 }
		}catch(SQLException sqe){System.out.println("Error : While Fetching records from database");}
		try
		{
		 con.close();	
		}catch(SQLException se){System.out.println("Error : While Closing Connection");}
	}
}
