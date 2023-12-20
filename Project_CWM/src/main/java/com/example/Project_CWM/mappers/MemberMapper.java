package com.example.Project_CWM.mappers;

import com.example.Project_CWM.dto.MemberDto;
import com.example.Project_CWM.dto.QnaDto;
import org.apache.ibatis.annotations.*;

import java.util.List;
import java.util.Map;

@Mapper
public interface MemberMapper {

    @Select("select count(*) from member")
    public int MemberCount();

    @Select("select count(*) from member where user_Id = #{userId}")
    public int setIdCheck(String userId);

    @Insert("insert into member values(null,#{userId}, #{userPasswd},#{userEmail},#{userName},#{userTel},now(),#{userAuthority})")
    public void setSignup(MemberDto registerDto);

    @Select("select * from member where user_id = #{userid}")
    public List<MemberDto> getUser(String userid);

    @Update("update member set user_passwd = #{userPasswd}, user_tel = #{userTel} where idx = #{idx}")
    public void setinfoUpdate(String userPasswd, String userTel, int idx);

    @Delete("delete from member where idx = #{idx}")
    public void Infodelete(int idx);

}
