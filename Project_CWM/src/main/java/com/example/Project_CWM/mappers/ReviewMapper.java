package com.example.Project_CWM.mappers;

import com.example.Project_CWM.dto.ReviewDto;
import org.apache.ibatis.annotations.*;

import java.util.List;
import java.util.Map;

@Mapper
public interface ReviewMapper {

    @Select("select * from review order by id desc")
    public List<ReviewDto> getReview();


    @Insert("insert into review values(null, #{subject}, #{userId}, #{content}, #{eval}, #{campName}, 0, now(), #{orgName}, #{savedFileName}, #{savedFilePathName}, #{savedFileSize}, #{folderName}, #{ext}, #{grp}, 1, 1)")
    public void setWrite(ReviewDto reviewDto);

    @Delete("delete from review where id = #{id}")
    public void setDelete(int id);

    @Select("select * from review where id = #{id}")
    public ReviewDto getView(int id);


    /*파일*/
    @Select("select ifnull(max(grp) + 1, 1) as maxGrp from review")
    int getMaxGrp();

    /*파일업데이트*/
    @Update("update review set subject=#{subject}, content=#{content}, eval=#{eval}, #{campName}, regdate=now(), orgName=#{orgName}, savedFileName=#{savedFileName}, savedFilePathName=#{savedFilePathName}, savedFileSize=#{savedFileSize}, folderName=#{folderName}, ext=#{ext}  where id=#{id}")
    void setUpdate(ReviewDto reviewDto);

    /*파일다운*/





    @Select("select count(*) from review")
    public int totalCount();

    @Select("select count(*) from review ${searchQuery}")
    int getListCount(Map<String, Object> map);

    @Select("select * from review ${searchQuery} order by grp desc, seq asc, depth asc limit #{startNum}, #{offset}")
    List<ReviewDto> getList(Map<String, Object> map);

    @Update("update review set visit = visit + 1 where id = #{id}")
    void updateVisit(int id);







    @Select("select count(*) from review")
    public int ReviewCount();

}
