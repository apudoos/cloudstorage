package com.udacity.jwdnd.course1.cloudstorage.exceptions;

import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.multipart.MultipartException;

@ControllerAdvice
class FileUploadExceptionHandler {

    @ExceptionHandler(MultipartException.class)
    public String handleFileSizeLimitException(MultipartException ex) {
        return "redirect:/result?failure=" + ex.getMessage();
    }

}
