package com.udacity.jwdnd.course1.cloudstorage.controller;

import com.udacity.jwdnd.course1.cloudstorage.model.*;
import com.udacity.jwdnd.course1.cloudstorage.services.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.ArrayList;
import java.util.List;

@Controller
@RequestMapping("/home")
public class HomeController {


    private final FileUploadService uploadService;
    private final NotesUploadService notesUploadService;
    private final CredentialsService credentialsService;
    private final CredentialsUploadService credentialsUploadService;

    @Autowired
    public HomeController(FileUploadService uploadService,
                          NotesUploadService notesUploadService,
                          CredentialsService credentialsService,
                          CredentialsUploadService credentialsUploadService) {
        this.uploadService = uploadService;
        this.notesUploadService = notesUploadService;
        this.credentialsService = credentialsService;
        this.credentialsUploadService = credentialsUploadService;
    }


    @GetMapping
    public String getHome(@ModelAttribute("notesForm") NotesForm notesForm,
                          @ModelAttribute("credentialsForm") CredentialsForm credentialsForm,
                          Authentication auth,
                          Model model) {
        ArrayList<Files> files = uploadService.listAllFiles(auth.getName());
        model.addAttribute("files", files);

        ArrayList<Notes> notes = notesUploadService.listAllNotes(auth.getName());
        model.addAttribute("notes", notes);

        List<CredentialsForm> credentials = credentialsService.listAllCredentials(auth.getName());
        model.addAttribute("credentials", credentials);
        model.addAttribute("credentialsUploadService", credentialsUploadService);

        return "home";

    }

}
