/*

create table goods
(
	g_idx			varchar2(400)		   ,		--��ǰ��ȣ
	g_name			varchar2(400) not null ,		--��ǰ��
	g_price					  int not null ,		--����
	g_category	    varchar2(200) not null ,		--ī�װ�
	g_image			varchar2(200) 		   ,		--�̹���
	g_link			varchar2(200)					--��ũ
	
)

--�⺻Ű
alter table goods
	add constraint pk_goods_idx primary key(g_idx);


select * from goods

select * from goods where g_idx=5

delete from goods where g_idx between 1 and 147

drop sequence seq_goods_g_idx

select * from bookmark_goods

select * from bmk_goods_view where m_idx=1

insert into goods values(#{g_idx},#{g_name},#{g_price},#{g_category},#{g_image},#{g_link})
			where not exists (select * from goods where g_idx = #{g_idx})
			
insert into goods values(1,1,1,1,1,1)
	select #{g_idx} from dual 
			where not exists (select g_idx from goods where g_idx = #{g_idx})

//merge into ����
MERGE INTO '���̺� ��' demo_table
     USING (SELECT #{PK �Ķ����} AS pk_column,
                   #{�μ�Ʈ�� �Ķ���͵�} AS demo_table_columns(�μ�Ʈ�� ��� ���̺��� �÷���)
              FROM dual
            ) dummy
        ON (demo_table.pk_column = dummy.pk_column)
 
WHEN NOT MATCHED THEN
    INSERT(
            pk_column,
            demo_table_columns
            )
    VALUES(
            dummy.pk_column,
            dummy.demo_table_columns
            )

on duplicate key update

select g_idx from goods

select 
( select g_idx from goods where g_idx='17919507777')
g_idx from goods

delete 


*/