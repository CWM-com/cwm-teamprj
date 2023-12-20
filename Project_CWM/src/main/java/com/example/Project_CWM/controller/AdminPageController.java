package com.example.Project_CWM.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class AdminPageController {

    @GetMapping("")
    public String getAdmin(@RequestParam(value="current", defaultValue="1") String current, Model model) {
        model.addAttribute("current", current);
        return "adminPage";
    }
}
