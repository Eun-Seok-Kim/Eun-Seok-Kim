/*

--장바구니 일련번호
create sequence seq_cart_idx

--장바구니 테이블
create table cart
(
  c_idx  int,  				--장바구니일련번호
  c_cnt  int  not null,		--수량
  p_idx  int,				--상품번호
  m_idx  int				--회원번호
)
--기본키
alter table cart 
	add constraint pk_cart_c_idx primary key(c_idx)


--상품테이블(product)의 idx와 cart p_idx간의 외래키 설정
alter table cart
  add constraint fk_cart_p_idx foreign key(p_idx) 
                               references product(p_idx) on delete cascade;

--회원테이블의 m_idx cart의 m_idx 외래키 설정
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

-- Join을 통해서 조회정보를 추출
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

--장바구니 상품의 총계
select nvl(sum(amount),0) from cart_view where m_idx=50 ;


insert into cart values(seq_cart_idx.nextVal,1,5,2)

select * from cart_view where m_idx=5

select * from member







*/