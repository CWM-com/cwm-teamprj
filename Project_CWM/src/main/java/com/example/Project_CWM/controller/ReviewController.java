package com.example.Project_CWM.controller;

import com.example.Project_CWM.dto.MemberDto;
import com.example.Project_CWM.dto.ReservationOrderDto;
import com.example.Project_CWM.dto.ReviewDto;
import com.example.Project_CWM.mappers.ReservationMapper;
import com.example.Project_CWM.mappers.ReviewMapper;
import com.example.Project_CWM.service.ReservationService;
import com.example.Project_CWM.service.ReviewService;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Controller
@RequestMapping("/review")
public class ReviewController {

    @Autowired
    ReviewService reviewService;

    @Autowired
    ReservationMapper reservationMapper;

    @Autowired
    ReviewMapper reviewMapper;


    @Value("${fileDir}")
    String fileDir;

    @GetMapping("")
    public String getReview(Model model, @RequestParam(value = "searchType", defaultValue = "") String searchType, @RequestParam(value = "words", defaultValue = "") String words, @RequestParam(value="page", defaultValue = "1") int page, HttpSession session
    ) {
        List<ReviewDto> review = reviewService.getReview();
        model.addAttribute("list", reviewService.getReview());
        model.addAttribute("cnt", reviewService.getSearchCnt(searchType, words));
        model.addAttribute("list", reviewService.getSearch(page, searchType, words));
        model.addAttribute("page", reviewService.ReviewPageCalc(page));

        MemberDto loggedInMember = (MemberDto) session.getAttribute("LoginIn");

//        int reviewWrite = reservationMapper.checkReview(idx);
//        model.addAttribute("reviewWrite", reviewWrite);
//        System.out.println(reviewWrite);
        if (loggedInMember != null) {
            int reviewWrite = reservationMapper.checkReview(loggedInMember.getIdx());
            model.addAttribute("reviewWrite", reviewWrite);
            System.out.println(reviewWrite);
            System.out.println("idx: " + loggedInMember.getIdx());
            System.out.println("리뷰 작성 수: " + reviewWrite);
        }


        return "review/review";
    }

    @GetMapping("/write")
    public String getWrite(@ModelAttribute ReviewDto reviewDto, Model model, HttpSession session) {
        model.addAttribute("review", reviewDto);

        MemberDto loggedInMember = (MemberDto) session.getAttribute("LoginIn");

        if (loggedInMember == null || loggedInMember.getUserId() == null) {
            // 로그인이 되어있지 않은 경우 또는 로그인 정보가 부족한 경우에 대한 예외 처리
            model.addAttribute("userId", "Unknown User");
        } else {
            // 세션에서 가져온 사용자의 userId를 모델에 추가
            model.addAttribute("userId", loggedInMember.getUserId());
        }

        List<String> allCampNames = reviewService.getAllCampNames();
        model.addAttribute("allCampNames", allCampNames);

        return "review/reviewWrite";
    }

    @PostMapping("/write")
    public String setWrite(@ModelAttribute ReviewDto reviewDto, @RequestParam("file") MultipartFile mf, HttpSession session) throws IOException {

        // 세션에서 사용자 정보를 가져옴
        MemberDto loggedInMember = (MemberDto) session.getAttribute("LoginIn");

        if (loggedInMember != null) {
            // 사용자 정보가 있는 경우에만 QnaDto에 사용자 ID 설정
            reviewDto.setUserId(loggedInMember.getUserId());

            // 그 외의 필요한 정보도 설정 가능
            // qnaDto.setOtherField(loggedInMember.getOtherField());
        }

        if(!mf.isEmpty()) {

            String folderName = new SimpleDateFormat("yyyyMMdd").format(System.currentTimeMillis());

            File makeFolder = new File(fileDir + folderName);


            if(!makeFolder.exists()) {
                makeFolder.mkdir();
            }

            String orgName = mf.getOriginalFilename();

            String ext = orgName.substring(orgName.lastIndexOf("."));
            String uuid = UUID.randomUUID().toString();

            String saveFileName = uuid + ext;

            String savedFilePathName = fileDir + folderName + "/" + saveFileName;

            /* boardDto => DB*/
            reviewDto.setOrgName(mf.getOriginalFilename());
            reviewDto.setOrgName(orgName);
            reviewDto.setSavedFileName(saveFileName);
            reviewDto.setSavedFilePathName(savedFilePathName);
            reviewDto.setSavedFileSize(mf.getSize());
            reviewDto.setFolderName(folderName);
            reviewDto.setExt(ext);



            /* 파일 업로드 쓰기 */
            mf.transferTo(new File(savedFilePathName));

        }
        int maxGrp = reviewMapper.getMaxGrp();
        reviewDto.setGrp(maxGrp);
        reviewMapper.setWrite(reviewDto);



        return "redirect:/review";
    }

    @GetMapping("/delete")
    public String getDelete(@RequestParam int id) {
        reviewService.setDelete(id);
        return "redirect:/review";
    }

    @GetMapping("/view")
    public String getView(@RequestParam int id, Model model, HttpServletRequest request) {
        model.addAttribute("view", reviewService.getView(id));
        reviewMapper.updateVisit(id);
        boolean isLoggedIn = isLoggedIn(request);
        model.addAttribute("isLoggedIn", isLoggedIn);
        return "review/reviewView";
    }

    @GetMapping("/update")
    public String getUpdate(@RequestParam int id, Model model) {
        ReviewDto bd = reviewMapper.getView(id);
        model.addAttribute("modify", bd);
        List<String> allCampNames = reviewService.getAllCampNames();
        model.addAttribute("allCampNames", allCampNames);
        return "review/reviewUpdate";
    }


    @PostMapping("/update")
    public String setUpdate(@ModelAttribute ReviewDto reviewDto, @RequestParam("file") MultipartFile mf) throws IOException {



        if(!mf.isEmpty()) {

            String folderName = new SimpleDateFormat("yyyyMMdd").format(System.currentTimeMillis());

            File makeFolder = new File(fileDir + folderName);


            if(!makeFolder.exists()) {
                makeFolder.mkdir();
            }

            String orgName = mf.getOriginalFilename();

            String ext = orgName.substring(orgName.lastIndexOf("."));
            String uuid = UUID.randomUUID().toString();

            String saveFileName = uuid + ext;

            String savedFilePathName = fileDir + folderName + "/" + saveFileName;

            /* boardDto => DB*/
            reviewDto.setOrgName(mf.getOriginalFilename());
            reviewDto.setOrgName(orgName);
            reviewDto.setSavedFileName(saveFileName);
            reviewDto.setSavedFilePathName(savedFilePathName);
            reviewDto.setSavedFileSize(mf.getSize());
            reviewDto.setFolderName(folderName);
            reviewDto.setExt(ext);



            /* 파일 업로드 쓰기 */
            mf.transferTo(new File(savedFilePathName));

        }else {
            /* db 저장 객체             html hidden */
            reviewDto.setOrgName(reviewDto.getOrgName());
            reviewDto.setSavedFileName(reviewDto.getSavedFileName());
            reviewDto.setSavedFilePathName(reviewDto.getSavedFilePathName());
            reviewDto.setSavedFileSize(reviewDto.getSavedFileSize());
            reviewDto.setFolderName(reviewDto.getFolderName());
            reviewDto.setExt(reviewDto.getExt());

//            System.out.println(boardDto);

        }

        reviewMapper.setUpdate(reviewDto);


        return "redirect:/review";
    }

    private boolean isLoggedIn(HttpServletRequest request) {
        HttpSession session = request.getSession(false);
        return session != null && session.getAttribute("userId") != null;
    }



}
