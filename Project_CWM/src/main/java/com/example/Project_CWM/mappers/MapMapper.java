package com.example.Project_CWM.mappers;


import com.example.Project_CWM.dto.MapDto;
import org.apache.ibatis.annotations.*;

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
    @Insert("insert into placeinfo values(null, #{placeCode}, #{placeName}, #{placeAddr}, #{placeCall}, 0, 0, 0, now())")
    void setPlace(MapDto mapDto);

    //캠핑장 코드 중복 확인
    @Select("select count(*) from placeinfo where place_code = #{placeCode}")
    int getCheckPlaceCode(String placeCode);

    //캠핑장 삭제
    @Delete("delete from placeinfo where place_code = #{placeCode}")
    void deletePlace(String placeCode);

    //캠핑장 추가시 첨부파일 테이블 생성
    @Select("create table place_${placeCode}_files(\n" +
            "orgName varchar(255),\n" +
            "savedFileName varchar(255),\n" +
            "savedPathName varchar(255),\n" +
            "savedFileSize bigint,\n" +
            "folderName varchar(10),\n" +
            "ext varchar(20)\n" +
            ");")
    void makeFiles(String placeCode);

    //캠핑장 제거시 첨부파일 테이블 제거
    @Select("drop table place_${placeCode}_files")
    void dropFiles(String placeCode);

    //캠핑당 디테일 출력
    @Select("select * from placeinfo where place_code = #{placeCode}")
    public MapDto getDetail(String placeCode);

    //캠핑장 조회수 업데이트
    @Update("update placeinfo set visit = visit + 1 where place_code = #{placeCode}")
    void updateVisit(String placeCode);


}
