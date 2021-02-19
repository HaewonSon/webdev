package service;

import java.util.ArrayList;
import dao.PhonebookDAO;
import vo.PhonebookVO;
import vo.LoginVO;

/**
 * @작성자 : 손해원 
 * @작성일 : 2021. 2. 7.
 * @package  : service
 * @filename : MemberService.java
 * @description : DAO 와 Controller의 사이에서 메소드를 전달하는 service
 */
public class MemberService {
	
//	모든 회원 검색
	public ArrayList<PhonebookVO> selectAll(String id){
		
		ArrayList<PhonebookVO> members = new PhonebookDAO().selectAll(id);
		int groupnum = 0;
		for(PhonebookVO member : members) {
			groupnum=member.getGroupnum();
			if(groupnum==1) {
				member.setGroupname("가족");
			}else if(groupnum==2) {
				member.setGroupname("친구");
			}else if(groupnum==3) {
				member.setGroupname("기타");
			}
		}
		return members;
	} // selectAll end 

//	로그인 처리 
	public LoginVO selectByIdPw (String id, String password) {
		LoginVO member = new PhonebookDAO().selectByIdPw(id, password);
		return member; //  로그인 화면에서 출력 
	}

//	연락처 - 회원 추가 
	public void insertMember(PhonebookVO phonebook) {
		PhonebookDAO pDao = new PhonebookDAO();
		pDao.insertMember(phonebook);
	}

//	Id로 검색 
	public PhonebookVO selectById(String id) {
		PhonebookVO phonebook = null;
		PhonebookDAO pDao = new PhonebookDAO();
		phonebook = pDao.selectById(id);
		return phonebook;
	}

//	연락처 - 회원 수정 
	public void updateMember(PhonebookVO phone) {
		PhonebookDAO pDao = new PhonebookDAO();
		pDao.updateMember(phone);
	}

//	회원가입 
	public void joinInsert(LoginVO login) {
		PhonebookDAO pDao = new PhonebookDAO();
		pDao.joinInsert(login);
		
	}
//	회원번호로 연락처 검색하기 
	public PhonebookVO selectByMembernum(int membernum) {
		
		PhonebookVO pb = new PhonebookVO();
		PhonebookDAO pDao = new PhonebookDAO();
		pb = pDao.selectByMembernum(membernum);
		
		return pb;
	}

//	연락처 삭제 
	public void deletePhonebook(int membernum) {
		
		PhonebookDAO pDao = new PhonebookDAO();
		pDao.deletePhonebook(membernum);
		
	}

//	회원가입한 회원의 정보 수정 
	public void updateLogin(LoginVO login) {	
		PhonebookDAO pDao = new PhonebookDAO();
		pDao.updateLogin(login);
		
		
	}

//	회원가입한 회원 정보만 보여주는 메소드 
	public int selectMembernum(String id) {
		PhonebookDAO pDao = new PhonebookDAO();
		int membernum = pDao.selectMembernum(id);
		return membernum;
	}

//	연락처 - 그룹으로 검색 
	public ArrayList<PhonebookVO> searchByCategory(String category, String search, String id) {
		PhonebookDAO pDao = new PhonebookDAO();
		ArrayList<PhonebookVO> members = pDao.searchByCategory(category, search, id);
		for(PhonebookVO member : members) {
			if(member.getGroupnum()==1) {
				member.setGroupname("가족");
			}else if(member.getGroupnum()==2){
				member.setGroupname("친구");
			}else {
				member.setGroupname("기타");
			}
		}
			return members;
	}

//	전화번호 중복체크 
	public boolean SearchPhoneNum(String phonenum,String id, int membernum) {
		PhonebookDAO pDao = new PhonebookDAO();
		boolean answer = pDao.SearchPhoneNum(phonenum,id, membernum);
		return answer;
	}

//	아이디 중복체크 
	public boolean SearchId(String id) {
		PhonebookDAO pDao = new PhonebookDAO();
		boolean answer = pDao.SearchId(id);
		return answer;
	}
}
