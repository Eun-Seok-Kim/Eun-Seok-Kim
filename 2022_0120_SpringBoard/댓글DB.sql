/*
   --�Ϸù�ȣ 
   create sequence seq_comment_tb_c_idx
   
   --���̺�
   create table comment_tb
   (
   		c_idx  				  int,		--�Ϸù�ȣ
   		c_content  varchar2(2000),		--����
   		c_ip	   varchar2(200),		--������
   		c_regdate  date,				--�������
   		m_idx				  int,		--ȸ����ȣ
   		m_id	   varchar2(100),       --ȸ�����̵�
   		m_name	   varchar2(100),		--ȸ���� 	
   		b_idx                 int       --�Խñ۹�ȣ
   )
   
   //�⺻Ű
   alter table comment_tb
      add constraint  pk_comment_tb_c_idx primary key(c_idx) ;
   
   //�ܷ�Ű
   alter table comment_tb
      add constraint  fk_comment_tb_m_idx foreign key(m_idx)
                                          references  member(m_idx) ;
                                          
   alter table comment_tb
      add constraint  fk_comment_tb_b_idx foreign key(b_idx)
                                          references board(b_idx) ; 
                                                                                              
  select * from comment_tb
  
  --join���
  select 
         c_idx,c_content,c_ip,c_regdate,c.m_idx,b_idx, --������̺�
         m.m_id,m.m_name                               --ȸ�����̺�           
  from
      comment_tb c inner join member m  on c.m_idx=m.m_idx
      
      
  --����¡ó���� ���� SQL
  select * from
  (
	  select
	     rank() over(order by c_idx desc) as no,
	     c.*
	  from  (select * from comment_tb where b_idx=45) c
  )
  where no between 1 and 3
       
              
    
      
        
            
  
     
        
              
            

*/