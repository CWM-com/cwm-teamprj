//북마크
function toggleImageClass(icon) {
    // 이미지에 'selected' 클래스가 있는지 확인
    if (icon.classList.contains('active')) {
        // 이미지에 'selected' 클래스가 있으면 제거
        icon.classList.remove('active');
    } else {
        // 이미지에 'selected' 클래스가 없으면 추가
        icon.classList.add('active');
    }
}

// 별점매기기
let currentRating = 0;
function setRating(rating) {
    // 현재 선택된 별점을 업데이트
    if (rating === currentRating) {
        // 같은 별을 다시 클릭하면 별점 초기화
        rating = 0;
    }
    currentRating = rating;
    // 모든 별에 대한 활성화 클래스 제거
    const stars = document.querySelectorAll('.star');
    stars.forEach(star => star.classList.remove('active'));

    // 선택된 별점까지 활성화 클래스 추가
    for (let i = 0; i < rating; i++) {
        stars[i].classList.add('active');
    }
    // 여기에서 서버로 현재 별점을 전송할 수 있습니다.
    // 예: fetch 또는 AJAX 요청을 사용하여 서버에 데이터를 전송
    // sendDataToServer(currentRating);
}

//슬라이드
$(document).ready(function(){
    var currentPic = 1;
    var totalPics = $(".pic00-list div").length;

    function showPic(index) {
        $(".pic00-list div").removeClass("visible").addClass("hidden");
        $(".pic00-list .pic0" + index).removeClass("hidden").addClass("visible");
    }

    $(".left-arrow").click(function(){
        currentPic = (currentPic === 1) ? totalPics : currentPic - 1;
        showPic(currentPic);
    });

    $(".right-arrow").click(function(){
        currentPic = (currentPic === totalPics) ? 1 : currentPic + 1;
        showPic(currentPic);
    });

    // 자동 슬라이드 (5초 간격)
    setInterval(function() {
        currentPic = (currentPic === totalPics) ? 1 : currentPic + 1;
        showPic(currentPic);
    }, 5000);
});
