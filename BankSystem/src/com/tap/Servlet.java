package com.tap;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.Date;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.Time;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/call")
public class Servlet extends HttpServlet
{
	private static final long serialVersionUID = 1L;
	Connection con;
	PreparedStatement pst;
	int n=0;
	PrintWriter out;
	Date date=new Date(0);
	String name;
	long number;
	long adhar;
	String email;
	String account;
	String password;
    String sql="insert into account(account_holder_name,mobile_number,adhar_card,email_id,date,time,type_of_account,password) values(?,?,?,?,?,?,?,?)"; 
     
    @Override
    protected void service(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException 
    {
    	out=res.getWriter();
    	res.setContentType("text/html");
    	name=req.getParameter("FullName");
        number=Long.parseLong(req.getParameter("number"));
    	adhar=Long.parseLong(req.getParameter("Adhar"));
        email=req.getParameter("email");
        account=req.getParameter("account-type");
        password=req.getParameter("password");
        craeteAccount();
    }
        
    public void craeteAccount()
    {
    	try
    	{
    		Class.forName("com.mysql.cj.jdbc.Driver");
    		con=DriverManager.getConnection("jdbc:mysql://localhost:3306/bank","root","root");
    		pst=con.prepareStatement(sql);
			pst.setString(1,name);
			pst.setLong(2,number);
			pst.setLong(3, adhar);
			pst.setString(4, email);
		    java.sql.Date sqldate=new Date(date.getDay());
		    pst.setDate(5, sqldate);
		    java.sql.Time sqltime=new Time(date.getTime());
		    pst.setTime(6, sqltime);
		    pst.setString(7,account);
		    pst.setString(8,password);
			n=pst.executeUpdate();
			verify();
    	}
    	catch(Exception e)
    	{
    		String msg=e.getMessage();
    		System.out.println(msg);
    	}
        finally
        {
       	    try 
        	{
				pst.close();
				con.close();
			}
       	   catch (Exception e)
       	    {
				e.printStackTrace();
		    }
       }
    }
    
    public void verify()
    {
    	if(n==1)
    	{
    	    out.println("Account Created Successfull....");
    	}
    	else
    	{
    		out.println("Account not created ?.");
    	}
    }
}
