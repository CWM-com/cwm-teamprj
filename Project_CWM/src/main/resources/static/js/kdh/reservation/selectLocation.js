// 캠핑장 선택 시작
let Lname = document.querySelectorAll(".l-name");

for(let i = 0; i < Lname.length; i++) {
    Lname[i].addEventListener('click',() => {

        Lname[i].classList.add("add");

        for(let j = 0; j < Lname.length; j++) {
            
            if(j !== i) {
                Lname[j].classList.remove("add");
            }
        }
    });
 }

const locationBtn = document.querySelector(".location-btn");
const campName = document.querySelector("#campName");
const locationList = document.querySelector(".location-List");
const closeBtn = document.querySelector(".close-btn");

for(let i = 0; i < Lname.length; i++) {
    Lname[i].addEventListener('click', () => {
        campName.value = Lname[i].innerHTML.trim();
    });
}

campName.addEventListener('click',() => {
    locationList.style.display = "block";
    Calendarbox.style.display = "none";
    document.querySelector(".count-content").style.display = "none";
});
// 선택하지 않고 close
closeBtn.onclick = () => {
    locationList.style.display = "none";
}
// 선택 후 close
locationBtn.addEventListener('click',() => {
    locationList.style.display = "none";
})

 // 캠핑장 선택 부분 끝


// 달력 부분 시작
let chkDate = document.querySelectorAll("input[name=checkYmd]");
let Calendar= document.querySelector(".Calendar");
let Calendarbox = document.querySelector(".Calendar-box");

for(let i = 0; i < chkDate.length; i++) {
    chkDate[i].addEventListener('click',() => {
        Calendarbox.style.display = "block";
        locationList.style.display = "none";
        document.querySelector(".count-content").style.display = "none";
    });
}

let nowDate = new Date();
let today = new Date();
today.setHours(0,0,0,0);

const calYear = document.querySelector("#calYear");
const calMonth = document.querySelector("#calMonth");
const DateList = document.querySelector(".Date-List");

createCalendar();
function createCalendar() { // 달력 생성 함수
    
    let firstDate = new Date(nowDate.getFullYear(), nowDate.getMonth(), 1); // 해당 월의 처음날 생성
    let lastDate = new Date(nowDate.getFullYear(), nowDate.getMonth()+1, 0); // 해당 월의 마지막날 생성
    
    calYear.innerHTML = nowDate.getFullYear(); // 년도 span에 현재 년도 추가
    calMonth.innerHTML = nowDate.getMonth() +1; // 달 span에 현재 달 추가

    while(DateList.rows.length > 0) {
        DateList.deleteRow(DateList.rows.length -1);
    }

    let nowRows = DateList.insertRow();  // tr 추가 생성

    for(let i = 0; i < firstDate.getDay(); i++) { 
        let nowColumn = nowRows.insertCell();  // td 추가
    }

    for(let newDay = firstDate; newDay <= lastDate; newDay.setDate(newDay.getDate() + 1 )) {
        let nowColumn = nowRows.insertCell(); 

        let newNum = document.createElement("p");
        newNum.innerHTML = newDay.getDate();
        nowColumn.appendChild(newNum); // innerHtml 한 속성 값을 만들어논 cell에 추가

        if(newDay.getDay()== 6) { // 6인 경우는 토요일이다. 그러므로 토요일이면 새로 tr 추가하여 거기 입력
            nowRows = DateList.insertRow();
        }

        if(newDay < today) {
            newNum.classList.add("past");  // 지난 날짜는 선택 불가
            newNum.title = "예약불가일자";
        }else if(newDay.getFullYear() == today.getFullYear() && newDay.getMonth() == today.getMonth() && newDay.getDate() == today.getDate()) {
            newNum.classList.add("today"); // 오늘 날짜를 표시 해줌
            newNum.title = "당일";
        }else {
            newNum.classList.add("after");
            newNum.title = "예약가능일자";
        }
    }
    selectCheckin();
}


