package vo;

/*
 1.삼위일체 : DB컬럼명==VO속성명==form파라메터명
 2.getter / setter 생성
 
 
*/


public class PersonVo {
	
	String	name;
	int		age;
	String	tel;
	
	public PersonVo() {
		// TODO Auto-generated constructor stub
	}
	
	public PersonVo(String name, int age, String tel) {
		super();
		this.name = name;
		this.age = age;
		this.tel = tel;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public int getAge() {
		return age;
	}
	public void setAge(int age) {
		this.age = age;
	}
	public String getTel() {
		return tel;
	}
	public void setTel(String tel) {
		this.tel = tel;
	}
	
	
	
	
}
