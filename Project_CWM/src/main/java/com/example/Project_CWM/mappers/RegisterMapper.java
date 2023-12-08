package com.example.Project_CWM.mappers;

import com.example.Project_CWM.dto.RegisterDto;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

import java.util.List;

@Mapper
public interface RegisterMapper {

    @Select("select count(*) from member where user_Id = #{userId}")
    public int setIdCheck(String userId);

    @Insert("insert into member values(null,#{userId},#{userPasswd},#{userEmail},#{userName},#{userTel},now())")
    public void setSignup(RegisterDto registerDto);

    @Select("select * from member where user_id = #{userid}")
    public List<RegisterDto> getUser(String userid);
}
