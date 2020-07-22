package com.udacity.jwdnd.course1.cloudstorage.mapper;

import com.udacity.jwdnd.course1.cloudstorage.model.Files;
import org.apache.ibatis.annotations.*;

import java.util.ArrayList;

@Mapper
public interface FileMapper {

    @Select("SELECT * FROM FILES WHERE USERID=#{userId}")
    ArrayList<Files> selectAllFiles(Integer userId);

    @Select("SELECT COUNT(*) FROM FILES WHERE FILENAME=#{filename}")
    Integer selectSpecificFile(String filename);

    @Select("SELECT * FROM FILES WHERE FILEID=#{fileId}")
    Files selectFileById(Long fileId);

    @Delete("DELETE FROM FILES WHERE FILEID=#{fileId}")
    Integer deleteSpecificFile(Long fileId);

    @Insert("INSERT INTO FILES ( FILENAME, FILESIZE, CONTENTTYPE, USERID, FILEDATA ) "
            + " VALUES ( #{fileName}, #{fileSize},  #{contentType}, #{userId}, #{fileData} ) ")
    @Options(useGeneratedKeys = true, keyColumn = "FILEID", keyProperty = "fileId")
    int insertNewFile(Files file);

}
