/*

--�Ϸù�ȣ
create sequence seq_board_b_idx

--���̺�
create table board
(
   b_idx   	 int                   ,	--�Ϸù�ȣ
   b_subject varchar2(500) not null,	--����
   b_content clob          not null,	--����
   b_ip      varchar2(200) not null,	--������
   b_regdate date				   ,    --�������
   b_readhit int     default 0	   ,    --��ȸ��
   b_use_yn  char(1) default 'y'   ,    --�������(y or n)
   m_idx     int                   ,    --�ۼ��� ȸ����ȣ 
   m_name    varchar2(100)         ,    --�ۼ��� �̸�
   b_ref     int                   ,    --�����۹�ȣ 
   b_step    int                   ,    --�ۼ���
   b_depth   int                        --�۱���(�������) 
)

--�⺻Ű
alter table board
  add constraint  pk_board_b_idx  primary key(b_idx) ;

--�ܷ�Ű
alter table board
  add constraint  fk_board_m_idx  foreign key(m_idx)
                                  references  member(m_idx) ;

select * from member     
               
--���۾��� Sample
insert into  board values(seq_board_b_idx.nextVal, 
                          '���� 1���̴�',
                          '�̹����� ���� 1���̳�~~',
                          '192.168.7.14',
                          sysdate,
                          default,
                          default,
                          1,
                          '�ϱ浿',
                          seq_board_b_idx.currVal,
                          0,
                          0
                         );
                                   
--��۾��� Sample
insert into board values(seq_board_b_idx.nextVal,
                          '�ƽ��� 2���̳�',
                          '�̹����� 2��°��',
                          '127.0.0.1',
                          sysdate,
                          default,
                          default,
                          5,
                          'ȫ�浿',
                          1,
                          1,
                          1 
                         );
                         
insert into board values(seq_board_b_idx.nextVal,
                          '�������� 1����',
                          '��������...',
                          '127.0.0.1',
                          sysdate,
                          default,
                          default,
                          1,
                          '�ϱ浿',
                          1,
                          2,
                          2 
                         );
                                                  
commit                                                                 
                                                                                                                                                                                                   
select * from board
order by  b_ref desc, b_step asc
                                                                                                                                                                                              
          
update board set b_readhit=0                                                                                                                                                                                                                                                                                                            


delete from board  where b_idx in(12)


update board set b_use_yn = 'y'







-- Pagingó���� ���� SQL����

select * from
(
	select 
	  rank() over(order by b_ref desc,b_step asc) as no,
	  (select nvl(count(*),0) from comment_tb where b_idx=b.b_idx) as comment_count,
	  b.*
	from 
	  ( select * from board ) b
)
where no between 6  and  10  


--��ü�Խù���
select nvl(count(*) , 0) from board



















*/