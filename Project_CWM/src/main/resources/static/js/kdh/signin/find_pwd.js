let userid = document.querySelector("#userid");
let userEmail = document.querySelector("#userEmail");
let certNum = document.querySelector(".cert_Num"); // 인증번호 버튼
let checkNum = document.querySelector("#checkNum");
let certifiNum;

mailSend();
function mailSend() {
    certNum.addEventListener('click', (e) => {

        e.preventDefault();

        $.ajax({
            type : "post",
            url : "/register/PasswdMailSend",
            data : {userEmail : userEmail.value},
            dataType : "json",
            success : (res) => {
                if(res != null) {
                    certifiNum = res.passwd;
                    alert("인증번호가 전송되었습니다.");
                }
            }
        });
    });
}

function checkPasswd() {

    if(userid.value == "") {
        alert("아이디를 입력해주세요.");
        userid.focus();
        return false;
    }

    if(userEmail.value == "") {
        alert("이메일을 입력해주세요.");
        userEmail.focus();
        return false;
    }

    if(checkNum.value == "") {
        alert("인증번호를 입력해주세요.");
        checkNum.focus();
        return false;
    }

    if(checkNum.value != certifiNum) {
        alert("인증번호가 일치하지 않습니다.");
        checkNum.value == "";
        checkNum.focus();
        return false;
    }
}



