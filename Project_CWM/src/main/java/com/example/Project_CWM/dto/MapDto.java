package com.example.Project_CWM.dto;

public class MapDto {
    private String placeCode;
    private String placeAddr;
    private String placeX;
    private String placeY;

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

    public String getPlaceX() {
        return placeX;
    }

    public void setPlaceX(String placeX) {
        this.placeX = placeX;
    }

    public String getPlaceY() {
        return placeY;
    }

    public void setPlaceY(String placeY) {
        this.placeY = placeY;
    }

    @Override
    public String toString() {
        return "MapDto{" +
                "placeCode='" + placeCode + '\'' +
                ", placeAddr='" + placeAddr + '\'' +
                ", placeX='" + placeX + '\'' +
                ", placeY='" + placeY + '\'' +
                '}';
    }
}
