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
	             내국인       외국인
	             남  여       남  여  
	    1900     1   2        5   6   
	    2000     3   4        7   8
	    1800     9   0
	*/
	
	//출생년도
	public int getYear() {
		//2자리 출생년도 추출
		String str_year = jumin_no.substring(0, 0+2);// "80"
		
		//문자열=>정수로 변환
		int year = Integer.parseInt(str_year);// "80"=> 80
		
		//성별코드 구하기
		char gender = jumin_no.charAt(7);// '1'
			
		switch(gender)
		{
		  case '1': case '2': case '5': case '6': year= year + 1900; break;
		  case '3': case '4': case '7': case '8': year= year + 2000; break;
		  default : year = year + 1800;
		}
		
		return year;
	}
	
	//나이 : 
	public int getAge() {
		//현재 년도 구해서 대입
		Calendar c = Calendar.getInstance();
		int current_year = c.get(Calendar.YEAR);
		//나이 = 현재년도 -  출생년도 + 1
		int age =  current_year - this.getYear() + 1;
		
		return age;
	}
	
	//띠구하기
	public String getTti() {
		//4  5  6  7  8  9  10 11 0  1  2  3   <= 출생년도%12 (0~11)
		//자 축 인 묘 진 사 오 미 신 유 술 해
		
		//출생년도 구하기
		int year = this.getYear();
		
		switch(year%12)
		{
			case  0: return "원숭이";
			case  1: return "닭";
			case  2: return "개";
			case  3: return "돼지";
			case  4: return "쥐";
			case  5: return "소";
			case  6: return "호랑이";
			case  7: return "토끼";
			case  8: return "용";
			case  9: return "뱀";
			case 10: return "말";
		}
		
		return "양";
	}
	
	//배열활용해서 구현
	//                         0       1   2      3    4    5     6        7     8    9   10   11
	String [] tti_array = { "원숭이","닭","개","돼지","쥐","소","호랑이","토끼","용","뱀","말","양"};
	
	public String getTti2() {
				
		return tti_array[this.getYear()%12];
	}
	
	
	
	//성별구하기
	public String getGender() {
		
		//성별코드 구하기
		char gender = jumin_no.charAt(7);// '1'
		
		/*
		switch(gender)
		{
		  case '1': case '3': case '5': case '7':  case '9': return "남자";
		}
		*/
		
		/*
		if(gender=='1' || gender=='3' || gender=='5' || gender=='7' || gender=='9')
			return "남자";
		*/
		
		//홀수면 남자
		if((gender-'0')%2==1) return "남자";
		
		
		return "여자";
	}
	
	//             01234567890123   <= index
	// jumin_no = "801212-1234560";

	
	//출생계절
	public String getSeason() {
		
		//봄:3~5  여름:6~8  가을:9~11  겨울:12 1 2
		
		//출생월
		String str_month = jumin_no.substring(2, 2+2); // "12"
		//문자열->변경
		int month = Integer.parseInt(str_month); // 12
		
		//if문활용
		/*
		if(month>=3 && month<=5) return "봄";
		else if(month>=6 && month<=8) return "여름";
		else if(month>=9 && month<=11) return "가을";
		*/
		
		//switch활용
		switch(month/3) //몫연산
		{
		   case 1 : return "봄";    //3  4  5
		   case 2 : return "여름";  //6  7  8
		   case 3 : return "가을";  //9 10 11
		}
		
		
		return "겨울";
	}
	
	//출생지역
	public String getLocal() {
		
		//지역코드 얻어오기
		String str_local = jumin_no.substring(8, 8+2);
		int local = Integer.parseInt(str_local);
		
		if(local>=0 && local<=8) return "서울";
		else if(local>=9 && local<=12) return "부산";
		else if(local>=13 && local<=15) return "인천";
		else if(local>= 16 && local<=25) return "경기도";
		else if(local>= 26 && local<=34) return "강원도";
		else if(local>= 35 && local<=39) return "충청북도";
		else if(local==40) return "대전";
		else if(local==44 || local==49) return "세종특별시";
		else if(local>= 41 && local<=47) return "충청남도";
		else if(local>= 48 && local<=54) return "전라북도";
		else if(local>= 55 && local<=56) return "광주";
		else if(local>= 57 && local<=66) return "전라남도";
		else if((local>= 67 && local<=69) || local==76) return "대구";
		else if(local>= 70 && local<=81) return "경상북도";
		else if(local==85) return "울산";
		else if(local>=82 && local<=92) return "경상남도";
		else if(local>=93 && local<=95) return "제주특별시";
		
		
		return "어디?";
	}
	
	
	//10간 12지
	
	//10간 
	//     4  5  6  7  8  9  0  1  2  3        <= 출생년도%10 (0~9) 
	//     갑 을 병 정 무 기 경 신 임 계
	
	//12지
	//     4  5  6  7  8  9  10 11 0  1  2  3  <= 출생년도%12 (0~11)
	//     자 축 인 묘 진 사 오 미 신 유 술 해 
	
	
	
	//60갑자
	public String getGanji() {
		
		int year = this.getYear();
		//                 0 1 2 3 4 5 6 7 8 9 
		String gan_list = "경신임계갑을병정무기";
        //                 0 1 2 3 4 5 6 7 8 9 1011
		String  ji_list = "신유술해자축인묘진사오미";
		
		char gan = gan_list.charAt(year%10);
		char ji  = ji_list.charAt(year%12);
				
		return String.format("%c%c년", gan,ji);
	}
	
	
	//             01234567890123   <= index
	// jumin_no = "801212-1234560";	
	
	//숫자바꾸기
	//1.  Integer.parseInt(jumin_no.substring(0,1)) => "8" -> 8  (문자열 -> 숫자로변환)
	//2.  jumin_no.charAt(0)-'0'  => '8'-'0' -> 8                (문자   -> 숫자로변환)  
	//    '0':48  '1':49  '2':50
	//    '1'-'0'=> 1
	//    '2'-'0'=> 50-48 -> 2
	
	
	//유효성 체크
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
			
			if(i==6)continue;//i=6일때는 Skip
			
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
