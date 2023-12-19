let cancleBtn = document.querySelectorAll(".cancle-btn");

cancleBtn.forEach((b) => {
    b.addEventListener('click', (e) => {
        e.preventDefault();

        let merchantUid = b.closest(".all-box-cotent").querySelector("#merchantUid").value;

        if (confirm("해당 예약을 취소하시겠습니까?")) {
            $.ajax({
                type: "post",
                url: "/payment/cancle",
                data: { impUid: merchantUid },
                dataType: "json"
            }).done(function (data) {
                if (data.msg == "success") {
                    alert("결제취소 완료");
                }else {
                    alert("다시 확인해주세요.");
                }
            });
        }
    });
});