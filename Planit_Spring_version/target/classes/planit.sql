create database planit;
drop database planit;
use planit;

drop table t_user;
create table t_user(
	userid varchar(300) primary key,
    userpw varchar(300) not null,
    username varchar(300) not null,
    gender enum('M','W'),
    userdob date default (current_time),
    useremail varchar(300) not null,
    userphone varchar(300) not null,
	zipcode varchar(300) not null,
    addr varchar(1000),
    addrdetail varchar(1000) not null,
    addretc varchar(300)
);

use planit;


#회원가입 
insert into t_user values ('apple','1111','김사과','W','1997-01-01','aa@aaa.aaa','01000001111','12345','역삼','(역삼동)','(역삼동)');
insert into t_user values ('banana','1111','바나나','W','1997-01-01','aa@aaa.aaa','01000001111','12345','역삼','(역삼동)','(역삼동)');
insert into t_user values ('cherry','1111','체리','W','1997-01-01','aa@aaa.aaa','01000001111','12345','역삼','(역삼동)','(역삼동)');
insert into t_user values ('durian','1111','두리안','W','1997-01-01','aa@aaa.aaa','01000001111','12345','역삼','(역삼동)','(역삼동)');
#친구 추가, 차단 
insert into t_friend values ('apple','banana',1,0);
insert into t_friend values ('apple','cherry',1,0);
insert into t_friend values ('apple','durian',1,1);

Drop table t_friend;
create table t_friend(
   userid varchar(300),
    friendid varchar(300),
    reqchk boolean default 0, # 0 일때 친구 신청 대기 1일때 수락 거절시 삭제
    blockchk int default 0, # 0일때 노차단 / 1일때 userid 이 friendid를 차단 / 2일때 friendid가 userid를 차단
    constraint foreign key(userid) references t_user(userid) ON DELETE CASCADE,
    constraint foreign key(friendid) references t_user(userid) ON DELETE CASCADE
);


drop table t_schedule;
create table t_schedule(
	scnum int primary key auto_increment,
    userid varchar(300),
    scheduletitle varchar(300),
    schedulecolor varchar(300),
    scheduledate datetime default now(),
    tempsave boolean,
    constraint foreign key (userid) references t_user(userid) ON DELETE CASCADE
);

Create table t_share;
create table t_share(
	scnum int, 
    userid varchar(300),
    authority enum('r','w'),
    friends varchar(1000),
	constraint foreign key (scnum) references t_schedule(scnum) ON DELETE CASCADE,
    constraint foreign key (userid) references t_user(userid) ON DELETE CASCADE
);

drop table t_scheduledate;
create table t_scheduledate(
	scnum int, 
    scdate date,
    constraint foreign key (scnum) references t_schedule(scnum) ON DELETE CASCADE
);

create table t_category(
	catnum int primary key,
    catname varchar(300)
);

#카테고리 추가 
insert into t_category values (1,'가계부');
insert into t_category values (2,'식단');
insert into t_category values (3,'문화생활');
insert into t_category values (4,'일기');
insert into t_category values (5,'지도');
insert into t_category values (6,'이미지');
insert into t_category values (7,'메모');


drop table t_chatroom;
create table t_chatroom(
	chatroomnum int primary key auto_increment,
    chatroomname varchar(300)
);

create table t_chatmember(
   chatroomnum bigint,
   userid varchar(300),
   constraint foreign key (userid) references t_user(userid) ON DELETE CASCADE
);

create table t_chatfromto(
   chatroomnum int,
   chatnum int,
   fromuser varchar(300),
   touser varchar(300),
   constraint foreign key (fromuser) references t_user(userid) ON DELETE CASCADE,
   constraint foreign key (touser) references t_user(userid) ON DELETE CASCADE
);

create table t_chat(
   chatroomnum int, 
       chatnum int primary key auto_increment,
       chatfrom varchar(300),
       chatto varchar(300),
       chatdate datetime default now(),
       chatcontents text,
       chattype varchar(300),
       constraint foreign key (chatfrom) references t_user(userid) ON DELETE CASCADE,
       constraint foreign key (chatto) references t_user(userid) ON DELETE CASCADE,
       constraint foreign key (chatroomnum) references t_chatroom(chatroomnum) ON DELETE CASCADE
);

