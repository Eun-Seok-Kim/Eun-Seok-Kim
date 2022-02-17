/*
   --일련번호 
   create sequence seq_comment_tb_c_idx
   
   --테이블
   create table comment_tb
   (
   		c_idx  				  int,		--일련번호
   		c_content  varchar2(2000),		--내용
   		c_ip	   varchar2(200),		--아이피
   		c_regdate  date,				--등록일자
   		m_idx				  int,		--회원번호
   		m_id	   varchar2(100),       --회원아이디
   		m_name	   varchar2(100),		--회원명 	
   		b_idx                 int       --게시글번호
   )
   
   //기본키
   alter table comment_tb
      add constraint  pk_comment_tb_c_idx primary key(c_idx) ;
   
   //외래키
   alter table comment_tb
      add constraint  fk_comment_tb_m_idx foreign key(m_idx)
                                          references  member(m_idx) ;
                                          
   alter table comment_tb
      add constraint  fk_comment_tb_b_idx foreign key(b_idx)
                                          references board(b_idx) ; 
                                                                                              
  select * from comment_tb
  
  --join방식
  select 
         c_idx,c_content,c_ip,c_regdate,c.m_idx,b_idx, --댓글테이블
         m.m_id,m.m_name                               --회원테이블           
  from
      comment_tb c inner join member m  on c.m_idx=m.m_idx
      
      
  --페이징처리를 위한 SQL
  select * from
  (
	  select
	     rank() over(order by c_idx desc) as no,
	     c.*
	  from  (select * from comment_tb where b_idx=45) c
  )
  where no between 1 and 3
       
              
    
      
        
            
  
     
        
              
            

*/