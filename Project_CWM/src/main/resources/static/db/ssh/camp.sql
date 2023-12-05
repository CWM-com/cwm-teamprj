create database cwmdb;

create table placeinfo(
placeCode int not null auto_increment,
placeName varchar(20) not null,
placeAddrCode varchar(10) not null,
placeAddrDetail varchar(255) not null,
placeCall varchar(20) not null,
placeBookmark int,
placeStar int,
regdate date,
primary key(placeCode)
);

insert into placeinfo values(
null,
"서울 난지 캠핑장",
"02",
"서울특별시 마포구 상암동 495-81",
"02-304-0061",
0,
0,
now()
);

insert into placeinfo values(
null,
"부산 대저 캠핑장",
"051",
"부산광역시 강서구 대저1동 1-12",
"051-941-0957",
0,
0,
now()
);