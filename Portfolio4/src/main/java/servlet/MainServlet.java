package servlet;

import java.io.IOException;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import Logic.TweetLogic;
import model.TweetModel;
import model.UserModel;

/**

 * Servlet implementation class MainServlet

 */

@WebServlet("/Main")

public class MainServlet extends HttpServlet {

	private static final long serialVersionUID = 1L;

	/**
	
	* @see HttpServlet#HttpServlet()
	
	*/

	public MainServlet() {

		super();

	}

	/**
	
	* @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	
	*/

	protected void doGet(HttpServletRequest request, HttpServletResponse response)

			throws ServletException, IOException {
		//userModelのインスタンスを生成
		//UserModel user = new UserModel();
		//セッションからユーザーモデルを取得
		HttpSession session = request.getSession();
		UserModel user = (UserModel) session.getAttribute("user");

		// リクエストパラメーター TweetLogic

		TweetLogic tweetLogic = new TweetLogic(); // TweetLogicのインスタンスを生成

		List<TweetModel> tweetlist = tweetLogic.getTweet(user); // getTweet()メソッドを呼び出す

		request.setAttribute("tweetList", tweetlist);

		// main.jspへフォワード

		RequestDispatcher dispatcher = request.getRequestDispatcher("/WEB-INF/jsp/main.jsp");

		dispatcher.forward(request, response);

	}

	/**
	
	* @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	
	*/

	protected void doPost(HttpServletRequest request, HttpServletResponse response)

			throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");
		//セッションからユーザーを取得
		HttpSession session = request.getSession();
		UserModel user = (UserModel) session.getAttribute("user");

		// リクエストパラメータの取得

		// requestオブジェクトから、"tweet"という名前のパラメータを取得し、String型の変数tweetに格納します。

		// このパラメータは、クライアントから送信されたツイート内容を保持しています。

		String tweet = request.getParameter("tweet");

		// 取得したパラメータをレコードに保存

		// ツイートデータを格納するためのTweetModelオブジェクトを生成します。

		TweetModel model = new TweetModel();

		// TweetModelオブジェクトのsettweetメソッドを使用して、取得したツイート内容をモデルに設定します。

		model.setTweet(tweet);

		// ツイートデータをデータベースに保存するためのTweetLogicオブジェクトを生成します。

		TweetLogic logic = new TweetLogic();

		// TweetLogicオブジェクトのcreateメソッドを呼び出し、モデルに設定されたツイートデータをデータベースに保存します。

		logic.create(model, user);

		// メイン画面にリダイレクトする

		response.sendRedirect(request.getContextPath() + "/Main");

	}

}