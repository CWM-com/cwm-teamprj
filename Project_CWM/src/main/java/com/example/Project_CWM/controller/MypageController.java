package com.example.Project_CWM.controller;

import com.example.Project_CWM.service.MypageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;

@Controller
@RequestMapping("/mypage")
public class MypageController {

    @Autowired
    private MypageService mypageService;

    @GetMapping("")
    public String getMypage() {

        return "mypage/mypage";
    }

    @GetMapping("/update")
    public String getinfoUpdate() {
        return "mypage/update";
    }

    @GetMapping("/reservation")
    public String getreservation() {
        return "mypage/reservation";
    }

    @PostMapping("/infoUpdate")
    @ResponseBody
    public Map<String, Object> setinfoUpdate(@RequestParam("userPasswd") String userPasswd, @RequestParam("userTel") String userTel, @RequestParam("idx") int idx) {

        System.out.println(userPasswd);
        System.out.println(userTel);
        System.out.println(idx);

        mypageService.setinfoUpdate(userPasswd, userTel, idx);

        Map<String,Object> map = new HashMap<>();

        map.put("msg", "success");

        return map;
    }
}
