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
/**
 * @작성자 : 손해원 
 * @작성일 : 2021. 2. 16.
 * @package  : controller
 * @filename : PhoneBookDeleteServlet.java
 * @description : 연락처에 등록된 정보를 삭제하는 기능의 서블릿 
 */
@WebServlet("/PhoneBookDeleteServlet")
public class PhoneBookDeleteServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    public PhoneBookDeleteServlet() {
    }

	protected void doGet(HttpServletRequest request
			, HttpServletResponse response) throws ServletException, IOException {
		
//		member 번호로 사용자를 찾기 위해 변수 설정 
		int membernum = Integer.parseInt(request.getParameter("membernum"));
		
//		사용자가 입력한 정보 추출
		request.setCharacterEncoding("utf-8");
		HttpSession session = request.getSession();
		String id = (String)session.getAttribute("id");
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
