package com.udacity.jwdnd.course1.cloudstorage.controller;

import com.udacity.jwdnd.course1.cloudstorage.model.Files;
import com.udacity.jwdnd.course1.cloudstorage.services.FileUploadService;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

@Controller
//@RequestMapping("/file")
public class FileController {

    FileUploadService uploadService;

    public FileController(FileUploadService uploadService) {
        this.uploadService = uploadService;
    }

    @ModelAttribute("files")
    public Files getFiles() {
        return new Files();
    }

    @PostMapping("/uploadFile")
    public String uploadFile(@RequestParam("fileUpload") MultipartFile file, Authentication auth) throws IOException {

        System.out.println("File Details: " + file.getOriginalFilename() + " " + file.getSize());
        try {
            uploadService.store(file, auth.getName());
        } catch (Exception ex) {
            return "redirect:/result?failure=" + ex.getMessage();
        }
        return "redirect:/result?success";
    }

    @GetMapping("deleteFile/{fileId}")
    public String deleteFile(@PathVariable(name="fileId") String fileId) {

        System.out.println("The file id is " + fileId);
        Long fileID = Long.parseLong(fileId);
        uploadService.deleteFile(fileID);
        return "redirect:/result?deleteSuccess";
    }

    @GetMapping("downloadFile/{fileId}")
    public ResponseEntity downloadFile(@PathVariable(name="fileId") String fileId) {

        Long fileID = Long.parseLong(fileId);
        Files files = uploadService.viewFile(fileID);
        return ResponseEntity.ok()
                .contentType(MediaType.parseMediaType(files.getContentType()))
                .header(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=\"" + files.getFileName() + "\"")
                .body(files.getFileData());
    }

}
