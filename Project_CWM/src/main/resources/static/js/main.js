// 해당 요소에 도달했을때 카운팅 js
const counter = ($counter, max) => {
  let now = max;

  const handle = setInterval(() => {
    $counter.innerHTML = Math.ceil(max - now);
  
    // 목표수치에 도달하면 정지
    if (now < 1) {
      clearInterval(handle);
    }
    
    // 증가되는 값이 계속하여 작아짐
    const step = now / 10;
    
    // 값을 적용시키면서 다음 차례에 영향을 끼침
    now -= step;
  }, 50);
}

let checked = document.querySelector("#checked");
let isVisible = false;


function checkvisible(checked, check = 'above') {
  // 페이지의 세로 스크롤 위치
  const scrolltop = window.scrollY;  
  // 현재 뷰포트의 세로 크기
  const viewportHeight = window.innerHeight;
  // 해당 요소의 상위 요소로부터의 세로거리(px 표시)
  const y = checked.offsetTop;
  // 해당 요소의 높이 px로 나타냄
  const elementHeight = checked.offsetHeight;

  if( check == "visible") { //  요소가 현재 화면에 보이는지 여부
    return (y < (viewportHeight + scrolltop) && y > (scrolltop - elementHeight));
  }
  if(check == "above") { // 요소가 화면 위에 위치하는지 여부 판단
    return (y < (viewportHeight + scrolltop));
  }
}

const evented = function () {
  if(!isVisible && checkvisible(checked)) {
    // alert("성공");
    // 카운트를 적용시킬 요소
    const $counter = document.querySelectorAll(".count");
  
    // 목표 수치
    const max1 = 22;
    const max2 = 40;
    const max3 = 78;
  
    setTimeout(() => counter($counter[0], max1), 800);
    setTimeout(() => counter($counter[1], max2), 800);
    setTimeout(() => counter($counter[2], max3), 800);
    isVisible = true;
  }

  if(isVisible) {
    window.removeEventListener('scroll', evented);
  }
}
window.addEventListener('scroll', evented);



// Top 버튼 js
const TopBtn = document.querySelector(".scroll-TopBtn");

 const scrollBtnMove = () => {
  window.scrollY > (window.innerHeight - window.screenY) ? TopBtn.classList.add("show") : TopBtn.classList.remove("show");
 };
 window.addEventListener('scroll', scrollBtnMove);

  function move() {
    if(window.scrollY != 0) {
      setTimeout(() => {
        // 해당 scrolly 값에서 - 100px 한만큼 위치 이동
        window.scrollTo(0,window.scrollY - 100);
        move();
      },10);
    }
  };
TopBtn.addEventListener('click', move);
  

