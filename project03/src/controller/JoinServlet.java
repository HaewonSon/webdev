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
		
		
//		예외처리 
		
//		이름이 공백인 경우 
		if(name.equals("")) {
			PhonebookVO member = new PhonebookVO(name,phonenum,id,address);
			request.setAttribute("member", member);
			request.setAttribute("nameMsg", "이름이 없습니다.");
			RequestDispatcher disp = request.getRequestDispatcher("joinForm.jsp");
			disp.forward(request, response);
//		아이디가 공백인 경우 	
		}else if(id.equals("")) {
			PhonebookVO member = new PhonebookVO(name,phonenum,id,address);
			request.setAttribute("member", member);
			request.setAttribute("idMsg", "아이디가 없습니다.");
			RequestDispatcher disp = request.getRequestDispatcher("joinForm.jsp");
			disp.forward(request, response);
//		이미 존재하는 아이디인 경우 	
		}else if(SearchId(id)) {
			PhonebookVO member = new PhonebookVO(name,phonenum,id,address);
			request.setAttribute("member", member);
			request.setAttribute("idMsg", "이미 존재하는 아이디입니다.");
			RequestDispatcher disp = request.getRequestDispatcher("joinForm.jsp");
			disp.forward(request, response);
//		비밀번호가 공백인 경우 	
		}else if(password.equals("")) {
			PhonebookVO member = new PhonebookVO(name,phonenum,id,address);
			request.setAttribute("member", member);
			request.setAttribute("pwMsg", "패스워드를 입력해주세요");
			RequestDispatcher disp = request.getRequestDispatcher("joinForm.jsp");
			disp.forward(request, response);
//		이미 저장된 번호인 경우 	
		}else if(SearchPhoneNum(phonenum,-1)) {
			PhonebookVO member = new PhonebookVO(name,phonenum,id,address);
			request.setAttribute("member", member);
			request.setAttribute("phoneMsg", "이미 저장되어 있는 번호입니다.");
			RequestDispatcher disp = request.getRequestDispatcher("joinForm.jsp");
			disp.forward(request, response);
//		전화번호 양식이 올바르지 않은 경우	
		}else if(phoneNumChecker(phonenum)) {
			PhonebookVO member = new PhonebookVO(name,phonenum,id,address);
			request.setAttribute("member", member);
			request.setAttribute("phoneMsg", "올바른 번호가 아니옵니다.");
			RequestDispatcher disp = request.getRequestDispatcher("joinForm.jsp");
			disp.forward(request, response);
			
//		모두 정상적으로 입력 시 회원 등록 	
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
			response.sendRedirect("joinSuccessForm.jsp");
		}
		
	}
	
//	전화번호 양식 확인 메소드 
	private boolean phoneNumChecker(String phonenum) {
//		전화번호의 길이가 11자리가 아닌 경우 
		if(phonenum.length()!=11) {
			return true;
		}
//		문자를 입력한 경우 
		char[] numbers = phonenum.toCharArray();
		for(char num : numbers) {
			if(num>'9'||num<'0') {
				return true;
			}
		}
//		정상적으로 입력했다면 통과 
		return false;
	}
	
	private boolean SearchId(String id) {
		MemberService mService = new MemberService();
		boolean answer = mService.SearchId(id);
		return answer;
	}

	public boolean SearchPhoneNum(String phonenum, int membernum) {
		MemberService mService = new MemberService();
		boolean answer = mService.SearchPhoneNum(phonenum,membernum);
		return answer;
	}

}
