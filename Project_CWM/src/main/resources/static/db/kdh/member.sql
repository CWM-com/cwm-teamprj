create table member(
idx int not null auto_increment,
user_Id varchar(20) not null,
user_Passwd varchar(20) not null,
user_Email varchar(50) not null,
user_Name varchar(10) not null,
user_Tel varchar(13) not null,
user_Regdate date,
user_Authority varchar(5),
primary key(idx)
);

insert into member values(null,'1q2w3e4r','1q2w3e4r','mails@naver.com','김길동','010-1111-1111',now(),'USER');



 -- 캠프 목록 시작 *****************
create table camp(
camp_idx int not null auto_increment,
camp_name varchar(20) not null unique,
primary key(camp_idx)
);

insert into camp values(null,'서울 난지 캠핑장'),(null,'공릉관광지 캠핑장'),(null,'용인 자연숲 캠핑장'),(null,'동강전망휴양림오토캠핑장')
,(null,'장호비치캠핑장'),(null,'정읍시 내장산 국민여가캠핑장'),(null,'변산반도국립공원 고사포 야영장'),(null,'충주카누캠핑장'),(null,'태안둘레길캠핑장')
,(null,'사천비토솔섬오토캠핑장');

 -- 캠프 목록 끝 *******************




 -- 캠프 타입 시작 **********************
create table campType(
campType_idx int not null auto_increment,
camp_name varchar(20) not null,
camp_type varchar(10) not null,
camp_price int not null,
primary key(campType_idx, camp_type),
foreign key(camp_name) references camp(camp_name) on update cascade on delete restrict,
INDEX (camp_type)
);

insert into campType values(null,'서울 난지 캠핑장','캠프-A',50000),(null,'서울 난지 캠핑장','캠프-B',50000),(null,'서울 난지 캠핑장','캠프-C',50000)
,(null,'서울 난지 캠핑장','글램핑-A',80000),(null,'서울 난지 캠핑장','글램핑-B',80000),(null,'서울 난지 캠핑장','글램핑-C',80000)
,(null,'서울 난지 캠핑장','카라반-A',100000),(null,'서울 난지 캠핑장','카라반-B',100000),(null,'서울 난지 캠핑장','카라반-C',100000);

insert into campType values(null,'공릉관광지 캠핑장','캠프-A',50000),(null,'공릉관광지 캠핑장','캠프-B',50000),(null,'공릉관광지 캠핑장','캠프-C',50000)
,(null,'공릉관광지 캠핑장','글램핑-A',80000),(null,'공릉관광지 캠핑장','글램핑-B',80000),(null,'공릉관광지 캠핑장','글램핑-C',80000)
,(null,'공릉관광지 캠핑장','카라반-A',100000),(null,'공릉관광지 캠핑장','카라반-B',100000),(null,'공릉관광지 캠핑장','카라반-C',100000);

insert into campType values(null,'용인 자연숲 캠핑장','캠프-A',50000),(null,'용인 자연숲 캠핑장','캠프-B',50000),(null,'용인 자연숲 캠핑장','캠프-C',50000)
,(null,'용인 자연숲 캠핑장','글램핑-A',80000),(null,'용인 자연숲 캠핑장','글램핑-B',80000),(null,'용인 자연숲 캠핑장','글램핑-C',80000)
,(null,'용인 자연숲 캠핑장','카라반-A',100000),(null,'용인 자연숲 캠핑장','카라반-B',100000),(null,'용인 자연숲 캠핑장','카라반-C',100000);

insert into campType values(null,'동강전망휴양림오토캠핑장','캠프-A',50000),(null,'동강전망휴양림오토캠핑장','캠프-B',50000),(null,'동강전망휴양림오토캠핑장','캠프-C',50000)
,(null,'동강전망휴양림오토캠핑장','글램핑-A',80000),(null,'동강전망휴양림오토캠핑장','글램핑-B',80000),(null,'동강전망휴양림오토캠핑장','글램핑-C',80000)
,(null,'동강전망휴양림오토캠핑장','카라반-A',100000),(null,'동강전망휴양림오토캠핑장','카라반-B',100000),(null,'동강전망휴양림오토캠핑장','카라반-C',100000);

