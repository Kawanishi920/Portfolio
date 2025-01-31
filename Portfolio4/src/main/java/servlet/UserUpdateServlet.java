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

@WebServlet("/Update")
public class UserUpdateServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public UserUpdateServlet() {
		super();
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// アカウント更新ページへフォワードする。
		RequestDispatcher dispatcher = request.getRequestDispatcher("/WEB-INF/jsp/userUpdate.jsp");
		dispatcher.forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		// リクエストパラメーター
		String username = request.getParameter("username");
		String email = request.getParameter("email");
		String password = request.getParameter("password");

		// ユーザーIDをセッションから取得する。
		HttpSession session = request.getSession();
		UserModel user = (UserModel) session.getAttribute("user");

		// セッションから取得したユーザー情報で更新処理を行う
		user.setUserName(username);//ユーザー名を設定
		user.setEmail(email);
		user.setPassword(password);

		// ユーザーを更新する
		UserLogic logic = new UserLogic();
		int result = logic.update(user);

		//更新結果に応じてフォワード先を変更
		if (result == 1) { //更新成功
			request.setAttribute("message", "更新が完了しました");
			request.setAttribute("user", user);
			RequestDispatcher dispatcher = request.getRequestDispatcher("WEB-INF/jsp/userUpdateResult.jsp");
			dispatcher.forward(request, response);
		} else {//更新失敗
			request.setAttribute("errorMessage", "更新中にエラーが発生しました");
			request.setAttribute("user", user);
			RequestDispatcher dispatcher = request.getRequestDispatcher("WEB-INF/jsp/Error.jsp");
			dispatcher.forward(request, response);
		}
	}
}