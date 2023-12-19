package com.example.Project_CWM.mappers;

import com.example.Project_CWM.dto.MapDto;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

import java.util.List;

@Mapper
public interface MapMapper {

    @Select("select * from placecoor")
    List<MapDto> getMapList(MapDto mapDto);
}
