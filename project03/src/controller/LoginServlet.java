package controller;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import service.MemberService;
import vo.*;

/**
 * Servlet implementation class LoginServlet
 */
@WebServlet("/LoginServlet")
public class LoginServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	public LoginServlet() {
        super();
    }

	protected void doGet(HttpServletRequest request
			, HttpServletResponse response) 
					throws ServletException, IOException {
//		로그인화면으로 이동전환 
		doPost(request, response);
	}
	
	protected void doPost(HttpServletRequest request
			, HttpServletResponse response) 
					throws ServletException, IOException {
		
//		입력한 아이디와 비밀번호가 데이터베이스에 존재하는지 확인 
		String id = request.getParameter("id");
		String password = request.getParameter("password");
		
		MemberService mService = new MemberService();
		
		LoginVO login = mService.selectByIdPw(id,password);
		
		if(login.getId() != null) {
//			정상 로그인의 경우 
//			- Session에 이름, 아이디 추가 
			HttpSession session = request.getSession();
			session.setAttribute("name", login.getName());
			session.setAttribute("id", login.getId());
//			- MainServlet을 탄다  
			response.sendRedirect("MainServlet");
		}else {
//			비정상 로그인 
			response.sendRedirect("loginForm.jsp");
		}
		
		
	}//doPost end

} // servlet end 



















