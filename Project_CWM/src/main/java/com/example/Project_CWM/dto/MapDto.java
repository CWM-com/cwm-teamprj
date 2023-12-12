package com.example.Project_CWM.dto;

public class MapDto {

    private int placeId;
    private String placeCode;
    private String placeName;
    private String placeAddr;
    private String placeCall;
    private int visit;
    private int bookmark;
    private int star;
    private String regdate;

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

    public int getBookmark() {
        return bookmark;
    }

    public void setBookmark(int bookmark) {
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

    @Override
    public String toString() {
        return "MapDto{" +
                "placeId=" + placeId +
                ", placeCode='" + placeCode + '\'' +
                ", placeName='" + placeName + '\'' +
                ", placeAddr='" + placeAddr + '\'' +
                ", placeCall='" + placeCall + '\'' +
                ", visit=" + visit +
                ", bookmark=" + bookmark +
                ", star=" + star +
                ", regdate='" + regdate + '\'' +
                '}';
    }
}