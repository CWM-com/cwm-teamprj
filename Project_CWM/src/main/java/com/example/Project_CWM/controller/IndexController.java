package com.example.Project_CWM.controller;

import com.example.Project_CWM.mappers.MemberMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class IndexController {

    @Autowired
    private MemberMapper memberMapper;

    @GetMapping("/index")
    public String getIndex() {
        return "index";
    }

}
