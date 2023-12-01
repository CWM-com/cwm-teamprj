create database cwmdb;

create table placeinfo(
placeId int not null auto_increment,
placeName varchar(20) not null,
placeAddrCode int not null,
placeAddrDetail varchar(255) not null,
placeCall varchar(20) not null,
placeCost int,
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
primary key(place_id)
);