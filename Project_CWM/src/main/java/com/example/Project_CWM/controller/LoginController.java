package com.example.Project_CWM.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/login")
public class LoginController {

    @GetMapping("")
    public String getLogin() {
        return "login/signin";
    }

    @GetMapping("/findid")
    public String getFindid() {
        return "login/find_id";
    }

    @GetMapping("/findpwd")
    public String getFindpwd() {
        return "login/find_pwd";
    }
}
