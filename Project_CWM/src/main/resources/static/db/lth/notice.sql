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
    visit int,
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
user_Id varchar(20) not null,
content text,
eval int not null;
camp_name not null;
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
primary key(id),
foreign key(user_id) references member(user_id) on update cascade on delete restrict
);


CREATE TABLE review (
    id INT NOT NULL AUTO_INCREMENT,
    subject VARCHAR(255) NOT NULL,
    user_id VARCHAR(20) NOT NULL,
    content TEXT,
    eval INT NOT NULL,
    visit INT,
    regdate DATE,
    orgName VARCHAR(255),
    savedFileName VARCHAR(255),
    savedFilePathName VARCHAR(255),
    savedFileSize BIGINT,
    folder_name VARCHAR(10),
    ext VARCHAR(20),
    grp INT,
    seq INT,
    depth INT,
    PRIMARY KEY (id),
    FOREIGN KEY (user_id) REFERENCES member(user_id) ON UPDATE CASCADE ON DELETE RESTRICT
);


create table comment(
c_id int not null auto_increment,
b_id int not null,
c_writer varchar(20),
c_comment varchar(100),
c_regdate date,
primary key(c_id)
);










