package com;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import androidx.annotation.Nullable;

public class DBhelper extends SQLiteOpenHelper {
    public static final String DATABASE_NAME = "EventBrat";
    public static final String TABLE_NAME = "Events";
    public static final String TITLE_COL = "title";
    public static final String DATE_COL = "date";
    public static final String IMAGE_ID_COL = "img_id";


    public DBhelper(@Nullable Context context, @Nullable String name, @Nullable SQLiteDatabase.CursorFactory factory, int version) {
        super( context, name, factory, version );
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        Log.i("INFO", "OC Called");
        db.execSQL("CREATE TABLE " + DBhelper.TABLE_NAME + "(" +
                "_id INTEGER PRIMARY KEY AUTOINCREMENT," +
                DBhelper.TITLE_COL + "TEXT," +
                DBhelper.DATE_COL + "TEXT," +
                DBhelper.IMAGE_ID_COL + "INTEGER" +
                ")");

    }



    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }
    public static Cursor getAllRows() {


        return null;
    }}
