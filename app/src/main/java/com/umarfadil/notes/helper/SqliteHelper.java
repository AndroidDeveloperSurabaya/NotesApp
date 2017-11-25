package com.umarfadil.notes.helper;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

/**
 * Created by umarfadil on 11/25/17.
 */

public class SqliteHelper extends SQLiteOpenHelper {

    private static final String DATABASE_NAME = "Catatan";
    private static final int DATABASE_VERSION = 1;

    public SqliteHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
        // TODO Auto-generated constructor stub
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        // TODO Auto-generated method stub
        db.execSQL(
                "CREATE TABLE catatan (catatan_id INTEGER PRIMARY KEY AUTOINCREMENT NOT NULL, judul TEXT," +
                        "catatan TEXT);"
        );
    }


    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        // TODO Auto-generated method stub
        // Drop older table if existed
        db.execSQL("DROP TABLE IF EXISTS catatan");
    }
}
