package com.tmtech.restaurantapp;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

/**
 * Created by Thomas on 2014-11-16.
 */
public class SQLiteHelper  extends SQLiteOpenHelper
{
    public static final String TABLE_REVIEWS;
    public static final String COLUMN_ID;
    public static final String COLUMN_LAT;
    public static final String COLUMN_LNG;
    public static final String COLUMN_TITLE;
    public static final String COLUMN_COMMENT;
    public static final String COLUMN_CATEGORY;
    public static final String COLUMN_RATING;
    private static final String DATABASE_NAME;
    private static final int DATABASE_VERSION;
    private static final String DATABASE_CREATE;

    static
    {
        TABLE_REVIEWS  = "names";
        COLUMN_ID    = "_ID";
        COLUMN_LAT = "latitude";
        COLUMN_LNG = "longitude";
        COLUMN_TITLE = "title";
        COLUMN_COMMENT = "comment";
        COLUMN_CATEGORY = "category";
        COLUMN_RATING = "rating";
        DATABASE_NAME = "names.db";
        DATABASE_VERSION = 1;
        DATABASE_CREATE = "create table " +
                TABLE_REVIEWS + "(" + COLUMN_ID + " integer primary key autoincrement, " + COLUMN_LAT +
                " latitude, " + COLUMN_LNG + " longitude, " + COLUMN_TITLE + " title, " +
                COLUMN_COMMENT + " comment, " + COLUMN_CATEGORY + " category, " +
                COLUMN_RATING + " rating);";

    }

    public SQLiteHelper(final Context context)
    {
        super(context,
                DATABASE_NAME,
                null,
                DATABASE_VERSION);
    }

    @Override
    public void onCreate(final SQLiteDatabase database)
    {
        database.execSQL(DATABASE_CREATE);
    }

    @Override
    public void onUpgrade(final SQLiteDatabase db,
                          final int            oldVersion,
                          final int            newVersion)
    {
        Log.w(SQLiteHelper.class.getName(),
                "Upgrading database from version " + oldVersion + " to "
                        + newVersion + ", which will destroy all old data");
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_REVIEWS);
        onCreate(db);
    }
}
