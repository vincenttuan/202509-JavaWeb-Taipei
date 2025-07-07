<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
	<head>
		<meta charset="UTF-8">
		<title>修改密碼</title>
		<link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/purecss@3.0.0/build/pure-min.css">
		<style type="text/css">
	        body {
	            height: 100vh;
	            margin: 0;
	            display: flex;
	            justify-content: center;
	            align-items: center;
	        }
	        .login-form {
	            padding: 20px;
	            border: 1px solid #ccc;
	            background: #fff;
	        }
		</style>
	</head>
	<body>
		<form class="pure-form login-form" method="post" action="/JavaWeb/user/change/password">
			<fieldset>
				<legend>修改密碼</legend>
				👨‍⚕️ <%=session.getAttribute("username") %><p /> 
				🔐 <input type="password" name="oldPassword" placeholder="請輸入舊密碼" required /><p />
				🔐 <input type="password" name="newPassword" placeholder="請輸入新密碼" required /><p />
				🔐 <input type="password" name="confirmPassword" placeholder="請再輸入一次新密碼" required /><p />
				<button type="submit" class="pure-button pure-button-primary">傳送</button>
			</fieldset>‍
		</form>
	</body>
</html>