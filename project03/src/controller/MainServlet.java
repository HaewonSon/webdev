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
import vo.*;

/**
 * Servlet implementation class MainServlet
 */
/**
 * @작성자 : 손해원 
 * @작성일 : 2021. 2. 7.
 * @package  : controller
 * @filename : MainServlet.java
 * @description :
 */
@WebServlet("/MainServlet")
public class MainServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    public MainServlet() {
        super();
    }

	/**
	 * doGet
	 */
	protected void doGet(HttpServletRequest request
			, HttpServletResponse response) 
					throws ServletException, IOException {
//		1. 로그인 여부 확인 
//		1-1. Session 확인 -> 로그인 후에 이름, 아이디를 Session 저장
		
//		get방식에서 session 정보는 request 에 담겨 있다. 
		HttpSession session = request.getSession();
		
//		object 형태로 나오기때문에 형변환 필요 
		String name = (String)session.getAttribute("name");
		String id = (String)session.getAttribute("id");

		if(name == null || id ==null) { 
// 			로그인 하지 않은 사용자인 경우 
			response.sendRedirect("LoginServlet");  
			
		}else {
//			2. 정상 로그인 한 경우의 처리
			
//			2-1. main.jsp 에 전달할 데이터 생성 -> request에 담아야 한다. 
//			회원(PhonebookVO) 목록(ArrayList)
			MemberService mService = new MemberService();
			ArrayList<PhonebookVO> members = mService.selectAll(id);
//			System.out.println(members.get(1).getGroupnum());
//			request에 members를 담는다 
			request.setAttribute("members", members);
			
//			2-2. main.jsp 로 포워딩 진행 
//			dispatch : request 를 보내는 녀석 ! 
			RequestDispatcher disp = 
					request.getRequestDispatcher("main.jsp");
			disp.forward(request, response);
			}

		}// doget end 

	/**
	 * doPost
	 */
	protected void doPost(HttpServletRequest request
			, HttpServletResponse response) 
					throws ServletException, IOException {

	}

} // class end 









