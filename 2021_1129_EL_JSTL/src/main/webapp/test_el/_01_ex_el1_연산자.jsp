<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    <!-- HEML 주석 : 브라우져에서 소스보기로 확인가능 
    	EL : 형식 
    -->
    <%-- JSP 주석 : 브라우져에서 소스보기로 확인불가능 
    
    3.연산자
    1) 산술연산자 : + - * / % or mod
    2) 관계연산자 : > >= < <= == !=
    				gt ge lt le eq ne
    3) 논리연산자 : and or not
    				&&	||  !
    4) 3항연산자 : (조건) ? 값1 : 값2
    5) 기타연산자 : empty
    
    --%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
<hr>
	산술연산자
	<hr>	
	\${2+3} : ${2+3}<br>
	\${2-3} : ${2-3}<br>
	\${2*3} : ${2*3}<br>
	\${10/5} : ${10/5} <%-- or \${10 div5} --%><br>
	\${10%3} : ${10%3} <%-- or ${10 mod 3 } --%><br>
<hr>
관계(비교)연산자
<hr>
	\${3>2}	 : ${3>2}  or ${3 gt 2 }<br><!-- gt " greater than -->
	\${3>=2} : ${3>=2} or ${3 ge 2 }<br><!-- ge " greater equal -->
	
	\${3<2}  : ${3<2}  or ${3 lt 2 }<br><!-- lt " less than -->
	\${3<=2} : ${3<=2} or ${3 le 2 }<br><!-- le " less equal -->
	
	\${3==2} : ${3==2} or ${3 eq 2 }<br><!-- gt " equal -->
	\${3!=2} : ${3!=2} <%-- or ${3 ne 2 } --%><br><!-- gt " not equal -->
<hr>
	논리연산자
<hr>
\${true && true} : ${true && true} or ${true and true }<br>
\${true || false} : ${true || false} or ${true or false }<br>
<hr>
	3항연산자
<hr>
\${empty param.msg ? '비어있음' : param.msg } : ${empty param.msg ? '비어있음' : 'param.msg' }
</body>

</html>