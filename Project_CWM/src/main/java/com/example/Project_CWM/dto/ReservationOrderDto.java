package com.example.Project_CWM.dto;

public class ReservationOrderDto {

    private  int reservIdx;
    private String campName;
    private String checkin;
    private String checkdays;
    private String checkout;
    private int adultNum;
    private int teenNum;
    private int cildNum;
    private String campType;
    private String campPrice;
    private String memIdx;
    private String reservName;
    private String reservTel;
    private String reservEmail;

    public int getReservIdx() {
        return reservIdx;
    }

    public void setReservIdx(int reservIdx) {
        this.reservIdx = reservIdx;
    }

    public String getCampName() {
        return campName;
    }

    public void setCampName(String campName) {
        this.campName = campName;
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

    public String getCampType() {
        return campType;
    }

    public void setCampType(String campType) {
        this.campType = campType;
    }

    public String getCampPrice() {
        return campPrice;
    }

    public void setCampPrice(String campPrice) {
        this.campPrice = campPrice;
    }

    public String getMemIdx() {
        return memIdx;
    }

    public void setMemIdx(String memIdx) {
        this.memIdx = memIdx;
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

    public String getReservEmail() {
        return reservEmail;
    }

    public void setReservEmail(String reservEmail) {
        this.reservEmail = reservEmail;
    }

    @Override
    public String toString() {
        return "ReservationOrderDto{" +
                "reservIdx=" + reservIdx +
                ", campName='" + campName + '\'' +
                ", checkin='" + checkin + '\'' +
                ", checkdays='" + checkdays + '\'' +
                ", checkout='" + checkout + '\'' +
                ", adultNum=" + adultNum +
                ", teenNum=" + teenNum +
                ", cildNum=" + cildNum +
                ", campType='" + campType + '\'' +
                ", campPrice='" + campPrice + '\'' +
                ", memIdx='" + memIdx + '\'' +
                ", reservName='" + reservName + '\'' +
                ", reservTel='" + reservTel + '\'' +
                ", reservEmail='" + reservEmail + '\'' +
                '}';
    }
}

