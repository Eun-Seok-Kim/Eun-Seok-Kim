/*

--��ٱ��� �Ϸù�ȣ
create sequence seq_cart_idx

--��ٱ��� ���̺�
create table cart
(
  c_idx  int,  				--��ٱ����Ϸù�ȣ
  c_cnt  int  not null,		--����
  p_idx  int,				--��ǰ��ȣ
  m_idx  int				--ȸ����ȣ
)
--�⺻Ű
alter table cart 
	add constraint pk_cart_c_idx primary key(c_idx)


--��ǰ���̺�(product)�� idx�� cart p_idx���� �ܷ�Ű ����
alter table cart
  add constraint fk_cart_p_idx foreign key(p_idx) 
                               references product(p_idx) on delete cascade;

--ȸ�����̺��� m_idx cart�� m_idx �ܷ�Ű ����
alter table cart
  add constraint fk_cart_m_idx foreign key(m_idx) 
                               references member(m_idx) on delete cascade;


select * from product
--										    c_cnt p_idx m_idx
insert into cart values(seq_cart_idx.nextVal,1,4,5);
insert into cart values(seq_cart_idx.nextVal,1,2,5);
insert into cart values(seq_cart_idx.nextVal,1,4,5);

select * from cart

commit

-- Join�� ���ؼ� ��ȸ������ ����
create or replace view cart_view
as
	select
	   p.p_idx as p_idx,
	   c_idx, 
	   p_num,
	   p_name,
	   p_price,
	   p_saleprice,
	   c_cnt, 
	   c_cnt * p_saleprice as amount,
	   m_idx
	from product p inner join  cart c on p.p_idx = c.p_idx  

select * from cart_view where m_idx=5

--��ٱ��� ��ǰ�� �Ѱ�
select nvl(sum(amount),0) from cart_view where m_idx=50 ;


insert into cart values(seq_cart_idx.nextVal,1,5,2)

select * from cart_view where m_idx=5

select * from member







*/