insert into campType values(null,'장호비치캠핑장','캠프-A',50000),(null,'장호비치캠핑장','캠프-B',50000),(null,'장호비치캠핑장','캠프-C',50000)
,(null,'장호비치캠핑장','글램핑-A',80000),(null,'장호비치캠핑장','글램핑-B',80000),(null,'장호비치캠핑장','글램핑-C',80000)
,(null,'장호비치캠핑장','카라반-A',100000),(null,'장호비치캠핑장','카라반-B',100000),(null,'장호비치캠핑장','카라반-C',100000);

insert into campType values(null,'정읍시 내장산 국민여가캠핑장','캠프-A',50000),(null,'정읍시 내장산 국민여가캠핑장','캠프-B',50000),(null,'정읍시 내장산 국민여가캠핑장','캠프-C',50000)
,(null,'정읍시 내장산 국민여가캠핑장','글램핑-A',80000),(null,'정읍시 내장산 국민여가캠핑장','글램핑-B',80000),(null,'정읍시 내장산 국민여가캠핑장','글램핑-C',80000)
,(null,'정읍시 내장산 국민여가캠핑장','카라반-A',100000),(null,'정읍시 내장산 국민여가캠핑장','카라반-B',100000),(null,'정읍시 내장산 국민여가캠핑장','카라반-C',100000);

insert into campType values(null,'변산반도국립공원 고사포 야영장','캠프-A',50000),(null,'변산반도국립공원 고사포 야영장','캠프-B',50000),(null,'변산반도국립공원 고사포 야영장','캠프-C',50000)
,(null,'변산반도국립공원 고사포 야영장','글램핑-A',80000),(null,'변산반도국립공원 고사포 야영장','글램핑-B',80000),(null,'변산반도국립공원 고사포 야영장','글램핑-C',80000)
,(null,'변산반도국립공원 고사포 야영장','카라반-A',100000),(null,'변산반도국립공원 고사포 야영장','카라반-B',100000),(null,'변산반도국립공원 고사포 야영장','카라반-C',100000);

insert into campType values(null,'충주카누캠핑장','캠프-A',50000),(null,'충주카누캠핑장','캠프-B',50000),(null,'충주카누캠핑장','캠프-C',50000)
,(null,'충주카누캠핑장','글램핑-A',80000),(null,'충주카누캠핑장','글램핑-B',80000),(null,'충주카누캠핑장','글램핑-C',80000)
,(null,'충주카누캠핑장','카라반-A',100000),(null,'충주카누캠핑장','카라반-B',100000),(null,'충주카누캠핑장','카라반-C',100000);

insert into campType values(null,'태안둘레길캠핑장','캠프-A',50000),(null,'태안둘레길캠핑장','캠프-B',50000),(null,'태안둘레길캠핑장','캠프-C',50000)
,(null,'태안둘레길캠핑장','글램핑-A',80000),(null,'태안둘레길캠핑장','글램핑-B',80000),(null,'태안둘레길캠핑장','글램핑-C',80000)
,(null,'태안둘레길캠핑장','카라반-A',100000),(null,'태안둘레길캠핑장','카라반-B',100000),(null,'태안둘레길캠핑장','카라반-C',100000);

insert into campType values(null,'사천비토솔섬오토캠핑장','캠프-A',50000),(null,'사천비토솔섬오토캠핑장','캠프-B',50000),(null,'사천비토솔섬오토캠핑장','캠프-C',50000)
,(null,'사천비토솔섬오토캠핑장','글램핑-A',80000),(null,'사천비토솔섬오토캠핑장','글램핑-B',80000),(null,'사천비토솔섬오토캠핑장','글램핑-C',80000)
,(null,'사천비토솔섬오토캠핑장','카라반-A',100000),(null,'사천비토솔섬오토캠핑장','카라반-B',100000),(null,'사천비토솔섬오토캠핑장','카라반-C',100000);

 -- 캠프 타입 끝 **********************

 -- 예약현황 *************

