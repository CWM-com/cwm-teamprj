package com.example.Project_CWM.service;

import com.example.Project_CWM.dto.MypagePageDto;
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

            MypagePageDto mypagePageDto = new MypagePageDto();
            int startNum = (page - 1) * mypagePageDto.getPageCount();

            map.put("memIdx", memIdx);
            map.put("startNum", startNum);
            map.put("offset", mypagePageDto.getPageCount());
            map.put("searchQuery", searchQuery);
            return reservationMapper.OrderList(map);
        }else {
            return null;
        }
    }

    public MypagePageDto ReservCount(int page, String memIdx, String search) {
        MypagePageDto mypagePageDto = new MypagePageDto();

        String searchQuery = selectSearch(search);

        int totalCount = reservationMapper.ReservCount(memIdx,searchQuery);
        int totalPage = (int) Math.ceil((double) totalCount / mypagePageDto.getPageCount());
        int startPage = ((int) (Math.ceil((double) page / mypagePageDto.getBlockCount()))-1) * mypagePageDto.getBlockCount() + 1;
        int endPage = startPage + mypagePageDto.getBlockCount() -1;

        if(endPage > totalPage) {
            endPage = totalPage;
        }
        mypagePageDto.setStartNum((page -1) * mypagePageDto.getBlockCount());
        mypagePageDto.setPage(page);
        mypagePageDto.setStartPage(startPage);
        mypagePageDto.setEndPage(endPage);
        mypagePageDto.setTotalPage(totalPage);

        return mypagePageDto;
    }
}
