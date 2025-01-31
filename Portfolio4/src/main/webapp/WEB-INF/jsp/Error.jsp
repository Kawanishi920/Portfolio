<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>エラー</title>
<link rel="stylesheet" href="css/style.css">
</head>
<body>
	<div class="center">
		<h1>エラーが発生しました</h1>

		<p>${error}</p>
		<%-- エラーメッセージを表示 --%>
		<a href="Login">メインページに戻る</a>
	</div>
</body>
</html>