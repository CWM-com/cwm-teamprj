package com.example.Project_CWM.controller;

import com.example.Project_CWM.dto.PlaceDto;
import com.example.Project_CWM.dto.ReviewDto;
import com.example.Project_CWM.mappers.MemberMapper;
import com.example.Project_CWM.mappers.PlaceMapper;
import com.example.Project_CWM.mappers.ReviewMapper;
import com.example.Project_CWM.service.AdminService;
import com.example.Project_CWM.service.PlaceService;
import com.example.Project_CWM.service.ReviewService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Controller
public class IndexController {

    @Autowired
    private MemberMapper memberMapper;
    @Autowired
    private PlaceMapper placeMapper;
    @Autowired
    private ReviewMapper reviewMapper;

    @Autowired
    private PlaceService placeService;

    @Autowired
    ReviewService reviewService;

    @GetMapping("/index")
    public String getIndex(Model model) {
        int cnt =  memberMapper.MemberCount();
        int cnt2 = placeMapper.MainPlaceCount();
        int cnt3 = reviewMapper.ReviewCount();

        model.addAttribute("BestPlace", placeService.getBestPlace());
        model.addAttribute("main", placeService.getMainFiles());
        model.addAttribute("cnt1", cnt);
        model.addAttribute("cnt2", cnt2);
        model.addAttribute("cnt3", cnt3);

        return "index";
    }

}
