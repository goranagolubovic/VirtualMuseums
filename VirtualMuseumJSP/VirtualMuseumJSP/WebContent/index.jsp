<%@page import="beans.UserAccountBean"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<%@ page import="service.UserAccountService"%>
<%@ page import="java.sql.*"%>
<%@ page import="java.io.*"%>
<%@page import="java.sql.DriverManager"%>
<%@page import="java.sql.ResultSet"%>
<%@page import="java.sql.Statement"%>
<%@page import="java.sql.Connection"%>
<jsp:useBean id="userAccountBean" class="beans.UserAccountBean"
	scope="session"></jsp:useBean>
<%
	if(request.getParameter("authorization")!="")
	{
	if ("GET".equals(request.getMethod())) {
} else {
	if ("delete".equals(request.getParameter("action"))) {
		UserAccountService.deleteUser(request.getParameter("id"));
	}
	if ("update".equals(request.getParameter("action"))) {
		UserAccountService.updatePassword(request.getParameter("id"));
	}
	if ("approve".equals(request.getParameter("action"))) {
		UserAccountService.updateApprovement(request.getParameter("id"), request.getParameter("approver"));
	}
}
}
	else{
		return;
	}
%>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Admin JSP</title>
<link href="styles/style.css" type="text/css" rel="stylesheet">
<link rel="stylesheet"
	href="https://maxcdn.bootstrapcdn.com/bootstrap/4.0.0/css/bootstrap.min.css"
	integrity="sha384-Gn5384xqQ1aoWXA+058RXPxPg6fy4IWvTNh0E263XmFcJlSAwiGgFAW/dAiS6JXm"
	crossorigin="anonymous">
</head>

<body class="background">

	<table class="table table-bordered">
		<thead>
			<tr>
				<th class="text-center" scope="col">Name</th>
				<th class="text-center" scope="col">Surname</th>
				<th class="text-center" scope="col">Username</th>
				<th class="text-center" scope="col">Password</th>
				<th class="text-center" scope="col">Reset password</th>
				<th class="text-center" scope="col">Email</th>
				<th class="text-center" scope="col">Active</th>
				<th class="text-center" scope="col">Approved</th>
				<th class="text-center" scope="col">Delete account</th>
			</tr>
		</thead>
		<tbody>
			<%
				for (UserAccountBean user : UserAccountService.getUsers()) {
			%>
			<tr>
				<td><%=user.getIme()%></td>
				<td><%=user.getPrezime()%></td>
				<td><%=user.getKorisnicko_ime()%></td>
				<td><input class="password-input" type="password"
					value="<%=user.getLozinka()%>"></td>
				<td class="text-center">
					<form method="post" action="?action=update&id=<%=user.getId()%>">
						<button type="submit" value="Reset">Reset</button>
					</form>
				</td>
				<td><%=user.getEmail()%></td>
				<td><%=user.getIs_active()%></td>
				<td class="text-center">
				<form  method="post"
					action="?action=approve&checked=approver&id=<%=user.getId()%>">
					<input class="checkbox-input"
					name="approver" type="checkbox"
					<%=user.getIs_approved() ? "checked='checked'" : ""%>
					onchange="submit()" />
					</form></td>
				
				<td>
					<form method="post" action="?action=delete&id=<%=user.getId()%>">
						<input type="submit" value="Delete">
					</form>
				</td>
				<%
					}
				%>
			
		</tbody>
	</table>
</body>
</html>