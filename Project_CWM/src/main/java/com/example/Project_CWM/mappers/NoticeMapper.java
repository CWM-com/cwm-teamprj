package com.example.Project_CWM.mappers;

import com.example.Project_CWM.dto.NoticeDto;
import com.example.Project_CWM.dto.QnaDto;
import org.apache.ibatis.annotations.*;

import java.util.List;

@Mapper
public interface NoticeMapper {

    @Select("select * from notice order by id desc")
    List<NoticeDto> getNotice();

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
    @Update("update board set subject=#{subject}, writer=#{writer}, content=#{content}, regdate=now(), orgName=#{orgName}, savedFileName=#{savedFileName}, savedFilePathName=#{savedFilePathName}, savedFileSize=#{savedFileSize}, folderName=#{folderName}, ext=#{ext}  where id=#{id}")
    void setUpdate(NoticeDto noticeDto);

    /*파일다운*/

}
