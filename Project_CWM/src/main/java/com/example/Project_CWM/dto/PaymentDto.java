package com.example.Project_CWM.dto;

import org.springframework.lang.NonNull;

public class PaymentDto {

    private int orderIdx;
    private String impUid;
    private String merchantUid;
    private String payMethod;
    private String orderName;
    private String amount;
    private String buyerName;
    private String buyerTel;
    private String buyerEmail;
    private String paymentStatus;
    private int memIdx;
    private String payment_date;

    public int getOrderIdx() {
        return orderIdx;
    }

    public void setOrderIdx(int orderIdx) {
        this.orderIdx = orderIdx;
    }

    public String getImpUid() {
        return impUid;
    }

    public void setImpUid(String impUid) {
        this.impUid = impUid;
    }

    public String getMerchantUid() {
        return merchantUid;
    }

    public void setMerchantUid(String merchantUid) {
        this.merchantUid = merchantUid;
    }

    public String getPayMethod() {
        return payMethod;
    }

    public void setPayMethod(String payMethod) {
        this.payMethod = payMethod;
    }

    public String getOrderName() {
        return orderName;
    }

    public void setOrderName(String orderName) {
        this.orderName = orderName;
    }

    public String getAmount() {
        return amount;
    }

    public void setAmount(String amount) {
        this.amount = amount;
    }

    public String getBuyerName() {
        return buyerName;
    }

    public void setBuyerName(String buyerName) {
        this.buyerName = buyerName;
    }

    public String getBuyerTel() {
        return buyerTel;
    }

    public void setBuyerTel(String buyerTel) {
        this.buyerTel = buyerTel;
    }

    public String getBuyerEmail() {
        return buyerEmail;
    }

    public void setBuyerEmail(String buyerEmail) {
        this.buyerEmail = buyerEmail;
    }

    public String getPaymentStatus() {
        return paymentStatus;
    }

    public void setPaymentStatus(String paymentStatus) {
        this.paymentStatus = paymentStatus;
    }

    public int getMemIdx() {
        return memIdx;
    }

    public void setMemIdx(int memIdx) {
        this.memIdx = memIdx;
    }

    public String getPayment_date() {
        return payment_date;
    }

    public void setPayment_date(String payment_date) {
        this.payment_date = payment_date;
    }

    @Override
    public String toString() {
        return "PaymentDto{" +
                "orderIdx=" + orderIdx +
                ", impUid='" + impUid + '\'' +
                ", merchantUid='" + merchantUid + '\'' +
                ", payMethod='" + payMethod + '\'' +
                ", orderName='" + orderName + '\'' +
                ", amount='" + amount + '\'' +
                ", buyerName='" + buyerName + '\'' +
                ", buyerTel='" + buyerTel + '\'' +
                ", buyerEmail='" + buyerEmail + '\'' +
                ", paymentStatus='" + paymentStatus + '\'' +
                ", memIdx=" + memIdx +
                ", payment_date='" + payment_date + '\'' +
                '}';
    }
}
