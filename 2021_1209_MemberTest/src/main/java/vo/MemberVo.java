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
	
	//��ȸ�� �н�����
	String m_pwd_hidden="";
	
	public String getM_pwd_hidden() {
		//len�� m_pwd���� /2 ���� �ֱ�
		int len = m_pwd.length()/2;
		StringBuffer sb = new StringBuffer();
		for(int i=0; i<len;i++) {
			if(i<len/2) {
			sb.append(m_pwd.charAt(i));
			}
			sb.append("*");
		}return sb.toString();
		/*
			// m_pwd���̸�ŭ for������
		   for(int i=0; i<m_pwd.length();i++){
		    //�ݺ�Ƚ�� < m_pwd/2���� ��
			if(i<len)//�����ϸ�
			//m_pwd_hidden�� hidden + m_pwd�� i��°��ŭ substring�ϱ�			
		    m_pwd_hidden=m_pwd_hidden+m_pwd.substring(i,i+1);
			//�ƴҰ�� *�� �ִ´�
		    else m_pwd_hidden=m_pwd_hidden+"*";
		   }
		   return m_pwd_hidden; //hidden�� ����
		  
			Ǯ��
			len = 2�ϰ�� (pwd : 1234 )/2 ���̴ϱ�
			for�� 2ȸ�ݺ� 0,1
			if�� �����Ǵ� i=0 ,1
			m_pwd.substring(1,1+1) << �� �ݺ� Ƚ���� ����Ǵ� �н�����
			m_pwd.substring(i,i+1)
			0 > m_pwd.substring(0,0+1) > '1'����
			1 > m_pwd.substring(1,1+1) > '2'����
			���� else�� �Ѿ�Ƿ� m_pwd.substring�� ���� ��������
			for + else�� ���ư���
			m_pwd_hidden�� �� ���� ��ȣ�ϱ� ���� 
			m_pwd_hidden+"*" ������ ���� �ֱ�
			���� return m_pwd_hidden���� *ó���� pwd����
			*/
	}
	
	public MemberVo() {
		// TODO Auto-generated constructor stub
	}
	//ȸ������
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
	//ȸ������
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
