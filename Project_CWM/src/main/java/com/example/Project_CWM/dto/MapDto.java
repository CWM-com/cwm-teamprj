package com.example.Project_CWM.dto;

public class MapDto {
    private String placeCode;
    private String placeAddr;
    private String placeName;
    private Float placeX;
    private Float placeY;

    public String getPlaceCode() {
        return placeCode;
    }

    public void setPlaceCode(String placeCode) {
        this.placeCode = placeCode;
    }

    public String getPlaceAddr() {
        return placeAddr;
    }

    public void setPlaceAddr(String placeAddr) {
        this.placeAddr = placeAddr;
    }

    public Float getPlaceX() {
        return placeX;
    }

    public void setPlaceX(Float placeX) {
        this.placeX = placeX;
    }

    public Float getPlaceY() {
        return placeY;
    }

    public void setPlaceY(Float placeY) {
        this.placeY = placeY;
    }

    public String getPlaceName() {
        return placeName;
    }

    public void setPlaceName(String placeName) {
        this.placeName = placeName;
    }

    @Override
    public String toString() {
        return "MapDto{" +
                "placeCode='" + placeCode + '\'' +
                ", placeAddr='" + placeAddr + '\'' +
                ", placeName='" + placeName + '\'' +
                ", placeX=" + placeX +
                ", placeY=" + placeY +
                '}';
    }
}