create table t_chatcontent(
   chatnum bigint auto_increment primary key,
   content varchar(3000),
   chatdate datetime default now()
);

create table t_memo(
    scnum int,
    catnum int,
    contnum int,
    memocontents text,
    constraint foreign key(scnum) references t_schedule(scnum) on delete cascade,
    constraint foreign key(catnum) references t_category(catnum) on delete cascade
);

create table t_image(
    imgname varchar(1000) not null,
    scnum int,
    catnum int,
    contnum int,
    constraint foreign key(scnum) references t_schedule(scnum) on delete cascade,
    constraint foreign key(catnum) references t_category(catnum) on delete cascade 
);

create table t_map(
   latitude varchar(300) not null,
    longitude varchar(300) not null,
   scnum int,
    catnum int,
    contnum int,
   constraint foreign key(scnum) references t_schedule(scnum) on delete cascade,
    constraint foreign key(catnum) references t_category(catnum) on delete cascade
    );

create table t_diary(
   diarydate varchar(300) not null,
    weather varchar(300) not null,
    diarytitle varchar(1000) not null,
    diarycontents text not null,
    scnum int,
    catnum int,
    contnum int,
   constraint foreign key(scnum) references t_schedule(scnum) on delete cascade,
	constraint foreign key(catnum) references t_category(catnum) on delete cascade
);

create table t_culture(
   cultitle varchar(1000) not null,
    cultype varchar(500) not null,
    culgenre varchar(500) not null,
    culcontents text,
    culscore int,
    scnum int,
    catnum int,
    contnum int,
   constraint foreign key(scnum) references t_schedule(scnum) on delete cascade,
    constraint foreign key(catnum) references t_category(catnum) on delete cascade

);

create table t_food(
   foodtime varchar(300) not null, 
    foodcontents text not null,
    foodscore int,
    scnum int,
    catnum int,
    contnum int,
   constraint foreign key(scnum) references t_schedule(scnum) on delete cascade,
    constraint foreign key(catnum) references t_category(catnum) on delete cascade

);

create table t_accountRow(
accountRowNum varchar(300),
 accdate varchar(300),
 history varchar(500) not null,
 amount varchar(1000) not null,
 acctype varchar(300) not null,
 scnum int,
 catnum int,
 contnum int,
 constraint foreign key(scnum) references t_schedule(scnum) on delete cascade,
 constraint foreign key(catnum) references t_category(catnum) on delete cascade
);

create table t_account(
	startdate varchar(300),
	enddate varchar(300),
   budget varchar(1000),
    accmemo varchar(1000),
    accountRowNums varchar(300),
    scnum int,
    catnum int,
    contnum int,
   constraint foreign key(scnum) references t_schedule(scnum) on delete cascade,
    constraint foreign key(catnum) references t_category(catnum) on delete cascade

);

create table t_todolist(
	userid varchar(300),
	todonum int primary key auto_increment,
    todocheck boolean default false,
    todoimport boolean default false,
    todocontents text not null,
    constraint foreign key (userid) references t_user(userid) ON DELETE CASCADE
);


create table t_timetable(
	userid varchar(300),
    timenum int primary key auto_increment,
	timetitle varchar(300),
    timestart datetime,
    timeend datetime,
timecolor varchar(100),
    constraint foreign key (userid) references t_user(userid) ON DELETE CASCADE
);


create table t_contact(
    userid varchar(300),
    contactnum int primary key auto_increment,
    contacttitle varchar(1000) not null,
    contactcontents text not null,
    contactdate datetime default now(),
    constraint foreign key(userid) references t_user(userid) ON DELETE CASCADE
);


create table t_setgoal(
	userid varchar(300),
    term date default (current_time),
	goal varchar(1000),
    goalnum int primary key auto_increment,
    constraint foreign key (userid) references t_user(userid) ON DELETE CASCADE
);

insert into t_setgoal(userid,term,goal) values('banana','2022-12-01','아침밥 챙겨먹기');
insert into t_getgoal values(10,'2022-12-01');
create table t_getgoal(
	goalnum int,
    goaldate date default (current_time),
    constraint foreign key (goalnum) references t_setgoal(goalnum) ON DELETE CASCADE
);


      


