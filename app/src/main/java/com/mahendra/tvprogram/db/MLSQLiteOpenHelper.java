package com.mahendra.tvprogram.db;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class MLSQLiteOpenHelper extends SQLiteOpenHelper {
    public MLSQLiteOpenHelper(Context context) {
        super(context, MLDbConstants.DATABASE_NAME, null, MLDbConstants.DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(MLDbConstants.Channel.CREATE_TABLE_SCRIPT);
        db.execSQL(MLDbConstants.Programme.CREATE_TABLE_SCRIPT);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL(MLDbConstants.Channel.DELETE_QUERY);
        db.execSQL(MLDbConstants.Programme.DELETE_QUERY);
        onCreate(db);
    }
}
