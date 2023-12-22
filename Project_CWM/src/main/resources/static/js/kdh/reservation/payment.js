let Number = Math.floor((Math.random() * 8000) + 1000);
let today = new Date();
let date = today.toISOString();
date = date.replace(/[-T:.Z]/g, "");
let MerchantUidNum = Number.toString() + date;


let paymentBtn = document.querySelector(".payment-btn");
let totalPrice = document.querySelector("#totalPrice");
let reservName = document.querySelector("input[name=reservName]");
let reservTel = document.querySelector("input[name=reservTel]");
let reservEmail = document.querySelector("input[name=reservEmail]");
let campName = document.querySelector("#campName");
let campType = document.querySelector("#campType");
let memIdx = document.querySelector("input[name=memIdx]");
let adultNum = document.querySelector("#adultNum");
let teenNum = document.querySelector("#teenNum");
let cildNum = document.querySelector("#cildNum");git 
let checkin = document.querySelector("#checkin");
let checkdays = document.querySelector("#checkdays");
let checkout = document.querySelector("#checkout");

let orderName = campName.value + " " + campType.value;

const emailPattern = /^[0-9a-zA-Z]([-_\.]?[0-9a-zA-Z])*@[0-9a-zA-Z]([-_\.]?[0-9a-zA-Z])*\.[a-zA-Z]{2,3}$/;
const telPattern = /^(01[016789]{1})-?[0-9]{3,4}-?[0-9]{4}$/; // 010-1111-1111 형식

paymentBtn.addEventListener('click', (e) => {
    e.preventDefault();

    if (reservName.value == "") {
        alert("예약자명을 적어주세요.");
        reservName.focus();
        return false
    }

    if (reservTel.value == "") {
        alert("예약자 전화번호는 필수입니다.");
        reservTel.focus();
        return false
    }

    if (reservEmail.value == "") {
        alert("예약자 이메일을 적어주세요.");
        reservEmail.focus();
        return false
    }

    if (!telPattern.test(reservTel.value)) {
        alert("전화번호 형식을 맞춰주세요.");
        reservTel.value = "";
        return false;
    }

    if (!emailPattern.test(reservEmail.value)) {
        alert("이메일 형식을 맞춰주세요.");
        reservEmail.value = "";
        return false;
    }

    kakaopay();
    function kakaopay() {
        IMP.init("imp42081844");
        IMP.request_pay({
            pg: 'kakaopay.TC0ONETIME',
            pay_method: 'card',
            merchant_uid: MerchantUidNum,
            name: orderName,
            amount: totalPrice.value,
            buyer_email: reservEmail.value,
            buyer_name: reservName.value,
            buyer_tel: reservTel.value,
        }, function (rsp) {

            let paymentObj = {
                impUid: rsp.imp_uid,
                merchantUid: rsp.merchant_uid,
                orderName: orderName,
                amount: totalPrice.value,
                buyerName: reservName.value,
                buyerTel: reservTel.value,
                buyerEmail: reservEmail.value,
                payMethod : rsp.pay_method,
                memIdx: memIdx.value
            };

            if (rsp.success) {
                $.ajax({
                    type: "post",
                    url: "/verifyIamport/" + rsp.imp_uid
                }).done(function (data) {
                    if (rsp.paid_amount == data.response.amount) {
                        alert("결제 및 결제검증완료");

                        $.ajax({
                            type: "post",
                            url: "/payment/order",
                            data: paymentObj,
                            dataType: "json",
                        }).done(function (data) {
                            if (data.msg == "success") {
                                alert("결제완료");

                                let reservObj = {
                                    campName: campName.value,
                                    checkin: checkin.value,
                                    checkdays: checkdays.value,
                                    checkout: checkout.value,
                                    adultNum: adultNum.value,
                                    teenNum: teenNum.value,
                                    cildNum: cildNum.value,
                                    campType: campType.value,
                                    campPrice: totalPrice.value,
                                    memIdx: memIdx.value,
                                    reservName: reservName.value,
                                    reservTel: reservTel.value,
                                    impUid: rsp.imp_uid,
                                    merchantUid: rsp.merchant_uid
                                };

                                $.ajax({
                                    type: "post",
                                    url: "/reservation/reservList",
                                    data: reservObj,
                                    dataType: "json",
                                    success: (res) => {
                                        if (res.msg == "success") {
                                            alert("예약이 완료되었습니다.")
                                            location.href = "/mypage";
                                        }
                                    }
                                });
                            }
                        })
                    } else {
                        alert("결제 금액이 일치하지 않습니다. 다시 진행해주세요.");
                    }
                });
            }
        });
    }
});



