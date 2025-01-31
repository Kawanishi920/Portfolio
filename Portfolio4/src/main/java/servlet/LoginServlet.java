package servlet;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import Logic.UserLogic;
import model.UserModel;

/**

 * Servlet implementation class LoginServlet

 * ユーザーログイン処理をするサーブレットです。

 */

@WebServlet("/Login")

public class LoginServlet extends HttpServlet {

	private static final long serialVersionUID = 1L;

	/**
	
	* @see HttpServlet#HttpServlet()
	
	* サーブレットのコンストラクタです。
	
	*/

	public LoginServlet() {

		super();

	}

	/**
	
	* @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	
	*      response)
	
	*      GETリクエストを処理するメソッドです。ログインページを表示します。
	
	*/

	protected void doGet(HttpServletRequest request, HttpServletResponse response)

			throws ServletException, IOException {

		// ログインページへフォワードする。

		RequestDispatcher dispatcher = request.getRequestDispatcher("login.jsp");

		dispatcher.forward(request, response);

	}

	/**
	
	* @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	
	*      response)
	
	*      POSTリクエストを処理するメソッドです。ログイン処理を実行します
	
	*/

	protected void doPost(HttpServletRequest request, HttpServletResponse response)

			throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");

		// フォームからのリクエストパラメータを取得する。
		String email = request.getParameter("email");

		String password = request.getParameter("password");

		// メールアドレスまたはパスワードが空の場合
		if (email == null || email.isEmpty() || password == null || password.isEmpty()) {
			request.setAttribute("error", "メールアドレスとパスワードを入力してください。");
			RequestDispatcher dispatcher = request.getRequestDispatcher("login.jsp");
			dispatcher.forward(request, response);
			return; // 処理を中断
		}

		try {

			//リクエストパラメータを元に、ユーザーを検索する

			UserLogic logic = new UserLogic();

			UserModel user = logic.find(email, password);

			if (user == null) {

				//ユーザーが見つからなかったときは、エラーメッセージをリクエストスコープに設定する。

				request.setAttribute("error", "メールアドレス、パスワードが一致しません。");

				//ログインに使用した情報を再表示するために、リクエストスコープに保存する。

				user = new UserModel();

				user.setEmail(request.getParameter("email"));

				user.setPassword(request.getParameter("password"));

				request.setAttribute("user", user);

				//ログインページへフォワードする。RequestDispatcherインスタンスのforward()メソッド

				RequestDispatcher dispatcher = request.getRequestDispatcher("login.jsp");//フォワード先

				dispatcher.forward(request, response);

				return;

			}

			// ユーザーが見つかったときは、ユーザーモデルをセッションに保存し、メインページへリダイレクトする。

			// ユーザーモデルがセッションに保存されていることでログイン状態を保持する。

			// セッションからユーザーモデルを削除することでログアウトとする。

			HttpSession session = request.getSession(); // セッションを取得
			session.setAttribute("user", user); // セッションにユーザー情報を保存

			// Mainへリダイレクトする。

			response.sendRedirect(request.getContextPath() + "/Main");

			return;

		} catch (Exception e) {
			//例外発生時の処理
			e.printStackTrace(); // ログ出力
			String errorMassage = "エラーが発生しました";
			request.setAttribute("error", "errerMessage");
			request.getRequestDispatcher("WEB-INF/jsp/error.jsp").forward(request, response);
			return;

		}

	}

}