<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>ユーザー登録</title>
<link rel="stylesheet" href="css/style.css">
</head>
<body>
	<div class="form-container">
		<h1>ユーザー登録</h1>
		<p style="color: red;">${error}</p>
		<form action="UserRegister" method="post">
			<label for="user_name">ユーザー名</label> <input type="text"
				id="user_name" name="user_name" value="${user.userName}"> <br>
			<label for="email">メールアドレス</label> <input type="email" id="email"
				name="email" value="${user.email}"> <br> <label
				for="password">パスワード</label> <input type="password" id="password"
				name="password" value="${user.password}"> <br> <label
				for="birthday">誕生日</label> <input type="date" id="birthday"
				name="birthday" value="${user.birthday}"> <br>
			<button type="submit">登録</button>
			<br> <a href="login.jsp">TOPへ</a>
		</form>
	</div>
</body>
</html>