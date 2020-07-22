package com.udacity.jwdnd.course1.cloudstorage.mapper;

import com.udacity.jwdnd.course1.cloudstorage.model.Files;
import com.udacity.jwdnd.course1.cloudstorage.model.Notes;
import org.apache.ibatis.annotations.*;

import java.util.ArrayList;

@Mapper
public interface NotesMapper {

    @Select("SELECT * FROM NOTES WHERE USERID=#{userId}")
    ArrayList<Notes> selectAllNotes(Integer userId);

    @Select("SELECT * FROM NOTES WHERE NOTEID=#{noteId}")
    Files selectNoteById(Long noteId);

    @Select("SELECT COUNT(*) FROM NOTES WHERE NOTETITLE=#{noteTitle}")
    Integer selectNoteByTitle(String noteTitle);

    @Update("UPDATE NOTES SET NOTETITLE=#{noteTitle}, NOTEDESCRIPTION=#{noteDescription} WHERE NOTEID=#{noteId}")
    Integer updateNoteByTitle(Notes notes);

    @Delete("DELETE FROM NOTES WHERE NOTEID=#{noteId}")
    Integer deleteSpecificNote(Long noteId);

    @Insert("INSERT INTO NOTES ( NOTETITLE, NOTEDESCRIPTION, USERID ) "
            + " VALUES ( #{noteTitle}, #{noteDescription},  #{userId} ) ")
    @Options(useGeneratedKeys = true, keyColumn = "NOTEID", keyProperty = "noteId")
    int insertNewNote(Notes notes);
}
