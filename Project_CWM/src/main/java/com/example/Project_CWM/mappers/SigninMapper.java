package com.example.Project_CWM.mappers;

import com.example.Project_CWM.dto.RegisterDto;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

import java.util.List;

@Mapper
public interface SigninMapper {

    @Select("select user_id from member where user_Name = #{userName} and user_Email = #{userEmail}")
    public String setFindResult(String userName,String userEmail);

    @Select("select user_passwd from member where user_id = #{userid} and user_Email = #{userEmail}")
    public String setFindPasswd(String userid,String userEmail);

    @Select("select * from member where user_id = #{userId} and user_passwd = #{userPasswd}")
    public RegisterDto setLogin(RegisterDto registerDto);
}
