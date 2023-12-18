// tab 메뉴 클릭시 변경
const tabList = document.querySelectorAll(".tabnav li");
const content = document.querySelector(".tab .tab-container .tab-content");

for(let i = 0; i < tabList.length; i++) {
    tabList[i].addEventListener('click', () => {
        
        for(let j = 0; j < tabList.length; j++) {
            tabList[j].classList.remove("on");
            tabList[j].querySelector("a").style.color = "#7c7c7c";
        }
        tabList[i].classList.add("on");
        tabList[i].querySelector("a").style.color = "#000";
    });
}