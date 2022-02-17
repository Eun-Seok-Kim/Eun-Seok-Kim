package myutil;

import java.util.Calendar;

public class Jumin {

	private String jumin_no;

	public void setJumin_no(String jumin_no) {
		this.jumin_no = jumin_no;
	}

	//             01234567890123   <= index
	// jumin_no = "801212-1234560";
	
	/*
	             ������       �ܱ���
	             ��  ��       ��  ��  
	    1900     1   2        5   6   
	    2000     3   4        7   8
	    1800     9   0
	*/
	
	//����⵵
	public int getYear() {
		//2�ڸ� ����⵵ ����
		String str_year = jumin_no.substring(0, 0+2);// "80"
		
		//���ڿ�=>������ ��ȯ
		int year = Integer.parseInt(str_year);// "80"=> 80
		
		//�����ڵ� ���ϱ�
		char gender = jumin_no.charAt(7);// '1'
			
		switch(gender)
		{
		  case '1': case '2': case '5': case '6': year= year + 1900; break;
		  case '3': case '4': case '7': case '8': year= year + 2000; break;
		  default : year = year + 1800;
		}
		
		return year;
	}
	
	//���� : 
	public int getAge() {
		//���� �⵵ ���ؼ� ����
		Calendar c = Calendar.getInstance();
		int current_year = c.get(Calendar.YEAR);
		//���� = ����⵵ -  ����⵵ + 1
		int age =  current_year - this.getYear() + 1;
		
		return age;
	}
	
	//�챸�ϱ�
	public String getTti() {
		//4  5  6  7  8  9  10 11 0  1  2  3   <= ����⵵%12 (0~11)
		//�� �� �� �� �� �� �� �� �� �� �� ��
		
		//����⵵ ���ϱ�
		int year = this.getYear();
		
		switch(year%12)
		{
			case  0: return "������";
			case  1: return "��";
			case  2: return "��";
			case  3: return "����";
			case  4: return "��";
			case  5: return "��";
			case  6: return "ȣ����";
			case  7: return "�䳢";
			case  8: return "��";
			case  9: return "��";
			case 10: return "��";
		}
		
		return "��";
	}
	
	//�迭Ȱ���ؼ� ����
	//                         0       1   2      3    4    5     6        7     8    9   10   11
	String [] tti_array = { "������","��","��","����","��","��","ȣ����","�䳢","��","��","��","��"};
	
	public String getTti2() {
				
		return tti_array[this.getYear()%12];
	}
	
	
	
	//�������ϱ�
	public String getGender() {
		
		//�����ڵ� ���ϱ�
		char gender = jumin_no.charAt(7);// '1'
		
		/*
		switch(gender)
		{
		  case '1': case '3': case '5': case '7':  case '9': return "����";
		}
		*/
		
		/*
		if(gender=='1' || gender=='3' || gender=='5' || gender=='7' || gender=='9')
			return "����";
		*/
		
		//Ȧ���� ����
		if((gender-'0')%2==1) return "����";
		
		
		return "����";
	}
	
	//             01234567890123   <= index
	// jumin_no = "801212-1234560";

	
	//�������
	public String getSeason() {
		
		//��:3~5  ����:6~8  ����:9~11  �ܿ�:12 1 2
		
		//�����
		String str_month = jumin_no.substring(2, 2+2); // "12"
		//���ڿ�->����
		int month = Integer.parseInt(str_month); // 12
		
		//if��Ȱ��
		/*
		if(month>=3 && month<=5) return "��";
		else if(month>=6 && month<=8) return "����";
		else if(month>=9 && month<=11) return "����";
		*/
		
		//switchȰ��
		switch(month/3) //�򿬻�
		{
		   case 1 : return "��";    //3  4  5
		   case 2 : return "����";  //6  7  8
		   case 3 : return "����";  //9 10 11
		}
		
		
		return "�ܿ�";
	}
	
