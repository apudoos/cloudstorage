package com.udacity.jwdnd.course1.cloudstorage.services;

import com.udacity.jwdnd.course1.cloudstorage.exceptions.StorageException;
import com.udacity.jwdnd.course1.cloudstorage.mapper.FileMapper;
import com.udacity.jwdnd.course1.cloudstorage.mapper.UserMapper;
import com.udacity.jwdnd.course1.cloudstorage.model.Files;
import com.udacity.jwdnd.course1.cloudstorage.model.User;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.ArrayList;

@Service
public class FileUploadService implements StorageService {
    UserMapper userMapper;
    FileMapper fileMapper;

    public FileUploadService(UserMapper userMapper, FileMapper fileMapper) {
        this.userMapper = userMapper;
        this.fileMapper = fileMapper;
    }

    @Override
    public void store(MultipartFile file, String userName) throws IOException {
        Files files = new Files();
        User user = new User();
        //validate if file is duplicate
        if (file.isEmpty()) {
            throw new StorageException("Failed to store empty file "
                    + file.getOriginalFilename());
        }
        Integer count = this.fileMapper.selectSpecificFile(file.getOriginalFilename());
        System.out.println("The count is: " + count);
        if(count != 0) {
            throw new StorageException("File already exists with this name:  "
                    + file.getOriginalFilename() + ". Please try a different file.");
        }

        //get UserId from username
        user = this.userMapper.getUser(userName);
        files.setContentType(file.getContentType());
        files.setFileName(file.getOriginalFilename());
        files.setFileSize(String.valueOf(file.getSize()));
        files.setFileData(file.getBytes());
        files.setUserId(user.getUserid());

        int rows = this.fileMapper.insertNewFile(files);

    }

    @Override
    public void deleteFile(Long fileId) {
        int rows = this.fileMapper.deleteSpecificFile(fileId);
    }

    @Override
    public Files viewFile(Long fileId) {
        return this.fileMapper.selectFileById(fileId);
    }

    @Override
    public ArrayList<Files> listAllFiles(String userName) {
        User user = this.userMapper.getUser(userName);
        return this.fileMapper.selectAllFiles(user.getUserid());
    }
}