let standardDate = new Date(); // 기준 달력 생성
standardDate = new Date(standardDate.getFullYear(), standardDate.getMonth(), standardDate.getDate());
// 이전달 가기
function prevBtn() {
    nowDate = new Date(nowDate.getFullYear(),nowDate.getMonth()-1, nowDate.getDate());

    // nowDate가 standardDate보다 작거나 같은 경우에는 이전버튼이 안보이게 설정
    if(standardDate >= nowDate) { 
        document.querySelector(".prev-btn").style.visibility = "hidden";
    }
    document.querySelector(".next-btn").style.visibility = "visible";

    createCalendar();
}

// 다음달 가기
function nextBtn() {
    nowDate = new Date(nowDate.getFullYear(),nowDate.getMonth()+1, nowDate.getDate());

    // nowDate가 standardDate보다 같거나 클 경우에는 prevBtn이 보이게 설정
    if(standardDate <= nowDate) {
        document.querySelector(".prev-btn").style.visibility = "visible";
    }

    // standardDate과 달의 차이가 2개월이 넘어가면 nextBtn이 보이지 않게 설정
    if(DateGap(standardDate,nowDate) >= 2) {
        document.querySelector(".next-btn").style.visibility = "hidden";
    }
    createCalendar();
}

// 개월 차이 계산
function DateGap(Date1,Date2) {
    return (Date2.getFullYear() - Date1.getFullYear()) * 12 + Date2.getMonth() - Date1.getMonth();
}

let checkinYmd = null;
let checkoutYmd = null;

let checkinDate = document.querySelector("#checkinYmd");
let checkoutDate = document.querySelector("#checkoutYmd");
let days = document.querySelector("#days");



function selectCheckin() {
    let newNums = document.querySelectorAll(".Date-List p");

    for (let i = 0; i < newNums.length; i++) {
        newNums[i].addEventListener('click', (e) => {
            e.preventDefault();

            checkoutDate.value = "";  // 체크인 날짜 선택 시 체크아웃 날짜 초기화

            let lastDate = new Date(nowDate.getFullYear(), nowDate.getMonth(), newNums[i].innerHTML);

            if (lastDate < today) {
                return false;
            }

            if (checkinYmd == null) {
                checkinYmd = newNums[i].innerHTML;
                checkinDate.value = DateToVal(nowDate.getFullYear(), nowDate.getMonth() + 1, checkinYmd);
            } else if (checkoutYmd == null && checkinYmd != newNums[i].innerHTML) {
                checkoutYmd = newNums[i].innerHTML;
                checkoutDate.value = DateToVal(nowDate.getFullYear(), nowDate.getMonth() + 1, checkoutYmd);

                let ckin = new Date(checkinDate.value);
                let ckout = new Date(checkoutDate.value);
                let daysNum = MaxDate(ckin,ckout);

                days.value = daysNum
            
                if (ckin >= ckout) {
                    alert("체크아웃 날짜가 더 빠릅니다.");
                    checkoutDate.value = "";
                    days.value = 1;
                    return false;
                }
                if (MaxDate(ckin, ckout) > 2) {
                    alert("캠핑은 최대 2박3일까지 가능합니다.");
                    checkoutDate.value = "";
                    days.value = 1;
                    return false;
                }
            } else {
                checkinYmd = newNums[i].innerHTML;
                checkoutYmd = null;
                checkinDate.value = DateToVal(nowDate.getFullYear(), nowDate.getMonth() + 1, checkinYmd);
                checkoutDate.value = "";
            }
        });
    }
}

let dateBtn = document.querySelector(".Date-Btn");
    dateBtn.addEventListener('click', () => {

        let today = new Date();
        today.setHours(0, 0, 0, 0);

        let todayCheck = new Date(checkinDate.value);
        if (todayCheck.getFullYear() == today.getFullYear() && todayCheck.getMonth() == today.getMonth() && todayCheck.getDate() == today.getDate()) {
            alert("당일 예약은 직접문의가 필요합니다.");
            return false;
    }else {
        createCalendar();
        Calendarbox.style.display = "none";
    }
});

// 달이나 일이 한 자리수 일때 앞에 0을 추가하여 출력
function DateToVal(year, month, day) {
    month = (month < 10) ? "0" + month : month;
    day = (day < 10) ? "0" + day : day;
    return year + "-" + month + "-" + day;
}

