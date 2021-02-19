package controller;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import service.MemberService;
import vo.PhonebookVO;

/**
 * @작성자 : 손해원
 * @작성일 : 2021. 2. 16.
 * @package : controller
 * @filename : SearchServlet.java
 * @description : 연락처 검색 기능의 서블릿
 */
@WebServlet("/SearchServlet")
public class SearchServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	public SearchServlet() {
	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		
		request.setCharacterEncoding("utf-8");
		HttpSession session = request.getSession();
		String id = (String) session.getAttribute("id");
		
		if (id == null) {
			response.sendRedirect("MainServlet");
		} else {
			String category = request.getParameter("category");
			String search = request.getParameter("search");
			
			MemberService mService 			= new MemberService();
			ArrayList<PhonebookVO> members  = mService.searchByCategory(category, search, id);
			
			request.setAttribute("members", members);
			RequestDispatcher disp = request.getRequestDispatcher("main.jsp");
			disp.forward(request, response);

		}

	}
}