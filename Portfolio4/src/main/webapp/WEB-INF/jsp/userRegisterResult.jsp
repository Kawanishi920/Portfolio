<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ page import="model.UserModel"%>
<%
//セッションスコープからユーザー情報を取得
UserModel loginUser = (UserModel) session.getAttribute("loginUser");
%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>新規アカウント登録</title>
<link rel="stylesheet" href="css/style.css">
</head>
<body>
	<div class="center">

		<h1>アカウント登録</h1>


		<p>アカウント登録しました</p>

		<div class="center-container">
			<a href="Main">つぶやく</a>
		</div>
	</div>
</body>
</html>