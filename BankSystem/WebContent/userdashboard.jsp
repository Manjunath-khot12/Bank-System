<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Insert title here</title>
</head>
<body>
   <%
    String name=(String)request.getAttribute("acc_name");
   out.println("hi "+name);
   %>
   <br>
   <br>
   <a href="updatepan.html">Update Pan_Card Number</a><br><br>
   <a href="UserAccountNumberSet.html">set Account_Number</a>
</body>
</html>