package com.example.Project_CWM.dto;

public class PlaceDto {

    private int placeId;
    private String placeCode;
    private String placeName;
    private String placeAddr;
    private String placeCall;
    private int visit;
    private String bookmark;
    private int star;
    private String regdate;
    private String placeContent1;
    private String placeContent2;
    private String placeContent3;
    private String placeContent4;

    public int getPlaceId() {
        return placeId;
    }

    public void setPlaceId(int placeId) {
        this.placeId = placeId;
    }

    public String getPlaceCode() {
        return placeCode;
    }

    public void setPlaceCode(String placeCode) {
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

    public int getVisit() {
        return visit;
    }

    public void setVisit(int visit) {
        this.visit = visit;
    }

    public String getBookmark() {
        return bookmark;
    }

    public void setBookmark(String bookmark) {
        this.bookmark = bookmark;
    }

    public int getStar() {
        return star;
    }

    public void setStar(int star) {
        this.star = star;
    }

    public String getRegdate() {
        return regdate;
    }

    public void setRegdate(String regdate) {
        this.regdate = regdate;
    }

    public String getPlaceContent1() {
        return placeContent1;
    }

    public void setPlaceContent1(String placeContent1) {
        this.placeContent1 = placeContent1;
    }

    public String getPlaceContent2() {
        return placeContent2;
    }

    public void setPlaceContent2(String placeContent2) {
        this.placeContent2 = placeContent2;
    }

    public String getPlaceContent3() {
        return placeContent3;
    }

    public void setPlaceContent3(String placeContent3) {
        this.placeContent3 = placeContent3;
    }

    public String getPlaceContent4() {
        return placeContent4;
    }

    public void setPlaceContent4(String placeContent4) {
        this.placeContent4 = placeContent4;
    }

    @Override
    public String toString() {
        return "PlaceDto{" +
                "placeId=" + placeId +
                ", placeCode='" + placeCode + '\'' +
                ", placeName='" + placeName + '\'' +
                ", placeAddr='" + placeAddr + '\'' +
                ", placeCall='" + placeCall + '\'' +
                ", visit=" + visit +
                ", bookmark='" + bookmark + '\'' +
                ", star=" + star +
                ", regdate='" + regdate + '\'' +
                ", placeContent1='" + placeContent1 + '\'' +
                ", placeContent2='" + placeContent2 + '\'' +
                ", placeContent3='" + placeContent3 + '\'' +
                ", placeContent4='" + placeContent4 + '\'' +
                '}';
    }
}