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

@WebServlet("/usersetaccount")
public class usersetaccountServlet extends HttpServlet
{
	private static final long serialVersionUID = 1L;

@Override
protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
	PrintWriter out=resp.getWriter();
	int acc=Integer.parseInt(req.getParameter("acc"));
	
	try
	{
		Class.forName("com.mysql.cj.jdbc.Driver");
		Connection con=DriverManager.getConnection("jdbc:mysql://localhost:3306/bank","root","root");
		PreparedStatement pst=con.prepareStatement("insert into transaction(account_number) values(?)");
		pst.setInt(1, acc);
		int n=pst.executeUpdate();
		if(n==1)
		{
			out.println("account number is updated");
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
