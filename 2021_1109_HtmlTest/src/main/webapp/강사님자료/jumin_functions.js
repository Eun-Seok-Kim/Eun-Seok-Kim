/**
 * 
 */

//자바스크립트함수의 인자는 변수명만 기록한다

/*
               01234567890123  <- index            
   jumin_no = "801212-1234560"
  
               내국인   외국인   
               남  여   남  여
   1900        1   2    5   6  
   2000        3   4    7   8
   1800        9   0

*/

function getYear(jumin_no){
	
	//년도 2자리 얻어오기->숫자변환
	var year   = parseInt(jumin_no.substring(0,2));
	var gender = parseInt(jumin_no.charAt(7)); 
	
	if(gender==1 || gender==2 || gender==5 || gender==6) 
	   year = year + 1900;
	else if(gender==3 || gender==4 || gender==7 || gender==8) 
	   year = year + 2000;
    else
       year = year + 1800;   

	return year;
}

function getAge(year){
	//자바스크립에서 현재날짜구하고
	//현재 년도를 구해서 이용
	var today = new Date();
	var current_year = today.getFullYear();
	
	return current_year-year+1;
}

function  getTti(year){
	
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
		case 11: return "양";
	}
	return "양";
}

/*
               01234567890123  <- index            
   jumin_no = "801212-1234560"

               내국인   외국인   
               남  여   남  여
   1900        1   2    5   6  
   2000        3   4    7   8
   1800        9   0
*/
function getGender(jumin_no){
	
	var gender = parseInt(jumin_no.charAt(7));

	if( gender%2 == 0 ) return "여자";
	
	return "남자";
}

function getSeason(jumin_no){
	
	var month = parseInt(jumin_no.substring(2,4));
	
	switch( Math.floor(month/3) )
	{
		case 1 : return "봄";
		case 2 : return "여름";
		case 3 : return "가을";
	}
	
	return "겨울";
}

function getLocal(jumin_no){
	
	var local = parseInt(jumin_no.substring(8,10));
	
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

function getGanji(year){
	
	//            0 1 2 3 4 5 6 7 8 9        <- index 
	var gan_10 = "경신임계갑을병정무기";
	//            0 1 2 3 4 5 6 7 8 9 10 11  <- index
	var ji_12  = "신유술해자축인묘진사오미";
	
	var gan = gan_10.charAt(year%10);  // 출생년도%10 (0~9)
	var ji  = ji_12.charAt(year%12);   // 출생년도%12 (0~11)
	
	return gan + ji + "년";
}














