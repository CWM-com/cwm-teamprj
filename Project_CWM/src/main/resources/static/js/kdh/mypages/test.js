let updateBtn = document.querySelector(".update-btn");
let userPasswd = document.querySelector("input[name=userPasswd]");
let userTel = document.querySelector("input[name=userTel]");
let idx = document.querySelector("#idx");

updateBtn.addEventListener('click', (e) => {
    e.preventDefault();

    InfoUpdate();
    function InfoUpdate() {

        const pwdPattern = /^(?=.*\d)(?=.*[a-zA-Z])[0-9a-zA-Z]{8,16}$/; // 영문 숫자 조합 8자리 이상
        const telPattern = /^(01[016789]{1})-?[0-9]{3,4}-?[0-9]{4}$/; // 010-1111-1111 형식

        if (userPasswd.value == "") {
            alert(" 변경할 비밀번호를 입력해주세요.");
            userPasswd.focus();
            return false;
        }

        if (userTel.value == "") {
            alert(" 변경할 전화번호를 입력해주세요.");
            userTel.focus();
            return false;
        }

        if (!pwdPattern.test(userPasswd.value)) {
            alert("비밀번호 형식을 맞춰주세요.");
            userPasswd.value = "";
            return false;
        }

        if (!telPattern.test(userTel.value)) {
            alert("전화번호 형식을 맞춰주세요.");
            userTel.value = "";
            return false;
        }

        if(confirm("회원정보를 변경하시겠습니까?")) {
            $.ajax({
                type: "post",
                url: "/mypage/infoUpdate",
                data: { userPasswd: userPasswd.value, userTel: userTel.value, idx: idx.value },
                dataType: "json",
                success: (res) => {
                    if (res.msg == "success") {
                        alert("회원 정보가 변경되었습니다.");
                        location.href = "/mypage";
                    }
                },
                error: () => {
                    alert("회원 정보 변경에 실패했습니다. 다시 확인해주세요.");
                    return location.href = "/mypage/userinfo";
                }
            });
        }
    }

});


function passwdCkeck() {

    let userPasswd = document.querySelector("input[name=userPasswd]");
    let checkPwd = document.querySelector("input[name=checkPwd]");
    let pwdText = document.querySelector(".pwd-text");
    if (userPasswd.value != checkPwd.value) {
        checkPwd.classList.add("change-1");
        checkPwd.classList.remove("change-2");
        pwdText.innerHTML = "<p>*비밀번호가 일치하지않습니다.</p>";
        return false;
    } else if (userPasswd.value == checkPwd.value) {
        checkPwd.classList.add("change-1");
        checkPwd.classList.remove("change-2");
        pwdText.innerHTML = "<p>*비밀번호가 일치합니다.</p>";
        if (userPasswd.value == "" || checkPwd.value == "") {
            checkPwd.classList.add("change-2");
            pwdText.innerHTML = "";
        }
    }
}