package com.example.Project_CWM.controller;

import com.example.Project_CWM.dto.MemberDto;
import com.example.Project_CWM.mappers.MemberMapper;
import com.example.Project_CWM.service.MailSerivce;
import jakarta.mail.MessagingException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.HashMap;
import java.util.Map;

@Controller
@RequestMapping("/register")
public class RegisterController {

    @Autowired
    private MemberMapper registerMapper;
    @Autowired
    private MailSerivce mailSerivce;

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
    public Map<String,Object> setSignup(@ModelAttribute MemberDto registerDto) {

        Map<String,Object> map = new HashMap<>();

        if(registerDto != null) {

            String auth = "USER";

            registerDto.setUserAuthority(auth);

            registerMapper.setSignup(registerDto);
            map.put("res","success");
        }
        return map;
    }

    @PostMapping("/mailsend")
    @ResponseBody
    public Map<String,Object> MailSend(@RequestParam String userEmail) throws MessagingException {

        String signupResult = mailSerivce.sendEmail(userEmail);

        Map<String,Object> map = new HashMap<>();

        map.put("result", signupResult);

        return map;
    }

    @PostMapping("/PasswdMailSend")
    @ResponseBody
    public Map<String,Object> PasswdMailSend(@RequestParam String userEmail) throws MessagingException {

        String passwdResult = mailSerivce.sendPwdEmail(userEmail);

        Map<String,Object> map = new HashMap<>();

        map.put("passwd",passwdResult);

        return map;
    }


    @PostMapping("/certifiCheck")
    public ModelAndView certifiCheck(@RequestParam("userEmail") String userEmail,@RequestParam("checkNum") String checkNum, RedirectAttributes ra) {

        ModelAndView mv = new ModelAndView();

        if (userEmail == null || checkNum == null) {

            mv.setViewName("/register/certification");

            return mv;
        }
        if(checkNum != null && !userEmail.isEmpty()) {

            mv.setViewName("/register/signup");
            mv.addObject("data", userEmail);

            return mv;
        }else {
            mv.setViewName("/register/certification");
            return mv;
        }
    }
}
