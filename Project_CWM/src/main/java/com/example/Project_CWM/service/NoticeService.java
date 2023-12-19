package com.example.Project_CWM.service;

import com.example.Project_CWM.dto.NoticeDto;
import com.example.Project_CWM.dto.PageDto;
import com.example.Project_CWM.mappers.NoticeMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class NoticeService {

    @Autowired
    NoticeMapper noticeMapper;

    public List<NoticeDto> getNotice() {
        return noticeMapper.getNotice();
    }

    public void setWrite(NoticeDto noticeDto) {
        noticeMapper.setWrite(noticeDto);
    }

    public void setDelete(int id) {
        noticeMapper.setDelete(id);
    }

    public NoticeDto getView(int id) {
        return noticeMapper.getView(id);
    }




    public List<NoticeDto> getSearch(int page, String searchType, String words) {
        Map<String, Object> map = new HashMap<>();

        String searchQuery = "";

        if(searchType.equals("subject")||searchType.equals("writer")) {
            searchQuery = "where " + searchType + " = '" + words + "'";
        }else {
            searchQuery = "";
        }

        PageDto pageDto = new PageDto();
//        System.out.println(searchQuery);
        //limit 시작, 개수

        int startNum = (page - 1) * pageDto.getPageCount();
        map.put("searchQuery", searchQuery);
        map.put("startNum", startNum);
        map.put("offset", pageDto.getPageCount());
//        System.out.println(searchQuery);

//        System.out.println(pageDto);
//        System.out.println(startNum);
        return noticeMapper.getList(map); //boardMapper.getList()로 보냄
    }

    public int getSearchCnt(String searchType, String words) {
        Map<String, Object> map = new HashMap<>();

        String searchQuery = "";
//        if -> subject, writer, content

        //searchType
        //subject, writer => where
        //content =>  where like
        if(searchType.equals("subject")||searchType.equals("writer")) {
            searchQuery = "where " + searchType + " = '" + words + "'";
//            System.out.println(searchQuery);
        } else {
            searchQuery = "";
//            System.out.println(searchQuery);
        }
//        System.out.println(searchQuery);
        map.put("searchQuery", searchQuery);

        return noticeMapper.getListCount(map); //boardMapper.getListCount()로 보냄
    }






    /* 페이지 알고리즘 구현 */
    public PageDto NoticePageCalc(int page) {
        PageDto pageDto = new PageDto();

        int totalCount = noticeMapper.totalCount();
        int totalPage = (int)Math.ceil((double) totalCount / pageDto.getPageCount());



        int startPage = ((int)(Math.ceil((double)page / pageDto.getBlockCount())) - 1) * pageDto.getBlockCount() + 1;
        int endPage = startPage + pageDto.getBlockCount() - 1;

        if (endPage > totalPage) {
            endPage = totalPage;
        }

        pageDto.setPage(page);
        pageDto.setStartPage(startPage);
        pageDto.setEndPage(endPage);
        pageDto.setTotalPage(totalPage);

        return pageDto;
    }
}
