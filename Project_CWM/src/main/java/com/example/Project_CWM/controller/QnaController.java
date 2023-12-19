package com.example.Project_CWM.controller;

import com.example.Project_CWM.service.QnaService;
import com.example.Project_CWM.dto.QnaDto;
import com.example.Project_CWM.mappers.QnaMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.util.List;

@Controller
@RequestMapping("/qna")
public class QnaController {

    @Autowired
    QnaService qnaService;

    @Autowired
    QnaMapper qnaMapper;

    @GetMapping("")
    public String getQna(Model model, @RequestParam(value = "searchType", defaultValue = "") String searchType, @RequestParam(value = "words", defaultValue = "") String words, @RequestParam(value="page", defaultValue = "1") int page) {
        List<QnaDto> qna = qnaService.getQna();
//        model.addAttribute("page", qnaService.QnaPageCalc(page));
//        System.out.println( "이거"+ qnaService.getSearch(page, searchType, words));
//        System.out.println(words);
        model.addAttribute("cnt", qnaService.getSearchCnt(searchType, words));
        model.addAttribute("list", qnaService.getSearch(page, searchType, words));
        model.addAttribute("page", qnaService.QnaPageCalc(page));
        model.addAttribute("qna", qna);


        return "qna/qna";
    }

    @GetMapping("/write")
    public String getWrite() {
        return "qna/qnaWrite";
    }

    @PostMapping("/write")
    public String setWrite(@ModelAttribute QnaDto qnaDto) {
//        System.out.println(qnaDto.toString());

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
    public String getView(@RequestParam int id, Model model) {
        model.addAttribute("view", qnaService.getView(id));
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
    public String setReply(@ModelAttribute QnaDto qnaDto) throws IOException {

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

        qnaMapper.setReply(qnaDto);
        return "redirect:/qna";
    }
}
