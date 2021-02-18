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
 * @description :
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

	
	public LoginVO selectByIdPw (String id, String password) {
		LoginVO member = new PhonebookDAO().selectByIdPw(id, password);
		return member; //  로그인 화면에서 출력 
	}

	
	public void insertMember(PhonebookVO phonebook) {
		PhonebookDAO pDao = new PhonebookDAO();
		pDao.insertMember(phonebook);
	}


	public PhonebookVO selectById(String id) {
		PhonebookVO phonebook = null;
		PhonebookDAO pDao = new PhonebookDAO();
		phonebook = pDao.selectById(id);
		return phonebook;
	}


	public void updateMember(PhonebookVO phone) {
		PhonebookDAO pDao = new PhonebookDAO();
		pDao.updateMember(phone);
	}


	public void joinInsert(LoginVO login) {
		PhonebookDAO pDao = new PhonebookDAO();
		pDao.joinInsert(login);
		
	}

	public PhonebookVO selectByMembernum(int membernum) {
		
		PhonebookVO pb = new PhonebookVO();
		PhonebookDAO pDao = new PhonebookDAO();
		pb = pDao.selectByMembernum(membernum);
		
		return pb;
	}


	public void deletePhonebook(int membernum) {
		
		PhonebookDAO pDao = new PhonebookDAO();
		pDao.deletePhonebook(membernum);
		
	}


	public void updateLogin(LoginVO login) {	
		PhonebookDAO pDao = new PhonebookDAO();
		pDao.updateLogin(login);
		
		
	}


	public int selectMembernum(String id) {
		PhonebookDAO pDao = new PhonebookDAO();
		int membernum = pDao.selectMembernum(id);
		return membernum;
	}


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


	public boolean SearchPhoneNum(String phonenum,String id, int membernum) {
		PhonebookDAO pDao = new PhonebookDAO();
		boolean answer = pDao.SearchPhoneNum(phonenum,id, membernum);
		return answer;
	}


	public boolean SearchId(String id) {
		PhonebookDAO pDao = new PhonebookDAO();
		boolean answer = pDao.SearchId(id);
		return answer;
	}
}
