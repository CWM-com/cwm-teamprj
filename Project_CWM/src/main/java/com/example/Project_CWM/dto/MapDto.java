package com.example.Project_CWM.dto;

public class MapDto {

    private int placeId;
    private int placeCode;
    private String placeName;
    private String placeAddr;
    private String placeCall;
    private int placeBookmark;
    private int placeStar;
    private String regdate;

    public int getPlaceId() {
        return placeId;
    }

    public void setPlaceId(int placeId) {
        this.placeId = placeId;
    }

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

    public String getPlaceAddr() {
        return placeAddr;
    }

    public void setPlaceAddr(String placeAddr) {
        this.placeAddr = placeAddr;
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
                "placeId=" + placeId +
                ", placeCode=" + placeCode +
                ", placeName='" + placeName + '\'' +
                ", placeAddr='" + placeAddr + '\'' +
                ", placeCall='" + placeCall + '\'' +
                ", placeBookmark=" + placeBookmark +
                ", placeStar=" + placeStar +
                ", regdate='" + regdate + '\'' +
                '}';
    }
}