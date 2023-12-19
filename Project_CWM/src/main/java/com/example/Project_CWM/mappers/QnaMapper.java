package com.example.Project_CWM.mappers;

import com.example.Project_CWM.dto.QnaDto;
import org.apache.ibatis.annotations.*;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;
import java.util.Map;

@Mapper
public interface QnaMapper {

    @Select("select * from qna order by id desc")
    public List<QnaDto> getQna();

    @Insert("insert into qna values(null, #{subject} ,#{writer}, #{content}, now(), #{ext} ,#{grp}, 1, 1)")
    public void setWrite(QnaDto qnaDto);

    @Delete("delete from qna where id = #{id}")
    public void setDelete(int id);

    @Select("select * from qna where id = #{id}")
    public QnaDto getView(int id);

    @Update("update qna set subject=#{subject}, writer=#{writer}, content=#{content}, regdate=now(), ext=#{ext}  where id=#{id}")
    public void setUpdate(QnaDto qnaDto);

    @Select("select * from qna ${searchQuery} order by grp desc, seq asc, depth asc limit #{startNum}, #{offset}")
    List<QnaDto> getList(Map<String, Object> map);

    @Select("select count(*) from qna ${searchQuery}")
    int getListCount(Map<String, Object> map);

    @Select("select count(*) from qna")
    public int totalCount();

    @Insert("insert into qna values(null, #{subject}, #{writer}, #{content}, now(), #{ext}, #{grp}, #{seq}, #{depth})")
    void setReply(QnaDto qnaDto);

    @Select("select ifnull(max(grp) + 1, 1) as maxGrp from qna")
    int getMaxGrp();


}
