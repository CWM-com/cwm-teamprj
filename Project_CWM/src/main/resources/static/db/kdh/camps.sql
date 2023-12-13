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
0,
0,
0,
now()
);

insert into placeinfo values(
null,
'102',
'서울 충주 캠핑장',
'서울특별시 강남 무슨동 495-81',
'02-304-0061',
0,
0,
0,
now()
);

insert into placeinfo values(
null,
'201',
'경기 수원 캠핑장',
'수원시 수원 512-81',
'162-642-1232',
0,
0,
0,
now()
);

insert into placeinfo values(
null,
'202',
'경기 성남 캠핑장',
'성남시 성남 232-81',
'843-231-5121',
0,
0,
0,
now()
);

insert into placeinfo values(
null,
'303',
'부산 광안 캠핑장',
'부산광역시 광안 613-81',
'055-642-5133',
0,
0,
0,
now()
);