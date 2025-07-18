<%@page import="model.Chat"%>
<%@page import="java.util.List"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%
	List<Chat> chats = (List)request.getAttribute("chats");
%>    
<!DOCTYPE html>
<html>
	<head>
		<meta charset="UTF-8">
		<title>Chat Result</title>
		<link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/purecss@3.0.0/build/pure-min.css">
	</head>
	<body>
		<table border="0">
		<% for(Chat chat : chats) { %>
			<tr>
				<td><%=chat.getCreateTime() %></td>
				<td><%=chat.getQuestion() %></td>
				<td><%=chat.getAnswer().replace("\n", "<br />") %></td>
			</tr>
		<% } %>
		</table>
	</body>
</html>