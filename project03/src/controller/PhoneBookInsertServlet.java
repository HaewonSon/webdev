package controller;

import java.io.IOException;

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
 * @filename : PhoneBookInsertServlet.java
 * @description : 연락처에 회원을 추가하는 기능의 서블릿
 */
@WebServlet("/PhoneBookInsertServlet")
public class PhoneBookInsertServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	public PhoneBookInsertServlet() {
		super();
	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		HttpSession session = request.getSession();
		String id = (String) session.getAttribute("id");
		if (id == null) {
			response.sendRedirect("MainServlet");

		} else {
			response.sendRedirect("insertForm.jsp");
		}
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		
		request.setCharacterEncoding("utf-8");
//		회원가입 처리 
		String name = request.getParameter("name");
		String phone1 = request.getParameter("phone1");
		String phone2 = request.getParameter("phone2");
		String phone3 = request.getParameter("phone3");
		String phonenum = phone1 + phone2 + phone3;
		String address = request.getParameter("address");
		int groupnum = Integer.parseInt(request.getParameter("groupnum"));

		HttpSession session = request.getSession();
		String id = (String) session.getAttribute("id");

//		예외처리 
		if (name.equals("")) {
			PhonebookVO member = new PhonebookVO();
			request.setAttribute("member", member);
			request.setAttribute("nameMsg", "이름을 입력해 주세요.");
			RequestDispatcher disp = request.getRequestDispatcher("insertForm.jsp");
			disp.forward(request, response);
			// 이미 저장된 번호인 경우
		} else if (SearchPhoneNum(phonenum, id, -1)) {
			PhonebookVO member = new PhonebookVO(name, phonenum, id, address);
			request.setAttribute("member", member);
			request.setAttribute("phoneMsg", "이미 저장되어 있는 번호입니다.");
			RequestDispatcher disp = request.getRequestDispatcher("insertForm.jsp");
			disp.forward(request, response);
			// 전화번호 양식이 올바르지 않은 경우
		} else if (phoneNumChecker(phonenum)) {
			PhonebookVO member = new PhonebookVO(name, phonenum, id, address);
			request.setAttribute("member", member);
			request.setAttribute("phoneMsg", "올바른 번호가 아니옵니다.");
			RequestDispatcher disp = request.getRequestDispatcher("insertForm.jsp");
			disp.forward(request, response);
//			정상 처리 시 
		} else {
			MemberService mService = new MemberService();
			PhonebookVO phonebook = new PhonebookVO();
			phonebook.setAddress(address);
			phonebook.setId(id);
			phonebook.setPhonenum(phonenum);
			phonebook.setName(name);
			phonebook.setGroupnum(groupnum);
			mService.insertMember(phonebook);
			response.sendRedirect("MainServlet");

		}
	}

//	전화번호 중복 체크 메소드 
	public boolean SearchPhoneNum(String phonenum, String id, int membernum) {
		MemberService mService = new MemberService();
		boolean answer = mService.SearchPhoneNum(phonenum, id, membernum);
		return answer;
	}

//		전화번호 양식 확인 메소드 
	private boolean phoneNumChecker(String phonenum) {
//			전화번호의 길이가 11자리가 아닌 경우 
		if (phonenum.length() != 11) {
			return true;
		}
//			문자를 입력한 경우 
		char[] numbers = phonenum.toCharArray();
		for (char num : numbers) {
//			아스키코드 비교 
			if (num > '9' || num < '0') {
				return true;
			}
		}
//			정상적으로 입력했다면 통과 
		return false;
	}

}
