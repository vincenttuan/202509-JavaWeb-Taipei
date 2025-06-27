<%@page import="model.Guestbook"%>
<%@page import="java.util.List"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%
	// 取得 servlet 傳來的 guestbooks 資料
	List<Guestbook> guestbooks = (List<Guestbook>)request.getAttribute("guestbooks");
%>    
<!DOCTYPE html>
<html>
	<head>
		<meta charset="UTF-8">
		<title>訪客留言版</title>
		<link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/purecss@3.0.0/build/pure-min.css">
	</head>
	<body style="padding: 20px">
		<form class="pure-form" method="post" action="/JavaWeb/guestbook">
			<fieldset>
				<legend>訪客留言</legend>
				暱稱: <input type="text" name="name" placeholder="請輸入暱稱" required /><p />
				留言: <input type="text" name="message" placeholder="請輸入留言" required />
				<button type="submit" class="pure-button pure-button-primary">送出</button>
			</fieldset>
		</form>
		<div class="pure-form">
			<fieldset>
				<legend>留言紀錄</legend>
				<table class="pure-table">
					<thead>
						<tr>
							<th>序號</th><th>暱稱</th><th>留言</th><th>時間</th><th>刪除</th><th>修改</th>
						</tr>
					</thead>
					<tbody>
						<% for(Guestbook gb : guestbooks) { %>
							<tr>
								<td><%=gb.getId() %></td>
								<td><%=gb.getName() %></td>
								<td><%=gb.getMessage() %></td>
								<td><%=gb.getCreateAt() %></td>
								<td><a href="/JavaWeb/guestbook/delete?id=<%=gb.getId() %>">✂</a></td>
								<td><a href="/JavaWeb/guestbook/update?id=<%=gb.getId() %>">📝</a></td>
							</tr>
						<% } %>
					</tbody>
				</table>
			</fieldset>
		</div>
	</body>
</html>