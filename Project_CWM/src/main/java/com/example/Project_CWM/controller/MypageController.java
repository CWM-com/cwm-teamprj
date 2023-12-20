package com.example.Project_CWM.controller;

import com.example.Project_CWM.dto.MemberDto;
import com.example.Project_CWM.dto.QnaDto;
import com.example.Project_CWM.dto.ReservationOrderDto;
import com.example.Project_CWM.mappers.PlaceMapper;
import com.example.Project_CWM.mappers.QnaMapper;
import com.example.Project_CWM.service.MypageService;
import com.example.Project_CWM.service.QnaService;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Controller
@RequestMapping("/mypage")
public class MypageController {

    @Autowired
    private MypageService mypageService;
    @Autowired
    private PlaceMapper placeMapper;
    @Autowired
    private QnaService qnaService;
    @Autowired
    private QnaMapper qnaMapper;

    @GetMapping("")
    public String getMypage(HttpSession session, Model model) {

        MemberDto setIDX = (MemberDto) session.getAttribute("LoginIn"); //사람정보

        int idx = setIDX.getIdx(); //그 사람 idx번호만

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
        model.addAttribute("main",placeMapper.getMainFiles());

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
    public String getMyQnA(HttpSession session, Model model, @RequestParam(value="page", defaultValue = "1") int page) {
        List<QnaDto> qna = qnaService.getQna();

        MemberDto setIDX = (MemberDto) session.getAttribute("LoginIn");
        String userId  =setIDX.getUserId();

        model.addAttribute("cnt", qnaMapper.getMyQnaCount(userId));
        model.addAttribute("list", mypageService.getMyQnA(page, userId));
        model.addAttribute("qna", qna);
        model.addAttribute("page", qnaService.QnaPageCalc(page));

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
