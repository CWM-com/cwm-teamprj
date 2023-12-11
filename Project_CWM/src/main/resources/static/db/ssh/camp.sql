create database cwmdb;

create table placeinfo(
place_id int not null auto_increment,
place_code varchar(10) unique not null,
place_name varchar(20) not null,
place_addr varchar(255) not null,
place_call varchar(20) not null,
visit int,
bookmark char(1) default 'N',
star int,
regdate date,
primary key(place_id)
);

create table placefiles(
place_code varchar(10) not null,
orgName varchar(255),
savedFileName varchar(255),
savedPathName varchar(255),
savedFileSize bigint,
folderName varchar(255),
ext varchar(20),
foreign key(place_code) references placeinfo(place_code)
);

insert into placeinfo values(
null,
'101',
'서울 난지 캠핑장',
'서울특별시 마포구 상암동 495-81',
'02-304-0061',
now()
);

