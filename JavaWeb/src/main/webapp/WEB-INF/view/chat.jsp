<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
	<head>
		<meta charset="UTF-8">
		<title>Chat</title>
		<link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/purecss@3.0.0/build/pure-min.css">
	</head>
	<body style="padding: 20px">
		<form class="pure-form" method="post" action="/JavaWeb/chat" target="_result">
			<fieldset>
				<legend>Chat</legend>
				${username} <%=session.getAttribute("username") %> 的問題: <input type="text" placeholder="請輸入問題" name="message" required /><p />
				<button type="submit" class="pure-button pure-button-primary">詢問 AI</button>
				<a href="/logout" class="pure-button">登出</a>
			</fieldset>
		</form>
		<iframe name="_result" frameborder="0" width="1000"></iframe>
	</body>
</html>