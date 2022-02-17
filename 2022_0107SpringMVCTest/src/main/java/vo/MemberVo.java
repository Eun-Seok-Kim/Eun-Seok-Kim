package vo;

public class MemberVo {

	String name;
	String id;
	String pwd;
	String email;
	String tel;
	String addr;
	
	public MemberVo() {
		// TODO Auto-generated constructor stub
	}
	
	
	
	
	public MemberVo(String name, String id, String pwd, String email, String tel, String addr) {
		super();
		this.name = name;
		this.id = id;
		this.pwd = pwd;
		this.email = email;
		this.tel = tel;
		this.addr = addr;
	}




	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getPwd() {
		return pwd;
	}
	public void setPwd(String pwd) {
		this.pwd = pwd;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getTel() {
		return tel;
	}
	public void setTel(String tel) {
		this.tel = tel;
	}
	public String getAddr() {
		return addr;
	}
	public void setAddr(String addr) {
		this.addr = addr;
	}
	
	
	
	
}
