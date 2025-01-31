package servlet;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

//ユーザーのログアウト処理を担当するサーブレット
@WebServlet("/Logout") //このサーブレットにアクセスするためのURLパターンを指定します。
public class LogoutServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 * サーブレットのコンストラクタです。
	 */
	public LogoutServlet() {
		super();
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 * GETリクエストを処理するメソッドです。ログアウト処理を実行します。
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		// セッションを破棄してログアウトする。
		HttpSession session = request.getSession(false);// セッションが存在しない場合は null を返す

		if (session != null) {
			session.invalidate();//セッションを無効化
		}

		// 成功メッセージをリクエストスコープに保存する。
		request.setAttribute("success", "ログアウトしました");//ログアウト成功のメッセージを設定

		//ログインページへリダイレクトする
		response.sendRedirect(request.getContextPath() + "/logout.jsp");//ログインページへリダイレクト
	}
}