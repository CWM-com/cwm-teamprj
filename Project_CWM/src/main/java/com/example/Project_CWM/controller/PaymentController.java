package com.example.Project_CWM.controller;

import com.example.Project_CWM.dto.MemberDto;
import com.example.Project_CWM.dto.PaymentDto;
import com.example.Project_CWM.mappers.PaymentMapper;
import com.example.Project_CWM.mappers.ReservationMapper;
import com.example.Project_CWM.service.PaymentService;
import com.siot.IamportRestClient.IamportClient;
import com.siot.IamportRestClient.exception.IamportResponseException;
import com.siot.IamportRestClient.request.CancelData;
import com.siot.IamportRestClient.response.IamportResponse;
import com.siot.IamportRestClient.response.Payment;
import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

@Controller
public class PaymentController {

    private IamportClient iamportClient;
    @Autowired
    private PaymentService paymentService;
    @Autowired
    private PaymentMapper paymentMapper;
    @Autowired
    private ReservationMapper reservationMapper;

    public PaymentController() {
        this.iamportClient = new IamportClient("3260770725002265","slRj2br5QBKt1cnKgeCIcvH6x5V0YbexX7jgkXAeDKhDVanqpVDREDxGgDf7NCRAkLyB0kRQksEtcvmL");
    }

    @ResponseBody
    @RequestMapping("/verifyIamport/{imp_uid}")
    public ResponseEntity<IamportResponse<Payment>> paymentByImpUid(@PathVariable(value = "imp_uid") String imp_uid) {

        try {
            if (imp_uid.isEmpty()) {
                return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
            } else {
                IamportResponse<Payment> res = iamportClient.paymentByImpUid(imp_uid);
                return new ResponseEntity<>(res, HttpStatus.OK);
            }
        } catch (IamportResponseException | IOException e) {
            e.printStackTrace();
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PostMapping("/payment/order")
    @ResponseBody
    public Map<String,Object> getPaymentOrder(PaymentDto paymentDto, HttpSession session) throws IOException {

        paymentService.getPaymentOrder(paymentDto);


        Map<String,Object> map = new HashMap<>();

        MemberDto setIDX = (MemberDto) session.getAttribute("LoginIn");

        if(paymentDto.getPaymentStatus() != null && setIDX != null) {
            map.put("msg","success");
        }
        return map;
    }

    @PostMapping("/payment/cancle")
    @ResponseBody
    public Map<String, Object> PaymentCancel(@RequestParam String impUid) throws IOException {

        System.out.println("번호입니다. " + impUid);
        
        String token = paymentService.getToken(); // 토큰값 가져오기
        System.out.println("토큰 " + token);

        System.out.println("포트원 결제값 " + paymentService.ImpPaymentInfo(impUid,token));
        String amount = paymentService.ImpPaymentInfo(impUid,token); // 포트원에 저장되어 있는 결제 값


        // ***********************************************************
        Map<String,Object> map = new HashMap<>();
        if(paymentService.paymentCancle(impUid,token,amount)) {

            paymentMapper.updatePayment(impUid);
            reservationMapper.updateReserInfo(impUid);
            map.put("msg", "success");
            return map;
        }else {
            System.out.println("결제 취소에 실패했습니다. 다시 시도해주세요.");
            return null;
        }
    }
}
