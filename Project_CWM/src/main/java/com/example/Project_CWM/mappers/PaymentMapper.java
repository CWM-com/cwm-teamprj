package com.example.Project_CWM.mappers;

import com.example.Project_CWM.dto.PaymentDto;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

@Mapper
public interface PaymentMapper {

    @Insert("insert into payment values(null,#{impUid},#{merchantUid},#{payMethod},#{orderName},#{amount},#{buyerName},#{buyerTel},#{buyerEmail},now(),#{paymentStatus},#{memIdx})")
    public void getPaymentOrder(PaymentDto paymentDto);

    @Select("select amount from payment where imp_uid = #{impUid}")
    public String getPay(String impUid);

    @Update("update payment set payment_status = 'cancel' where imp_uid = #{impUid}")
    public void updatePayment(String impUid);
}
