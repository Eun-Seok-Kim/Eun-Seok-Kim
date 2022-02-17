package vo;

public class PersonVo {

	String name;
	int age;
	String tel;
	
	
	public PersonVo() {
		// TODO Auto-generated constructor stub
		System.out.println("111");
	}
	
	
	public PersonVo(String name, int age, String tel) {
		super();
		this.name = name;
		this.age = age;
		this.tel = tel;
		System.out.println("222");
	}

	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
		System.out.println("setname");
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
