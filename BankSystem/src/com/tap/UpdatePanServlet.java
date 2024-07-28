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

@WebServlet("/Updatepan")
public class UpdatePanServlet extends HttpServlet
{
	private static final long serialVersionUID = 1L;

@Override
protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
	PrintWriter out=resp.getWriter();
	 String pancard=req.getParameter("pan");
	 long mob=Long.parseLong(req.getParameter("mob"));
	 
	 try 
	 {
		Class.forName("com.mysql.cj.jdbc.Driver");
		Connection con=DriverManager.getConnection("jdbc:mysql://localhost:3306/bank","root","root");
		PreparedStatement pst=con.prepareStatement("update account set pan_card=? where mobile_number=?");
		pst.setString(1, pancard);
		pst.setLong(2, mob);
		int n=pst.executeUpdate();
		
		if(n==1)
		{
			out.println("pancard updated successfully");
		}
		else
		{
			out.println("not updated");
		}
	}
	 catch (Exception e)
	 {
		 e.printStackTrace();
	}
	 
}
}
