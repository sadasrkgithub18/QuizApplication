package com.unt.quiz.controller;

import java.io.IOException;
import java.io.InputStream;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.Collection;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.Part;


import db_connection.ConnectionProvider;


@MultipartConfig
@WebServlet("/checkRegister")
public class RegistrationController extends HttpServlet {
	private static final long serialVersionUID = 1L;
  
    public RegistrationController() {
        super();
    }

    
    
	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {		
		Collection<Part> parts = request.getParts();
		String resp = "/WEB-INF/jsps/regSuccess.jsp";
		if (parts.size() != 3) {
	       }
		
		 Part filePart = request.getPart("photo");
	       InputStream imageInputStream = filePart.getInputStream();


	      
	       
	       
		String username=request.getParameter("username");
		String email=request.getParameter("email");
		String password=request.getParameter("password");
		String mobile=request.getParameter("mobile");
		String city=request.getParameter("City");
		
		Connection con=ConnectionProvider.getConnection();
		
		try
		{
			PreparedStatement ps = con.prepareStatement("INSERT INTO users(username,password,email,mobile,city,photo) values(?,?,?,?,?,?)");
		 ps.setString(1, username);
		 ps.setString(2, password);
		 ps.setString(3, email);
		 ps.setString(4, mobile);
		 ps.setString(5, city);
		 ps.setBinaryStream(6,imageInputStream,imageInputStream.available());
		 int i = ps.executeUpdate();
		 if(i!=0){
			 System.out.println("image inserted successfully");
		 }else{
			 System.out.println("problem in image insertion");
		 }
		}catch(SQLException sqe){
			resp = "/WEB-INF/jsps/register.jsp";
			System.out.println("Error : While Inserting record in database");
			request.setAttribute("err",sqe.getMessage());
			}
		try
		{
		 con.close();	
		}catch(SQLException se){
			System.out.println("Error : While Closing Connection");
			
		}
        request.setAttribute("newUser",username);
		RequestDispatcher dispatcher=request.getRequestDispatcher(resp);
		dispatcher.forward(request, response);		
	}
	
	
	

}
