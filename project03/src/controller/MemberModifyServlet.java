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
import vo.LoginVO;
import vo.PhonebookVO;

/**
 * @작성자 : 손해
 * @작성일 : 2021. 2. 16.
 * @package  : controller
 * @filename : MemberModifyServlet.java
 * @description : 회원정보 수정 처리 서블릿 
 */
@WebServlet("/MemberModifyServlet")
public class MemberModifyServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	public MemberModifyServlet() {
	}

	protected void doGet(HttpServletRequest request
			, HttpServletResponse response) 
					throws ServletException, IOException {
//		session 에서 id 추출 
		HttpSession session = request.getSession();
		String id = (String)session.getAttribute("id");
		if (id == null) {
//			session에서 꺼낸 id가 공백일 때  
			response.sendRedirect("MainServlet");
//			 -> loginForm.jsp
		}else {
			MemberService mService = new MemberService();
//			System.out.println(id);
			int membernum = mService.selectMembernum(id);
//			System.out.println(membernum);
			session.setAttribute("membernum", membernum);
			PhonebookVO member = mService.selectByMembernum(membernum);  //session에서 추출한 아이디 
//			System.out.println(member.getPhonenum());
//			request 에 select한 결과를 담는다 
			request.setAttribute("id", id);
			request.setAttribute("member", member);
			request.setAttribute("phone2", member.getPhonenum().substring(3,7));
			request.setAttribute("phone3", member.getPhonenum().substring(7));
			
//			modifyForm.jsp 를 forward
			RequestDispatcher disp = request.getRequestDispatcher("memberModifyForm.jsp");
			disp.forward(request, response);
		}
		
	} // 

	
	protected void doPost(HttpServletRequest request
			, HttpServletResponse response) 
					throws ServletException, IOException {
//		사용자가 입력한 정보 추출
		request.setCharacterEncoding("utf-8");
		HttpSession session = request.getSession();
		String id = (String)session.getAttribute("id");
		if(id == null) {
			response.sendRedirect("MainServlet");
		}else {
			String password = request.getParameter("password");
//			비밀번호가 정상 입력된 경우  -> 정상적으로 수정처리 (update)
			PhonebookVO phonebook= new PhonebookVO();
			String name = request.getParameter("name");
			String phone1 = request.getParameter("phone1");
			String phone2 = request.getParameter("phone2");
			String phone3 = request.getParameter("phone3");
			String phonenum = phone1+phone2+phone3;
			String address = request.getParameter("address");
			int groupnum = 4;
			
			int membernum =(int)session.getAttribute("membernum");
			
			
//			예외처리 
			
//			이름이 공백인 경우 
			if(name.equals("")) {
				PhonebookVO member = new PhonebookVO(name,phonenum,id,address);
				request.setAttribute("member", member);
				request.setAttribute("nameMsg", "이름이 없습니다.");
				RequestDispatcher disp = request.getRequestDispatcher("memberModifyForm.jsp");
				disp.forward(request, response);
//			아이디가 공백인 경우 	
			}else if(password.equals("")) {
				PhonebookVO member = new PhonebookVO(name,phonenum,id,address);
				request.setAttribute("member", member);
				request.setAttribute("pwMsg", "패스워드를 입력해주세요");
				RequestDispatcher disp = request.getRequestDispatcher("memberModifyForm.jsp");
				disp.forward(request, response);
//			이미 저장된 번호인 경우 	
			}else if(SearchPhoneNum(phonenum,membernum)) {
				PhonebookVO member = new PhonebookVO(name,phonenum,id,address);
				request.setAttribute("member", member);
				request.setAttribute("phoneMsg", "이미 저장되어 있는 번호입니다.");
				RequestDispatcher disp = request.getRequestDispatcher("memberModifyForm.jsp");
				disp.forward(request, response);
//			전화번호 양식이 올바르지 않은 경우	
			}else if(phoneNumChecker(phonenum)) {
				PhonebookVO member = new PhonebookVO(name,phonenum,id,address);
				request.setAttribute("member", member);
				request.setAttribute("phoneMsg", "올바른 번호가 아니옵니다.");
				RequestDispatcher disp = request.getRequestDispatcher("memberModifyForm.jsp");
				disp.forward(request, response);
				
//			모두 정상적으로 입력 시 회원 등록 	
			}else {
			
			MemberService mService = new MemberService();
			LoginVO login = mService.selectByIdPw(id, password);
			
				
//				VO에 set 
				phonebook.setId(id);
				phonebook.setName(name);
				phonebook.setPhonenum(phonenum);
				phonebook.setAddress(address);
				phonebook.setGroupnum(groupnum);
				phonebook.setMembernum(membernum);
				
//				다시 입력한 id와 pw가 틀렸을 경우 
				if(login == null) {
					request.setAttribute("phone2", phone2);
					request.setAttribute("phone3", phone3);
					request.setAttribute("member", phonebook);
					RequestDispatcher disp = request.getRequestDispatcher("memberModifyForm.jsp");
					disp.forward(request, response);
				}else {
					login.setName(name);
					login.setId(id);
					mService.updateLogin(login);
					mService.updateMember(phonebook);
					session.invalidate();
					response.sendRedirect("modifySuccessForm.jsp");
				}
				}
			
		}
		
	}// dopost end
//	전화번호 양식 확인 메소드 
	private boolean phoneNumChecker(String phonenum) {
//		전화번호의 길이가 11자리가 아닌 경우 
		if(phonenum.length()!=11) {
			return true;
		}
//		문자를 입력한 경우 
		char[] numbers = phonenum.toCharArray();
		for(char num : numbers) {
//			아스키코드
			if(num>'9'||num<'0') {
				return true;
			}
		}
//		정상적으로 입력했다면 통과 
		return false;
	}
	

	public boolean SearchPhoneNum(String phonenum, int membernum) {
		MemberService mService = new MemberService();
		boolean answer = mService.SearchPhoneNum(phonenum,membernum);
		return answer;
	}


}//class end
