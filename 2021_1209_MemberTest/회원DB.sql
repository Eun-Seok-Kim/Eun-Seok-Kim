/*

--일련번호를 처리(시퀀스x)

--테이블지우기
drop table member



--테이블
create table member
(
	m_idx		int,							--일련번호p-key 변경불가
	m_name		varchar2(100) not null,			--이름		 변경불가
	m_id		varchar2(100) not null,			--id(고유값) 변경불가
	m_pwd		varchar2(100),					--비밀번호
	m_zipcode	varchar2(100),					--우편번호
	m_addr		varchar2(200),					--주소
	m_ip		varchar2(100),					--ip
	m_regdate	date,							--등록일자	
	m_grade		varchar2(100)					--회원구분(일반/관리자)
)

--기본키지정(p-key)
alter table member add constraint pk_member_idx primary key(m_idx);

--유니크제약
alter table member add constraint unique_member_id unique(m_id)

--일련번호관리
select nvl(max(m_idx),0)+1 from member

--sample data
insert into member values( (select nvl(max(m_idx),0)+1 from member), 
							'김관리',
							'admin',
							'1234',
							'12345',
							'서울시 관악구 시흥대로 552',
							'192.168.0.1',
							sysdate,
							'관리자'
							);

insert into member values( (select nvl(max(m_idx),0)+1 from member), 
							'일길동',
							'one',
							'1234',
							'12345',
							'서울시 관악구 시흥대로 552',
							'192.168.0.1',
							sysdate,
							'일반'
							);

--수정

update member set m_pwd='11111' where m_idx=1

update member set m_pwd='?', m_zipcode='?', m_addr='?', m_ip='?', m_regdate=sysdate,  where m_idx=?

select * from member

update member set m_pwd='1111',
				  m_id='two',
				  m_zipcode='55555',
				  m_addr='경기도 수원시',
				  m_ip='192.168.0.1',
				  m_regdate=sysdate where m_idx=1


select * from member where m_id='admin'

insert into member values( (select nvl(max(m_idx),0)+1 from member), 
							'일길동',
							'one',
							'1234',
							'12345',
							'서울시 관악구 시흥대로 552',
							'192.168.0.1',
							sysdate,
							);












*/