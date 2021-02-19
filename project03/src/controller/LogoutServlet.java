package controller;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 * Servlet implementation class LogoutServlet
 */
/**
 * @작성자 : 손해원 
 * @작성일 : 2021. 2. 19.
 * @package  : controller
 * @filename : LogoutServlet.java
 * @description : 로그아웃 서블릿 
 */
@WebServlet("/LogoutServlet")
public class LogoutServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    public LogoutServlet() {
        super();
    }

	protected void doGet(HttpServletRequest request
			, HttpServletResponse response) 
					throws ServletException, IOException {
		HttpSession session = request.getSession();
		session.invalidate(); // 모든 정보를 지운다 
		response.sendRedirect("MainServlet");
	}

	protected void doPost(HttpServletRequest request
			, HttpServletResponse response) 
					throws ServletException, IOException {
	}

}
