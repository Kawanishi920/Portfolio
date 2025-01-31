package servlet;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/Option")
public class OptionServlet extends HttpServlet {

	public void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		// 遷移先のOption画面のJSPを指定
		String optionJsp = "/WEB-INF/jsp/Option.jsp";

		// RequestDispatcherを取得
		RequestDispatcher dispatcher = request.getRequestDispatcher(optionJsp);//option.jspを呼び出す

		// Option画面にフォワード
		dispatcher.forward(request, response);//画面遷移する
	}

}