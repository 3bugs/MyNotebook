package com.example.mynotebook;

import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import com.example.mynotebook.db.DatabaseHelper;
import com.example.mynotebook.model.NoteItem;

import java.util.ArrayList;

public class NoteListActivity extends AppCompatActivity {

    public static ArrayList<NoteItem> mNoteList = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_note_list);

        loadDataFromDatabase();

        ArrayAdapter adapter = new ArrayAdapter<>(
                this,
                R.layout.item_note,
                R.id.note_title_text_view,
                mNoteList
        );

        ListView noteListView = findViewById(R.id.note_list_view);

        noteListView.setAdapter(adapter);

        noteListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int position, long l) {
                Log.i("NoteListActivity", "Position: " + position);

                Intent i = new Intent(NoteListActivity.this, NoteDetailsActivity.class);
                i.putExtra("note_position", position);
                startActivity(i);
            }
        });
    }

    private void loadDataFromDatabase() {
        DatabaseHelper dbHelper = new DatabaseHelper(this);
        SQLiteDatabase db = dbHelper.getWritableDatabase();

        Cursor cursor = db.query(
                DatabaseHelper.TABLE_NOTEBOOK,
                null,
                null,
                null,
                null,
                null,
                null
        );

        while (cursor.moveToNext()) {
            long id = cursor.getLong(cursor.getColumnIndex(DatabaseHelper.COL_ID));
            String title = cursor.getString(cursor.getColumnIndex(DatabaseHelper.COL_TITLE));
            String details = cursor.getString(cursor.getColumnIndex(DatabaseHelper.COL_DETAILS));

            NoteItem noteItem = new NoteItem(title, details);
            mNoteList.add(noteItem);
        }
    }
}
