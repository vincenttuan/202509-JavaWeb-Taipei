<%@page import="model.Guestbook"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%
	Guestbook gb = (Guestbook)request.getAttribute("guestbook");
%>    
<!DOCTYPE html>
<html>
	<head>
		<meta charset="UTF-8">
		<title>訪客留言版(修改)</title>
		<link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/purecss@3.0.0/build/pure-min.css">
	</head>
	<body style="padding: 20px">
		<form class="pure-form" method="post" action="/JavaWeb/guestbook/update">
			<fieldset>
				<legend>訪客留言(修改)</legend>
				<input type="hidden" name="id" value="<%=gb.getId() %>" />
				暱稱: <input type="text" name="name" value="<%=gb.getName() %>" placeholder="請輸入暱稱" required /><p />
				留言: <input type="text" name="message" value="<%=gb.getMessage() %>" placeholder="請輸入留言" required />
				<button type="submit" class="pure-button pure-button-primary">(修改)</button>
			</fieldset>
		</form>
	</body>
</html>