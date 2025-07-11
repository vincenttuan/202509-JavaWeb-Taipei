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
		<script type="text/javascript">
			// 檢驗密碼資訊
			function checkPassword() {
				const oldPwd = document.getElementById('oldPassword').value;
				const newPwd = document.getElementById('newPassword').value;
				const confirmPwd = document.getElementById('confirmPassword').value;
				// 新密碼不可以等於舊密碼
				if(oldPwd == newPwd) {
					alert('新密碼不可以等於舊密碼');
					return false;
				}
				// 二次新密碼必須相等
				if(newPwd != confirmPwd) {
					alert('二次新密碼必須相等');
					return false;
				}
				return true;
			}
		</script>
	</head>
	<body>
		<form class="pure-form login-form" onsubmit="return checkPassword()" method="post" action="/JavaWeb/user/change/password">
			<fieldset>
				<legend>修改密碼</legend>
				👨‍⚕️ <%=session.getAttribute("username") %><p /> 
				🔐 <input type="password" id="oldPassword" name="oldPassword" placeholder="請輸入舊密碼" required /><p />
				🔐 <input type="password" id="newPassword" name="newPassword" placeholder="請輸入新密碼" required /><p />
				🔐 <input type="password" id="confirmPassword" name="confirmPassword" placeholder="請再輸入一次新密碼" required /><p />
				<button type="submit" class="pure-button pure-button-primary">傳送</button>
			</fieldset>‍
		</form>
	</body>
</html>