<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
 <%@ page import ="dto.Transaction" %>
 <%@ page import ="dao.BillDAO" %>
<!DOCTYPE html>
<jsp:useBean id="transactionBean" class="beans.TransactionBean" scope="request"></jsp:useBean>
<jsp:useBean id="paymentBean" class="beans.PaymentBean" scope = "request"></jsp:useBean>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Insert title here</title>
<link href="styles/style.css" rel="stylesheet" type="text/css">

</head>
<body >
<h1>TRANSACTIONS</h1>
<br>
<br>
<div class="switch-button">
<label class="switch">
<% if(request.getParameter("userId")==null) { %>
<h2>You don't have permission for this page....</h2>
<% return; }   %>
<% if(BillDAO.selectByUserId(Integer.valueOf(request.getParameter("userId"))).isPaymentEnabled()) { %>
<input type="checkbox" checked method="post"  onclick="checkState(event)">
<% } else {  %>
<input type="checkbox" method="post"  onclick="checkState(event)">
<% }  %>
<span class="slider round"></span>
</label>
<p> Allow payment</p>
</div>
<%
	for(Transaction transaction:transactionBean.getAll())
		{%>
		<div class="content-div">
		<p><%=transaction.getDate() %></p>
		<table>
			<tr>
			<div style='float: left; margin-left:10px; text-align: left; '>€</div>
            <div style='float: right; margin-right:10px; text-align: right;'>- <%=transaction.getOutflow() %></div>
			</tr>
			</table>
		</div><%
		 }
%>
<form method="get" action="transactions.jsp">
</form>
</body>
</html>
<script>
function checkState(event)
{ 
    if (event.target.checked)
    {
    	fetch("http://localhost:8081/VirtualMuseumJSPM2/PaymentController?"+new URLSearchParams({
    	    state: 1,
    	    userId:<%=request.getParameter("userId")%>}))
    	.then(response=>response.text())
    	.then(text=>console.log(text)); 
    }
    else{
    	fetch("http://localhost:8081/VirtualMuseumJSPM2/PaymentController?"+new URLSearchParams({
    	    state: 0,
    	    userId:<%=request.getParameter("userId")%>}))
    	.then(response=>response.text())
    	.then(text=>console.log(text)); 
    }
	
    
}

</script>