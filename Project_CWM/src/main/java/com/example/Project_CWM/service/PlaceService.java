package com.example.Project_CWM.service;



import com.example.Project_CWM.dto.*;
import com.example.Project_CWM.mappers.PlaceMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class PlaceService {

    @Autowired
    PlaceMapper placeMapper;

    @Value("${fileDir}")
    String fileDir;

    public Map<String, String> selectSearch(String selectType, String search){
        String searchQuery = "";
        Map<String, String> select = new HashMap<>();
        if(!search.isEmpty()){
            if(selectType.equals("regdate")){
                searchQuery = "where place_name like '%" + search + "%' order by regdate desc";
                select.put("searchQuery", searchQuery);
                select.put("selectType", selectType);
            }else if(selectType.equals("name")){
                searchQuery = "where place_name like '%" + search + "%' order by place_name asc";
                select.put("searchQuery", searchQuery);
                select.put("selectType", selectType);
            }else if(selectType.equals("code")){
                searchQuery = "where place_name like '%" + search + "%' order by place_code asc";
                select.put("searchQuery", searchQuery);
                select.put("selectType", selectType);
            }else{
                searchQuery = "where place_name like '%" + search + "%' order by place_id desc";
                select.put("searchQuery", searchQuery);
                select.put("selectType", "");
            }
        }else{
            if(selectType.equals("regdate")){
                searchQuery = "order by regdate desc";
                select.put("searchQuery", searchQuery);
                select.put("selectType", selectType);
            }else if(selectType.equals("name")){
                searchQuery = "order by place_name asc";
                select.put("searchQuery", searchQuery);
                select.put("selectType", selectType);
            }else if(selectType.equals("code")){
                searchQuery = "order by place_code asc";
                select.put("searchQuery", searchQuery);
                select.put("selectType", selectType);
            }else{
                searchQuery = "order by place_id desc";
                select.put("searchQuery", searchQuery);
                select.put("selectType", "");
            }
        }
        return select;
    }

    public PageDto PageCalc(int page, String selectType, String search){
        PageDto pageDto = new PageDto();

        String searchQuery = selectSearch(selectType, search).get("searchQuery");

        int totalCount = placeMapper.getSearchCount(searchQuery);
        int totalPage = (int)Math.ceil((double)totalCount / pageDto.getPlacePageCount());
        int startPage = ((int)(Math.ceil((double)page / pageDto.getBlockCount())) - 1) * pageDto.getBlockCount() + 1;
        int endPage = startPage + pageDto.getBlockCount() - 1;

        if(endPage > totalPage){
            endPage = totalPage;
        }
        pageDto.setStartNum((page - 1) * pageDto.getPlacePageCount());
        pageDto.setPage(page);
        pageDto.setStartPage(startPage);
        pageDto.setEndPage(endPage);
        pageDto.setTotalPage(totalPage);

        return pageDto;
    }

    public List<PlaceDto> getSearch(int page, String selectType, String search){
        Map<String, Object> mapp = new HashMap<>();
        PageDto pageDto = PageCalc(page, selectType, search);

        String searchQuery = selectSearch(selectType, search).get("searchQuery");

        mapp.put("searchQuery", searchQuery);
        mapp.put("startNum", pageDto.getStartNum());
        mapp.put("offset", pageDto.getPlacePageCount());

        return placeMapper.getSearch(mapp);
    }

    public void setPlace(PlaceDto placeDto){
        placeMapper.setPlace(placeDto);
    }

    public int getCheckPlaceCode(String placeCode){
        return placeMapper.getCheckPlaceCode(placeCode);
    }

    public void deletePlace(String placeCode){
        placeMapper.deletePlace(placeCode);
    }

    public void dropFiles(String placeCode){
        placeMapper.dropFiles(placeCode);
    }
    public void dropAddr(String placeCode){
        placeMapper.dropAddr(placeCode);
    }
    public void dropContent(String placeCode){placeMapper.dropContent(placeCode);}

    public PlaceDto getDetail(String placeCode){
        return placeMapper.getDetail(placeCode);
    }
    public List<PlaceFilesDto> getMainFiles(){
        return placeMapper.getMainFiles();
    }
    public PlaceDto getContent(String placeCode){return placeMapper.getContent(placeCode);}
    public void setFiles(PlaceFilesDto mapFileDto){
        placeMapper.setFiles(mapFileDto);
    }
    public void setAddr(MapDto mapDto){
        placeMapper.setAddr(mapDto);
    }
    public void setContent(PlaceDto placeDto){placeMapper.setContent(placeDto);}

//    public void addBookmark(String placeCode, int idx){
//        placeMapper.addBookmark(placeCode, idx);
//    }
//    public void delBookmark(String placeCode, int idx){
//        placeMapper.delBookmark(placeCode, idx);
//    }
//    public void updateBookmark(String placeCode){
//        placeMapper.updateBookmark(placeCode);
//    }

    public List<PlaceDto> getBestPlace() {
        return placeMapper.getBestPlace();
    }
}
