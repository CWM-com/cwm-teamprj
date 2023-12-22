let cancleBtn = document.querySelectorAll(".cancle-btn");

cancleBtn.forEach((b) => {
    b.addEventListener('click', (e) => {
        e.preventDefault();

        let merchantUid = b.closest(".all-box-cotent").querySelector("#merchantUid").value;

        let changeDate = b.closest(".all-box-cotent").querySelector("#cancelDate").value;

        let reservDate = new Date(changeDate);
        let today = new Date();

        let reservDates = MaxDate(today, reservDate);
        console.log(reservDates);

        if (reservDates == 0 || reservDates > 0) {
            alert("취소 가능한 날짜가 지났습니다.");
            return false;
        } else if (confirm("해당 예약을 취소하시겠습니까?")) {
            $.ajax({
                type: "post",
                url: "/payment/cancle",
                data: { impUid: merchantUid },
                dataType: "json"
            }).done(function (data) {
                if (data.msg == "success") {
                    alert("결제취소 완료");
                } else {
                    alert("다시 확인해주세요.");
                }
            });
        }
    });
});

function MaxDate(day1, day2) {
    return Math.floor((day1 - day2) / (1000 * 60 * 60 * 24));
}