package com.example.mynotebook;

import android.content.ContentValues;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.EditText;

import com.example.mynotebook.db.DatabaseHelper;

public class AddNoteActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_note);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.add_note, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();

        if (id == R.id.action_save_note) {
            EditText noteTitleEditText = findViewById(R.id.note_title_edit_text);
            String noteTitle = noteTitleEditText.getText().toString();

            EditText noteDetailsEditText = findViewById(R.id.note_details_edit_text);
            String noteDetails = noteDetailsEditText.getText().toString();

            DatabaseHelper dbHelper = new DatabaseHelper(this);
            SQLiteDatabase db = dbHelper.getWritableDatabase();

            ContentValues cv = new ContentValues();
            cv.put(DatabaseHelper.COL_TITLE, noteTitle);
            cv.put(DatabaseHelper.COL_DETAILS, noteDetails);
            db.insert(DatabaseHelper.TABLE_NOTEBOOK, null, cv);

            finish();

            return true;
        }

        return super.onOptionsItemSelected(item);
    }
}
