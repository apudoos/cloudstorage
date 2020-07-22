package com.udacity.jwdnd.course1.cloudstorage.model;

public class NotesForm {
    Integer notesId;
    String notesTitle;
    String notesDescription;

    public Integer getNotesId() {
        return notesId;
    }

    public void setNotesId(Integer notesId) {
        this.notesId = notesId;
    }

    public String getNotesTitle() {
        return notesTitle;
    }

    public void setNotesTitle(String notesTitle) {
        this.notesTitle = notesTitle;
    }

    public String getNotesDescription() {
        return notesDescription;
    }

    public void setNotesDescription(String notesDescription) {
        this.notesDescription = notesDescription;
    }

    @Override
    public String toString() {
        return "NotesForm{" +
                "notesTitle='" + notesTitle + '\'' +
                ", notesDescription='" + notesDescription + '\'' +
                '}';
    }
}
