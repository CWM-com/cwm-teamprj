package com.example.Project_CWM.mappers;


import com.example.Project_CWM.dto.MapDto;
import com.example.Project_CWM.dto.PlaceDto;
import com.example.Project_CWM.dto.PlaceFilesDto;
import org.apache.ibatis.annotations.*;

import java.util.List;
import java.util.Map;

@Mapper
public interface PlaceMapper {
    //신규 캠핑장 추가
    @Insert("insert into placeinfo values(null, #{placeCode}, #{placeName}, #{placeAddr}, #{placeCall}, 0, 0, 0, now())")
    void setPlace(PlaceDto placeDto);

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
    List<PlaceDto> getSearch(Map<String, Object> mapp);

    //캠핑당 디테일 출력
    @Select("select * from placeinfo where place_code = #{placeCode}")
    public PlaceDto getDetail(String placeCode);
    @Select("select * from placefiles where fileType = 'main'")
    public List<PlaceFilesDto> getMainFiles();
    @Select("select * from placefiles where fileType = 'detail' and place_code = #{placeCode}")
    public List<PlaceFilesDto> getDetailFiles(String placeCode);
    @Select("select * from placefiles where fileType = 'around'")
    public List<PlaceFilesDto> getAroundFiles();
    @Select("select * from placefiles where place_code = #{placeCode}")
    public PlaceFilesDto getFilesDetail(String placeCode);

    //캠핑장 조회수 업데이트
    @Update("update placeinfo set visit = visit + 1 where place_code = #{placeCode}")
    void updateVisit(String placeCode);

    //캠핑장 추가시 첨부파일 입력
    @Insert("insert into placefiles values(#{placeCode}, #{fileType}, #{orgName}, #{savedFileName}, #{savedPathName}, #{savedFileSize}, #{folderName}, #{ext})")
    public void setFiles(PlaceFilesDto mapFileDto);

    //캠핑장 추가시 주소 입력
    @Insert("insert into placecoor values(#{placeCode}, #{placeAddr}, null, null)")
    public void setAddr(MapDto mapDto);

    //캠핑장 추가시 주소 좌표 업데이트
    @Update("update placecoor set place_x = #{placeX}, place_y = {placeY} where place_code = #{placeCode}")
    public void updateAddr(MapDto mapDto);

    //캠핑장 북마크 추가
    @Insert("insert into placebookmark values(#{placeCode}, #{idx}, 1, null)")
    void setBookmark(String placeCode, int idx);
    //캠핑장 북마크 제거
    @Delete("delete from placebookmark where place_code = #{placeCode} and idx = #{idx}")
    public void delBookmark(String placeCode, int idx);
    //캠핑장 별점 추가
    @Insert("insert into placebookmark values(#{placeCode}, #{idx}, null, #{perStar})")
    public void setStar(String placeCode, int idx, String perStar);
    //캠핑장 별점 제거
    @Delete("delete from placebookmark where place_code = #{placeCode} and idx = #{idx}")
    public void delStar(String placeCode, int idx);






}
