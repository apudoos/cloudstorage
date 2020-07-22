package com.udacity.jwdnd.course1.cloudstorage.controller;

import com.udacity.jwdnd.course1.cloudstorage.model.NotesForm;
import com.udacity.jwdnd.course1.cloudstorage.services.NotesUploadService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class NotesController {

    @Autowired
    NotesUploadService notesUploadService;

    @PostMapping("/uploadNotes")
    public String uploadNotes(@ModelAttribute("notesForm") NotesForm notesForm, Authentication auth, Model model) {

        try {
            notesUploadService.store(notesForm, auth.getName());
        } catch (Exception ex) {
            return "redirect:/result?failure=" + ex.getMessage();
        }

        return "redirect:/result?success";
    }

    @GetMapping("deleteNote/{noteId}")
    public String deleteNote(@PathVariable(name="noteId") String noteId) {

        System.out.println("The file id is " + noteId);
        Long noteID = Long.parseLong(noteId);
        notesUploadService.deleteNote(noteID);
        return "redirect:/result?deleteSuccess";
    }
}
