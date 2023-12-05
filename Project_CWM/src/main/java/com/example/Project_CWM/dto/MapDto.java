package com.example.Project_CWM.dto;

public class MapDto {

    private int placeCode;
    private String placeName;
    private String placeAddrCode;
    private String placeAddrDetail;
    private String placeCall;
    private int placeBookmark;
    private int placeStar;
    private String regdate;

    public int getPlaceCode() {
        return placeCode;
    }

    public void setPlaceCode(int placeCode) {
        this.placeCode = placeCode;
    }

    public String getPlaceName() {
        return placeName;
    }

    public void setPlaceName(String placeName) {
        this.placeName = placeName;
    }

    public String getPlaceAddrCode() {
        return placeAddrCode;
    }

    public void setPlaceAddrCode(String placeAddrCode) {
        this.placeAddrCode = placeAddrCode;
    }

    public String getPlaceAddrDetail() {
        return placeAddrDetail;
    }

    public void setPlaceAddrDetail(String placeAddrDetail) {
        this.placeAddrDetail = placeAddrDetail;
    }

    public String getPlaceCall() {
        return placeCall;
    }

    public void setPlaceCall(String placeCall) {
        this.placeCall = placeCall;
    }

    public int getPlaceBookmark() {
        return placeBookmark;
    }

    public void setPlaceBookmark(int placeBookmark) {
        this.placeBookmark = placeBookmark;
    }

    public int getPlaceStar() {
        return placeStar;
    }

    public void setPlaceStar(int placeStar) {
        this.placeStar = placeStar;
    }

    public String getRegdate() {
        return regdate;
    }

    public void setRegdate(String regdate) {
        this.regdate = regdate;
    }

    @Override
    public String toString() {
        return "MapDto{" +
                "placeCode=" + placeCode +
                ", placeName='" + placeName + '\'' +
                ", placeAddrCode='" + placeAddrCode + '\'' +
                ", placeAddrDetail='" + placeAddrDetail + '\'' +
                ", placeCall='" + placeCall + '\'' +
                ", placeBookmark=" + placeBookmark +
                ", placeStar=" + placeStar +
                ", regdate='" + regdate + '\'' +
                '}';
    }
}