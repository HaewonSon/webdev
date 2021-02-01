package com.kbigdata.edu;

import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.Statement;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 * Servlet implementation class ModifyOk
 */
@WebServlet("/ModifyOk")
public class ModifyOk extends HttpServlet {
	private static final long serialVersionUID = 1L;
    
	private Connection connection;
	private Statement stmt;
	
	private String name, id, pw, phone1, phone2, phone3, gender;
	
	HttpSession httpSession;
    /**
     * @see HttpServlet#HttpServlet()
     */
    public ModifyOk() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		actionDo(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		actionDo(request, response);
	}
	
	private void actionDo(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		request.setCharacterEncoding("utf-8");
		httpSession = request.getSession();
		
		name = request.getParameter("name");
		id = request.getParameter("id");
		pw = request.getParameter("pw");
		phone1 = request.getParameter("phone1");
		phone2 = request.getParameter("phone2");
		phone3 = request.getParameter("phone3");
		gender = request.getParameter("gender");
		
		
		if(pwConfirm()){  // 메소드가 true이면 update 처리 
			System.out.println("OK");
			
			String query = "update member set name ='" + name
					+ "', phone1= '" + phone1 
					+ "', phone2 = '" + phone2 
					+ "', phone3 = '" + phone3 
					+ "', gender = '" + gender 
					+ "'" + "where id = '" + id 
					+ "'" + "and pw = '" + pw + "'";
			System.out.println(query);
			try {
				Class.forName("oracle.jdbc.driver.OracleDriver");
				connection = DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:xe" , "ora_user" , "hong");
				stmt = connection.createStatement();
				int i = stmt.executeUpdate(query);
				if(i ==1 ){ // 업데이트 성공 
					System.out.println("update success");
					httpSession.setAttribute("name", name);
					response.sendRedirect("modifyResult.jsp");
				} else { // 수정시 입력한 비밀번호가 잘못된 경우 
					System.out.println("update fail");
					response.sendRedirect("modify.jsp"); // 비밀번호 입력form 
				}
			} catch(Exception e) {
				e.printStackTrace();
			} finally {
				try {
					if(stmt != null) stmt.close();
					if(connection != null) connection.close();
				} catch (Exception e) {}
			}
			
		} else {
			System.out.println("NG");
		}
		
	}
	
//	회원정보 수정시 비밀번호를 한번 더 물어봄. 
//	로그인 처리할 때 세션에 저장되었던 pw와 입력받는 pw를 비교한다. 
	private boolean pwConfirm() {
		boolean rs = false;
		
		String sessionPw =  (String)httpSession.getAttribute("pw");
		
		if(sessionPw.equals(pw)) {
			rs = true;
		} else {
			rs = false;
		}
		
		return rs;
	}

}
