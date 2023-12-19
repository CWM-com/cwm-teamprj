package com.example.Project_CWM.controller;

import com.example.Project_CWM.service.NoticeService;
import com.example.Project_CWM.dto.NoticeDto;
import com.example.Project_CWM.dto.QnaDto;
import com.example.Project_CWM.mappers.NoticeMapper;
import com.example.Project_CWM.mappers.QnaMapper;
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
@RequestMapping("/notice")
public class NoticeController {

    @Autowired
    NoticeService noticeService;

    @Autowired
    NoticeMapper noticeMapper;

    @Value("${fileDir}")
    String fileDir;

    @GetMapping("")
    public String getNotice(Model model, @RequestParam(value = "searchType", defaultValue = "") String searchType, @RequestParam(value = "words", defaultValue = "") String words, @RequestParam(value="page", defaultValue = "1") int page) {
        List<NoticeDto> notice = noticeService.getNotice();
        model.addAttribute("list", noticeService.getNotice());

        model.addAttribute("cnt", noticeService.getSearchCnt(searchType, words));
        model.addAttribute("list", noticeService.getSearch(page, searchType, words));
        model.addAttribute("page", noticeService.NoticePageCalc(page));
        model.addAttribute("qna", notice);
        return "notice/notice";
    }

    @GetMapping("/write")
    public String getWrite(@ModelAttribute NoticeDto noticeDto, Model model) {
        model.addAttribute("notice", noticeDto);
        return "notice/noticeWrite";
    }

    @PostMapping("/write")
    public String setWrite(@ModelAttribute NoticeDto noticeDto, @RequestParam("file") MultipartFile mf) throws IOException {

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
            noticeDto.setOrgName(mf.getOriginalFilename());
            noticeDto.setOrgName(orgName);
            noticeDto.setSavedFileName(saveFileName);
            noticeDto.setSavedFilePathName(savedFilePathName);
            noticeDto.setSavedFileSize(mf.getSize());
            noticeDto.setFolderName(folderName);
            noticeDto.setExt(ext);



            /* 파일 업로드 쓰기 */
            mf.transferTo(new File(savedFilePathName));

        }
        int maxGrp = noticeMapper.getMaxGrp();
        noticeDto.setGrp(maxGrp);
        noticeMapper.setWrite(noticeDto);

        return "redirect:/notice";
    }

    @GetMapping("/delete")
    public String getDelete(@RequestParam int id) {
        noticeService.setDelete(id);
        return "redirect:/notice";
    }

    @GetMapping("/view")
    public String getView(@RequestParam int id, Model model) {
        model.addAttribute("view", noticeService.getView(id));

        return "notice/noticeView";
    }

    @GetMapping("/update")
    public String getUpdate(@RequestParam int id, Model model) {
        NoticeDto bd = noticeMapper.getView(id);
        model.addAttribute("modify", bd);
        return "notice/noticeUpdate";
    }


    @PostMapping("/update")
    public String setUpdate(@ModelAttribute NoticeDto noticeDto, @RequestParam("file") MultipartFile mf) throws IOException {

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
            noticeDto.setOrgName(mf.getOriginalFilename());
            noticeDto.setOrgName(orgName);
            noticeDto.setSavedFileName(saveFileName);
            noticeDto.setSavedFilePathName(savedFilePathName);
            noticeDto.setSavedFileSize(mf.getSize());
            noticeDto.setFolderName(folderName);
            noticeDto.setExt(ext);



            /* 파일 업로드 쓰기 */
            mf.transferTo(new File(savedFilePathName));

        }else {
            /* db 저장 객체             html hidden */
            noticeDto.setOrgName(noticeDto.getOrgName());
            noticeDto.setSavedFileName(noticeDto.getSavedFileName());
            noticeDto.setSavedFilePathName(noticeDto.getSavedFilePathName());
            noticeDto.setSavedFileSize(noticeDto.getSavedFileSize());
            noticeDto.setFolderName(noticeDto.getFolderName());
            noticeDto.setExt(noticeDto.getExt());

//            System.out.println(boardDto);

        }

        noticeMapper.setUpdate(noticeDto);


        return "redirect:/notice";
    }


}
