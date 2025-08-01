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
				<td width="50" title="刪除">
					<a style="color: red;" href="/JavaWeb/chat/delete?id=<%=chat.getId() %>">✂</a>
				</td>
				<td width="100"><%=chat.getCreateTime() %></td>
				<td style="color: blue"><%=chat.getQuestion() %></td>
			</tr>		
			<tr>	
				<td colspan="3"><%=chat.getAnswer().replace("\n", "<br />") %></td>
			</tr>
		<% } %>
		</table>
	</body>
</html>