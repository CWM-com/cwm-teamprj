create table member(
idx int not null auto_increment,
user_Id varchar(20) not null,
user_Passwd varchar(20) not null,
user_Email varchar(50) not null,
user_Name varchar(10) not null,
user_Tel varchar(13) not null,
user_Regdate date,
primary key(idx)
);

create table campType(
camp_name varchar(10) not null,
camp_price int not null,
place_code
);

서울 텐트1  1500
서울 텐트2  1500
서울 텐트3  1500
서울 글램핑1 1500
서울 글램핑2 1500
서울 카라반1 3000

부산 텐트1  1500
부산 텐트2  1500
부산 글램핑1 2000

create table reservation(
reserv_idx int not null auto_increment,
reserv_Check_In,
reserv_Check_Out,
place_name,
camp_name,
reserv_people_Num,
user_Name,
user_Tel,
reserv_price
);
+ 예약 여부
campType ,member












certNext.addEventListener('click', (e) => {

    e.preventDefault();
    if (checkNum.value == certifiNum) {
        alert("인증번호가 일치합니다.");
        $.ajax({
            type : "post",
            url : "/register/emailval",
            data : {userEmail : userEmail.value},
            dataType : "json",
            success : (res) => {
                if(res.res == userEmail.value) {
                    location.href = "register/signup?userEmail="+ userEmail.value;
                }
            }
        });
        
    }else {
        alert("인증번호가 일치하지 않습니다. 다시 확인해주세요.");
        checkNum.value = "";
        checkNum.focus();
        return false;
    }
});