create table reservation(
reserv_idx int not null auto_increment,
reserv_name varchar(20) not null,
reserv_tel varchar(13) not null,
camp_name varchar(20) not null,
camp_type varchar(10) not null,
check_in date not null,
check_days char(3) not null,
check_out date not null,
adult_num int not null,
teen_num int not null,
cildNum int not null,
reserv_status varchar(15),
mem_idx int not null,
imp_uid varchar(50) not null,
merchantUid varchar(50) not null,
regdate date not null,
primary key(reserv_idx),
foreign key(mem_idx) references member(idx) on update cascade on delete restrict,
foreign key(camp_name) references camp(camp_name) on update cascade on delete restrict,
foreign key(camp_type) references campType(camp_type) on update cascade on delete restrict,
foreign key(imp_uid) references payment(imp_uid) on update cascade on delete restrict
);

 -- 예약현황 *************

insert into reservation values(null,'김동현1','010-1234-1234','서울 난지 캠핑장','카라반-B','2023-12-18','2','2023-12-20',2,1,0,'Y',2);
insert into reservation values(null,'김동현2','010-1234-1234','서울 난지 캠핑장','카라반-c','2023-12-18','2','2023-12-20',2,1,0,'Y',2);
insert into reservation values(null,'김동현3','010-1234-1234','서울 난지 캠핑장','글램핑-B','2023-12-16','2','2023-12-18',2,1,0,'Y',2);
insert into reservation values(null,'김동현4','010-1234-1234','서울 난지 캠핑장','캠프-B','2023-12-16','2','2023-12-18',2,1,0,'Y',2);
insert into reservation values(null,'김동현5','010-1234-1234','서울 난지 캠핑장','캠프-A','2023-12-19','2','2023-12-21',2,1,2,'Y',2);

insert into reservation values(null,'김동현1','010-1234-1234','공릉관광지 캠핑장','카라반-B','2023-12-18','2','2023-12-20',2,1,0,'Y',2);
insert into reservation values(null,'김동현2','010-1234-1234','공릉관광지 캠핑장','카라반-c','2023-12-18','2','2023-12-20',2,1,0,'Y',2);
insert into reservation values(null,'김동현1','010-1234-1234','공릉관광지 캠핑장','캠프-C','2023-12-18','2','2023-12-20',1,1,0,'Y',2);
insert into reservation values(null,'김동현3','010-1234-1234','공릉관광지 캠핑장','글램핑-B','2023-12-16','2','2023-12-18',2,1,0,'Y',2);
insert into reservation values(null,'김동현4','010-1234-1234','공릉관광지 캠핑장','캠프-B','2023-12-16','2','2023-12-18',2,1,0,'Y',2);
insert into reservation values(null,'김동현5','010-1234-1234','공릉관광지 캠핑장','캠프-A','2023-12-19','2','2023-12-21',2,1,2,'Y',2);


-- 결제 확인 테이블 **************
create table Payment(
order_idx int not null auto_increment,
imp_uid varchar(50) not null unique,
merchant_uid varchar(50) not null,
pay_Method varchar(20) not null,
orderName varchar(50) not null,
amount varchar(20) not null,
buyer_name varchar(20) not null,
buyer_tel varchar(13) not null,
buyer_email varchar(50) not null,
payment_date dateTime,
payment_status varchar(15) not null,
mem_idx int not null,
primary key(order_idx)
);
-- 결제 확인 테이블 ****************


-- 사용가능 =>

