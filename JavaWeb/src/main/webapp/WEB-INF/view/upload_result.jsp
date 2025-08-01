<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
	<head>
		<meta charset="UTF-8">
		<title>Upload Result</title>
		<link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/purecss@3.0.0/build/pure-min.css">
		<link rel="stylesheet" href="/JavaWeb/css/buttons.css">
	</head>
	<body>
		<div id="pure-form">
			<fieldset>
				<legend>上傳結果</legend>
				<%=request.getAttribute("message") %>
			</fieldset>
		</div>
	</body>
</html>