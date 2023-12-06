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

        String result = signinService.setID(userName, userEmail);

        if(result != null) {
            ra.addFlashAttribute("res", result);
            return "redirect:/login/findresult";
        }else  {
            ra.addFlashAttribute("res","해당 이름과 이메일로 등록된 아이디가 없습니다.");
            return "redirect:/login/findid";
        }
    }
}
