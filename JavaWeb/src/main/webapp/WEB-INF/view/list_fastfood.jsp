<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="jakarta.tags.core" %>    
<!DOCTYPE html>
<html>
	<head>
		<meta charset="UTF-8">
		<title>List Fastfood</title>
		<link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/purecss@3.0.0/build/pure-min.css">
		<link rel="stylesheet" href="/JavaWeb/css/buttons.css">
	</head>
	<body style="padding: 20px">
		<div class="pure-form">
			<fieldset>
				<legend>List Fastfood</legend>
				<table class="pure-table pure-table-bordered">
					<thead>
						<tr>
							<th>id</th><th>name</th><th>price</th><th>image</th>
						</tr>
					</thead>
					<tbody>
						<c:forEach var="fastfood" items="${ fastfoods }">
							<tr>
								<td>${ fastfood.productId }</td>
								<td>${ fastfood.productName }</td>
								<td>${ fastfood.productPrice }</td>
								<td>${ fastfood.productImage }</td>
							</tr>
						</c:forEach>
					</tbody>
				
				</table>
			</fieldset>
		</div>
	</body>
</html>