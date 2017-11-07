package com.example.a32150.dummynote1;

import android.content.Context;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

/**
 * Created by 32150 on 2017/11/7.
 */

public class DB {

    private static final String DATABASE_NAME="notes.db";
    private static final int DATABASE_VERSION=1;
    private static final String DATABASE_TABLE="notes";
    private static final String DATABASE_CREATE="CREATE TABLE notes(_id INTEGER PRIMARY KEY, note TEXT, created INTEGER);";
    private static final String DATABASE_DROP="DROP TABLE IF EXISTS"+DATABASE_TABLE;





    private static class DatabaseHelper extends SQLiteOpenHelper
    {
        public DatabaseHelper(Context context) {
            super(context, DATABASE_TABLE, null, DATABASE_VERSION);
        }

        @Override
        public void onCreate(SQLiteDatabase sqLiteDatabase) {
            sqLiteDatabase.execSQL(DATABASE_CREATE);
        }

        @Override
        public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {
            sqLiteDatabase.execSQL("");
            onCreate(sqLiteDatabase);
        }
    }


    private Context mCtx = null;
    private DatabaseHelper dbHelper ;
    private SQLiteDatabase db;

    public DB(Context ctx)
    {
        this.mCtx = ctx;
    }

    public DB open () throws SQLException
    {
        dbHelper = new DatabaseHelper(mCtx);
        db = dbHelper.getWritableDatabase();
        return this;
    }

    public void close()
    {
        dbHelper.close();
    }

    public Cursor getAll()  {
        return db.rawQuery("SELECT * FROM notes", null);
    }
}
