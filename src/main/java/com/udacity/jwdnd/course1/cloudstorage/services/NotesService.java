package com.udacity.jwdnd.course1.cloudstorage.services;

import com.udacity.jwdnd.course1.cloudstorage.model.Notes;
import com.udacity.jwdnd.course1.cloudstorage.model.NotesForm;

import java.util.ArrayList;

public interface NotesService {
    void store(NotesForm notesForm, String userName);
    void deleteNote(Long noteId);
    //Notes editNote(Long noteId);
    ArrayList<Notes> listAllNotes(String userName);
}
