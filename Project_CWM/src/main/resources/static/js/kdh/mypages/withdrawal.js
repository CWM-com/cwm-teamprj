let userId = document.querySelector("#userId");
let userPasswd = document.querySelector("#userPwd");

function InfoDelete() {

    if(userId.value == ""){
        alert("회원탈퇴를 하려면 아이디 입력이 필요합니다.")
        userId.focus();
        return false
    }

    if(userPasswd.value == ""){
        alert("회원탈퇴를 하려면 비밀번호 입력이 필요합니다.")
        userPasswd.focus();
        return false;
    }
    

}