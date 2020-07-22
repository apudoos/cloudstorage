package com.udacity.jwdnd.course1.cloudstorage.services;

import com.udacity.jwdnd.course1.cloudstorage.model.Files;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.ArrayList;

public interface StorageService {
    void store(MultipartFile file, String userName) throws IOException;
    void deleteFile(Long filename);
    Files viewFile(Long filename);
    ArrayList<Files> listAllFiles(String userName);
    //Stream<Path> loadAll();
    //Path load(String filename);
    //Resource loadAsResource(String filename);
    //void init();
}
