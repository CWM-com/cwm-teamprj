package com.example.Project_CWM.controller;

import com.example.Project_CWM.dto.MemberDto;
import com.example.Project_CWM.dto.ReservationOrderDto;
import com.example.Project_CWM.service.MypageService;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.HashMap;
import java.util.Map;

@Controller
@RequestMapping("/mypage")
public class MypageController {

    @Autowired
    private MypageService mypageService;

    @GetMapping("")
    public String getMypage(HttpSession session, Model model) {

        MemberDto setIDX = (MemberDto) session.getAttribute("LoginIn");

        int idx = setIDX.getIdx();

        model.addAttribute("recent",mypageService.recentCamp(idx));

        return "mypage/mypage";
    }
    @GetMapping("/reservConfirm")
    public String getreservation(@RequestParam(defaultValue = "1") int page,@RequestParam(value = "search", defaultValue = "") String search ,Model model, HttpSession session) {

        MemberDto setIDX = (MemberDto) session.getAttribute("LoginIn");

        String memIdx = String.valueOf(setIDX.getIdx());

        model.addAttribute("order",mypageService.OrderList(memIdx,page,search));
        model.addAttribute("page",mypageService.ReservCount(page,memIdx,search));
        model.addAttribute("search",search);

        return "mypage/reservConfirm";
    }
    @GetMapping("/update")
    public String getinfoUpdate() {
        return "mypage/update";
    }
    @GetMapping("/withdrawal")
    public String getWithdrawalCheck() {

        return "mypage/withdrawal";
    }

    @GetMapping("/myQnA")
    public String getMyQnA() {
        return "mypage/myQnA";
    }

    @PostMapping("/infoUpdate")
    public String setinfoUpdate(@RequestParam("userPasswd") String userPasswd, @RequestParam("userTel") String userTel, @RequestParam("idx") int idx, HttpSession session, RedirectAttributes ra) {

        System.out.println(userPasswd);
        System.out.println(userTel);
        System.out.println(idx);

        if(idx > 0) {
            mypageService.setinfoUpdate(userPasswd, userTel, idx);
            ra.addFlashAttribute("res","회원정보가 변경되었습니다\n다시 로그인 후 이용가능합니다.");
            session.invalidate();
            return "redirect:/index";
        }else {
            ra.addFlashAttribute("res","입력하신 정보가 올바르지않습니다.\n다시 확인해주세요..");
            return "redirect:/mypage/update";
        }

    }

    @PostMapping("/delete")
    public String infoDelete(@RequestParam String userId, @RequestParam String userPasswd, @RequestParam int idx, HttpSession session,RedirectAttributes ra) {

        MemberDto setIDX = (MemberDto) session.getAttribute("LoginIn");

        if (setIDX.getUserId().equals(userId) && setIDX.getUserPasswd().equals(userPasswd) && setIDX.getIdx() == idx) {
            mypageService.Infodelete(idx);
            session.invalidate();
            ra.addFlashAttribute("msg", "회원탈퇴되었습니다.\n이용해주셔서 감사합니다.");
            return "redirect:/index";
        }else {
            ra.addFlashAttribute("msg", "입력하신 정보가 올바르지않습니다.");
            return "redirect:/mypage/withdrawal";
        }
    }
}
