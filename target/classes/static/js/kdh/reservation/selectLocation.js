// 캠핑장 선택 시 border 바뀌기
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
 // 캠핑장 선택 시 border 바뀌기 끝
 

 // content의 적힌 값 불러오기 되긴하나 일단 보류

const locationBtn = document.querySelector(".location-btn");
const campName = document.querySelector("#campName");
const locationList = document.querySelector(".location-List");
const closeBtn = document.querySelector(".close-btn");
// locationBtn.addEventListener('click', () => {

//     Lname.forEach((l) => {
//         l.addEventListener('click', () => {
           
//             let clickedValue = l.textContent;

//             document.getElementById("campName").value = clickedValue.trim();
//         });
//     });
// });
campName.addEventListener('click',() => {
    locationList.style.display = "block";
    Calendarbox.style.display = "none";
});
// 선택하지 않고 close
closeBtn.onclick = () => {
    locationList.style.display = "none";
}
// 선택 후 close
locationBtn.addEventListener('click',() => {
    locationList.style.display = "none";
})


// 달력 부분
let chkDate = document.querySelectorAll("input[name=checkYmd]");
let Calendar= document.querySelector(".Calendar");
let Calendarbox = document.querySelector(".Calendar-box");

for(let i = 0; i < chkDate.length; i++) {
    chkDate[i].addEventListener('click',() => {
        Calendarbox.style.display = "block";
        locationList.style.display = "none";
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


// **************** 여기 밑부터 코드도 잘못짠거같고 아직 해답도 모르겟음 ****************
let checkinYmd = null;
let checkoutYmd = null;

let checkinDate = document.querySelector("#checkinYmd");
let checkoutDate = document.querySelector("#checkoutYmd");




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
                console.log(ckin);
                console.log(ckout);
                if (ckin >= ckout) {
                    alert("체크아웃 날짜가 더 빠릅니다.");
                    checkoutDate.value = "";
                    return false;
                }
                if (MaxDate(ckin, ckout) > 2) {
                    alert("캠핑은 최대 2박3일까지 가능합니다.");
                    checkoutDate.value = "";
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
        createCalendar();
        Calendarbox.style.display = "none";
    });

// 달이나 일이 한 자리수 일때 앞에 0을 추가하여 출력
function DateToVal(year, month, day) {
    month = (month < 10) ? "0" + month : month;
    day = (day < 10) ? "0" + day : day;
    return year + "-" + month + "-" + day;
}

// day2,day1을 나눠서 일 수 구하기
function MaxDate(day1,day2) {
    return Math.floor((day2 - day1) / (1000 * 60 * 60 * 24));
}




