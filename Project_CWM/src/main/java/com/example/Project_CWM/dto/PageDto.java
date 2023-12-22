package com.example.Project_CWM.dto;

public class PageDto {
    private int startNum;
    private int pageCount = 10; //한 페이지 게시물 개수
    private int placePageCount = 5;
    private int myPageCount = 3;
    private int blockCount = 10;
    private int page; //현제 페이지 번호
    private int startPage;
    private int endPage;
    private int totalPage;

    public int getPageCount() {
        return pageCount;
    }

    public void setPageCount(int pageCount) {
        this.pageCount = pageCount;
    }

    public int getBlockCount() {
        return blockCount;
    }

    public void setBlockCount(int blockCount) {
        this.blockCount = blockCount;
    }

    public int getPage() {
        return page;
    }

    public void setPage(int page) {
        this.page = page;
    }

    public int getStartPage() {
        return startPage;
    }

    public void setStartPage(int startPage) {
        this.startPage = startPage;
    }

    public int getEndPage() {
        return endPage;
    }

    public void setEndPage(int endPage) {
        this.endPage = endPage;
    }

    public int getTotalPage() {
        return totalPage;
    }

    public void setTotalPage(int totalPage) {
        this.totalPage = totalPage;
    }

    public int getPlacePageCount() {
        return placePageCount;
    }

    public void setPlacePageCount(int placePageCount) {
        this.placePageCount = placePageCount;
    }

    public int getMyPageCount() {
        return myPageCount;
    }

    public void setMyPageCount(int myPageCount) {
        this.myPageCount = myPageCount;
    }

    public int getStartNum() {
        return startNum;
    }

    public void setStartNum(int startNum) {
        this.startNum = startNum;
    }

    @Override
    public String   toString() {
        return "PageDto{" +
                "startNum=" + startNum +
                ", pageCount=" + pageCount +
                ", placePageCount=" + placePageCount +
                ", myPageCount=" + myPageCount +
                ", blockCount=" + blockCount +
                ", page=" + page +
                ", startPage=" + startPage +
                ", endPage=" + endPage +
                ", totalPage=" + totalPage +
                '}';
    }
}
