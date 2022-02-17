/*

--일련번호
create sequence seq_board_b_idx

--테이블
create table board
(
   b_idx   	 int                   ,	--일련번호
   b_subject varchar2(500) not null,	--제목
   b_content clob          not null,	--내용
   b_ip      varchar2(200) not null,	--아이피
   b_regdate date				   ,    --등록일자
   b_readhit int     default 0	   ,    --조회수
   b_use_yn  char(1) default 'y'   ,    --사용유무(y or n)
   m_idx     int                   ,    --작성자 회원번호 
   m_name    varchar2(100)         ,    --작성자 이름
   b_ref     int                   ,    --참조글번호 
   b_step    int                   ,    --글순서
   b_depth   int                        --글깊이(답글정도) 
)

--기본키
alter table board
  add constraint  pk_board_b_idx  primary key(b_idx) ;

--외래키
alter table board
  add constraint  fk_board_m_idx  foreign key(m_idx)
                                  references  member(m_idx) ;

select * from member     
               
--새글쓰기 Sample
insert into  board values(seq_board_b_idx.nextVal, 
                          '내가 1등이다',
                          '이번에도 내가 1등이네~~',
                          '192.168.7.14',
                          sysdate,
                          default,
                          default,
                          1,
                          '일길동',
                          seq_board_b_idx.currVal,
                          0,
                          0
                         );
                                   
--답글쓰기 Sample
insert into board values(seq_board_b_idx.nextVal,
                          '아쉽네 2등이네',
                          '이번에도 2번째네',
                          '127.0.0.1',
                          sysdate,
                          default,
                          default,
                          5,
                          '홍길동',
                          1,
                          1,
                          1 
                         );
                         
insert into board values(seq_board_b_idx.nextVal,
                          '다음에는 1등해',
                          '다음에는...',
                          '127.0.0.1',
                          sysdate,
                          default,
                          default,
                          1,
                          '일길동',
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







-- Paging처리를 위한 SQL문장

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


--전체게시물수
select nvl(count(*) , 0) from board



















*/