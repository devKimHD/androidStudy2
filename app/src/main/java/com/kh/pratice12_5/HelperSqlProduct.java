package com.kh.pratice12_5;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import androidx.annotation.Nullable;

public class HelperSqlProduct extends SQLiteOpenHelper {
    public HelperSqlProduct(@Nullable Context context, @Nullable String name, @Nullable SQLiteDatabase.CursorFactory factory, int version) {
        super(context, name, factory, version);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        Log.d("TAG", "onCreate: "+"실행");
    String sql="CREATE TABLE prodTable (" +
            "num INTEGER Primary Key Autoincrement, " +
            "   uName TEXT NOT NULL, " +
            "   product TEXT NOT NULL, " +
            " count INTEGER NOT NULL) "
            ;

        db.execSQL(sql);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }
}
