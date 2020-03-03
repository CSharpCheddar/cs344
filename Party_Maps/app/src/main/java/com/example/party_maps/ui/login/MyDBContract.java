package com.example.party_maps.ui.login;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.provider.BaseColumns;

public final class MyDBContract {
    private static final String TEXT_TYPE = " TEXT";
    private static final String COMMA_SEP = " ,";
    private static final String SQL_CREATE_ENTRIES =
            "CREATE TABLE " +
            DBEntry.TABLE_NAME + " (" +
            DBEntry.COLUMN_NAME_USERNAME +
            " TEXT PRIMARY KEY" + COMMA_SEP +
            DBEntry.COLUMN_NAME_PASSWORD + TEXT_TYPE + ")";

    private static final String SQL_DELETE_ENTRIES =
            "DROP TABLE IF EXISTS " + DBEntry.TABLE_NAME;
    //Empty constructor in case someone tries to
    //create an instance of MyDBContract
    public MyDBContract() {}
    //inner class that defines the schema
    public static abstract class DBEntry implements BaseColumns {
        public static final String TABLE_NAME = "SavedCredentials";
        public static final String COLUMN_NAME_USERNAME = "username";
        public static final String COLUMN_NAME_PASSWORD = "password";
    }
    //SQL Helper
    public static class MyDbHelper extends SQLiteOpenHelper {
        //if the schema changes, the version must change
        public static final int DATABASE_VERSION = 1;
        public static final String DATABASE_NAME = "DB.db";

        public MyDbHelper(Context context) {
            super(context, DATABASE_NAME, null, DATABASE_VERSION);
        }
        public void onCreate(SQLiteDatabase db) {
            db.execSQL(SQL_CREATE_ENTRIES);
        }
        public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
            //onUpgrade, just start over
            db.execSQL(SQL_DELETE_ENTRIES);
            onCreate(db);
        }
        public void onDowngrade(SQLiteDatabase db, int oldVersion, int newVersion) {
            onUpgrade(db, oldVersion, newVersion);
        }
    }
}
