package com.example.Project_CWM.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/mypage")
public class MypageController {

    @GetMapping("/mypage")
    public String getMypage() {
        return "mypage/mypage";
    }

    @GetMapping("/infoUpdate")
    public String getinfoUpdate() {
        return "/mypage/infoUpdate";
    }
}
