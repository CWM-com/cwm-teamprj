package com.example.Project_CWM.mappers;


import com.example.Project_CWM.dto.MapDto;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

import java.util.List;
import java.util.Map;

@Mapper
public interface MapMapper {
    //조건에 맞는 캠핑장 출력
    @Select("select * from placeinfo ${searchQuery} limit #{startNum}, #{offset}")
    List<MapDto> getSearch(Map<String, Object> mapp);
    //조건에 맞는 캠핑장 수 출력
    @Select("select count(*) from placeinfo ${searchQuery}")
    int getSearchCount(Map<String, Object> mapp);
    //전체 캠핑장 수 출력
    @Select("select count(*) from placeinfo")
    int totalCount();
    //신규 캠핑장 추가
    @Insert("insert into placeinfo values(null, #{placeName}, #{placeAddrCode}, #{placeAddrDetail}, #{placeCall}, 0, 0, now())")
    void setPlace(MapDto mapDto);


}
