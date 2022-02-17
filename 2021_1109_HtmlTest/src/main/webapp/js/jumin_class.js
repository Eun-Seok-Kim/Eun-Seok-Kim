/**
 * 
 */

class Jumin{
	//new Jumin("801212-1234560")
	constructor(jumin_no){
		//member field(변수)
		this.jumin_no = jumin_no;
	}
	
	//getter / setter 
	
	get jumin_no(){
		return this._jumin_no;
	}
	set jumin_no(no){
		this._jumin_no = no;	
	}
	
	//member 함수
	getYear(){
		//class의 멤버변수 또는 함수 표현시 앞에 this를 붙여하 한다
		var year   = parseInt(this.jumin_no.substring(0,2));
		var gender = parseInt(this.jumin_no.charAt(7)); 
		
		if(gender==1 || gender==2 || gender==5 || gender==6) 
		   year = year + 1900;
		else if(gender==3 || gender==4 || gender==7 || gender==8) 
		   year = year + 2000;
		else
		   year = year + 1800;   		
		return year;					
	}
	
	getAge(){
		var today = new Date();
		var current_year = today.getFullYear();
		var year = this.getYear();
		return current_year-year+1;		
	}
	
	getTti(){
		var tti;
			switch(this.getYear%12)
	{
		case  0: tti= "원숭이"; break;
		case  1: tti= "닭";break;
		case  2: tti= "개";break;
		case  3: tti= "돼지";break;
		case  4: tti= "쥐";break;
		case  5: tti= "소";break;
		case  6: tti= "호랑이";break;
		case  7: tti= "토끼";break;
		case  8: tti= "용";break;
		case  9: tti= "뱀";break;
		case 10: tti= "말";
		case 11: tti= "양";break;
	}
		return tti;
	}
	
	getGender(){
		var gender = parseInt(this.jumin_no.charAt(7));
		if( gender%2 == 0 ) return "여자";
		return "남자";		
	}
	getSeason(){
		var month = parseInt(this.jumin_no.substring(2,4));
		
		switch( Math.floor(month/3) )
	{
			case 1 : return "봄"; break;
			case 2 : return "여름";break;
			case 3 : return "가을";break;
		}
		
		return "겨울";
	}
	
	getLocal(){
		var local = parseInt(this.jumin_no.substring(8,10));
		
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
	getGanji(){
		//            0 1 2 3 4 5 6 7 8 9        <- index 
		var gan_10 = "경신임계갑을병정무기";
		//            0 1 2 3 4 5 6 7 8 9 10 11  <- index
		var ji_12  = "신유술해자축인묘진사오미";
		var year = this.getYear();
		var gan = gan_10.charAt(year%10);  // 출생년도%10 (0~9)
		var ji  = ji_12.charAt(year%12);   // 출생년도%12 (0~11)
		
		return gan + ji + "년";			
	}
	isValid(){
	var sum = 0;
	//			 0 1 2 3 4 5 6 7 8 9 0 1 2
	var check = [2,3,4,5,6,7,0,8,9,2,3,4,5];
	
	for(var i=0; i<check.length; i++){
	if(i==6) continue;
	sum = sum + (parseInt(this.jumin_no.charAt(i))*check[i]);
	}
	
	sum = sum%11;
	sum = 11-sum;
	sum = sum%10;
	
	var last_no = parseInt(this.jumin_no.charAt(13));
	
	return (sum=last_no);
	
	
	}
	
	
	
	
}