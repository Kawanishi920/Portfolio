<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>ログイン</title>
<link rel="stylesheet" href="css/style.css">
</head>
<body>
	<div id="login" class="form-container">
		<h1>ログイン</h1>
		<%-- エラーメッセージがあれば表示 --%>
		<c:if test="${not empty error}">
			<div style="color: red;">${error}</div>
		</c:if>
		<form action="Login" method="post">

			<label for="email">メールアドレス</label> <input type="email" id="email"
				name="email" value="${user.email}"> <br> <label
				for="password">パスワード</label> <input type="password" id="password"
				name="password" value="${user.password}"> <br>
			<button type="submit">ログイン</button>
		</form>
		<a href="userRegister.jsp">新規登録はこちら</a>

	</div>

</body>
</html>