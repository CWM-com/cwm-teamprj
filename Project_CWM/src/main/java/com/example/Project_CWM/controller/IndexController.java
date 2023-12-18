package com.example.Project_CWM.controller;

import com.example.Project_CWM.dto.PlaceDto;
import com.example.Project_CWM.mappers.MemberMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

@Controller
public class IndexController {

    @Autowired
    private MemberMapper memberMapper;

    @GetMapping("/index")
    public String getIndex() {
        return "index";
    }

}
