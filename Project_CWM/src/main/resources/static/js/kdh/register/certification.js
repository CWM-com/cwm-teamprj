let userEmail = document.querySelector("#userEmail");
let checkNum = document.querySelector("#checkNum");
let certNum = document.querySelector(".cert_Num"); // 인증번호 전송 버튼
let certNext = document.querySelector(".cert-next"); // 다음페이지 버튼

let certifiNum;
let emailVal;
mailSend();
function mailSend() {
    certNum.addEventListener('click', (e) => {
        e.preventDefault();

        $.ajax({
            type : "post",
            url : "/register/mailsend",
            data : {userEmail : userEmail.value},
            dataType : "json",
            success : (res) => {
                if(res.result == "NOT" ) {
                    alert("이미 존재하는 이메일입니다.");
                    return false;
                }else {
                    certifiNum = res.result;
                    alert("인증번호가 전송되었습니다.");
                }
            }
        });
    });
}


function certifiNumCheck() {
    let num = document.querySelector("input[name=checkNum]");

    if(num.value === "") {
        alert("인증번호를 입력해주세요.");
        checkNum.focus();
        return false;
    }
    if(num.value !== certifiNum) {
        alert("인증번호가 일치하지 않습니다. 다시 확인해주세요.");
        checkNum.focus();
        return false;
    }else{
        alert("인증번호가 일치합니다.");
    }
}


