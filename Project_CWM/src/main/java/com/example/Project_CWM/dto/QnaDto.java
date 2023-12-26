package com.example.Project_CWM.dto;

public class QnaDto {
    private int id;
    private String subject;
    private String userId;
    private String content;
    private String regdate;
    private String ext;
    private int grp;
    private int seq;
    private int depth;
    private int visit;
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getSubject() {
        return subject;
    }

    public void setSubject(String subject) {
        this.subject = subject;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getRegdate() {
        return regdate;
    }

    public void setRegdate(String regdate) {
        this.regdate = regdate;
    }

    public String getExt() {
        return ext;
    }

    public void setExt(String ext) {
        this.ext = ext;
    }

    public int getGrp() {
        return grp;
    }

    public void setGrp(int grp) {
        this.grp = grp;
    }

    public int getSeq() {
        return seq;
    }

    public void setSeq(int seq) {
        this.seq = seq;
    }

    public int getDepth() {
        return depth;
    }

    public void setDepth(int depth) {
        this.depth = depth;
    }

    public int getVisit() {
        return visit;
    }

    public void setVisit(int visit) {
        this.visit = visit;
    }

    @Override
    public String toString() {
        return "QnaDto{" +
                "id=" + id +
                ", subject='" + subject + '\'' +
                ", userId='" + userId + '\'' +
                ", content='" + content + '\'' +
                ", regdate='" + regdate + '\'' +
                ", ext='" + ext + '\'' +
                ", grp=" + grp +
                ", seq=" + seq +
                ", depth=" + depth +
                '}';
    }
}
