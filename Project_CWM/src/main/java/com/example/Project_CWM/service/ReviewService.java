package com.example.Project_CWM.service;

import com.example.Project_CWM.dto.NoticeDto;
import com.example.Project_CWM.dto.PageDto;
import com.example.Project_CWM.dto.ReviewDto;
import com.example.Project_CWM.mappers.NoticeMapper;
import com.example.Project_CWM.mappers.ReviewMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class ReviewService {

    @Autowired
    ReviewMapper reviewMapper;

    public List<ReviewDto> getReview() {
        return reviewMapper.getReview();
    }

    public void setWrite(ReviewDto reviewDto) {
        reviewMapper.setWrite(reviewDto);
    }

    public void setDelete(int id) {
        reviewMapper.setDelete(id);
    }

    public ReviewDto getView(int id) {
        return reviewMapper.getView(id);
    }




    public List<ReviewDto> getSearch(int page, String searchType, String words) {
        Map<String, Object> map = new HashMap<>();

        String searchQuery = "";

        if(searchType.equals("subject")||searchType.equals("content")) {
            searchQuery = "where " + searchType + " = '" + words + "'";
        }else {
            searchQuery = "";
        }

        PageDto pageDto = new PageDto();

        int startNum = (page - 1) * pageDto.getPageCount();
        map.put("searchQuery", searchQuery);
        map.put("startNum", startNum);
        map.put("offset", pageDto.getPageCount());

        return reviewMapper.getList(map);
    }

    public int getSearchCnt(String searchType, String words) {
        Map<String, Object> map = new HashMap<>();

        String searchQuery = "";

        if(searchType.equals("subject")||searchType.equals("content")) {
            searchQuery = "where " + searchType + " = '" + words + "'";
        } else {
            searchQuery = "";
        }
        map.put("searchQuery", searchQuery);

        return reviewMapper.getListCount(map);
    }

    public PageDto ReviewPageCalc(int page) {
        PageDto pageDto = new PageDto();

        int totalCount = reviewMapper.totalCount();
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
