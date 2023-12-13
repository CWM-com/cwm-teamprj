package com.example.Project_CWM.mappers;


import com.example.Project_CWM.dto.MapDto;
import com.example.Project_CWM.dto.MapFilesDto;
import org.apache.ibatis.annotations.*;

import java.util.List;
import java.util.Map;

@Mapper
public interface MapMapper {
    //신규 캠핑장 추가
    @Insert("insert into placeinfo values(null, #{placeCode}, #{placeName}, #{placeAddr}, #{placeCall}, 0, 0, 0, now())")
    void setPlace(MapDto mapDto);

    //캠핑장 코드 중복 확인
    @Select("select count(*) from placeinfo where place_code = #{placeCode}")
    int getCheckPlaceCode(String placeCode);

    //캠핑장 제거
    @Delete("delete from placeinfo where place_code = #{placeCode}")
    void deletePlace(String placeCode);

    //캠핑장 제거시 헤딩 첨부파일 테이블 열 제거
    @Delete("delete from placefiles where place_code = #{placeCode}")
    void dropFiles(String placeCode);

    //조건에 맞는 캠핑장 수 출력
    @Select("select count(*) from placeinfo ${searchQuery}")
    int getSearchCount(String searchQuery);

    //조건에 맞는 캠핑장 출력
    @Select("select * from placeinfo ${searchQuery} limit #{startNum}, #{offset}")
    List<MapDto> getSearch(Map<String, Object> mapp);

    //캠핑당 디테일 출력
    @Select("select * from placeinfo where place_code = #{placeCode}")
    public MapDto getDetail(String placeCode);
    @Select("select * from placefiles where fileType = 'main'")
    public List<MapFilesDto> getMainFiles();
    @Select("select * from placefiles where fileType = 'detail'")
    public List<MapFilesDto> getDetailFiles();
    @Select("select * from placefiles where fileType = 'around'")
    public List<MapFilesDto> getAroundFiles();
    @Select("select * from placefiles where place_code = #{placeCode}")
    public MapFilesDto getFilesDetail(String placeCode);

    //캠핑장 조회수 업데이트
    @Update("update placeinfo set visit = visit + 1 where place_code = #{placeCode}")
    void updateVisit(String placeCode);

    //캠핑장 추가시 첨부파일 입력
    @Insert("insert into placefiles values(#{placeCode}, #{fileType}, #{orgName}, #{savedFileName}, #{savedPathName}, #{savedFileSize}, #{folderName}, #{ext})")
    public void setFiles(MapFilesDto mapFileDto);






}
