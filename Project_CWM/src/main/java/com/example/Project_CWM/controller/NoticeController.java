package com.example.Project_CWM.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/notice")
public class NoticeController {

    @GetMapping("")
    public String notice() {
        return "notice/notice";
    }

    @GetMapping("/noticedetail")
    public String noticeDetail() {
        return "notice/noticedetail";
    }

    @GetMapping("/write")
    public String write() {
        return "notice/write";
    }

    @GetMapping("/test")
    public String test() {
        return "notice/test";
    }
}
