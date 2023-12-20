package com.example.Project_CWM.mappers;


import com.example.Project_CWM.dto.*;
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

    //캠핑장 추가시 첨부파일 입력
    @Insert("insert into placefiles values(#{placeCode}, #{fileType}, #{orgName}, #{savedFileName}, #{savedPathName}, #{savedFileSize}, #{folderName}, #{ext})")
    public void setFiles(PlaceFilesDto mapFileDto);
    //캠핑장 제거시 해당 첨부파일 테이블 열 제거
    @Delete("delete from placefiles where place_code = #{placeCode}")
    void dropFiles(String placeCode);

    //캠핑장 추가시 주소 입력
    @Insert("insert into placecoor values(#{placeCode}, #{placeAddr}, #{placeName}, #{placeX}, #{placeY})")
    public void setAddr(MapDto mapDto);
    //캠핑장 제거시 해당 주소 테이블 열 제거
    @Delete("delete from placecoor where place_code = #{placeCode}")
    void dropAddr(String placeCode);

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

    //캠핑장 조회수 업데이트
    @Update("update placeinfo set visit = visit + 1 where place_code = #{placeCode}")
    void updateVisit(String placeCode);

//    //캠핑장 개인 북마크 추가
//    @Insert("insert into placebookmark values(#{placeCode}, #{idx}, 1, null)")
//    public void addBookmark(String placeCode, int idx);
//    //캠핑장 개인 북마크 제거
//    @Delete("delete from placebookmark where place_code = #{placeCode} and idx = #{idx}")
//    public void delBookmark(String placeCode, int idx);
//    //캠핑장 총 북마크 업데이트
//    @Select("update placeinfo set bookmark = (select count(*) from placebookmark where place_code = #{placeCode}) where place_code = #{placeCode}")
//    public void updateBookmark(String placeCode);
//    //캠핑장 개인 별점 추가
//    @Insert("insert into placebookmark values(#{placeCode}, #{idx}, null, #{perStar})")
//    public void addStar(String placeCode, int idx, String perStar);
//    //캠핑장 개인 별점 제거
//    @Delete("delete from placebookmark where place_code = #{placeCode} and idx = #{idx}")
//    public void delStar(String placeCode, int idx);

    // 메인페이지 캠프 개수 카운팅
    @Select("select count(*) from placeinfo")
    public int MainPlaceCount();







}
