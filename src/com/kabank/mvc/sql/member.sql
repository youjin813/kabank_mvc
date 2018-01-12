select * from member;
select * from tab;
select * from member where id like 'aa';
select * from member where id like ?;
select * from member where name like '%aa%';
update member set addr = '서울' where id = 'aa';