/*

	--�Ϸù�ȣ ������ü
	create sequence seq_sungtb_idx   --�������̺� idx������ü

	--���̺����
	create table sungtb
	(
		idx 	int,    			--�Ϸù�ȣ
		name	varchar2(100) not null,  --�̸�,�ݵ�� �Էµǰ� not null���� ����
		kor		int default 0,		--��������, �⺻���� 0���� ����
		eng		int default 0,		--��������, �⺻���� 0���� ����
		mat		int default 0		--��������, �⺻���� 0���� ����	
	)

	
	--�⺻Ű ����
	alter table sungtb
		add constraint pk_sungtb_idx primary key(idx)

	--üũ����(�������� ����ɱ�)
	alter table sungtb
		add constraint ck_sungtb_kor check(kor between 0 and 100 )
	
	alter table sungtb
		add constraint ck_sungtb_eng check(eng between 0 and 100 )
		
	alter table sungtb
		add constraint ck_sungtb_mat check(mat between 0 and 100 )
		

	--Sample Date �Է�
	insert into sungtb values(seq_sungtb_idx.nextVal ,'�ϱ浿',90,80,70 );
	insert into sungtb values(seq_sungtb_idx.nextVal ,'�̱浿',100,100,100 );
	insert into sungtb values(seq_sungtb_idx.nextVal ,'��浿',90,90,90 );

	--Data ����
	update sungtb set name='�̱浿',kor=88,eng=88,mat=88
	where idx = 2
	--idx�� ������� ��ü�����Ͱ� �����ȴ�.
	
	--����, ���, ���  => ��ȸ�� view ����
	
	create or replace view sungtb_view
	as 
	select
		idx,name,kor,eng,mat,
		(kor+eng+mat) as tot,
		round((kor+eng+mat)/3,1) as avg,
		rank() over(order by(kor+eng+mat) desc) as rank
	from sungtb
	order by idx asc

	
	---��ɾ� ����
	
	commit

	select * from sungtb
	
	select * from sungtb_view
	
	##SQL Injection(����)���� DB��ŷ : ������� + or 1+1
	select * from sungtb_view where idx=10 or 1=1
	
	
	## PreparedStatement : SQL , �����δ�(parameter)�� ���� ������
	select * from sungtb_view where idx=?
	
	
	
	select * from sawon
	
	select * from sungtb_view
	
	select * from sungtb_view where idx=12
	
	
	
*/