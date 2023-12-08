const userName = document.querySelector("#userName");
const userEmail = document.querySelector("#userEmail");
const idBtn = document.querySelector(".id-btn");


function checkId() {
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
}


