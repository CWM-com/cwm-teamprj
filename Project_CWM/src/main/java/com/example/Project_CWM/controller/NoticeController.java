package com.example.Project_CWM.controller;

import com.example.Project_CWM.Service.NoticeService;
import com.example.Project_CWM.dto.NoticeDto;
import com.example.Project_CWM.dto.QnaDto;
import com.example.Project_CWM.mappers.NoticeMapper;
import com.example.Project_CWM.mappers.QnaMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

@Controller
@RequestMapping("/notice")
public class NoticeController {

    @Autowired
    NoticeService noticeService;

    @Autowired
    NoticeMapper noticeMapper;

    @GetMapping("")
    public String getNotice() {
        List<NoticeDto> notice = noticeService.getNotice();
        return "notice/notice";
    }

    @GetMapping("/write")
    public String getWrite() {
        return "notice/write";
    }

    @PostMapping("/write")
    public String setWrite() {
        return "redirect:/notice";
    }







}
