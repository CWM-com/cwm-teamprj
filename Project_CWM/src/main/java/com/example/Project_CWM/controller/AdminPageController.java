package com.example.Project_CWM.controller;

import com.example.Project_CWM.dto.MemberDto;
import com.example.Project_CWM.service.AdminService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

@Controller
public class AdminPageController {
    @Autowired
    private AdminService adminService;

    @GetMapping("")
    public String getAdmin(@RequestParam(value="current", defaultValue="1") String current, Model model) {

        model.addAttribute("current", current);
        model.addAttribute("member", adminService.AdminMember());
        return "adminPage";
    }
}

