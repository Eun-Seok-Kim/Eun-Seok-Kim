/*

--�Ϸù�ȣ�� ó��(������x)

--���̺������
drop table member



--���̺�
create table member
(
	m_idx		int,							--�Ϸù�ȣp-key ����Ұ�
	m_name		varchar2(100) not null,			--�̸�		 ����Ұ�
	m_id		varchar2(100) not null,			--id(������) ����Ұ�
	m_pwd		varchar2(100),					--��й�ȣ
	m_zipcode	varchar2(100),					--�����ȣ
	m_addr		varchar2(200),					--�ּ�
	m_ip		varchar2(100),					--ip
	m_regdate	date,							--�������	
	m_grade		varchar2(100)					--ȸ������(�Ϲ�/������)
)

--�⺻Ű����(p-key)
alter table member add constraint pk_member_idx primary key(m_idx);

--����ũ����
alter table member add constraint unique_member_id unique(m_id)

--�Ϸù�ȣ����
select nvl(max(m_idx),0)+1 from member

--sample data
insert into member values( (select nvl(max(m_idx),0)+1 from member), 
							'�����',
							'admin',
							'1234',
							'12345',
							'����� ���Ǳ� ������ 552',
							'192.168.0.1',
							sysdate,
							'������'
							);

insert into member values( (select nvl(max(m_idx),0)+1 from member), 
							'�ϱ浿',
							'one',
							'1234',
							'12345',
							'����� ���Ǳ� ������ 552',
							'192.168.0.1',
							sysdate,
							'�Ϲ�'
							);

--����

update member set m_pwd='11111' where m_idx=1

update member set m_pwd='?', m_zipcode='?', m_addr='?', m_ip='?', m_regdate=sysdate,  where m_idx=?

select * from member

update member set m_pwd='1111',
				  m_id='two',
				  m_zipcode='55555',
				  m_addr='��⵵ ������',
				  m_ip='192.168.0.1',
				  m_regdate=sysdate where m_idx=1


select * from member where m_id='admin'

insert into member values( (select nvl(max(m_idx),0)+1 from member), 
							'�ϱ浿',
							'one',
							'1234',
							'12345',
							'����� ���Ǳ� ������ 552',
							'192.168.0.1',
							sysdate,
							);












*/