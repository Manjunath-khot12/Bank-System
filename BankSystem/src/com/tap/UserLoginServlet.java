package com.tap;
 import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/userLogin")
public class UserLoginServlet  extends  HttpServlet
{
	private static final long serialVersionUID = 1L;

@Override
protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
	PrintWriter out=resp.getWriter();
	String email=req.getParameter("email");
	String password=req.getParameter("password");
	String ad =null;
	String pass = null;
	
	try
	{
		Class.forName("com.mysql.cj.jdbc.Driver");
		Connection con=DriverManager.getConnection("jdbc:mysql://localhost:3306/bank","root","root");
		PreparedStatement pst=con.prepareStatement("select * from account where password=?");
		pst.setString(1,password);
		ResultSet rs=pst.executeQuery();
		while(rs.next())
		{
			 ad=rs.getString("email_id");
		     pass=rs.getString("password");
			String name=rs.getString(2);
			req.setAttribute("acc_name", name);
		}
		if(email.equals(ad) && password.equals(pass))
		{
			RequestDispatcher rd=req.getRequestDispatcher("userdashboard.jsp");
			rd.include(req, resp);
		}
		else
		{
			out.println("invalid user.....");
		}
		
	}
	catch (Exception e)
	{
		e.printStackTrace();
	}
}
}
