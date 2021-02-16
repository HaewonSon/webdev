package controller;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import service.MemberService;

import vo.LoginVO;
import vo.PhonebookVO;

/**
 * Servlet implementation class JoinServlet
 */
/**
 * @작성자 : 손해원 
 * @작성일 : 2021. 2. 11.
 * @package  : controller
 * @filename : JoinServlet.java
 * @description : 회원가입 처리 서블릿 
 */
@WebServlet("/JoinServlet")
public class JoinServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    public JoinServlet() {
        super();
    }

	protected void doGet(HttpServletRequest request
			, HttpServletResponse response) 
					throws ServletException, IOException {
//		회원가입 하도록 화면 이동 
		response.setCharacterEncoding("utf-8");
//		request.setAttribute("msg", "아이디, 비밀번호는 필수 입력 항목입니다.");
		RequestDispatcher disp = request.getRequestDispatcher("joinForm.jsp");
		disp.forward(request, response);
	}

	protected void doPost(HttpServletRequest request
			, HttpServletResponse response) 
					throws ServletException, IOException {
		request.setCharacterEncoding("utf-8");
//		회원가입 처리 : insert
		LoginVO login = new LoginVO();
		String name = request.getParameter("name");
		String id = request.getParameter("id");
		String password = request.getParameter("password");
		String phone1 = request.getParameter("phone1");
		String phone2 = request.getParameter("phone2");
		String phone3 = request.getParameter("phone3");
		String phonenum = phone1+phone2+phone3;
		String address = request.getParameter("address");
		
		request.setAttribute("login",login);
		
//			id나 pw가 입력되지 않은 경우 페이지로 돌아가게 한다. 
		if(id.equals("")&& password.equals("")) {
//			request.setAttribute("msg", "아이디, 비밀번호는 필수 입력 항목입니다.");
//			response.sendRedirect("joinForm.jsp?msg='아이디와 비밀번호는 필수 입력 항목입니다.");
			doGet(request, response); 
			//아이디, 비밀번호 없을 경우 다시 회원가입 폼으로 가게 한다 
		}else {
//			Insert 
			login.setName(name);
			login.setPassword(password);
			login.setId(id);
			
			MemberService mService = new MemberService();
			mService.joinInsert(login);
			
			PhonebookVO phonebook = new PhonebookVO();
			phonebook.setAddress(address);
			phonebook.setId(id);
			phonebook.setPhonenum(phonenum);
			phonebook.setName(name);
			phonebook.setGroupnum(4);
			mService.insertMember(phonebook);
			response.sendRedirect("MainServlet");
		}
		
	}

}
