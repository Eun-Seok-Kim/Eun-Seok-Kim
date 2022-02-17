/**
 * 
 */

//자바스크립트 함수의 인자는 변수명만 쓴다
/*
			01234567890123
jumin_no = "801212-1234560"
			내국인	외국인
			남  여  남  여
1900		1   2	5	6  
2000		3   4	7	8
1800		9   0	
*/
function getYear(jumin_no){
	
	//년도2자리 얻어오기
	var year   = parseInt(jumin_no.substr(0,2));
	var gender1 = parseInt(jumin_no.charAt(7)); 
	if(gender1==1 || gender1==2 || gender1==5 || gender1 == 6)
	year = 1900+year;
	else if(gender1==3 || gender1==4 || gender1==7 || gender1 == 8)
	year = 2000+year;
	else if(gender1==9 || gender1==0)
	year = 1800+year;
	return year;
}
function getAge(year){
	var date = new Date();	
	var age = date.getFullYear() - year+1;
	return age;
}
function getTti(year){
	var tti;
	switch(year%12){
	case 0: tti = "원숭이";
	case 1: tti = "닭";
	case 2: tti = "개";
	case 3: tti = "돼지";
	case 4: tti = "쥐";
	case 5: tti = "소";
	case 6: tti = "호랑이";
	case 7: tti = "토끼";
	case 8: tti = "용";
	case 9: tti = "뱀";
	case 10: tti = "말";
	case 11: tti = "양";
	}
 	return tti;
}
function getGender(jumin_no){
	var gender1 = parseInt(jumin_no.charAt(7));
	if(gender1==1 || gender1==3 || gender1==5 || gender1==7 || gender1==9)
	{gender ="남자"}
	else gender = "여자";
	return gender;
}

function getSeason(jumin_no){
	var a  = parseInt(jumin_no.substr(2,2));
	
	if(a>=3 || 5>=a){
		season = "봄";	
	}else 
	if(a>=6 || 8>=a){
		season = "여름";	
	}else 
	if(a>=9 || 11>=a){
		season = "가을";	
	}else
	 season = "겨울";		
	return season;
	
}

function getLocal(jumin_no){
	var a  = parseInt(jumin_no.substr(8,2));
	var local;
	if(a<=8){
		local='서울'
	}else if(a>=9||12>=a){
		local='부산'
	}else if(a>=13||15>=a){
		local='인천'
	}else if(a>=16||25>=a){
		local='경기도'
	}else if(a>=26||34>=a){
		local='강원도'
	}else if(a>=35||39>=a){
		local='충청북도';
	}else if(a>=40||47>=a){
		local='충청남도'
	}else if(a=40){
		local='대전'
	}else if(a>=48||55>=a){
		local='전라북도'
	}else if(a>=56||66>=a){
		local='전라남도'
	}else if(a>=65||66>=a){
		local='광주'
	}else if(a>=67||80>=a){
		local='경상북도'
	}else if(a>=67||69>=a){
		local='대구'
	}else if(a>=81||99>=a){
		local='경상남도'
	}else if(a=85){
		local='울산'
	}
	return local;
}

function getGanji(year){
	var gan_10 = "경신임계갑을병정무기";
	
	var ji_12 = "신유술해자축인묘진사오미";
	
	var gan = gan_10.charAt(year%10);
	
	var ji = ji_12.charAt(year%12);
	
	return gan+ji +"년";
	
	
}

/*
function isValid(jumin_no){

			01234567890123	
jumin_no = "801212-1234560"
			234567 892345


	var sum = 0;
	sum = sum + (parseInt(jumin_o.charAt(0))*2);
	sum = sum + (parseInt(jumin_o.charAt(1))*3);
	sum = sum + (parseInt(jumin_o.charAt(2))*4);
	sum = sum + (parseInt(jumin_o.charAt(3))*5);
	sum = sum + (parseInt(jumin_o.charAt(4))*6);
	sum = sum + (parseInt(jumin_o.charAt(5))*7);
	sum = sum + (parseInt(jumin_o.charAt(7))*8);
	sum = sum + (parseInt(jumin_o.charAt(8))*9);
	sum = sum + (parseInt(jumin_o.charAt(9))*2);
	sum = sum + (parseInt(jumin_o.charAt(10))*3);
	sum = sum + (parseInt(jumin_o.charAt(11))*4);
	sum = sum + (parseInt(jumin_o.charAt(12))*5);
	
	sum = sum%11;
	sum = 11-sum;
	sum = sum%10;
	
	var last_no = parseInt(jumin.charAt(13));
	
	return (sum=last_no);
}
*/
function isValid(jumin_no){

/*			01234567890123	
jumin_no = "801212-1234560"
			234567 892345
*/

	var sum = 0;
	//			 0 1 2 3 4 5 6 7 8 9 0 1 2
	var check = [2,3,4,5,6,7,0,8,9,2,3,4,5];
	
	for(var i=0; i<check.length; i++){
	if(i==6) continue;
	sum = sum + (parseInt(jumin_no.charAt(i))*check[i]);
	}
	
	
/*	sum = sum + (parseInt(jumin_o.charAt(0))*2);
	sum = sum + (parseInt(jumin_o.charAt(1))*3);
	sum = sum + (parseInt(jumin_o.charAt(2))*4);
	sum = sum + (parseInt(jumin_o.charAt(3))*5);
	sum = sum + (parseInt(jumin_o.charAt(4))*6);
	sum = sum + (parseInt(jumin_o.charAt(5))*7);
	sum = sum + (parseInt(jumin_o.charAt(7))*8);
	sum = sum + (parseInt(jumin_o.charAt(8))*9);
	sum = sum + (parseInt(jumin_o.charAt(9))*2);
	sum = sum + (parseInt(jumin_o.charAt(10))*3);
	sum = sum + (parseInt(jumin_o.charAt(11))*4);
	sum = sum + (parseInt(jumin_o.charAt(12))*5);
	*/
	
	sum = sum%11;
	sum = 11-sum;
	sum = sum%10;
	
	var last_no = parseInt(jumin_no.charAt(13));
	
	return (sum=last_no);
}







