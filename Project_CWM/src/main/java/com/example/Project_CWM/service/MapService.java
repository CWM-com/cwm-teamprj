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

//    public List<MapDto> getSearch(int page, String search){
//        Map<String, Object> map = new HashMap<>();

//        String searchQuery = "";

//        if(!search.isEmpty()){
//            searchQuery = "where placeName like '%" + search + "%' ";
//        }else{
//            searchQuery = "";
//        }

//        MapPageDto mapPageDto = new MapPageDto();

//        int startNum = (page - 1) * mapPageDto.getPageCount();

//        map.put("startNum", startNum);
//        map.put("offset", mapPageDto.getPageCount());

//        return mapMapper.getList(map);
//    }

    public int getSearchCnt(String search){
        Map<String, Object> map = new HashMap<>();

        String searchQuery = "";

        if(!search.isEmpty()){
            searchQuery = "where placeName like '%" + search + "%' ";
        }else{
            searchQuery = "";
        }
        map.put("searchQuery", searchQuery);


        return mapMapper.getListCount(map);
    }

//    public String getCheckPlaceCode(String placeCode){
//        return mapMapper.getCheckPlaceCode(placeCode);
//    }

    public void setPlace(MapDto mapDto){
        mapMapper.setPlace(mapDto);
    }

    public List<MapDto> getPlaceList(String selectType){

        Map<String, Object> mapp = new HashMap<>();
        String order = "";

        if(selectType.equals("date")){
            order = "regdate";
        }else if(selectType.equals("star")){
            order = "place_star";
        }else{
            order = "place_name";
        }
        mapp.put("order", order);

        return mapMapper.getPlaceList(mapp);
    }
}