// day2,day1을 나눠서 일 수 구하기 1회 최대 ?박 정하는데에 사용
function MaxDate(day1,day2) {
    return Math.floor((day2 - day1) / (1000 * 60 * 60 * 24));
}
/* 달력 끝 */


/* 인원 수 체크 시작 */
const cnt = document.querySelectorAll(".cnt");
const countType1 = document.querySelector("#count-type-1");
const countType2 = document.querySelector("#count-type-2");
const countType3 = document.querySelector("#count-type-3");
const countCloseBtn = document.querySelector(".count-close-btn");

let quantity = document.querySelectorAll(".quantity");
let countBtn = document.querySelector(".count-btn");

for(let i = 0; i < quantity.length; i++) {
    quantity[i].onclick = () => {
        document.querySelector(".count-content").style.display = "block";
        Calendarbox.style.display = "none";
        locationList.style.display = "none";
    }
}

countBtn.onclick = () => {
    document.querySelector(".count-content").style.display = "none";
}


let cntA = 0;
let cntB = 0;
let cntC = 0;

function downCnt(type) {
    if(type == "downAdult") {
        cntA--;
        countType1.value = cntA;
        if(cntA < 0) {
            cntA = 0;
            countType1.value = cntA;
        }
    }else if(type == "downTeen") {
        cntB--;
        countType2.value = cntB;
        if(cntB < 0) {
            cntB = 0;
            countType2.value = cntB;
        }
    }else if(type == "downCild") {
        cntC--;
        countType3.value = cntC;
        if(cntC < 0) {
            cntC = 0;
            countType3.value = cntC;
        }
    }
}


function upCnt(type) {
  if(type == "plusAdult") {
    cntA++;
    countType1.value = cntA;
    if(cntA > 5) {
        cntA = 5;
        countType1.value = cntA;
        alert("최대 인원 수는 5명입니다.")

    }
  }else if(type == "plusTeen") {
    cntB++;
    countType2.value = cntB;
    if(cntB > 5) {
        cntB = 5;
        countType2.value = cntB;
        alert("최대 인원 수는 5명입니다.")
    }
  }else if(type == "plusChild") {
    cntC++;
    countType3.value = cntC;
    if(cntC > 5) {
        cntC = 5;
        countType3.value = cntC;
        alert("최대 인원 수는 5명입니다.")
    }
  }
}

let adult = document.querySelector("#adult");
let Teen = document.querySelector("#Teen");
let child = document.querySelector("#child");

checkPeople();
function checkPeople() {

    countCloseBtn.addEventListener('click', () => {

        let countType1Val = parseInt(countType1.value,10);
        let countType2Val = parseInt(countType2.value,10);
        let countType3Val = parseInt(countType3.value,10);
        
        if(countType1Val + countType2Val + countType3Val > 5) {
            alert("전체 인원 제한은 최대 5명입니다.")
        }else if(countType1Val + countType2Val + countType3Val <= 5 && countType1Val >= 1) {
            adult.value = countType1Val;
            Teen.value = countType2Val;
            child.value = countType3Val;

            document.querySelector(".count-content").style.display = "none";
        }else if(countType1Val < 1 ) {
            alert("성인 1명 이상 필수 동반되어야합니다.");
        }
    });
}

/* 인원 수 체크 끝 */

/* 캠핑 종류 선택 시작 */
let campBox = document.querySelectorAll(".camp-box");
let chooseActive = document.querySelector(".choose-active");

for(let i = 0; i < campBox.length; i++) {
    campBox[i].addEventListener('click', () => {
        campBox[i].classList.add("choose-active");
    });
    for(let j = 0; j < campBox.length; j++) {
        campBox[j].addEventListener('click', () => {
            campBox[i].classList.remove("choose-active");
            campBox[j].classList.add("choose-active");
        });
    }
}

/* 캠핑 종류 선택 끝 */

/* 다음 페이지 이동 */

let reservBtn = document.querySelector(".reserv-btn");

reservBtn.addEventListener('click', () => {
    location.href = "/reservation/reservationcheck";
});



