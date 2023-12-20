//package com.example.Project_CWM.controller;
//
//import com.example.Project_CWM.dto.ReviewDto;
//import com.example.Project_CWM.mappers.ReviewMapper;
//import com.example.Project_CWM.service.ReviewService;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.stereotype.Controller;
//import org.springframework.ui.Model;
//import org.springframework.web.bind.annotation.*;
//
//import java.io.IOException;
//import java.util.List;
//
//@Controller
//@RequestMapping("/review")
//public class ReviewController {
//
//    @Autowired
//    ReviewService reviewService;
//
//    @Autowired
//    ReviewMapper reviewMapper;
//
//    @GetMapping("")
//    public String getReview(Model model, @RequestParam(value = "searchType", defaultValue = "") String searchType, @RequestParam(value = "words", defaultValue = "") String words, @RequestParam(value="page", defaultValue = "1") int page) {
//        List<ReviewDto> review = reviewService.getReview();
//        model.addAttribute("cnt", reviewService.getSearchCnt(searchType, words));
//        model.addAttribute("list", reviewService.getSearch(page, searchType, words));
//        model.addAttribute("page", reviewService.ReviewPageCalc(page));
//        model.addAttribute("review", review);
//        return "review/review";
//    }
//
//    @GetMapping("/write")
//    public String getWrite() {
//        return "review/reviewWrite";
//    }
//
//    @PostMapping("/write")
//    public String setWrite(@ModelAttribute ReviewDto reviewDto) {
//
//        int maxGrp = reviewMapper.getMaxGrp();
//        reviewDto.setGrp(maxGrp);
//        reviewMapper.setWrite(reviewDto);
//        return "redirect:/review";
//    }
//
//    @GetMapping("/delete")
//    public String getDelete(@RequestParam int id) {
//        reviewService.setDelete(id);
//        return "redirect:/review";
//    }
//
//    @GetMapping("/view")
//    public String getView(@RequestParam int id, Model model) {
//        model.addAttribute("view", reviewService.getView(id));
//        return "review/view";
//    }
//
//    @GetMapping("/update")
//    public String getUpdate(@RequestParam int id, Model model) {
//        ReviewDto db = reviewMapper.getView(id);
//        model.addAttribute("modify", db);
//        return "review/reviewUpdate";
//    }
//
//    @PostMapping("/update")
//    public String setUpdate(@ModelAttribute ReviewDto reviewDto) {
//        reviewMapper.setUpdate(reviewDto);
//        return "redirect:/review";
//    }
//
//    //답글
//    @GetMapping("/reply")
//    public String getReply(@RequestParam int id, Model model) {
//        ReviewDto b = reviewMapper.getView(id);
//        model.addAttribute("reply", b);
//        return "review/reviewReply";
//    }
//
//    @PostMapping("/reply")
//    public String setReply(@ModelAttribute ReviewDto reviewDto) throws IOException {
//
//        /*
//        grp(게시물그룹), seq(답글순서), depth(들여쓰기)
//         */
//
//        //원본글 : grp(게시물그룹), seq(답글순서), depth(들여쓰기)
//        //1, 1, 1
//        ReviewDto bd = reviewMapper.getView(reviewDto.getId());
//
//        //답글
//        //1, 2, 2
//        reviewDto.setGrp(bd.getGrp());
//        reviewDto.setSeq(bd.getSeq() + 1);
//        reviewDto.setDepth(bd.getDepth() + 1);
//
//        reviewMapper.setReply(reviewDto);
//        return "redirect:/review";
//    }
//}
