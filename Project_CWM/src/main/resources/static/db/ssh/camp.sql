create database cwmdb;

create table placeinfo(
place_code int not null auto_increment,
place_name varchar(20) not null,
place_addrCode varchar(10) unique,
place_addrDetail varchar(255) not null,
place_call varchar(20) not null,
place_bookmark int,
place_star int,
regdate date,
primary key(place_code)
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

create table files_${placeAddrCode}(
id int not null,
orgName varchar(255),
savedFileName varchar(255),
savedPathFileName varchar(255),
savedFileSize bigint,
folderName varchar(10),
ext varchar(20)
);