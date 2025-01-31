<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ page import="model.TweetModel"%>
<%@ page import="java.util.List"%>
<%@ page import="model.UserModel"%>
<%
// リクエストスコープからツイートリストを取得する。
List<TweetModel> tweetList = (List<TweetModel>) request.getAttribute("tweetList");
%>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>メインページ</title>
<link rel="stylesheet" href="css/style.css">
</head>
<body>
	<div class="center">
		<h1>メインページ</h1>
		
		<p>
			ようこそ、
			<%
		// セッションからユーザー名を取得
		try {
			UserModel user = (UserModel) session.getAttribute("user");
			if (user != null) {
				String userName = user.getUserName();
				out.print(userName);
			} else {
				out.print("ゲスト"); // ユーザー情報がない場合は「ゲスト」と表示
			}
		} catch (Exception e) {
			// 例外処理
			out.print("エラーが発生しました: " + e.getMessage());
			// ログ出力
			System.err.println("セッションからユーザー名を取得中にエラーが発生しました: " + e.getMessage());
		}
		%>
			さん！ <a href="Logout">ログアウト</a> <a href="Option">オプション</a>
		</p>

		<h2>投稿</h2>
		<form action="Main" method="post">
			<textarea name="tweet" rows="5" cols="40"></textarea>
			<br> <input type="submit" value="投稿">

		</form>
		<h2>投稿一覧</h2>


		<form action="DeleteTweet" method="post">
			<%
			if (tweetList != null && !tweetList.isEmpty()) {
			%>
			<ul>

				<%
				for (TweetModel tweet : tweetList) {
				%>
				<li><%=tweet.getTweet()%> - <%=tweet.getcreateDateTime()%> <input
					type="checkbox" name="deleteTweets[]" value="<%=tweet.getId()%>">

				</li>
				<%
				}
				%>

			</ul>
			<input type="submit" value="削除">
			<%
			}
			%>
		</form>
	</div>
</body>
</html>