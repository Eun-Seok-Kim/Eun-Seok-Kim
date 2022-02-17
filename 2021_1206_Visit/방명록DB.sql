/*

--일련번호 관리객체

create sequence seq_visit_idx

--테이블

create table visit
(
	idx			int,						--일련번호
	name		varchar2(100) not null,		--작성자이름(익명)
	content 	varchar2(2000),				--내용
	pwd			varchar2(100),				--비밀번호
	ip			varchar2(100),				--ip
	regdate		date						--등록일자	
)

--기본키지정

alter table visit
	add constraint pk_visit_idx primary key(idx)

--sample date insert

insert into visit values(seq_visit_idx.nextVal,
									'일길동',
									'1등이다',
									'1234',
									'192.168.0.10',
									sysdate);
insert into visit values(seq_visit_idx.nextVal,'이길동','2등이다','1234','192.168.0.10',sysdate);

commit

update visit set name='이길동', content='어서오세요', pwd='2222', ip='192.168.0.11',regdate=sysdate where idx=9

update sungtb set name='이길동',kor=88,eng=88,mat=88 where idx = 2
select * from visit order by idx desc

update visit set content = replace(content,'\r\n', '<br>')


select * from visit where idx=9




*/
