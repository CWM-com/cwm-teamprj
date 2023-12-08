create database cwmdb;

create table placeinfo(
place_id int not null auto_increment,
place_code varchar(10) unique not null,
place_name varchar(20) not null,
place_addr varchar(255) not null,
place_call varchar(20) not null,
visit int,
bookmark int,
star int,
regdate date,
primary key(place_id)
);

insert into placeinfo values(
null,
'101',
'서울 난지 캠핑장',
'서울특별시 마포구 상암동 495-81',
'02-304-0061',
now()
);

insert into placeinfo values(
null,
"201",
"부산 대저 캠핑장",
"부산광역시 강서구 대저1동 1-12",
"051-941-0957",
0,
0,
now()
);