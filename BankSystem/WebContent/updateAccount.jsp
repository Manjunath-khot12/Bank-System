<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ page import="java.sql.*" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Insert title here</title>
</head>
<body>
  <%
   Class.forName("com.mysql.cj.jdbc.Driver");
   Connection con=DriverManager.getConnection("jdbc:mysql://localhost:3306/bank","root","root");
   Statement st=con.createStatement();
   ResultSet rs=st.executeQuery("select account_holder_name,adhar_card,account_number from account where account_number is null");
   while(rs.next())
   {
  %>
  <table border="1px solid black">
   <thead>
   <tr>
   <th>Account-holder-name</th>
   <th>Adhar-card</th>
   <th>Account-number</th>
   <th>status</th>
   </tr>
   </thead>
   <tbody>
   <tr>
   <td><%=rs.getString(1)%></td>
   <td><%=rs.getLong(2) %></td>
   <td><%=rs.getInt(3) %></td>
   <td><a href="setAccountNumber.html">update</a></td>
   </tr>
   </tbody>
  </table>
  <%
   }
  %>
</body>
</html>