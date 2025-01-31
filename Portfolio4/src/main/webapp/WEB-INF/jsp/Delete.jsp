<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>アカウント削除画面</title>
<link rel="stylesheet" href="css/style.css">
</head>
<body>
	<div class="form-container">

		<h1>アカウント削除</h1>
		<form action="./Delete" method="post"
			onsubmit="return confirmDelete()">
			<%-- フォームタグを修正 --%>
			<div class="form-group">
				<label for="username">ユーザー名</label> <input type="text" id="username"
					name="username" required placeholder="ユーザー名を入力">
			</div>
			<div class="form-group">
				<label for="email">メールアドレス</label> <input type="text" id="email"
					name="email" required placeholder="メールアドレスを入力">
			</div>
			<div class="form-group">
				<%-- 閉じタグを修正 --%>
				<label for="password">パスワード</label> <input type="password"
					id="password" name="password" required placeholder="パスワードを入力">
			</div>
			<button type="submit">削除</button>
			<br> <a href="./Main">Homeに戻る</a>

		</form>

	</div>

	<script>
		function confirmDelete() {
			if (confirm("本当にアカウントを削除しますか？")) {
				return true;
			} else {
				return false;
			}
		}
	</script>
	<%-- JavaScriptを追加 --%>
</body>
</html>