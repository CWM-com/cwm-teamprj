package com.example.Project_CWM.mappers;


import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

import java.util.Map;

@Mapper
public interface MapMapper {
    @Select("select count(*) from placeinfo ${searchQuery}")
    int getListCount(Map<String, Object> map);
}
