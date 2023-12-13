package com.example.Project_CWM.mappers;

import com.example.Project_CWM.dto.MemberDto;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

import java.util.List;

@Mapper
public interface RegisterMapper {

    @Select("select count(*) from member where user_Id = #{userId}")
    public int setIdCheck(String userId);

    @Insert("insert into member values(null,#{userId},#{userPasswd},#{userEmail},#{userName},#{userTel},now(),#{userAuthority})")
    public void setSignup(MemberDto registerDto);

    @Select("select * from member where user_id = #{userid}")
    public List<MemberDto> getUser(String userid);
}
