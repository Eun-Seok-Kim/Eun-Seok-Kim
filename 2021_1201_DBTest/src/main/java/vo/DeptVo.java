package vo;

/*
 	1.DB Column명 == Vo Property명 == 입력폼의 Parameter명
  	2.getter , setter 반드시 만들것
 */


public class DeptVo {

	int 	deptno;
	String  dname;
	String 	loc;
	
	public int getDeptno() {
		return deptno;
	}
	public void setDeptno(int deptno) {
		this.deptno = deptno;
	}
	public String getDname() {
		return dname;
	}
	public void setDname(String dname) {
		this.dname = dname;
	}
	public String getLoc() {
		return loc;
	}
	public void setLoc(String loc) {
		this.loc = loc;
	}
	
	
}