SELECT ct.camp_type
FROM campType as ct
WHERE ct.camp_name = #{campName}
  AND ct.camp_type NOT IN (
    SELECT DISTINCT r.camp_type
    FROM reservation as r
    WHERE r.camp_name = #{campName}
      AND (
        (#{checkin} BETWEEN r.reserv_Check_in AND r.reserv_Check_out - INTERVAL 1 DAY)
        OR (#{checkout} BETWEEN r.reserv_Check_in + INTERVAL 1 DAY AND r.reserv_Check_out)
        OR (r.reserv_Check_in <= #{checkin} AND r.reserv_Check_out > #{checkout})
      )
  );


-- ********** 활용 중 ************
    SELECT ct.camp_type
FROM campType as ct
WHERE ct.camp_name = '서울 난지 캠핑장'
  AND ct.camp_type NOT IN (
    SELECT DISTINCT r.camp_type
    FROM reservation as r
    WHERE r.camp_name = '서울 난지 캠핑장' and r.reserv_status = 'Y'
      AND (
        ('2023-12-15' BETWEEN r.reserv_Check_in AND r.reserv_Check_out - INTERVAL 1 DAY)
        OR ('2023-12-17' BETWEEN r.reserv_Check_in + INTERVAL 1 DAY AND r.reserv_Check_out)
        OR (r.reserv_Check_in <= '2023-12-15' AND r.reserv_Check_out > '2023-12-17')
      ) 
  );
-- ************** 활용 중 **************

-- *********카운트 활용중 *************
  SELECT COUNT(ct.camp_type) AS camp_type
FROM campType AS ct
WHERE ct.camp_name = '서울 난지 캠핑장'
  AND ct.camp_type NOT IN (
    SELECT DISTINCT r.camp_type
    FROM reservation AS r
    WHERE r.camp_name = '서울 난지 캠핑장' and r.reserv_status = 'Y'
      AND (
        ('2023-12-12' BETWEEN r.reserv_Check_in AND r.reserv_Check_out - INTERVAL 1 DAY)
        OR ('2023-12-14' BETWEEN r.reserv_Check_in + INTERVAL 1 DAY AND r.reserv_Check_out)
        OR (r.reserv_Check_in <= '2023-12-12' AND r.reserv_Check_out > '2023-12-14')
      ) 
  );
-- ***********카운트 활용중 *************


-- 테스트 쿼리문들 --
  SELECT ct.camp_type
FROM campType as ct
WHERE ct.camp_name = '서울 난지 캠핑장'
  AND ct.camp_type NOT IN (
    SELECT DISTINCT r.camp_type
    FROM reservation as r
    WHERE r.camp_name = '서울 난지 캠핑장'
      AND (
        ('2023-12-16' BETWEEN r.reserv_Check_in AND r.reserv_Check_out - INTERVAL 1 DAY)
        OR ('2023-12-18' BETWEEN r.reserv_Check_in + INTERVAL 1 DAY AND r.reserv_Check_out)
        OR (r.reserv_Check_in <= '2023-12-16' AND r.reserv_Check_out > '2023-12-18')
      ) and r.reserv_status = 'Y'
  );

      SELECT ct.camp_type
FROM campType as ct
WHERE ct.camp_name = '서울 난지 캠핑장'
  AND ct.camp_type NOT IN (
    SELECT DISTINCT r.camp_type
    FROM reservation as r
    WHERE r.camp_name = '서울 난지 캠핑장'
      AND (
        ('2023-12-12' BETWEEN r.reserv_Check_in AND r.reserv_Check_out - INTERVAL 1 DAY)
        OR ('2023-12-14' BETWEEN r.reserv_Check_in + INTERVAL 1 DAY AND r.reserv_Check_out)
        OR (r.reserv_Check_in <= '2023-12-12' AND r.reserv_Check_out > '2023-12-14')
      )
  );

  SELECT ct.camp_type
FROM campType as ct
WHERE ct.camp_name = #{campName}
  AND ct.camp_type NOT IN (
    SELECT DISTINCT r.camp_type
    FROM reservation as r
    WHERE r.camp_name = #{campName}
      AND (
        (#{checkin} BETWEEN r.reserv_Check_in AND r.reserv_Check_out - INTERVAL 1 DAY)
        OR (#{checkout} BETWEEN r.reserv_Check_in + INTERVAL 1 DAY AND r.reserv_Check_out)
        OR (r.reserv_Check_in <= #{checkin} AND r.reserv_Check_out > #{checkout})
      )
  );

  select r.*,m.user_name from reservation r inner join member m on(m.idx = r.mem_idx);



  SELECT ct.camp_price
FROM campType as ct
WHERE ct.camp_name = #{campName}
  AND ct.camp_type NOT IN (
    SELECT DISTINCT r.camp_type
    FROM reservation as r
    WHERE r.camp_name = #{campName}
      AND (
        (#{checkin} BETWEEN r.reserv_Check_in AND r.reserv_Check_out - INTERVAL 1 DAY)
        OR (#{checkout} BETWEEN r.reserv_Check_in + INTERVAL 1 DAY AND r.reserv_Check_out)
        OR (r.reserv_Check_in <= #{checkin} AND r.reserv_Check_out > #{checkout})
      )
  );


  select p.place_code, r.* from reservation as r inner join placeinfo as p on(r.camp_name = p.place_name) where mem_idx = 1;
    