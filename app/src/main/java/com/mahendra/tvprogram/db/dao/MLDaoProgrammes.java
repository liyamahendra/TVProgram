package com.mahendra.tvprogram.db.dao;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteStatement;
import android.util.Log;

import com.mahendra.tvprogram.db.MLDbConstants;
import com.mahendra.tvprogram.db.MLSQLiteOpenHelper;
import com.mahendra.tvprogram.models.MLProgrammeModel;
import com.mahendra.tvprogram.utils.MLConstants;
import com.mahendra.tvprogram.utils.MLUtils;

import java.util.ArrayList;

public class MLDaoProgrammes {
    private static final String LOG_TAG = MLDaoProgrammes.class.getSimpleName();

    private SQLiteDatabase database;
    private MLSQLiteOpenHelper dbHelper;

    public MLDaoProgrammes(Context context) {
        dbHelper = new MLSQLiteOpenHelper(context);
    }

    public synchronized void open() {

        database = dbHelper.getWritableDatabase();
    }

    public synchronized void close() {
        if(database.isOpen())
            dbHelper.close();
    }

    public void saveProgrammes(ArrayList<MLProgrammeModel> programmes) {
        if (MLConstants.DEGUB_ON)
            Log.d(LOG_TAG, "Size is: " + programmes.size());
        this.open();

        database.beginTransaction();
        SQLiteStatement insert = database.compileStatement(MLDbConstants.Programme.INSERT_QUERY);

        for (MLProgrammeModel programme : programmes) {
            try {
                insert.bindString(1, programme.getIdNo());
                insert.bindString(2, programme.getChannel());
                insert.bindString(3, MLUtils.formatDateToStoreInSqlite(programme.getStart()));
                insert.bindString(4, MLUtils.formatDateToStoreInSqlite(programme.getStop()));
                insert.bindString(5, programme.getTitle());
                insert.bindString(6, programme.getSubTitle());
                insert.bindString(7, programme.getCategory());
                insert.bindString(8, programme.getDescription());
                insert.bindString(9, programme.getIconURL());

                insert.execute();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        database.setTransactionSuccessful();

        if (MLConstants.DEGUB_ON)
            Log.d(LOG_TAG, "Completed inserting the programmes data");

        database.endTransaction();
        this.close();
    }

    public void removeAll(){
        if(MLConstants.DEGUB_ON)
            Log.d(LOG_TAG, "Going to remove all data");
        this.open();
        database.execSQL(MLDbConstants.Channel.DELETE_QUERY);
        this.close();
    }
    
}
