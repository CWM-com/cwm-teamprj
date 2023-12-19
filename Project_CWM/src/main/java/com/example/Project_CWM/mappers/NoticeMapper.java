package com.example.Project_CWM.mappers;

import com.example.Project_CWM.dto.NoticeDto;
import org.apache.ibatis.annotations.*;

import java.util.List;
import java.util.Map;

@Mapper
public interface NoticeMapper {

    @Select("select * from notice order by id desc")
    public List<NoticeDto> getNotice();


    @Insert("insert into notice values(null, #{subject}, #{writer}, #{content}, 0, now(), #{orgName}, #{savedFileName}, #{savedFilePathName}, #{savedFileSize}, #{folderName}, #{ext}, #{grp}, 1, 1)")
    public void setWrite(NoticeDto noticeDto);

    @Delete("delete from notice where id = #{id}")
    public void setDelete(int id);

    @Select("select * from notice where id = #{id}")
    public NoticeDto getView(int id);


    /*파일*/
    @Select("select ifnull(max(grp) + 1, 1) as maxGrp from notice")
    int getMaxGrp();

    /*파일업데이트*/
    @Update("update notice set subject=#{subject}, writer=#{writer}, content=#{content}, regdate=now(), orgName=#{orgName}, savedFileName=#{savedFileName}, savedFilePathName=#{savedFilePathName}, savedFileSize=#{savedFileSize}, folderName=#{folderName}, ext=#{ext}  where id=#{id}")
    void setUpdate(NoticeDto noticeDto);

    /*파일다운*/





    @Select("select count(*) from notice")
    public int totalCount();

    @Select("select count(*) from notice ${searchQuery}")
    int getListCount(Map<String, Object> map);

    @Select("select * from notice ${searchQuery} order by grp desc, seq asc, depth asc limit #{startNum}, #{offset}")
    List<NoticeDto> getList(Map<String, Object> map);

    @Update("update notice set visit = visit + 1 where id = #{id}")
    void updateVisit(int id);

}
