package com.tap;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/admincall")
public class Adminservlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	String username;
	String password;
	PrintWriter out;

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		out = resp.getWriter();
		username = req.getParameter("username");
		password = req.getParameter("password");
		getData(req, resp);

	}

	public void getData(HttpServletRequest req, HttpServletResponse resp) {
		ResultSet rs = null;
		Statement st = null;
		Connection con = null;

		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
			con = DriverManager.getConnection("jdbc:mysql://localhost:3306/bank", "root", "root");
			st = con.createStatement();
			rs = st.executeQuery("select * from admin");
			while (rs.next()) {
				String user = rs.getString("username");
				String pass = rs.getString("password");
				verify(resp, user, pass);
			}
		} catch (Exception e) {
			e.getMessage();
		} finally {
			try {
				rs.close();
				st.close();
				con.close();
			} catch (Exception e) {
				e.getMessage();
			}
		}

	}

	public void verify(HttpServletResponse resp, String user, String pass) {
		if (username.equals(user) && password.equals(pass)) {
			try {
				resp.sendRedirect("admindashboard.html");
			} catch (Exception e) {
				e.printStackTrace();
			}
		} else {
			out.println("login failed.......");
		}
	}

}
