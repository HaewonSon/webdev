package controller;

import java.io.IOException;
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
 * @package  : controller
 * @filename : PhoneBookInsertServlet.java
 * @description : 연락처에 회원을 추가하는 기능의 서블릿 
 */
@WebServlet("/PhoneBookInsertServlet")
public class PhoneBookInsertServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    public PhoneBookInsertServlet() {
        super();
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		HttpSession session = request.getSession();
		String id = (String)session.getAttribute("id");
		if(id==null) {
			response.sendRedirect("MainServlet");
			
		}else {
			response.sendRedirect("insertForm.jsp");
		}
	}

	protected void doPost(HttpServletRequest request
			, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("utf-8");
//		회원가입 처리 : insert
		String name = request.getParameter("name");
		String phone1 = request.getParameter("phone1");
		String phone2 = request.getParameter("phone2");
		String phone3 = request.getParameter("phone3");
		String phonenum = phone1+phone2+phone3;
		String address = request.getParameter("address");
		int groupnum = Integer.parseInt(request.getParameter("groupnum"));
		
		HttpSession session = request.getSession();
		String id = (String)session.getAttribute("id");
		
		if(id == null) {
			response.sendRedirect("MainServlet");
		}else {
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

}
