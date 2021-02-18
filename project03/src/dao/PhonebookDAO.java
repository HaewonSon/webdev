package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;

import vo.LoginVO;
import vo.PhonebookVO;

/**
 * @작성자 : 손해원 
 * @작성일 : 2021. 2. 7.
 * @package  : dao
 * @filename : PhonebookDAO.java
 * @description :
 */
public class PhonebookDAO {
	
	DBConnection dbCon = DBConnection.getInstance();
	
//	회원가입 
	public void joinInsert(LoginVO login) {
		Connection con 			= dbCon.getConnection();
		PreparedStatement pstmt = null;
		
		String query = "insert into login_info values(?,?,?) ";
		
		DBConnection dbCon = DBConnection.getInstance();
		
		try {
			pstmt = con.prepareStatement(query);
			pstmt.setString(1, login.getId());
			pstmt.setString(2, login.getPassword());
			pstmt.setString(3, login.getName());
			pstmt.executeUpdate();
			
		}catch(Exception e) {
			e.printStackTrace();
		}finally {
			dbCon.close(con, pstmt);
		}
		
	}// joinInsert end
	
	
//	로그인 처리 
	public LoginVO selectByIdPw(String id, String password) {
		Connection con 			= dbCon.getConnection();
		PreparedStatement pstmt = null;
		ResultSet rs 			= null;
		LoginVO login = new LoginVO();
		StringBuilder query = new StringBuilder();
		query.append("select name, id 		");
		query.append("  from login_info		");
		query.append(" where id = ?			");
		query.append("   and password = ? 	");
		
		DBConnection dbCon = DBConnection.getInstance();
		
		try {
			pstmt = con.prepareStatement(query.toString());
			pstmt.setString(1, id);
			pstmt.setString(2, password);
			
			rs = pstmt.executeQuery();
//			
			if(rs.next()) {
				login.setName(rs.getString("name"));
				login.setId(rs.getString("id"));
			}
		}catch(Exception e) {
			e.printStackTrace();
		}finally {
			try {
				if(rs != null) {
					rs.close();
				}
				dbCon.close(con, pstmt);
				
			}catch(Exception e) {
				e.printStackTrace();
			}
		}
		return login;
	} //selectByIdPw end 
		
	
//	연락처 - 모든 회원 
	public ArrayList<PhonebookVO> selectAll(String id){
		
		Connection con 			= dbCon.getConnection();
		PreparedStatement pstmt = null;
		ResultSet rs			= null;
		
		ArrayList<PhonebookVO> members = new ArrayList<PhonebookVO>();
		
		String query = "select * from phonebook where id = ? and groupnum < 4 ";
		
		try {
			
			pstmt 	= con.prepareStatement(query); 
//			접속한 아이디의 연락처만 보여준다. 
			pstmt.setString(1, id);
			rs 	= pstmt.executeQuery();
			
//			ArrayList에 담는다. 
			while(rs.next()) {
				PhonebookVO phonebook = new PhonebookVO();
				phonebook.setName(rs.getString("name"));
				phonebook.setPhonenum(rs.getString("phonenum"));
				phonebook.setAddress(rs.getString("address"));
				phonebook.setGroupnum(rs.getInt("groupnum"));
				phonebook.setId(rs.getString("id"));
				phonebook.setMembernum(rs.getInt("membernum"));
				
//				ArrayList 에 값 추가 
				members.add(phonebook);
			}
		}catch(Exception e) {
			e.printStackTrace();
		}finally {
			dbCon.close(con, pstmt, rs);
		}
		return members;
	} // selectAll end 
	

	

//	ID로 검색 
	public PhonebookVO selectById(String id) {
		Connection con 			= null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		PhonebookVO phonebook   = null;
		DBConnection dbCon = DBConnection.getInstance();
		String query = "select * from phonebook where id = ?";
		
		try {
			con = dbCon.getConnection();
			pstmt = con.prepareStatement(query);
			pstmt.setString(1, id);
			rs = pstmt.executeQuery();
			if(rs.next()) {
				phonebook = new PhonebookVO();
				phonebook.setName(rs.getString("name"));
				phonebook.setPhonenum(rs.getString("phonenum"));
				phonebook.setAddress(rs.getString("address"));
				phonebook.setGroupnum(rs.getInt("groupnum"));
				phonebook.setMembernum(rs.getInt("membernum"));
				phonebook.setId(rs.getString("id"));
			}
		}catch(Exception e) {
			e.printStackTrace();
		}finally {
			try {
				if (rs!=null) {
					rs.close();
				}
				dbCon.close(con, pstmt);
			}catch(Exception e) {
				e.printStackTrace();
			}
		}
		return phonebook;
	}//selectById end 
	
	
//	연락처 - 회원 추가 
	public void insertMember(PhonebookVO phonebook) {
		Connection con 			= null;
		PreparedStatement pstmt = null;
		String query = "insert into phonebook values(seq.nextval,?,?,?,?,?) ";
		
		DBConnection dbCon = DBConnection.getInstance();
		
		try {
			con = dbCon.getConnection();
			pstmt = con.prepareStatement(query);
			pstmt.setString(1, phonebook.getName());
			pstmt.setString(2, phonebook.getPhonenum());
			pstmt.setString(3, phonebook.getAddress());
			pstmt.setInt(4, phonebook.getGroupnum());
			pstmt.setString(5, phonebook.getId());
			
			pstmt.executeUpdate();
			//결과는 받지 않는다  
			
		}catch(Exception e) {
			e.printStackTrace();
		}finally {
			dbCon.close(con, pstmt);
		}
	}


//	연락처 - 회원 수정 
	public void updateMember(PhonebookVO phonebook) {
		Connection con 			= null;
		PreparedStatement pstmt = null;
		DBConnection dbCon = DBConnection.getInstance();
		
		StringBuilder query = new StringBuilder();
		query.append("update phonebook		");
		query.append("   set name = ? 		");
		query.append("	   , phonenum = ? 	");
		query.append("	   , address = ?	");
		query.append("	   , groupnum = ?	");
		query.append(" where membernum = ? 	");

		
		try {
			con = dbCon.getConnection();
			pstmt = con.prepareStatement(query.toString());
			pstmt.setString(1, phonebook.getName());
			pstmt.setString(2, phonebook.getPhonenum());
			pstmt.setString(3, phonebook.getAddress());
			pstmt.setInt(4, phonebook.getGroupnum());
			pstmt.setInt(5, phonebook.getMembernum());
			System.out.println(phonebook.getMembernum());
			
			pstmt.executeUpdate();
			
		}catch(Exception e) {
			e.printStackTrace();
		}finally {
			dbCon.close(con, pstmt);
		}
		
	}// updateMember end

//	회원 번호로 연락처 찾기 
	public PhonebookVO selectByMembernum(int membernum) {
		
		Connection con 			= dbCon.getConnection();
		PreparedStatement pstmt = null;
		ResultSet rs			= null;
		
		PhonebookVO phonebook = new PhonebookVO();
		String query = "select * from phonebook where membernum = ? ";
		
		try {
			
			pstmt 	= con.prepareStatement(query); 
//			접속한 아이디의 연락처만 보여준다. 
			pstmt.setInt(1, membernum);
			rs 	= pstmt.executeQuery();

			if(rs.next()) {
				
				phonebook.setName(rs.getString("name"));
				phonebook.setPhonenum(rs.getString("phonenum"));
				phonebook.setAddress(rs.getString("address"));
				phonebook.setGroupnum(rs.getInt("groupnum"));
				phonebook.setId(rs.getString("id"));
				phonebook.setMembernum(rs.getInt("membernum"));
			}
		}catch(Exception e) {
			e.printStackTrace();
		}finally {
			dbCon.close(con, pstmt, rs);
		}
		return phonebook;
	}


//	회원 삭제 
	public void deletePhonebook(int membernum) {
		
		Connection con 			= null;
		PreparedStatement pstmt = null;
		String query = "	delete from phonebook where membernum = ?	";
		
		DBConnection dbCon = DBConnection.getInstance();
		
		try {
			con = dbCon.getConnection();
			pstmt = con.prepareStatement(query);
			pstmt.setInt(1, membernum);
			
			pstmt.executeUpdate();
			
		}catch(Exception e) {
			e.printStackTrace();
		}finally {
			dbCon.close(con, pstmt);
		}
		
	}


