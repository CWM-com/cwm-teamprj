package com.example.Project_CWM.dto;

public class ReservationOrderDto {
    private  int reservIdx;
    private String reservName;
    private String reservTel;
    private String campName;
    private String campType;
    private String checkin;
    private String checkdays;
    private String checkout;
    private int adultNum;
    private int teenNum;
    private int cildNum;
    private String reservStatus;
    private String memIdx;
    private String campPrice;
    private String impUid;
    private String merchantUid;
    private String regdate;
    public int getReservIdx() {
        return reservIdx;
    }

    public void setReservIdx(int reservIdx) {
        this.reservIdx = reservIdx;
    }

    public String getReservName() {
        return reservName;
    }

    public void setReservName(String reservName) {
        this.reservName = reservName;
    }

    public String getReservTel() {
        return reservTel;
    }

    public void setReservTel(String reservTel) {
        this.reservTel = reservTel;
    }

    public String getCampName() {
        return campName;
    }

    public void setCampName(String campName) {
        this.campName = campName;
    }

    public String getCampType() {
        return campType;
    }

    public void setCampType(String campType) {
        this.campType = campType;
    }

    public String getCheckin() {
        return checkin;
    }

    public void setCheckin(String checkin) {
        this.checkin = checkin;
    }

    public String getCheckdays() {
        return checkdays;
    }

    public void setCheckdays(String checkdays) {
        this.checkdays = checkdays;
    }

    public String getCheckout() {
        return checkout;
    }

    public void setCheckout(String checkout) {
        this.checkout = checkout;
    }

    public int getAdultNum() {
        return adultNum;
    }

    public void setAdultNum(int adultNum) {
        this.adultNum = adultNum;
    }

    public int getTeenNum() {
        return teenNum;
    }

    public void setTeenNum(int teenNum) {
        this.teenNum = teenNum;
    }

    public int getCildNum() {
        return cildNum;
    }

    public void setCildNum(int cildNum) {
        this.cildNum = cildNum;
    }

    public String getReservStatus() {
        return reservStatus;
    }

    public void setReservStatus(String reservStatus) {
        this.reservStatus = reservStatus;
    }

    public String getMemIdx() {
        return memIdx;
    }

    public void setMemIdx(String memIdx) {
        this.memIdx = memIdx;
    }

    public String getCampPrice() {
        return campPrice;
    }

    public void setCampPrice(String campPrice) {
        this.campPrice = campPrice;
    }

    public String getImpUid() {
        return impUid;
    }

    public void setImpUid(String impUid) {
        this.impUid = impUid;
    }

    public String getRegdate() {
        return regdate;
    }

    public void setRegdate(String regdate) {
        this.regdate = regdate;
    }

    public String getMerchantUid() {
        return merchantUid;
    }

    public void setMerchantUid(String merchantUid) {
        this.merchantUid = merchantUid;
    }

    @Override
    public String toString() {
        return "ReservationOrderDto{" +
                "reservIdx=" + reservIdx +
                ", reservName='" + reservName + '\'' +
                ", reservTel='" + reservTel + '\'' +
                ", campName='" + campName + '\'' +
                ", campType='" + campType + '\'' +
                ", checkin='" + checkin + '\'' +
                ", checkdays='" + checkdays + '\'' +
                ", checkout='" + checkout + '\'' +
                ", adultNum=" + adultNum +
                ", teenNum=" + teenNum +
                ", cildNum=" + cildNum +
                ", reservStatus='" + reservStatus + '\'' +
                ", memIdx='" + memIdx + '\'' +
                ", campPrice='" + campPrice + '\'' +
                ", impUid='" + impUid + '\'' +
                ", merchantUid=" + merchantUid +
                ", regdate='" + regdate + '\'' +
                '}';
    }
}



