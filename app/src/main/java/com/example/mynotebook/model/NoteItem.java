package com.example.mynotebook.model;

/**
 * Created by Promlert on 2018-03-21.
 */

public class NoteItem {

    public final String title;
    public final String details;

    public NoteItem(String title, String details) {
        this.title = title;
        this.details = details;
    }

    @Override
    public String toString() {
        return title;
    }
}
