package com.example.mynotebook;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.widget.TextView;

import com.example.mynotebook.model.NoteItem;

import static com.example.mynotebook.NoteListActivity.mNoteList;

public class NoteDetailsActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_note_details);

        Intent i = getIntent();
        int notePosition = i.getIntExtra("note_position", -1);

        // เข้าถึงออบเจ็ค NoteItem ใน ArrayList
        NoteItem noteItem = mNoteList.get(notePosition);

        // กำหนด Title ของ Note บน App Bar
        ActionBar ab = getSupportActionBar();
        ab.setTitle(noteItem.title);

        // กำหนด Details ของ Note ใน TextView
        TextView noteDetailsTextView = findViewById(R.id.note_details_text_view);
        noteDetailsTextView.setText(noteItem.details);
    }
}
