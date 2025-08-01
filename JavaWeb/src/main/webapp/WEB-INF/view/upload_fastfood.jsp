<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
	<head>
		<meta charset="UTF-8">
		<title>上傳速食商品</title>
		<link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/purecss@3.0.0/build/pure-min.css">
		<link rel="stylesheet" href="/JavaWeb/css/buttons.css">
		<style type="text/css">
			#previewImage {
				max-width: 300px;
				max-height: 300px;
				margin-top: 10px;
				display: none;
				border: 1px solid #ccc;
			}
		</style>
	</head>
	<body style="padding: 20px">
		<!-- Menu -->
		<%@include file="/WEB-INF/view/menu.jspf" %>
		<form class="pure-form" method="post" action="/JavaWeb/upload/fastfood" enctype="multipart/form-data">
			<fieldset>
				<legend>上傳速食商品</legend>
				商品編號: <input type="text" id="productId" name="productId" required /><p />
				商品名稱: <input type="text" id="productName" name="productName" required /><p />
				商品價格: <input type="number" id="productPrice" name="productPrice" required /><p />
				<input type="file" id="productImage" name="productImage" accept="image/*" />
				<button type="submit" class="button-success pure-button">傳送</button><p />
				<!-- 圖片預覽 -->
				<img id="previewImage" alt="圖片預覽" /><p />
			</fieldset>
		</form>
	</body>
	<script>
		document.getElementById('productImage').addEventListener('change', function(event) {
			// 得到檔案
			const file = event.target.files[0];
			if(!file) return;
			
			// 取得檔名
			// 例如: d1417玉米湯33.png 去除附檔名後變成 d1417玉米湯33
			const fileName = file.name.substring(0, file.name.lastIndexOf('.'));
			console.log(fileName);
			
			// 利用正則表示來拆解檔名
			const regex = /^([a-zA-Z0-9]+)(.+?)(\d+)$/;
			const match = fileName.match(regex);
			
			if(match) {
				document.getElementById('productId').value = match[1];
				document.getElementById('productName').value = match[2];
				document.getElementById('productPrice').value = match[3];
				// 預覽圖片
				const preview = document.getElementById('previewImage');
				const reader = new FileReader();
				reader.onload = function(e) {
					preview.src = e.target.result; // 圖片源
					console.log(e.target.result);
					preview.style.display = "block"; // 顯示圖片
				};
				// 用 readAsDataURL() 讀取選取的圖片檔案，並從 onload 事件中取得 Base64 字串設給圖片 src。
				reader.readAsDataURL(file);
			} else {
				alert('檔名格式不正確 !');
			}
			
		});
	</script>
	
</html>