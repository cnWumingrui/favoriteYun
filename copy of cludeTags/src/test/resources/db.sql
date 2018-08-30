--创建库
create database favarite;
--使用库
use favarite;
--备份表
mysqldump -hlocalhost -p3306 -uroot -paaaa --databases favarite > d:/backupfile.sql
--在这个库中创建表
create table tag(
	tid int primary key auto_increment unique,
	tname varchar(100),
	tcount int 
);
drop table tag;
select * from tag;
create table favorite(
	fid int primary key auto_increment unique,
	flabel varchar(1000),
	furl varchar(1000),
	fdesc varchar(2000)
);
drop table favorite;
select * from favorite;
create table tf(
	tid int ,
	fid int ,
	primary key( tid,fid)
);
drop table tf;
select * from tf;

--------------------------------------------------
alter table tf
   add 	CONSTRAINT fk_tid
	foreign key(tid) references tag(tid);	
alter table tf
	add CONSTRAINT fk_fid
	foreign key(fid) references favorite(fid);
	
	select * from favorite;
	select * from tag;
	select * from tf;
	
	--未分类:
	select * from favorite
	   where fid not in(select fid from tf);