/*

create table goods
(
	g_idx			varchar2(400)		   ,		--용품번호
	g_name			varchar2(400) not null ,		--용품명
	g_price					  int not null ,		--가격
	g_category	    varchar2(200) not null ,		--카테고리
	g_image			varchar2(200) 		   ,		--이미지
	g_link			varchar2(200)					--링크
	
)

--기본키
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

//merge into 예시
MERGE INTO '테이블 명' demo_table
     USING (SELECT #{PK 파라미터} AS pk_column,
                   #{인서트할 파라미터들} AS demo_table_columns(인서트할 사용 테이블의 컬럼들)
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