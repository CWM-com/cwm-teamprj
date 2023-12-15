package com.example.Project_CWM.mappers;

import com.example.Project_CWM.dto.MemberDto;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

import java.util.List;

@Mapper
public interface MemberMapper {

    @Select("select count(*) from member where user_Id = #{userId}")
    public int setIdCheck(String userId);

    @Insert("insert into member values(null,#{userId},#{userPasswd},#{userEmail},#{userName},#{userTel},now(),#{userAuthority})")
    public void setSignup(MemberDto registerDto);

    @Select("select * from member where user_id = #{userid}")
    public List<MemberDto> getUser(String userid);

    @Update("update member set user_passwd = #{userPasswd}, user_tel = #{userTel} where idx = #{idx}")
    public void setinfoUpdate(String userPasswd, String userTel, int idx);
}
