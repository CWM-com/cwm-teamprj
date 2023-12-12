package com.example.Project_CWM.mappers;

import com.example.Project_CWM.dto.NoticeDto;
import com.example.Project_CWM.dto.QnaDto;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

import java.util.List;

@Mapper
public interface NoticeMapper {

    @Select("select * from notice order by id desc")
    List<NoticeDto> getNotice();

    @Insert("insert into notice (null, #{subject}, #{writer}, #{content}, 0, now(), #{orgName}, #{savedFileName}, #{savedFilePathName}, #{savedFileSize}, #{folderName}, #{ext}, #{grp}, 1, 1)")
    public void setWrite(NoticeDto noticeDto);

}
