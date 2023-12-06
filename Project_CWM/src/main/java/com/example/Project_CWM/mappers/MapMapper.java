package com.example.Project_CWM.mappers;


import com.example.Project_CWM.dto.MapDto;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

import java.util.List;
import java.util.Map;

@Mapper
public interface MapMapper {
    @Select("select count(*) from placeinfo ${searchQuery}")
    int getListCount(Map<String, Object> map);

//    @Select("select count(*) from placeinfo where place_code = #{placeCode}")
//    String getCheckPlaceCode(String placeCode);

    @Insert("insert into placeinfo values(null, #{placeName}, #{placeAddrCode}, #{placeAddrDetail}, #{placeCall}, 0, 0, now())")
    void setPlace(MapDto mapDto);

    @Select("select * from placeinfo order by ${order} desc")
    List<MapDto> getPlaceList(Map<String, Object> mapp);
}
