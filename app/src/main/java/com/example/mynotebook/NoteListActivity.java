package com.example.mynotebook;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import com.example.mynotebook.model.NoteItem;

import java.util.ArrayList;

public class NoteListActivity extends AppCompatActivity {

    public static ArrayList<NoteItem> mNoteList = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_note_list);

        NoteItem item = new NoteItem(
                "เลขเด็ดงวดนี้",
                "บน 12, ล่าง 34"
        );
        mNoteList.add(item);

        item = new NoteItem(
                "aaa",
                "bbb"
        );
        mNoteList.add(item);

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
}
