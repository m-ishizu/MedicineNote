package com.example.ishidzumichiko.medicinenote;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

/**
 * Created by ishidzumichiko on 2016/04/18.
 */
public class MyOpenHelper extends SQLiteOpenHelper {

    private static final String DB_NAME = "medicineNote.db";
    private static final int DB_VERSION = 1;
    private static final String createTableString = "create table personal_data(" +
            "_id integer primary key autoincrement," +
            "date text not null, hospital text not null, doctor text not null," +
            "medicine text not null)";

    public MyOpenHelper(Context context){
        super(context, DB_NAME, null, DB_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db){
        db.execSQL(createTableString);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion){

    }
}
