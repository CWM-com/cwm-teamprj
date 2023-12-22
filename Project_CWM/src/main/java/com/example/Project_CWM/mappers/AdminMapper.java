package com.example.Project_CWM.mappers;

import com.example.Project_CWM.dto.MemberDto;
import com.example.Project_CWM.dto.PlaceDto;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

import java.util.List;

@Mapper
public interface AdminMapper {

    @Select("select user_Name,user_Id,user_Passwd,user_Email,user_Tel from member where user_Authority = 'USER' order by idx desc")
    public List<MemberDto> AdminMember();
}
