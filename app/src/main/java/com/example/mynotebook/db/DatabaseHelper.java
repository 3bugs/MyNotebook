package com.example.mynotebook.db;

import android.content.ContentValues;
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

/**
 * Created by Promlert on 2018-03-22.
 */

public class DatabaseHelper extends SQLiteOpenHelper {

    private static final String DATABASE_NAME = "notebook.db";
    private static final int DATABASE_VERSION = 1;

    public static final String TABLE_NOTEBOOK = "notebook";
    public static final String COL_ID = "_id";
    public static final String COL_TITLE = "title";
    public static final String COL_DETAILS = "details";

    private static final String SQL_CREATE_TABLE_NOTEBOOK = "CREATE TABLE " + TABLE_NOTEBOOK + "("
            + COL_ID + " INTEGER PRIMARY KEY AUTOINCREMENT,"
            + COL_TITLE + " TEXT,"
            + COL_DETAILS + " TEXT"
            + ")";

    public DatabaseHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(SQL_CREATE_TABLE_NOTEBOOK);
        insertInitialData(db);
    }

    private void insertInitialData(SQLiteDatabase db) {
        ContentValues cv = new ContentValues();
        cv.put(COL_TITLE, "เลขเด็ดงวดนี้");
        cv.put(COL_DETAILS, "บน 12, ล่าง 34");
        db.insert(TABLE_NOTEBOOK, null, cv);

        cv = new ContentValues();
        cv.put(COL_TITLE, "เรียนแอนดรอยด์");
        cv.put(COL_DETAILS, "20-22 มี.ค. 61");
        db.insert(TABLE_NOTEBOOK, null, cv);

        cv = new ContentValues();
        cv.put(COL_TITLE, "abc");
        cv.put(COL_DETAILS, "xyz");
        db.insert(TABLE_NOTEBOOK, null, cv);

        cv = new ContentValues();
        cv.put(COL_TITLE, "test title");
        cv.put(COL_DETAILS, "test details");
        db.insert(TABLE_NOTEBOOK, null, cv);
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {

    }
}
