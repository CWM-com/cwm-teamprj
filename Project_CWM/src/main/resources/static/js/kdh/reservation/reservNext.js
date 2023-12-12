
function FirstOrderCheck() {

    let camptype = document.querySelector("input[name=campType]");
    let campPrice = document.querySelector("input[name=campPrice]");
    let idx = document.querySelector("input[name=idx]");
    let campName = document.querySelector("input[name=campName]");
    let checkin = document.querySelector("input[name=checkin]");
    let Days = document.querySelector("input[name=checkdays]");
    let checkout = document.querySelector("input[name=checkout]");
    let adultNum = document.querySelector("input[name=adultNum]");
    let teenNum = document.querySelector("input[name=teenNum]");
    let cildNum = document.querySelector("input[name=cildNum]");

    let today = new Date();

    let d = DateToVal(today.getFullYear(),today.getMonth()+1,today.getDate());

    if(campName.value == "") {
        alert("캠핑장을 선택해주세요.");
        return false;
    }

    if(checkin.value == "") {
        alert("체크인 날짜를 선택해주세요.");
        return false;
    }

    if(checkin.value == d) {
        alert("당일 예약은 직접 문의가 필요합니다.");
        return false;
    }

    if(checkout.value == "") {
        alert("체크아웃 날짜를 선택해주세요.");
        return false;
    }
    adultNum = parseInt(adultNum.value);
    if(adultNum == 0) {
        alert("성인 1명 이상 필수 동반되어야합니다.");
        return false;
    }

    if(camptype.value == "" && campPrice.value == "") {
        alert("캠핑 종류를 선택해주세요.");
        return false;
    }

    function DateToVal(year, month, day) {
        month = (month < 10) ? "0" + month : month;
        day = (day < 10) ? "0" + day : day;
        return year + "-" + month + "-" + day;
    }
}