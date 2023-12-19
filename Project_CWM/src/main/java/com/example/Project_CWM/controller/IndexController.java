package com.example.Project_CWM.controller;

import com.example.Project_CWM.dto.PlaceDto;
import com.example.Project_CWM.mappers.MemberMapper;
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

    @GetMapping("/index")
    public String getIndex(Model model) {
       int cnt =  memberMapper.MemberCount();

       model.addAttribute("cnt1", cnt);

        return "index";
    }

//    @PostMapping("/index")
//    @ResponseBody
//    public Map<String, Object> getIndexCount() {
//
//        Map<String,Object> map = new HashMap<>();
//
//        int cnt = memberMapper.MemberCount();
//        System.out.println(cnt);
//        map.put("cnt", cnt);
//
//        return map;
//    }
}
