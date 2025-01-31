
package servlet;

import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import model.UserModel;

//このサーブレットは、ユーザーの削除処理を担当します。
@WebServlet("/Delete")
public class UserDeleteServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	// データベース接続情報
	private static final String JDBC_DRIVER = "com.mysql.cj.jdbc.Driver";
	private static final String DB_URL = "jdbc:mysql://localhost/tweets?connectionCollation=utf8mb4_general_ci";
	private static final String DB_USER = "root";//データベースのユーザー名
	private static final String DB_PASS = ""; //データベースのパスワード

	/*
	 * GETリクエストを処理するメソッドです。ユーザー削除処理を実行します。
	 */

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		RequestDispatcher dispatcher = request.getRequestDispatcher("/WEB-INF/jsp/Delete.jsp");
		dispatcher.forward(request, response);

	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		// セッションからユーザーIDを取得
		HttpSession session = request.getSession();
		UserModel user = (UserModel) session.getAttribute("user");
		//Integer userId = user.getId(); // UserModelからuserIdを取得

		//コンテキストパスを取得
		String contextPath = ((HttpServletRequest) request).getContextPath();

		// ユーザーIDが取得できない場合はエラー
		if (user == null) {
			//response.sendRedirect(); // Deleteページへリダイレクト
			RequestDispatcher dispatcher = request.getRequestDispatcher("/WEB-INF/jsp/Delete.jsp");
			dispatcher.forward(request, response);
			return;

		}

		// データベースに接続
		Connection conn = null; //データベースへの接続を保持するオブジェクト
		PreparedStatement stmt = null;//SQL文を実行するためのオブジェクト
		try {
			Class.forName(JDBC_DRIVER);//JDBCドライバをロードします。
			conn = DriverManager.getConnection(DB_URL, DB_USER, DB_PASS);//データベースへ接続します

			// SQL文を準備
			String sql = "DELETE FROM users WHERE id = ?";//ユーザーを削除するためのSQL文
			stmt = conn.prepareStatement(sql);//SQL文をプリコンパイルします。
			stmt.setInt(1, user.getId());//SQL文のプレースホルダーにユーザーIDを設定します。

			// SQL文を実行
			int result = stmt.executeUpdate();

			// 削除に成功したらセッションを無効化してログインページへリダイレクト
			if (result == 1) {
				session.invalidate();
				response.sendRedirect("DeleteResult.jsp");
			} else {
				response.sendRedirect("Main"); // Deleteページへリダイレクト
			}

		} catch (ClassNotFoundException | SQLException e) {
			e.printStackTrace();//エラーのスタックトレースを出力します
			request.setAttribute("errorMessage", "アカウント削除に失敗しました。");
			RequestDispatcher dispatcher = request.getRequestDispatcher("/WEB-INF/jsp/Delete.jsp");
			dispatcher.forward(request, response);
			//response.sendRedirect("Delete.jsp"); // Deleteへリダイレクトします。
		} finally {
			// リソースを解放
			try {
				if (stmt != null)
					stmt.close();//PreparedStatmentを閉じます。
			} catch (SQLException se2) {
			}
			try {
				if (conn != null)
					conn.close();//データベース接続を閉じます。
			} catch (SQLException se) {
				se.printStackTrace();//エラーのスタックトレースを出力します。
			}
		}
	}
}