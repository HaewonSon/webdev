package vo;

/**
 * @작성자 :	손해원 
 * @작성일 : 2021. 2. 19.
 * @package  : vo
 * @filename : LoginVO.java
 * @description : 한 명의 회원정보를 저장하는 VO
 */
public class LoginVO {
	private String id;
	private String password;
	private String name;
	
	public LoginVO() {
		
	}
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	
	

}
