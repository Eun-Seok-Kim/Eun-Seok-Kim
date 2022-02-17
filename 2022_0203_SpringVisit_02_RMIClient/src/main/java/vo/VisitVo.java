package vo;

import java.io.Serializable;

//직렬화(Serializable)를 해야
//RMI기술을 통해서 원격서버로 데이터 전달(역 직렬화: 재조립) 될 수 있다.
public class VisitVo implements Serializable{

	int		idx;
	String	name;
	String	content;
	String	pwd;
	String	ip;
	String	regdate;
	
	
	//기본생성자
	public VisitVo() {
		// TODO Auto-generated constructor stub
	}
	
	//등록용 오버로드	
	public VisitVo(String name, String content, String pwd, String ip) {
		super();
		this.name = name;
		this.content = content;
		this.pwd = pwd;
		this.ip = ip;
	}
	
	//수정용 오버로드
	public VisitVo(int idx, String name, String content, String pwd, String ip) {
		super();
		this.idx = idx;
		this.name = name;
		this.content = content;
		this.pwd = pwd;
		this.ip = ip;
	}
	
	public int getIdx() {
		return idx;
	}
	public void setIdx(int idx) {
		this.idx = idx;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getContent() {
		return content;
	}
	public void setContent(String content) {
		this.content = content;
	}
	public String getPwd() {
		return pwd;
	}
	public void setPwd(String pwd) {
		this.pwd = pwd;
	}
	public String getIp() {
		return ip;
	}
	public void setIp(String ip) {
		this.ip = ip;
	}
	public String getRegdate() {
		return regdate;
	}
	public void setRegdate(String regdate) {
		this.regdate = regdate;
	}
	
	
}
