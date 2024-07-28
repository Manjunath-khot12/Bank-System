package com.tap;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/setNumber")
public class SetNumberServlet extends HttpServlet
{
	private static final long serialVersionUID = 1L;
	@Override
	protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException 
	{
		PrintWriter out=resp.getWriter();
		long adhar=Long.parseLong(req.getParameter("adhar"));
		int acc=Integer.parseInt(req.getParameter("account_number"));
		
		try
		{
			Class.forName("com.mysql.cj.jdbc.Driver");
			Connection con=DriverManager.getConnection("jdbc:mysql://localhost:3306/bank","root","root");
			PreparedStatement pst=con.prepareStatement("update account set account_number=? where adhar_card=?");
			pst.setInt(1, acc);
			pst.setLong(2, adhar);
			
			int n=pst.executeUpdate();
			if(n==1)
			{
			  out.println("Your, Account Number is Updated");	
			}
			else
			{
				out.println("Account number is not updated.....");
			}
		}
		catch (Exception e)
		{
		  e.getMessage();
		}
	}

}
