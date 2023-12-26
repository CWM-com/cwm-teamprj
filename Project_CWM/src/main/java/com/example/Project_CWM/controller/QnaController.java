package com.example.Project_CWM.controller;

import com.example.Project_CWM.dto.MemberDto;
import com.example.Project_CWM.service.MypageService;
import com.example.Project_CWM.service.QnaService;
import com.example.Project_CWM.dto.QnaDto;
import com.example.Project_CWM.mappers.QnaMapper;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.List;
import java.util.Map;
import java.util.UUID;

@Controller
@RequestMapping("/qna")
public class QnaController {

    @Autowired
    QnaService qnaService;

    @Autowired
    QnaMapper qnaMapper;



    @GetMapping("")
    public String getQna(Model model, @RequestParam(value = "searchType", defaultValue = "") String searchType, @RequestParam(value = "words", defaultValue = "") String words, @RequestParam(value="page", defaultValue = "1") int page, HttpServletRequest request) {
        List<QnaDto> qna = qnaService.getQna();
//        model.addAttribute("page", qnaService.QnaPageCalc(page));
//        System.out.println( "이거"+ qnaService.getSearch(page, searchType, words));
//        System.out.println(words);
        model.addAttribute("cnt", qnaService.getSearchCnt(searchType, words));
        model.addAttribute("list", qnaService.getSearch(page, searchType, words));
        model.addAttribute("page", qnaService.QnaPageCalc(page));
        model.addAttribute("qna", qna);

        boolean isLoggedIn = isLoggedIn(request);
        model.addAttribute("isLoggedIn", isLoggedIn);

        return "qna/qna";
    }


    @GetMapping("/write")
    public String getWrite(HttpSession session, Model model) {
        MemberDto loggedInMember = (MemberDto) session.getAttribute("LoginIn");

        if (loggedInMember == null || loggedInMember.getUserId() == null) {
            // 로그인이 되어있지 않은 경우 또는 로그인 정보가 부족한 경우에 대한 예외 처리
            model.addAttribute("userId", "Unknown User");
        } else {
            // 세션에서 가져온 사용자의 userId를 모델에 추가
            model.addAttribute("userId", loggedInMember.getUserId());
        }

        return "qna/qnaWrite";
    }

    @PostMapping("/write")
    public String setWrite(@ModelAttribute QnaDto qnaDto, HttpSession session) {
        // 세션에서 사용자 정보를 가져옴
        MemberDto loggedInMember = (MemberDto) session.getAttribute("LoginIn");

        if (loggedInMember != null) {
            // 사용자 정보가 있는 경우에만 QnaDto에 사용자 ID 설정
            qnaDto.setUserId(loggedInMember.getUserId());

            // 그 외의 필요한 정보도 설정 가능
            // qnaDto.setOtherField(loggedInMember.getOtherField());
        }

        // 나머지 로직은 그대로 유지
        int maxGrp = qnaMapper.getMaxGrp();
        qnaDto.setGrp(maxGrp);
        qnaMapper.setWrite(qnaDto);

        return "redirect:/qna";
    }


    @GetMapping("/delete")
    public String getDelete(@RequestParam int id) {
        qnaService.setDelete(id);
        return "redirect:/qna";
    }

    @GetMapping("/view")
    public String getView(@RequestParam int id, Model model, HttpServletRequest request) {
        model.addAttribute("view", qnaService.getView(id));
        boolean isLoggedIn = isLoggedIn(request);
        qnaMapper.updateVisit(id);
        model.addAttribute("isLoggedIn", isLoggedIn);
        return "qna/view";
    }

    @GetMapping("/update")
    public String getUpdate(@RequestParam int id, Model model) {
        QnaDto db = qnaMapper.getView(id);
        model.addAttribute("modify", db);
        return "qna/qnaUpdate";
    }

    @PostMapping("/update")
    public String setUpdate(@ModelAttribute QnaDto qnaDto) {
        qnaMapper.setUpdate(qnaDto);
        return "redirect:/qna";
    }

    //답글
    @GetMapping("/reply")
    public String getReply(@RequestParam int id, Model model) {
        QnaDto b = qnaMapper.getView(id);
        model.addAttribute("reply", b);


        return "qna/qnaReply";
    }

    @PostMapping("/reply")
    public String setReply(@ModelAttribute QnaDto qnaDto, HttpSession session) throws IOException {

        /*
        grp(게시물그룹), seq(답글순서), depth(들여쓰기)
         */

        //원본글 : grp(게시물그룹), seq(답글순서), depth(들여쓰기)
        //1, 1, 1
        QnaDto bd = qnaMapper.getView(qnaDto.getId());

        //답글
        //1, 2, 2
        qnaDto.setGrp(bd.getGrp());
        qnaDto.setSeq(bd.getSeq() + 1);
        qnaDto.setDepth(bd.getDepth() + 1);

        // 세션에서 사용자 정보를 가져옴
        MemberDto loggedInMember = (MemberDto) session.getAttribute("LoginIn");

        if (loggedInMember != null) {
            // 사용자 정보가 있는 경우에만 QnaDto에 사용자 ID 설정
            qnaDto.setUserId(loggedInMember.getUserId());

            // 그 외의 필요한 정보도 설정 가능
            // qnaDto.setOtherField(loggedInMember.getOtherField());
        }

        qnaMapper.setReply(qnaDto);
        return "redirect:/qna";
    }

    private boolean isLoggedIn(HttpServletRequest request) {
        HttpSession session = request.getSession(false);
        return session != null && session.getAttribute("userId") != null;
    }
}
