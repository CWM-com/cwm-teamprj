package com.example.Project_CWM.service;



import com.example.Project_CWM.dto.PlaceDto;
import com.example.Project_CWM.dto.PlaceFilesDto;
import com.example.Project_CWM.dto.PlacePageDto;
import com.example.Project_CWM.mappers.PlaceMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class PlaceService {

    @Autowired
    PlaceMapper placeMapper;

    @Value("${fileDir}")
    String fileDir;

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

    public PlacePageDto PageCalc(int page, String selectType, String search){
        PlacePageDto placePageDto = new PlacePageDto();

        String searchQuery =selectSearch(selectType, search);

        int totalCount = placeMapper.getSearchCount(searchQuery);
        int totalPage = (int)Math.ceil((double)totalCount / placePageDto.getPageCount());
        int startPage = ((int)(Math.ceil((double)page / placePageDto.getBlockCount())) - 1) * placePageDto.getBlockCount() + 1;
        int endPage = startPage + placePageDto.getBlockCount() - 1;

        if(endPage > totalPage){
            endPage = totalPage;
        }
        placePageDto.setStartNum((page - 1) * placePageDto.getPageCount());
        placePageDto.setPage(page);
        placePageDto.setStartPage(startPage);
        placePageDto.setEndPage(endPage);
        placePageDto.setTotalPage(totalPage);

        return placePageDto;
    }

    public List<PlaceDto> getSearch(int page, String selectType, String search){
        Map<String, Object> mapp = new HashMap<>();
        PlacePageDto placePageDto = PageCalc(page, selectType, search);

        String searchQuery = selectSearch(selectType, search);

        mapp.put("searchQuery", searchQuery);
        mapp.put("startNum", placePageDto.getStartNum());
        mapp.put("offset", placePageDto.getPageCount());

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

    public PlaceDto getDetail(String placeCode){
        return placeMapper.getDetail(placeCode);
    }
    public List<PlaceFilesDto> getMainFiles(){
        return placeMapper.getMainFiles();
    }
    public void setFiles(PlaceFilesDto mapFileDto){
        placeMapper.setFiles(mapFileDto);
    }
    public void setAddr(PlaceDto placeDto){
        placeMapper.setAddr(placeDto);
    }


}
