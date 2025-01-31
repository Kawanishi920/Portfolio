package servlet;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import Logic.TweetLogic;

/**
 * Servlet implementation class DeleteTweetServlet
 */
@WebServlet("/DeleteTweet")
public class DeleteTweetServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public DeleteTweetServlet() {
		super();
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	
		
	
	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("utf-8");
		response.setContentType("text/html; charset=utf-8");
		
		String[] check = request.getParameterValues("deleteTweets[]");
		
		//チェックボックスにチェックがついたものを削除する
		if(check != null) {
			for(int j = check.length-1; j >=0; j--) {
				if(check[j] != null) {
					int id = Integer.parseInt(check[j]);
					TweetLogic tweetLogic = new TweetLogic();
					tweetLogic.delete(id);
				}
			}
		}
		
		
//		RequestDispatcher rd = request.getRequestDispatcher("./Main");
//		rd.forward(request, response);
		response.sendRedirect("./Main");
	}

	
		
	}


