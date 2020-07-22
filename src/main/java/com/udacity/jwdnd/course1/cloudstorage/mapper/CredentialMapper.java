package com.udacity.jwdnd.course1.cloudstorage.mapper;

import com.udacity.jwdnd.course1.cloudstorage.model.Credentials;
import org.apache.ibatis.annotations.*;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;

@Repository
@Mapper
public interface CredentialMapper {
    @Select("SELECT * FROM CREDENTIALS WHERE USERID=#{userId}")
    ArrayList<Credentials> selectAllCredentials(Integer userId);

    @Select("SELECT * FROM CREDENTIALS WHERE CREDENTIALID=#{credentialId}")
    Credentials selectCredentialsById(Integer credentialId);

    @Select("SELECT COUNT(*) FROM CREDENTIALS WHERE URL=#{url}")
    Integer selectCredentialsByUrl(String url);

    @Update("UPDATE CREDENTIALS SET URL=#{url}, USERNAME=#{userName}, PASSWORD=#{password}, KEY=#{key} WHERE CREDENTIALID=#{credentialId}")
    Integer updateCredentials(Credentials credentials);

    @Delete("DELETE FROM CREDENTIALS WHERE CREDENTIALID=#{credentialId} ")
    Integer deleteSpecificCredential(Long credentialId);

    @Insert("INSERT INTO CREDENTIALS ( URL, USERNAME, KEY, PASSWORD, USERID ) "
            + " VALUES ( #{url}, #{userName},  #{key}, #{password}, #{userId} ) ")
    @Options(useGeneratedKeys = true, keyColumn = "CREDENTIALID", keyProperty = "credentialId")
    int insertNewCredentials(Credentials cred);
}
