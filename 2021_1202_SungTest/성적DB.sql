/*

	--일련번호 관리객체
	create sequence seq_sungtb_idx   --성적테이블에 idx관리객체

	--테이블생성
	create table sungtb
	(
		idx 	int,    			--일련번호
		name	varchar2(100) not null,  --이름,반드시 입력되게 not null조건 붙임
		kor		int default 0,		--국어점수, 기본점수 0으로 지정
		eng		int default 0,		--영어점수, 기본점수 0으로 지정
		mat		int default 0		--수학점수, 기본점수 0으로 지정	
	)

	
	--기본키 제약
	alter table sungtb
		add constraint pk_sungtb_idx primary key(idx)

	--체크제약(국어점수 제약걸기)
	alter table sungtb
		add constraint ck_sungtb_kor check(kor between 0 and 100 )
	
	alter table sungtb
		add constraint ck_sungtb_eng check(eng between 0 and 100 )
		
	alter table sungtb
		add constraint ck_sungtb_mat check(mat between 0 and 100 )
		

	--Sample Date 입력
	insert into sungtb values(seq_sungtb_idx.nextVal ,'일길동',90,80,70 );
	insert into sungtb values(seq_sungtb_idx.nextVal ,'이길동',100,100,100 );
	insert into sungtb values(seq_sungtb_idx.nextVal ,'삼길동',90,90,90 );

	--Data 수정
	update sungtb set name='이길동',kor=88,eng=88,mat=88
	where idx = 2
	--idx가 없을경우 전체데이터가 수정된다.
	
	--총점, 평균, 등수  => 조회용 view 생성
	
	create or replace view sungtb_view
	as 
	select
		idx,name,kor,eng,mat,
		(kor+eng+mat) as tot,
		round((kor+eng+mat)/3,1) as avg,
		rank() over(order by(kor+eng+mat) desc) as rank
	from sungtb
	order by idx asc

	
	---명령어 모음
	
	commit

	select * from sungtb
	
	select * from sungtb_view
	
	##SQL Injection(주입)의한 DB해킹 : 기존명령 + or 1+1
	select * from sungtb_view where idx=10 or 1=1
	
	
	## PreparedStatement : SQL , 전달인다(parameter)를 따로 전송함
	select * from sungtb_view where idx=?
	
	
	
	select * from sawon
	
	select * from sungtb_view
	
	select * from sungtb_view where idx=12
	
	
	
*/