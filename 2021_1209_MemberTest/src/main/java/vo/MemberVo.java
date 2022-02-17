package vo;

public class MemberVo {

	int m_idx;
	String m_name;
	String m_id;
	String m_pwd;
	String m_zipcode;
	String m_addr;
	String m_ip;
	String m_regdate;
	String m_grade;
	
	//조회용 패스워드
	String m_pwd_hidden="";
	
	public String getM_pwd_hidden() {
		//len에 m_pwd길이 /2 값을 넣기
		int len = m_pwd.length()/2;
		StringBuffer sb = new StringBuffer();
		for(int i=0; i<len;i++) {
			if(i<len/2) {
			sb.append(m_pwd.charAt(i));
			}
			sb.append("*");
		}return sb.toString();
		/*
			// m_pwd길이만큼 for돌리기
		   for(int i=0; i<m_pwd.length();i++){
		    //반복횟수 < m_pwd/2값을 비교
			if(i<len)//만족하면
			//m_pwd_hidden에 hidden + m_pwd에 i번째만큼 substring하기			
		    m_pwd_hidden=m_pwd_hidden+m_pwd.substring(i,i+1);
			//아닐경우 *을 넣는다
		    else m_pwd_hidden=m_pwd_hidden+"*";
		   }
		   return m_pwd_hidden; //hidden값 리턴
		  
			풀이
			len = 2일경우 (pwd : 1234 )/2 값이니까
			for는 2회반복 0,1
			if의 증가되는 i=0 ,1
			m_pwd.substring(1,1+1) << 이 반복 횟수가 노출되는 패스워드
			m_pwd.substring(i,i+1)
			0 > m_pwd.substring(0,0+1) > '1'저장
			1 > m_pwd.substring(1,1+1) > '2'저장
			이후 else로 넘어가므로 m_pwd.substring은 진행 하지않음
			for + else만 돌아가고
			m_pwd_hidden에 들어간 값을 보호하기 위해 
			m_pwd_hidden+"*" 식으로 새로 넣기
			최종 return m_pwd_hidden으로 *처리된 pwd전달
			*/
	}
	
	public MemberVo() {
		// TODO Auto-generated constructor stub
	}
	//회원가입
	public MemberVo(String m_name, String m_id, String m_pwd, 
			String m_zipcode, String m_addr, String m_ip) {
		super();
		this.m_name = m_name;
		this.m_id = m_id;
		this.m_pwd = m_pwd;
		this.m_zipcode = m_zipcode;
		this.m_addr = m_addr;
		this.m_ip = m_ip;
		//this.m_grade = m_grade;
	}	
	//회원수정
	public MemberVo(int m_idx, String m_name, String m_id, String m_pwd,
				String m_zipcode, String m_addr, String m_ip) {
		super();
		this.m_idx = m_idx;
		this.m_name = m_name;
		this.m_id = m_id;
		this.m_pwd = m_pwd;
		this.m_zipcode = m_zipcode;
		this.m_addr = m_addr;
		this.m_ip = m_ip;
		//this.m_grade = m_grade;
	}
	
	
	
	


	public int getM_idx() {
		return m_idx;
	}

	public void setM_idx(int m_idx) {
		this.m_idx = m_idx;
	}
	public String getM_name() {
		return m_name;
	}
	public void setM_name(String m_name) {
		this.m_name = m_name;
	}
	public String getM_id() {
		return m_id;
	}
	public void setM_id(String m_id) {
		this.m_id = m_id;
	}
	public String getM_pwd() {
		return m_pwd;
	}
	public void setM_pwd(String m_pwd) {
		this.m_pwd = m_pwd;
	}
	public String getM_zipcode() {
		return m_zipcode;
	}
	public void setM_zipcode(String m_zipcode) {
		this.m_zipcode = m_zipcode;
	}
	public String getM_addr() {
		return m_addr;
	}
	public void setM_addr(String m_addr) {
		this.m_addr = m_addr;
	}
	public String getM_ip() {
		return m_ip;
	}
	public void setM_ip(String m_ip) {
		this.m_ip = m_ip;
	}
	public String getM_regdate() {
		return m_regdate;
	}
	public void setM_regdate(String m_regdate) {
		this.m_regdate = m_regdate;
	}
	public String getM_grade() {
		return m_grade;
	}
	public void setM_grade(String m_grade) {
		this.m_grade = m_grade;
	}
	
	
	
}