	//�������
	public String getLocal() {
		
		//�����ڵ� ������
		String str_local = jumin_no.substring(8, 8+2);
		int local = Integer.parseInt(str_local);
		
		if(local>=0 && local<=8) return "����";
		else if(local>=9 && local<=12) return "�λ�";
		else if(local>=13 && local<=15) return "��õ";
		else if(local>= 16 && local<=25) return "��⵵";
		else if(local>= 26 && local<=34) return "������";
		else if(local>= 35 && local<=39) return "��û�ϵ�";
		else if(local==40) return "����";
		else if(local==44 || local==49) return "����Ư����";
		else if(local>= 41 && local<=47) return "��û����";
		else if(local>= 48 && local<=54) return "����ϵ�";
		else if(local>= 55 && local<=56) return "����";
		else if(local>= 57 && local<=66) return "���󳲵�";
		else if((local>= 67 && local<=69) || local==76) return "�뱸";
		else if(local>= 70 && local<=81) return "���ϵ�";
		else if(local==85) return "���";
		else if(local>=82 && local<=92) return "��󳲵�";
		else if(local>=93 && local<=95) return "����Ư����";
		
		
		return "���?";
	}
	
	
	//10�� 12��
	
	//10�� 
	//     4  5  6  7  8  9  0  1  2  3        <= ����⵵%10 (0~9) 
	//     �� �� �� �� �� �� �� �� �� ��
	
	//12��
	//     4  5  6  7  8  9  10 11 0  1  2  3  <= ����⵵%12 (0~11)
	//     �� �� �� �� �� �� �� �� �� �� �� �� 
	
	
	
	//60����
	public String getGanji() {
		
		int year = this.getYear();
		//                 0 1 2 3 4 5 6 7 8 9 
		String gan_list = "����Ӱ谩����������";
        //                 0 1 2 3 4 5 6 7 8 9 1011
		String  ji_list = "�������������ι��������";
		
		char gan = gan_list.charAt(year%10);
		char ji  = ji_list.charAt(year%12);
				
		return String.format("%c%c��", gan,ji);
	}
	
	
	//             01234567890123   <= index
	// jumin_no = "801212-1234560";	
	
	//���ڹٲٱ�
	//1.  Integer.parseInt(jumin_no.substring(0,1)) => "8" -> 8  (���ڿ� -> ���ڷκ�ȯ)
	//2.  jumin_no.charAt(0)-'0'  => '8'-'0' -> 8                (����   -> ���ڷκ�ȯ)  
	//    '0':48  '1':49  '2':50
	//    '1'-'0'=> 1
	//    '2'-'0'=> 50-48 -> 2
	
	
	//��ȿ�� üũ
	public boolean isValid() {
		
		int sum = 0;
		
		/*
		sum = sum + (jumin_no.charAt( 0) - '0')  * 2;
		sum = sum + (jumin_no.charAt( 1) - '0')  * 3;
		sum = sum + (jumin_no.charAt( 2) - '0')  * 4;
		sum = sum + (jumin_no.charAt( 3) - '0')  * 5;
		sum = sum + (jumin_no.charAt( 4) - '0')  * 6;
		sum = sum + (jumin_no.charAt( 5) - '0')  * 7;
		sum = sum + (jumin_no.charAt( 7) - '0')  * 8;
		sum = sum + (jumin_no.charAt( 8) - '0')  * 9;
		sum = sum + (jumin_no.charAt( 9) - '0')  * 2;
		sum = sum + (jumin_no.charAt(10) - '0')  * 3;
		sum = sum + (jumin_no.charAt(11) - '0')  * 4;
		sum = sum + (jumin_no.charAt(12) - '0')  * 5;
		*/
		
		int check_su = 2;
		for(int i=0;i<13;i++) { //i = 0~12
			
			if(i==6)continue;//i=6�϶��� Skip
			
			sum = sum + (jumin_no.charAt(i) - '0')  * check_su;
			
			check_su++;
			if(check_su>9) check_su=2;
		}
		
		
		//System.out.println(sum);
		int check_sum = (11-(sum%11))%10;
		System.out.println(check_sum);
		int last_no   = (jumin_no.charAt(13) - '0');
		
		return (last_no==check_sum);
	}
	
	
	//                 0 1 2 3 4 5 6 7 8 9 0 1 2  <=i
	int [] check_su = {2,3,4,5,6,7,0,8,9,2,3,4,5};
	
	public boolean isValid2() {
		
		int sum = 0;
		
		for(int i=0;i<13;i++) { //i = 0~12
			sum = sum + (jumin_no.charAt(i) - '0')  * check_su[i];
		}
		
		
		//System.out.println(sum);
		int check_sum = (11-(sum%11))%10;
		System.out.println(check_sum);
		int last_no   = (jumin_no.charAt(13) - '0');
		
		return (last_no==check_sum);
	}
	
	
	
	
	
	
	
}
