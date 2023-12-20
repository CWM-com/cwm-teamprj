CREATE TABLE qna (
    id INT NOT NULL AUTO_INCREMENT,
    user_Id varchar(20) not null,
    subject varchar(255) not null,
    content text,
    regdate DATE,
    ext varchar(255),
    grp INT,
    seq INT,
    depth INT,
    PRIMARY KEY (id),
    foreign key(user_id) references member(user_id) on update cascade on delete restrict
);

create table notice(
id int not null auto_increment,
subject varchar(255) not null,
writer varchar(20) not null,
content text,
visit int,
regdate date,
orgName varchar(255),
savedFileName varchar(255),
savedFilePathName varchar(255),
savedFileSize bigint,
folderName varchar(10),
ext varchar(20),
grp int,
seq int,
depth int,
primary key(id)
);

create table review(
id int not null auto_increment,
subject varchar(255) not null,
writer varchar(20) not null,
content text,
visit int,
regdate date,
orgName varchar(255),
savedFileName varchar(255),
savedFilePathName varchar(255),
savedFileSize bigint,
folderName varchar(10),
ext varchar(20),
grp int,
seq int,
depth int,
primary key(id)
);

create table comment(
c_id int not null auto_increment,
b_id int not null,
c_writer varchar(20),
c_comment varchar(100),
c_regdate date,
primary key(c_id)
);










