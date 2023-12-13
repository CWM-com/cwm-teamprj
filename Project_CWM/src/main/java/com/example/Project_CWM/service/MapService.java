package com.example.Project_CWM.service;



import com.example.Project_CWM.dto.MapDto;
import com.example.Project_CWM.dto.MapFilesDto;
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

    public String selectSearch(String selectType, String search){
        String searchQuery = "";
        if(!search.isEmpty()){
            if(selectType.equals("regdate")){
                searchQuery = "where place_name like '%" + search + "%' order by regdate desc";
            }else if(selectType.equals("pop")){
                searchQuery = "where place_name like '%" + search + "%' order by star desc";
            }else if(selectType.equals("name")){
                searchQuery = "where place_name like '%" + search + "%' order by place_name asc";
            }else if(selectType.equals("code")){
                searchQuery = "where place_name like '%" + search + "%' order by place_code asc";
            }else{
                searchQuery = "where place_name like '%" + search + "%' order by place_id desc";
            }
        }else{
            if(selectType.equals("regdate")){
                searchQuery = "order by regdate desc";
            }else if(selectType.equals("pop")){
                searchQuery = "order by star desc";
            }else if(selectType.equals("name")){
                searchQuery = "order by place_name asc";
            }else if(selectType.equals("code")){
                searchQuery = "order by place_code asc";
            }else{
                searchQuery = "order by place_id desc";
            }
        }
        return searchQuery;
    }

    public MapPageDto PageCalc(int page, String selectType, String search){
        MapPageDto mapPageDto = new MapPageDto();

        String searchQuery =selectSearch(selectType, search);

        int totalCount = mapMapper.getSearchCount(searchQuery);
        int totalPage = (int)Math.ceil((double)totalCount / mapPageDto.getPageCount());
        int startPage = ((int)(Math.ceil((double)page / mapPageDto.getBlockCount())) - 1) * mapPageDto.getBlockCount() + 1;
        int endPage = startPage + mapPageDto.getBlockCount() - 1;

        if(endPage > totalPage){
            endPage = totalPage;
        }
        mapPageDto.setStartNum((page - 1) * mapPageDto.getPageCount());
        mapPageDto.setPage(page);
        mapPageDto.setStartPage(startPage);
        mapPageDto.setEndPage(endPage);
        mapPageDto.setTotalPage(totalPage);

        return mapPageDto;
    }

    public List<MapDto> getSearch(int page, String selectType, String search){
        Map<String, Object> mapp = new HashMap<>();
        MapPageDto mapPageDto = PageCalc(page, selectType, search);

        String searchQuery = selectSearch(selectType, search);

        mapp.put("searchQuery", searchQuery);
        mapp.put("startNum", mapPageDto.getStartNum());
        mapp.put("offset", mapPageDto.getPageCount());

        return mapMapper.getSearch(mapp);
    }

    public void setPlace(MapDto mapDto){
        mapMapper.setPlace(mapDto);
    }

    public int getCheckPlaceCode(String placeCode){
        return mapMapper.getCheckPlaceCode(placeCode);
    }

    public void deletePlace(String placeCode){
        mapMapper.deletePlace(placeCode);
    }

    public void dropFiles(String placeCode){
        mapMapper.dropFiles(placeCode);
    }

    public MapDto getDetail(String placeCode){
        return mapMapper.getDetail(placeCode);
    }
    public List<MapFilesDto> getMainFiles(){
        return mapMapper.getMainFiles();
    }
    public List<MapFilesDto> getDetailFiles(){
        return mapMapper.getDetailFiles();
    }
    public List<MapFilesDto> getAroundFiles(){
        return mapMapper.getAroundFiles();
    }
    public MapFilesDto getFilesDetail(String placeCode){
        return mapMapper.getFilesDetail(placeCode);
    }
    public void setFiles(MapFilesDto mapFileDto){
        mapMapper.setFiles(mapFileDto);
    }

}
