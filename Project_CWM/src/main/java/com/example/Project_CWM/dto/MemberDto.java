package com.example.Project_CWM.dto;

public class MemberDto {

    private int idx;
    private String userId;
    private String userPasswd;
    private String userEmail;
    private String userName;
    private String userTel;
    private String userRegdate;
    private String userAuthority;

    public int getIdx() {
        return idx;
    }

    public void setIdx(int idx) {
        this.idx = idx;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getUserPasswd() {
        return userPasswd;
    }

    public void setUserPasswd(String userPasswd) {
        this.userPasswd = userPasswd;
    }

    public String getUserEmail() {
        return userEmail;
    }

    public void setUserEmail(String userEmail) {
        this.userEmail = userEmail;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getUserTel() {
        return userTel;
    }

    public void setUserTel(String userTel) {
        this.userTel = userTel;
    }

    public String getUserRegdate() {
        return userRegdate;
    }

    public void setUserRegdate(String userRegdate) {
        this.userRegdate = userRegdate;
    }

    public String getUserAuthority() {
        return userAuthority;
    }

    public void setUserAuthority(String userAuthority) {
        this.userAuthority = userAuthority;
    }

    @Override
    public String toString() {
        return "RegisterDto{" +
                "idx=" + idx +
                ", userId='" + userId + '\'' +
                ", userPasswd='" + userPasswd + '\'' +
                ", userEmail='" + userEmail + '\'' +
                ", userName='" + userName + '\'' +
                ", userTel='" + userTel + '\'' +
                ", userRegdate='" + userRegdate + '\'' +
                ", userAuthority='" + userAuthority + '\'' +
                '}';
    }
}
