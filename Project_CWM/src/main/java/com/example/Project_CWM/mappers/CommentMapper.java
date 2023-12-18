package com.example.Project_CWM.mappers;

import com.example.Project_CWM.dto.CommentDto;
import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

import java.util.List;

@Mapper
public interface CommentMapper {

    @Insert("INSERT INTO comment VALUES(NULL, #{bId}, #{cWriter}, #{cComment}, now())")
    void setComment(CommentDto commentDto);

    @Select("SELECT * FROM comment WHERE b_id = #{bId}")
    List<CommentDto> getCommentList(CommentDto commentDto);

    @Delete("delete from comment where c_id = #{cId}")
    public void setDelete(int id);
}
