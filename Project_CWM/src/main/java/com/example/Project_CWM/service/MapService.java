package com.example.Project_CWM.service;



import com.example.Project_CWM.dto.MapDto;
import com.example.Project_CWM.dto.MapPageDto;
import com.example.Project_CWM.mappers.MapMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class MapService {

    @Autowired
    MapMapper mapMapper;

    public List<MapDto> getSearch(int page, String search, String selectType){
        Map<String, Object> mapp = new HashMap<>();
        MapPageDto mapPageDto = new MapPageDto();

        String searchQuery = "";

        if(!search.isEmpty()){
            if(selectType.equals("option1")){
                searchQuery = "where place_name like '%" + search + "%' order by regdate desc";
            }else if(selectType.equals("option2")){
                searchQuery = "where place_name like '%" + search + "%' order by place_star desc";
            }else if(selectType.equals("option3")){
                searchQuery = "where place_name like '%" + search + "%' order by place_name desc";
            }
        }else{
            if(selectType.equals("option1")){
                searchQuery = "order by regdate desc";
            }else if(selectType.equals("option2")){
                searchQuery = "order by place_star desc";
            }else if(selectType.equals("option3")){
                searchQuery = "order by place_name desc";
            }
        }

        int startNum = (page - 1) * mapPageDto.getPageCount();

        mapp.put("searchQuery", searchQuery);
        mapp.put("startNum", startNum);
        mapp.put("offset", mapPageDto.getPageCount());

        return mapMapper.getSearch(mapp);
    }

    public int getSearchCnt(String search, String selectType){
        Map<String, Object> mapp = new HashMap<>();

        String searchQuery = "";

        if(!search.isEmpty()){
            if(selectType.equals("option1")){
                searchQuery = "where place_name like '%" + search + "%' order by regdate desc";
            }else if(selectType.equals("option2")){
                searchQuery = "where place_name like '%" + search + "%' order by place_star desc";
            }else if(selectType.equals("option3")){
                searchQuery = "where place_name like '%" + search + "%' order by place_name desc";
            }
        }else{
            if(selectType.equals("option1")){
                searchQuery = "order by regdate desc";
            }else if(selectType.equals("option2")){
                searchQuery = "order by place_star desc";
            }else if(selectType.equals("option3")){
                searchQuery = "order by place_name desc";
            }
        }
        mapp.put("searchQuery", searchQuery);


        return mapMapper.getSearchCount(mapp);
    }

    public void setPlace(MapDto mapDto){
        mapMapper.setPlace(mapDto);
    }

    public MapPageDto PageCalc(int page){
        MapPageDto mapPageDto = new MapPageDto();

        int totalCount = mapMapper.totalCount();
        int totalPage = (int)Math.ceil((double)totalCount / mapPageDto.getPageCount());

        int startPage = ((int)(Math.ceil((double)page / mapPageDto.getBlockCount())) - 1) * mapPageDto.getBlockCount() + 1;
        int endPage = startPage + mapPageDto.getBlockCount() - 1;

        if(endPage > totalPage){
            endPage = totalPage;
        }

        mapPageDto.setPage(page);
        mapPageDto.setStartPage(startPage);
        mapPageDto.setEndPage(endPage);
        mapPageDto.setTotalPage(totalPage);

        return mapPageDto;
    }
}
