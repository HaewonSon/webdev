package controller;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import service.MemberService;

/**
 * Servlet implementation class PhoneBookDeleteServlet
 */
@WebServlet("/PhoneBookDeleteServlet")
public class PhoneBookDeleteServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    public PhoneBookDeleteServlet() {
    }

	protected void doGet(HttpServletRequest request
			, HttpServletResponse response) throws ServletException, IOException {
		int membernum = Integer.parseInt(request.getParameter("membernum"));
		
//		request = > 사용자가 입력한 정보 추출
		request.setCharacterEncoding("utf-8");
		HttpSession session = request.getSession();
		String id = (String)session.getAttribute("id");
//		비밀번호 확인 
		if(id == null) {
			response.sendRedirect("MainServlet");
		}else {
			MemberService mService = new MemberService();
			mService.deletePhonebook(membernum);
			response.sendRedirect("MainServlet");
		}
		
	}

	protected void doPost(HttpServletRequest request
			, HttpServletResponse response) throws ServletException, IOException {
	}

}
