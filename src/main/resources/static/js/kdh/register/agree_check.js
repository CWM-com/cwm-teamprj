const All = document.querySelector("#checkAll");
const check = document.querySelectorAll(".check");

All.onclick = () => {
    if(All.checked == true) {
        for(let i = 0; i < check.length; i++) {
            check[i].checked = true;
        }
    }else {
        for(let i = 0; i < check.length; i++) {
            check[i].checked = false;
        }
    }
}

for(let i = 0; i < check.length; i++) {
    check[i].onclick = () => {
        if(check[i].checked == false) {
            All.checked = false;
        }
    }
}

const agreebtn1 = document.querySelector(".agree-btn_1 svg"); 
const modal = document.querySelector(".modal");
const cover = document.querySelector(".modal-content");

agreebtn1.onclick = () => {
    modal.style.display = "block";
}

modal.addEventListener('click', function(e){
    if(e.target == cover) {
        modal.style.display = "none";
    }
});
