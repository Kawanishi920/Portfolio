<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>アカウント更新</title>
<link rel="stylesheet" href="css/style.css">
</head>
<body>
	<div class="form-container">
		<h1>アカウント更新</h1>
		<form action="./Update" method="post">
			<label for="username">新しいユーザー名</label><input type="text"
				id="username" name="username" value="${user.userName }"><br>
			<label for="email">新しいメールアドレス</label> <input type="email" id="email"
				name="email" value="${user.email}"> <br> <label
				for="password">新しいパスワード</label> <input type="password" id="password"
				name="password" value="${user.password}"> <br>
			<button type="submit">更新</button>
		</form>
		<a href="./Main">Homeに戻る</a>
	</div>
</body>
</html>