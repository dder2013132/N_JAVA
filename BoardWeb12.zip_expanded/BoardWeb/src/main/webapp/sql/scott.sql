select *
from tab;

select *
from tbl_member;

update tbl_member
set responsibility = 'User';
insert into tbl_member
values('admin', '9999', '관리자',  'Admin');

update tbl_member
set images='lch.jpg'
where member_id='user10';

alter table tbl_member add responsibility varchar2(30);
alter table tbl_member add images varchar2(50);

--게시판(글번호, 제목, 내용, 작성자, 작성일시)
create table tbl_board (
 board_no number primary key --글번호(PK)
 ,title varchar2(300) not null --글제목(not null)
 ,content varchar2(1000) not null --내용(not null)
 ,writer varchar2(20) not null --작성자(not null)
 ,write_date date default sysdate --작성일시
);
create sequence board_seq;

insert into tbl_board(board_no, title, content, writer)
values(board_seq.nextval, '글등록연습', '첫번째 글등록입니다', 'user01');
insert into tbl_board(board_no, title, content, writer)
values(board_seq.nextval, '오늘은금요일', '즐거운날입니다', 'user02');
insert into tbl_board(board_no, title, content, writer)
values(board_seq.nextval, '서블릿공부', '하는 날이네요..', 'user03');

insert into tbl_board
select board_seq.nextval, title, content, writer, write_date
from tbl_board
--order by board_no desc
;
-- paging
select b.*
from   (select rownum rn, a.*
        from (select *
              from tbl_board
              where writer like '%'||'오늘은'||'%'
              order by board_no) a) b
where b.rn > (:page - 1) * 5
and   b.rn <= (:page * 5);

select count(*) from tbl_board;

delete from tbl_board
where board_no = 8;

select *--empno, ename, job, mgr, hiredate, sal
from emp
order by empno desc;

insert into emp (empno, ename, job, mgr, hiredate, sal)
values (9998, '홍길동', 'CLERK', 7788, '2020-01-01', 1000);

update emp
set    sal = 2000
      ,deptno = 10
where empno = 9998;

delete from emp
where empno = 9999;

commit;

-- table 생성.
-- 도서코드, 도서명, 저자, 출판사, 도서가격
create table tbl_book (
 book_code varchar2(5) primary key, --도서코드.
 book_title varchar2(50) not null, --도서명.
 author varchar2(30) not null, --저자.
 company varchar2(30) not null, --출판사.
 price number default 1000
);
create sequence book_seq;
select book_seq.nextval from dual;

insert into tbl_book (book_code, book_title, author, company, price)
values(book_seq.nextval, '이것이자바다', '신용권', '한빛출판사', 20000);
insert into tbl_book (book_code, book_title, author, company, price)
values(book_seq.nextval, '혼공자', '신용권', '한빛출판사', 22000);
insert into tbl_book (book_code, book_title, author, company, price)
values(book_seq.nextval, '웹기초', '김기초', '기초출판사', 23000);

select *
from tbl_book
where company = nvl('', company)
order by book_code;

-- 데이터베이스 프로그래밍.
update tbl_book
set    book_title = nvl(?, book_title)
      ,price      = ?
      ,author     = nvl(?, author)
      ,company    = nvl(?, company)
where book_code = ?;

create table tbl_member (
 user_id varchar2(10) primary key, --사용자 ID
 password varchar2(30) not null, --비밀번호
 user_name varchar2(50) not null
);
insert into tbl_member 
values ('user01', '1111', '홍길동');
insert into tbl_member 
values ('user02', '2222', '김길동');
insert into tbl_member 
values ('user03', '3333', '박길동');

select *
from tbl_member;

