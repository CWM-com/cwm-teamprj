package com.example.Project_CWM.controller;

import com.example.Project_CWM.dto.NoticeDto;
import com.example.Project_CWM.dto.ReviewDto;
import com.example.Project_CWM.mappers.NoticeMapper;
import com.example.Project_CWM.mappers.ReviewMapper;
import com.example.Project_CWM.service.NoticeService;
import com.example.Project_CWM.service.ReviewService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.List;
import java.util.UUID;

@Controller
@RequestMapping("/review")
public class ReviewController {

    @Autowired
    ReviewService reviewService;

    @Autowired
    ReviewMapper reviewMapper;

    @Value("${fileDir}")
    String fileDir;

    @GetMapping("")
    public String getReview(Model model, @RequestParam(value = "searchType", defaultValue = "") String searchType, @RequestParam(value = "words", defaultValue = "") String words, @RequestParam(value="page", defaultValue = "1") int page) {
        List<ReviewDto> review = reviewService.getReview();
        model.addAttribute("list", reviewService.getReview());
        model.addAttribute("cnt", reviewService.getSearchCnt(searchType, words));
        model.addAttribute("list", reviewService.getSearch(page, searchType, words));
        model.addAttribute("page", reviewService.ReviewPageCalc(page));
        return "review/review";
    }

    @GetMapping("/write")
    public String getWrite(@ModelAttribute ReviewDto reviewDto, Model model) {
        model.addAttribute("review", reviewDto);
        return "review/reviewWrite";
    }

    @PostMapping("/write")
    public String setWrite(@ModelAttribute ReviewDto reviewDto, @RequestParam("file") MultipartFile mf) throws IOException {

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
    public String getView(@RequestParam int id, Model model) {
        model.addAttribute("view", reviewService.getView(id));
        reviewMapper.updateVisit(id);
        return "review/reviewView";
    }

    @GetMapping("/update")
    public String getUpdate(@RequestParam int id, Model model) {
        ReviewDto bd = reviewMapper.getView(id);
        model.addAttribute("modify", bd);
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

}
