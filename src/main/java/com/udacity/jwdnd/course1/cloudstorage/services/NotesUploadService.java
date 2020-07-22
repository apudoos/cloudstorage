package com.udacity.jwdnd.course1.cloudstorage.services;

import com.udacity.jwdnd.course1.cloudstorage.mapper.NotesMapper;
import com.udacity.jwdnd.course1.cloudstorage.mapper.UserMapper;
import com.udacity.jwdnd.course1.cloudstorage.model.Notes;
import com.udacity.jwdnd.course1.cloudstorage.model.NotesForm;
import com.udacity.jwdnd.course1.cloudstorage.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;

@Service
public class NotesUploadService implements NotesService {
    @Autowired
    NotesMapper notesMapper;
    @Autowired
    UserMapper userMapper;

    @Override
    public void store(NotesForm notesForm, String userName) {
        Notes notes = new Notes();

        //set the notes details
        notes.setNoteTitle(notesForm.getNotesTitle());
        notes.setNoteDescription(notesForm.getNotesDescription());
        User user = this.userMapper.getUser(userName);
        notes.setUserId(user.getUserid());

        if (notesForm.getNotesId() != null) {
            notes.setNoteId(notesForm.getNotesId());
            int rows = this.notesMapper.updateNoteByTitle(notes);
            //System.out.println("The record updated: " + rows);
        } else {

        //validate if the notes title is duplicate
        //Integer count = this.notesMapper.selectNoteByTitle(notesForm.getNotesTitle());
        //System.out.println("The count is: " + count);
        //if(count > 0) {
        //    throw new StorageException("Note already exists with this name:  "
        //            + notesForm.getNotesTitle() + ". Please try a different name.");
        //} else {


            //set the notes details
            int rows = this.notesMapper.insertNewNote(notes);
            //System.out.println("Row inserted: " + rows);
        }
    }

    @Override
    public void deleteNote(Long noteId) {
        this.notesMapper.deleteSpecificNote(noteId);
    }

    @Override
    public ArrayList<Notes> listAllNotes(String userName) {

        //get UserId from username
        User user = this.userMapper.getUser(userName);
        return this.notesMapper.selectAllNotes(user.getUserid());
    }
}
