package com.example.Project_CWM.controller;

import com.example.Project_CWM.Service.SigninService;
import com.example.Project_CWM.dto.RegisterDto;
import com.example.Project_CWM.mappers.SigninMapper;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;
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

    @GetMapping("/idresult")
    public String getIDResult() {
        return "login/id_result";
    }

    @GetMapping("/pwdresult")
    public String getPwdResult() {
        return "login/pwd_result";
    }

    @PostMapping("/findid")
    public String setFindResult(@RequestParam("userName") String userName, @RequestParam("userEmail") String userEmail, RedirectAttributes ra) {

        String result = signinService.setID(userName, userEmail);

        if(result != null) {
            ra.addFlashAttribute("res", result);
            return "redirect:/login/idresult";
        }else  {
            ra.addFlashAttribute("res","해당 이름과 이메일로 등록된 아이디가 없습니다.");
            return "redirect:/login/findid";
        }
    }
    @PostMapping("/findpwd")
    public String setFindPasswd(@RequestParam("userid") String userid, @RequestParam("userEmail") String userEmail, RedirectAttributes ra) {

        String result = signinService.setPasswd(userid,userEmail);

        if(result != null) {
            ra.addFlashAttribute("res", result);
            return "redirect:/login/pwdresult";
        }else  {
            ra.addFlashAttribute("res","해당 아이디와 이메일로 등록된 비밀번호가 없습니다.");
            return "redirect:/login/findpwd";
        }
    }

    @PostMapping("/sessoinlogin")
    public String setLogin(@ModelAttribute RegisterDto registerDto, HttpServletRequest req, HttpSession session) {

        RegisterDto m = signinService.setLogin(registerDto);

        if(m != null) {
            session = req.getSession(); // 세션 생성할 준비
            session.setAttribute("LoginIn", m);
            session.setMaxInactiveInterval(60 * 10);
            System.out.println("로그인 완료");
            return "redirect:/index";
        }else {
            return "redirect:/login";
        }
    }
    @GetMapping("/logout")
    public String getLogout(HttpServletRequest req) {

        HttpSession session = req.getSession(false);

        if(session != null) {
            session.invalidate();
        }
        return "redirect:/index";
    }
}
