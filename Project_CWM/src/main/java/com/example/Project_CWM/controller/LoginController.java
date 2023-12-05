package com.example.Project_CWM.controller;

import com.example.Project_CWM.Service.SigninService;
import com.example.Project_CWM.dto.RegisterDto;
import com.example.Project_CWM.mappers.SigninMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Controller
@RequestMapping("/login")
public class LoginController {

    @Autowired
    private SigninMapper signinMapper;
    @Autowired
    private SigninService signinService;

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

    @GetMapping("/findresult")
    public String getFindResult() {
        return "login/find_result";
    }

    @PostMapping("/findid")
    public String setFindResult(@RequestParam("userName") String userName, @RequestParam("userEmail") String userEmail, RedirectAttributes ra) {

        String id = signinService.setID(userName, userEmail);
        System.out.println(id);

        ra.addFlashAttribute("res",id);

        return "redirect:/login/findresult";
    }
}
