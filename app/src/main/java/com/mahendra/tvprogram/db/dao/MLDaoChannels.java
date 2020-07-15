package com.mahendra.tvprogram.db.dao;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteStatement;
import android.util.Log;

import com.mahendra.tvprogram.db.MLDbConstants;
import com.mahendra.tvprogram.db.MLSQLiteOpenHelper;
import com.mahendra.tvprogram.models.MLChannelModel;
import com.mahendra.tvprogram.models.MLProgrammeModel;
import com.mahendra.tvprogram.utils.MLConstants;
import com.mahendra.tvprogram.utils.MLUtils;

import java.util.ArrayList;
import java.util.Date;

public class MLDaoChannels {
    private static final String LOG_TAG = MLDaoChannels.class.getSimpleName();

    private SQLiteDatabase database;
    private MLSQLiteOpenHelper dbHelper;

    public MLDaoChannels(Context context) {
        dbHelper = new MLSQLiteOpenHelper(context);
    }

    public synchronized void open() {

        database = dbHelper.getWritableDatabase();
    }

    public synchronized void close() {
        if(database.isOpen())
            dbHelper.close();
    }

    public void saveChannels(ArrayList<MLChannelModel> channels) {
        if (MLConstants.DEGUB_ON)
            Log.d(LOG_TAG, "Size is: " + channels.size());
        this.open();

        database.beginTransaction();
        SQLiteStatement insert = database.compileStatement(MLDbConstants.Channel.INSERT_QUERY);

        for (MLChannelModel channel : channels) {
            try {
                insert.bindString(1, channel.getIdNo());
                insert.bindString(2, channel.getId());
                insert.bindString(3, channel.getDisplayName());
                insert.bindString(4, channel.getIconURL());

                insert.execute();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        database.setTransactionSuccessful();

        if (MLConstants.DEGUB_ON)
            Log.d(LOG_TAG, "Completed inserting the channels data");

        database.endTransaction();
        this.close();
    }


    public ArrayList<MLChannelModel> getAllChannels() {
        ArrayList<MLChannelModel> channels = new ArrayList<MLChannelModel>();
        this.open();
        Cursor cursor = database.query(MLDbConstants.Channel.TABLE_NAME, MLDbConstants.Channel.ALL_COLUMNS, null, null, null, null, null);

        cursor.moveToFirst();
        while (!cursor.isAfterLast()) {
            MLChannelModel channel = new MLChannelModel(
                    cursor.getString(cursor.getColumnIndex(MLDbConstants.Channel.COLUMN_ID_NO)),
                    cursor.getString(cursor.getColumnIndex(MLDbConstants.Channel.COLUMN_CHANNEL_ID)),
                    cursor.getString(cursor.getColumnIndex(MLDbConstants.Channel.COLUMN_DISPLAY_NAME)),
                    cursor.getString(cursor.getColumnIndex(MLDbConstants.Channel.COLUMN_ICON_URL))
            );

            channels.add(channel);
            cursor.moveToNext();
        }

        cursor.close();
        this.close();
        return channels;
    }

    public ArrayList<MLProgrammeModel> getProgrammesForChannel(String channelID) {
        ArrayList<MLProgrammeModel> programmes = new ArrayList<MLProgrammeModel>();
        this.open();
        Cursor cursor = database.query(MLDbConstants.Programme.TABLE_NAME, MLDbConstants.Programme.ALL_COLUMNS, MLDbConstants.Programme.COLUMN_CHANNEL_ID  +" = '" + channelID + "'", null, null, null, null);

        cursor.moveToFirst();
        try {
            while (!cursor.isAfterLast()) {
                MLProgrammeModel programme = cursorToAbgeordnete(cursor);
                programmes.add(programme);
                cursor.moveToNext();
            }
        } catch (Exception ex) {
            ex.printStackTrace();
        }

        cursor.close();
        this.close();
        return programmes;
    }

    public MLProgrammeModel cursorToAbgeordnete(Cursor cursor) throws Exception{

        MLProgrammeModel programme = new MLProgrammeModel(
                cursor.getString(cursor.getColumnIndex(MLDbConstants.Programme.COLUMN_CHANNEL_ID)),
                cursor.getString(cursor.getColumnIndex(MLDbConstants.Programme.COLUMN_ID_NO)),
                MLUtils.getDateFromXQueryDate(cursor.getString(cursor.getColumnIndex(MLDbConstants.Programme.COLUMN_START))),
                MLUtils.getDateFromXQueryDate(cursor.getString(cursor.getColumnIndex(MLDbConstants.Programme.COLUMN_STOP))),
                cursor.getString(cursor.getColumnIndex(MLDbConstants.Programme.COLUMN_TITLE)),
                cursor.getString(cursor.getColumnIndex(MLDbConstants.Programme.COLUMN_SUBTITLE)),
                cursor.getString(cursor.getColumnIndex(MLDbConstants.Programme.COLUMN_CATEGORY)),
                cursor.getString(cursor.getColumnIndex(MLDbConstants.Programme.COLUMN_DESCRIPTION)),
                cursor.getString(cursor.getColumnIndex(MLDbConstants.Programme.COLUMN_ICON_URL)));

        return programme;
    }

    public void removeAll(){
        if(MLConstants.DEGUB_ON)
            Log.d(LOG_TAG, "Going to remove all data");
        this.open();
        database.execSQL(MLDbConstants.Channel.DELETE_QUERY);
        this.close();
    }

}
