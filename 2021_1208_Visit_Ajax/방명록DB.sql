/*

--�Ϸù�ȣ ������ü

create sequence seq_visit_idx

--���̺�

create table visit
(
	idx			int,						--�Ϸù�ȣ
	name		varchar2(100) not null,		--�ۼ����̸�(�͸�)
	content 	varchar2(2000),				--����
	pwd			varchar2(100),				--��й�ȣ
	ip			varchar2(100),				--ip
	regdate		date						--�������	
)

--�⺻Ű����

alter table visit
	add constraint pk_visit_idx primary key(idx)

--sample date insert

insert into visit values(seq_visit_idx.nextVal,
									'�ϱ浿',
									'1���̴�',
									'1234',
									'192.168.0.10',
									sysdate);
insert into visit values(seq_visit_idx.nextVal,'�̱浿','2���̴�','1234','192.168.0.10',sysdate);

commit

update visit set name='�̱浿', content='�������', pwd='2222', ip='192.168.0.11',regdate=sysdate where idx=9

update sungtb set name='�̱浿',kor=88,eng=88,mat=88 where idx = 2
select * from visit order by idx desc

update visit set content = replace(content,'\r\n', '<br>')


select * from visit where idx=9




*/
