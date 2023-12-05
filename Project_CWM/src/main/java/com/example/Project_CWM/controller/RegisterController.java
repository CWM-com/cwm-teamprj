package com.example.Project_CWM.controller;

import com.example.Project_CWM.dto.RegisterDto;
import com.example.Project_CWM.mappers.RegisterMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Controller
@RequestMapping("/register")
public class RegisterController {

    @Autowired
    private RegisterMapper registerMapper;

    @GetMapping("")
    public String getCert() {
        return "register/certification";
    }

    @GetMapping("/signup")
    public String getSignup() {
        return "register/signup";
    }

    @PostMapping("/idcheck")
    @ResponseBody
    public Map<String, Object> setIdCheck(@RequestParam("userId") String userId) {

        int cnt = 0;

        cnt = registerMapper.setIdCheck(userId);

        Map<String,Object> map = new HashMap<>();

        map.put("cnt",cnt);

        return map;
    }

    @PostMapping("/signup")
    @ResponseBody
    public Map<String,Object> setSignup(@ModelAttribute RegisterDto registerDto) {

        Map<String,Object> map = new HashMap<>();

        if(registerDto != null) {
            registerMapper.setSignup(registerDto);
            map.put("res","success");
        }
        return map;
    }
}
