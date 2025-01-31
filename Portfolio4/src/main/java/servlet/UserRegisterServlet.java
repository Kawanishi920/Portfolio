package servlet;

import java.io.IOException;
import java.sql.Date;
import java.sql.SQLException;

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
 * Servlet implementation class UserCreate
 */
@WebServlet("/UserRegister")
public class UserRegisterServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public UserRegisterServlet() {
		super();
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// フォワードする。
		RequestDispatcher dispatcher = request.getRequestDispatcher("/WEB-INF/jsp/userRegister.jsp");
		dispatcher.forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// リクエストパラメータ
		request.setCharacterEncoding("UTF-8");
		String name = request.getParameter("user_name"); // "user_name"に修正
		String email = request.getParameter("email");
		String password = request.getParameter("password");
		String birthday = request.getParameter("birthday");

		// 入力チェック
		if (name == null || name.isEmpty() ||
				email == null || email.isEmpty() ||
				password == null || password.isEmpty() ||
				birthday == null || birthday.isEmpty()) {
			// 入力エラーの場合、エラーメッセージを設定して登録画面に戻す
			request.setAttribute("error", "すべての項目を入力してください");
			RequestDispatcher dispatcher = request.getRequestDispatcher("userRegister.jsp");
			dispatcher.forward(request, response);
			return;
		}

		// リクエストパラメータをユーザーモデルに設定する。
		UserModel user = new UserModel();
		user.setUserName(name);
		user.setEmail(email);
		user.setPassword(password);
		// DBで日付を扱う場合はDate型に変換する
		user.setBirthday(Date.valueOf(birthday));

		// ユーザーを登録する。
		UserLogic logic;
		logic = new UserLogic();
		try {
			int ret = logic.create(user);

			//登録成功時の処理を追加
			if (ret > 0) {
				// ユーザーIDを取得
				int userId = logic.getUserId(user);
				//ユーザーIDをUserModelにセットする
				user.setId(userId);
				//セッションに保存されているUserModelオブジェクトを更新
				HttpSession session = request.getSession();
				session.setAttribute("user", user);//セッションに登録したユーザー情報を保存
			} else {
				//登録失敗時の処理
				request.setAttribute("error", "ユーザー登録に失敗しました。");
			}
		} catch (ClassNotFoundException | SQLException e) {
			e.printStackTrace();
		}

		// リクエストスコープにユーザーモデルを保存する。
		request.setAttribute("user", user);

		// ユーザー登録結果ページへフォワードする。
		RequestDispatcher dispatcher = request.getRequestDispatcher("/WEB-INF/jsp/userRegisterResult.jsp");
		dispatcher.forward(request, response);

		return;
	}

}
