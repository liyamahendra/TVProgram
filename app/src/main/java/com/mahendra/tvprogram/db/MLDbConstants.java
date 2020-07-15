package com.mahendra.tvprogram.db;

public class MLDbConstants {
    public static final String DATABASE_NAME = "tvprogram.db";
    public static final int DATABASE_VERSION = 1;

    public static class Channel {
        public static final String TABLE_NAME = "tbl_channels";

        public static final String COLUMN_ID = "_id";
        public static final String COLUMN_CHANNEL_ID = "id";
        public static final String COLUMN_ID_NO = "id_no";
        public static final String COLUMN_DISPLAY_NAME = "display_name";
        public static final String COLUMN_ICON_URL = "icon_url";

        public static final String[] ALL_COLUMNS = {COLUMN_ID, COLUMN_ID_NO, COLUMN_CHANNEL_ID, COLUMN_DISPLAY_NAME, COLUMN_ICON_URL};

        public static final String CREATE_TABLE_SCRIPT = "CREATE TABLE IF NOT EXISTS " + TABLE_NAME
                + " ( "
                + COLUMN_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, "
                + COLUMN_ID_NO + " INTEGER NOT NULL, "
                + COLUMN_CHANNEL_ID + " TEXT NOT NULL, "
                + COLUMN_DISPLAY_NAME + " TEXT NOT NULL, "
                + COLUMN_ICON_URL + " TEXT NOT NULL "
                + " );";

        public static final String INSERT_QUERY = "INSERT INTO " + TABLE_NAME + " ( " + COLUMN_ID_NO + ", '" + COLUMN_CHANNEL_ID + "', '" + COLUMN_DISPLAY_NAME + "', '" + COLUMN_ICON_URL + "' ) VALUES (?, ?, ?, ?);";

        public static final String DELETE_QUERY = "DELETE FROM " + TABLE_NAME;
    }

    public static class Programme {
        public static final String TABLE_NAME = "tbl_programmes";

        public static final String COLUMN_ID = "_id";
        public static final String COLUMN_CHANNEL_ID = "channel_id";
        public static final String COLUMN_ID_NO = "id_no";
        public static final String COLUMN_START = "start";
        public static final String COLUMN_STOP = "stop";
        public static final String COLUMN_TITLE = "title";
        public static final String COLUMN_SUBTITLE = "subtitle";
        public static final String COLUMN_CATEGORY = "category";
        public static final String COLUMN_DESCRIPTION = "desc";
        public static final String COLUMN_ICON_URL = "icon_url";

        public static final String[] ALL_COLUMNS = {COLUMN_ID, COLUMN_CHANNEL_ID, COLUMN_ID_NO, COLUMN_START, COLUMN_STOP, COLUMN_TITLE, COLUMN_SUBTITLE, COLUMN_CATEGORY, COLUMN_DESCRIPTION, COLUMN_ICON_URL};

        public static final String CREATE_TABLE_SCRIPT = "CREATE TABLE IF NOT EXISTS " + TABLE_NAME
                + " ( "
                + COLUMN_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, "
                + COLUMN_ID_NO + " INTEGER NOT NULL, "
                + COLUMN_CHANNEL_ID + " TEXT NOT NULL, "
                + COLUMN_START + " TEXT NOT NULL, "
                + COLUMN_STOP + " TEXT NOT NULL, "
                + COLUMN_TITLE + " TEXT NOT NULL, "
                + COLUMN_SUBTITLE + " TEXT , "
                + COLUMN_CATEGORY + " TEXT , "
                + COLUMN_DESCRIPTION + " TEXT , "
                + COLUMN_ICON_URL + " TEXT  "
                + " );";

        public static final String INSERT_QUERY = "INSERT INTO " + TABLE_NAME + " ( " + COLUMN_ID_NO + ", '" + COLUMN_CHANNEL_ID + "', '" + COLUMN_START + "', '" + COLUMN_STOP + "', '"  + COLUMN_TITLE + "', '" + COLUMN_SUBTITLE + "', '" + COLUMN_CATEGORY + "', '" + COLUMN_DESCRIPTION + "', '" + COLUMN_ICON_URL + "' ) VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?);";

        public static final String DELETE_QUERY = "DELETE FROM " + TABLE_NAME;
    }

}
