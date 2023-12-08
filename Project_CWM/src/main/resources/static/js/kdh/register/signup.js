let signupCheck = document.querySelector(".signup-check");

let userName = document.querySelector("#userName");
let userEmail = document.querySelector("#userEmail");
let userId = document.querySelector("#userId");
let userPasswd = document.querySelector("#userPasswd");
let checkPwd = document.querySelector("#checkPwd");
let userTel = document.querySelector("#userTel");
let check1 = document.querySelector("#check_1");
let check2 = document.querySelector("#check_2");
let check3 = document.querySelector("#check_3");

let checked = document.querySelector("#checked");
let pwdText = document.querySelector(".pwd-text");

const emailPattern = /^[0-9a-zA-Z]([-_\.]?[0-9a-zA-Z])*@[0-9a-zA-Z]([-_\.]?[0-9a-zA-Z])*\.[a-zA-Z]{2,3}$/;
const idPattern = /^[a-zA-Z][0-9a-zA-Z]{5,19}$/;
const pwdPattern = /^(?=.*\d)(?=.*[a-zA-Z])[0-9a-zA-Z]{8,16}$/; // 영문 숫자 조합 8자리 이상
const telPattern = /^(01[016789]{1})-?[0-9]{3,4}-?[0-9]{4}$/; // 010-1111-1111 형식

/* 회원가입 시작 */
signup();
function signup() {
    signupCheck.addEventListener('click', (e) => {
        e.preventDefault();

        let obj = {
            userId : userId.value,
            userPasswd : userPasswd.value,
            userEmail : userEmail.value,
            userName : userName.value,
            userTel : userTel.value
        };

        if(!emailPattern.test(userEmail.value)) {
            alert("이메일 형식을 맞춰주세요.");
            userEmail.value = "";
            return false;
        }

        if(!idPattern.test(userId.value)) {
                    alert("아이디 형식을 맞춰주세요.");
                    userId.value = "";
                    return false;
        }

        if(!pwdPattern.test(userPasswd.value)) {
                    alert("비밀번호 형식을 맞춰주세요.");
                    userPasswd.value = "";
                    return false;
        }

        if(!telPattern.test(userTel.value)) {
                    alert("전화번호 형식을 맞춰주세요.");
                    userTel.value = "";
                    return false;
        }

        if(userName.value == "") {
            alert("이름을 입력해주세요.");
            userName.focus();
            return false;
        }

        if(userEmail.value == "") {
            alert("이메일을 입력해주세요.");
            userEmail.focus();
            return false;
        }

        if(userId.value == "") {
            alert("아이디를 입력해주세요.");
            userId.focus();
            return false;
        }

        if(userPasswd.value == "") {
            alert("비밀번호를 입력해주세요.");
            userPasswd.focus();
            return false;
        }

        if(checkPwd.value == "") {
            alert("비밀번호를 재입력해주세요.");
            checkPwd.focus();
            return false;
        }

        if(userPasswd.value != checkPwd.value) {
            alert("비밀번호가 다릅니다.");
            return false;
        }

        if(userTel.value == "") {
            alert("전화번호를 입력해주세요.");
            userTel.focus();
            return false;
        }

        if(check1.checked != true || check2.checked != true || check3.checked != true) {
            alert("체크 여부를 확인해주세요.");
            return false;
        }

        if(checked.value != "Y") {
            alert("아이디 중복체크를 해주세요.");
            userId.focus();
            return false;
        }

        $.ajax({
            type : "post",
            url : "/register/signup",
            data : obj,
            dataType : "json",
            success : (res) => {
                let result = res.res;
                if(result == "success") {
                    alert("회원가입 완료");
                    location.href = "/index";
                }
            },
            error : (e) => {
                alert("회원가입 정보를 다시 확인해주세요.");
                return false;
            }
        });
    });
}
/* 회원가입 끝 */

/* 비밀번호 동일 여부 확인 시작 */
function passwdCkeck() {
            if(userPasswd.value != checkPwd.value) {
                checkPwd.classList.add("change-1");
                checkPwd.classList.remove("change-2");
                pwdText.innerHTML = "<p>*비밀번호가 일치하지않습니다.</p>";
                return false;
            }else if(userPasswd.value == checkPwd.value) {
                checkPwd.classList.add("change-1");
                checkPwd.classList.remove("change-2");
                pwdText.innerHTML = "<p>*비밀번호가 일치합니다.</p>";
                if(userPasswd.value == "" || checkPwd.value == "") {
                    checkPwd.classList.add("change-2");
                    pwdText.innerHTML = "";
                }
            }
}
/* 비밀번호 동일 여부 확인 끝 */

/* 아이디 체크 시작 */
idcheked();
function idcheked() {
    let idCk = document.querySelector(".Id-check");
    console.log(userEmail.value);
    idCk.addEventListener('click', (e) => {
            e.preventDefault();

            if(userId.value == "") {
                alert("아이디를 입력해주세요.");
                return false;
            }

            $.ajax({
                type : "post",
                url : "/register/idcheck",
                data : {userId : userId.value},
                dataType : "json",
                success : (res) => {
                    if(res.cnt == 1) {
                        alert("이미 존재하는 아이디입니다.");
                        checked.value = "N";
                        userId.focus();
                        return false;
                    }else {
                        alert("사용 가능한 아이디입니다.");
                        checked.value = "Y";
                    }
                }
            });
        });
}
/* 아이디 체크 끝 */







