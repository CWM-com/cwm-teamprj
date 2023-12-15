// tab 메뉴 클릭시 변경
const tabList = document.querySelectorAll(".tab .tabnav li");
const content = document.querySelectorAll(".tab .tab-container .tab-content");

for(let i = 0; i < tabList.length; i++) {
    tabList[i].querySelector("a").addEventListener('click', (e) => {
        e.preventDefault();

        for(let j = 0; j < tabList.length; j++) {
            tabList[j].classList.remove("on");
            tabList[j].querySelector("a").style.color = "#7c7c7c";
            content[j].style.display = "none";
        }
        tabList[i].classList.add("on");
        tabList[i].querySelector("a").style.color = "#000";
        content[i].style.display = "block";
    });
}