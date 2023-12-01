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
picThumbName varchar(255),
picDetailName1 varchar(255),
picDetailName2 varchar(255),
picDetailName3 varchar(255),
picAroundName1 varchar(255),
picAroundName2 varchar(255),
picAroundName3 varchar(255),
picAroundName4 varchar(255),
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
now(),
"camp-thumb-thumb",
"camp1-detail",
"camp2-detail",
"camp3-detail",
"around1-detail",
"around2-detail",
"around3-detail",
"around4-detail"
);