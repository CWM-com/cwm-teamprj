package com.example.Project_CWM.service;

import com.example.Project_CWM.dto.PageDto;
import com.example.Project_CWM.dto.ReservationOrderDto;
import com.example.Project_CWM.mappers.MemberMapper;
import com.example.Project_CWM.mappers.ReservationMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class MypageService {

    @Autowired
    private MemberMapper memberMapper;
    @Autowired
    private ReservationMapper reservationMapper;

    public void setinfoUpdate(String userPasswd, String userTel, int idx) {

        if (!userPasswd.isEmpty() && !userTel.isEmpty() && idx != 0) {
           memberMapper.setinfoUpdate(userPasswd,userTel,idx);
        }
    }

    public List<ReservationOrderDto> recentCamp(int idx) {
        return reservationMapper.recentCamp(idx);
    }

    public void Infodelete(int idx) {

        memberMapper.Infodelete(idx);
    }

    public String selectSearch(String search) {
        String searchQuery = "";

        if(search.equals("complete")) {
            searchQuery = " reserv_status = '"+ search+ "' AND ";
        }else if(search.equals("cancel")) {
            searchQuery = " reserv_status = '"+ search +"' AND ";
        }else {
            searchQuery = "";
        }
        return searchQuery;
    }

    public List<ReservationOrderDto> OrderList(String memIdx, int page, String search){
        Map<String,Object> map = new HashMap<>();

        if(!memIdx.isEmpty()) {

            String searchQuery = selectSearch(search);

            PageDto pageDto = new PageDto();
            int startNum = (page - 1) * pageDto.getMyPageCount();

            map.put("memIdx", memIdx);
            map.put("startNum", startNum);
            map.put("offset", pageDto.getMyPageCount());
            map.put("searchQuery", searchQuery);
            return reservationMapper.OrderList(map);
        }else {
            return null;
        }
    }

    public PageDto ReservCount(int page, String memIdx, String search) {
        PageDto pageDto = new PageDto();

        String searchQuery = selectSearch(search);

        int totalCount = reservationMapper.ReservCount(memIdx,searchQuery);
        int totalPage = (int) Math.ceil((double) totalCount / pageDto.getMyPageCount());
        int startPage = ((int) (Math.ceil((double) page / pageDto.getBlockCount()))-1) * pageDto.getBlockCount() + 1;
        int endPage = startPage + pageDto.getBlockCount() -1;

        if(endPage > totalPage) {
            endPage = totalPage;
        }
        pageDto.setStartNum((page -1) * pageDto.getBlockCount());
        pageDto.setPage(page);
        pageDto.setStartPage(startPage);
        pageDto.setEndPage(endPage);
        pageDto.setTotalPage(totalPage);

        return pageDto;
    }
}
