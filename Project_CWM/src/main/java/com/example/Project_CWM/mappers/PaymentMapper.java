package com.example.Project_CWM.mappers;

import com.example.Project_CWM.dto.PaymentDto;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface PaymentMapper {

    @Insert("insert into payment values(null,#{impUid},#{merchantUid},#{orderName},#{amount},#{buyerName},#{buyerTel},#{buyerEmail},now(),#{paymentStatus},#{memIdx})")
    public void getPaymentOrder(PaymentDto paymentDto);
}
