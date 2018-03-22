package com.example.mynotebook;

import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import com.example.mynotebook.db.DatabaseHelper;
import com.example.mynotebook.model.NoteItem;

import java.util.ArrayList;

public class NoteListActivity extends AppCompatActivity {

    public static ArrayList<NoteItem> mNoteList = new ArrayList<>();

    private ListView mNoteListView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_note_list);

        mNoteListView = findViewById(R.id.note_list_view);

        mNoteListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int position, long l) {
                Log.i("NoteListActivity", "Position: " + position);

                Intent i = new Intent(NoteListActivity.this, NoteDetailsActivity.class);
                i.putExtra("note_position", position);
                startActivity(i);
            }
        });
    }

    @Override
    protected void onResume() {
        super.onResume();

        loadDataFromDatabase();

        ArrayAdapter adapter = new ArrayAdapter<>(
                this,
                R.layout.item_note,
                R.id.note_title_text_view,
                mNoteList
        );
        mNoteListView.setAdapter(adapter);
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

        mNoteList.clear();
        while (cursor.moveToNext()) {
            long id = cursor.getLong(cursor.getColumnIndex(DatabaseHelper.COL_ID));
            String title = cursor.getString(cursor.getColumnIndex(DatabaseHelper.COL_TITLE));
            String details = cursor.getString(cursor.getColumnIndex(DatabaseHelper.COL_DETAILS));

            NoteItem noteItem = new NoteItem(title, details);
            mNoteList.add(noteItem);
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();

        if (id == R.id.action_add_note) {
            Intent i = new Intent(this, AddNoteActivity.class);
            startActivity(i);
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
}
