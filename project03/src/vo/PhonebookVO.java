package vo;

public class PhonebookVO {
	private int membernum;
	private String name;
	private String phonenum;
	private String address;
	private int groupnum;
	private String id;
	private String groupname;
	
	public PhonebookVO() {
		
	}
	
	public PhonebookVO(String name, String phonenum, String id, String address) {
		this.name=name;
		this.phonenum=phonenum;
		this.id=id;
		this.address=address;
	}
	
	public String getGroupname() {
		return groupname;
	}
	public void setGroupname(String groupname) {
		this.groupname = groupname;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getPhonenum() {
		return phonenum;
	}
	public int getMembernum() {
		return membernum;
	}
	public void setMembernum(int membernum) {
		this.membernum = membernum;
	}
	public void setPhonenum(String phonenum) {
		this.phonenum = phonenum;
	}
	public String getAddress() {
		return address;
	}
	public void setAddress(String address) {
		this.address = address;
	}
	public int getGroupnum() {
		return groupnum;
	}
	public void setGroupnum(int groupnum) {
		this.groupnum = groupnum;
	}
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	
	
	

}
