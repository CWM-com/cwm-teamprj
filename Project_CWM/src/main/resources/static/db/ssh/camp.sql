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
fileType varchar(10),
orgName varchar(255),
savedFileName varchar(255),
savedPathName varchar(255),
savedFileSize bigint,
folderName varchar(255),
ext varchar(20),
foreign key(place_code) references placeinfo(place_code)
);

create table placecoor(
place_code varchar(10) not null,
place_addr varchar(255),
place_name varchar(255),
place_x float,
place_y float,
foreign key(place_code) references placeinfo(place_code)
);

create table placecontent(
place_code varchar(10) not null,
place_content1 varchar(255),
place_content2 varchar(255),
place_content3 varchar(255),
place_content4 varchar(255),
foreign key(place_code) references placeinfo(place_code)
);

create table placebookmark(
place_code varchar(10) not null,
idx int not null,
per_bookmark int,
per_star int,
foreign key(place_code) references placeinfo(place_code),
foreign key(idx) references member(idx)
);


