package com.example.Project_CWM.dto;

public class ReservSearchDto {

    private String campName;
    private String checkin;
    private String checkout;
    private String camptype;
    private int campPrice;

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

    public String getCheckout() {
        return checkout;
    }

    public void setCheckout(String checkout) {
        this.checkout = checkout;
    }

    public String getCamptype() {
        return camptype;
    }

    public void setCamptype(String camptype) {
        this.camptype = camptype;
    }

    public int getCampPrice() {
        return campPrice;
    }

    public void setCampPrice(int campPrice) {
        this.campPrice = campPrice;
    }

    @Override
    public String toString() {
        return "ReservSearchDto{" +
                "campName='" + campName + '\'' +
                ", checkin='" + checkin + '\'' +
                ", checkout='" + checkout + '\'' +
                ", camptype='" + camptype + '\'' +
                ", campPrice=" + campPrice +
                '}';
    }
}
