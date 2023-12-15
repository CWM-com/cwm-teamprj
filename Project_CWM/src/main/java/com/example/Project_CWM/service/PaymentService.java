package com.example.Project_CWM.service;

import com.example.Project_CWM.dto.PaymentDto;
import com.example.Project_CWM.mappers.PaymentMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class PaymentService {
    @Autowired
    private PaymentMapper paymentMapper;

    public void getPaymentOrder(PaymentDto paymentDto) {

        String uid = paymentDto.getImpUid();

        if(!uid.isEmpty()) {
            paymentDto.setPaymentStatus("결제완료");
            paymentMapper.getPaymentOrder(paymentDto);
        }
    }
}