	public void updateLogin(LoginVO login) {
		
		Connection con 			= null;
		PreparedStatement pstmt = null;
		DBConnection dbCon = DBConnection.getInstance();
		
		StringBuilder query = new StringBuilder();
		query.append("update login_info		");
		query.append("   set name = ? 		");
		query.append(" where id = ?		 	");

		
		try {
			con = dbCon.getConnection();
			pstmt = con.prepareStatement(query.toString());
			pstmt.setString(1, login.getName());
			pstmt.setString(2, login.getId());
			
			pstmt.executeUpdate();
			
		}catch(Exception e) {
			e.printStackTrace();
		}finally {
			dbCon.close(con, pstmt);
		}
		
	}

//	회원가입한 회원 정보만 보여주는 메소드 
	public int selectMembernum(String id) {
		int membernum = 0;
		
		Connection con 			= dbCon.getConnection();
		PreparedStatement pstmt = null;
		ResultSet rs			= null;
		
		System.out.println(id);
		String query = "select membernum from phonebook where id = ? and groupnum = 4 ";
		
		try {
			
			pstmt 	= con.prepareStatement(query); 
			pstmt.setString(1, id);
			rs 	= pstmt.executeQuery();

			if(rs.next()) {
				membernum = rs.getInt("membernum");
			}
		}catch(Exception e) {
			e.printStackTrace();
		}finally {
			dbCon.close(con, pstmt, rs);
		}
		
		return membernum;
	}

//	카테고리별 검색 
	public ArrayList<PhonebookVO> searchByCategory(String category, String search, String id) {
		Connection con 			= dbCon.getConnection();
		PreparedStatement pstmt = null;
		ResultSet rs			= null;
		StringBuilder query = new StringBuilder();
		
		ArrayList<PhonebookVO> members = new ArrayList<PhonebookVO>();
		
		query.append("	select * 	");
		query.append("	from phonebook	");
		query.append("	where	");
		query.append(category);
		query.append("	like ? 	");
		query.append("	and id = ? and groupnum < 4	 ");
		
		try {
			pstmt 	= con.prepareStatement(query.toString()); 
			pstmt.setString(1, "%"+search+"%");
			pstmt.setString(2, id);
			
			rs 	= pstmt.executeQuery();
			
			while(rs.next()) {
				PhonebookVO phonebook = new PhonebookVO();
				phonebook.setName(rs.getString("name"));
				phonebook.setPhonenum(rs.getString("phonenum"));
				phonebook.setAddress(rs.getString("address"));
				phonebook.setGroupnum(rs.getInt("groupnum"));
				phonebook.setId(rs.getString("id"));
				phonebook.setMembernum(rs.getInt("membernum"));
				
				members.add(phonebook);
			}
		}catch(Exception e) {
			e.printStackTrace();
		}finally {
			dbCon.close(con, pstmt, rs);
		}
		return members;
	}

//	전화번호 중복 체크 
	public boolean SearchPhoneNum(String phonenum, int membernum) {
		Connection con 			= dbCon.getConnection();
		PreparedStatement pstmt = null;
		ResultSet rs			= null;
		StringBuilder query = new StringBuilder();
		boolean answer = false;
		
		query.append("	select * from phonebook where phonenum=?	");
		
		try {
			pstmt 	= con.prepareStatement(query.toString()); 
			pstmt.setString(1, phonenum);
			
			rs 	= pstmt.executeQuery();
			
			while(rs.next()) {
				int oldmembernum = Integer.parseInt(rs.getString("membernum"));
				if(oldmembernum==membernum) {
					answer=false;
				}else {
				answer=true;
				}
			}
		}catch(Exception e) {
			e.printStackTrace();
		}finally {
			dbCon.close(con, pstmt, rs);
		}
		return answer;
	}


//	아이디 중복 체크 
	public boolean SearchId(String id) {
		Connection con 			= dbCon.getConnection();
		PreparedStatement pstmt = null;
		ResultSet rs			= null;
		StringBuilder query = new StringBuilder();
		boolean answer = false;
		
		query.append("	select * from login_info where id=?	");
		
		try {
			pstmt 	= con.prepareStatement(query.toString()); 
			pstmt.setString(1, id);
			
			rs 	= pstmt.executeQuery();
			
			while(rs.next()) {
				answer=true;
			}
		}catch(Exception e) {
			e.printStackTrace();
		}finally {
			dbCon.close(con, pstmt, rs);
		}
		return answer;
	}
	
} // PhonebookDAO class end 
