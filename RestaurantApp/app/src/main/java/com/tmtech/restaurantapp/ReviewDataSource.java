package com.tmtech.restaurantapp;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteConstraintException;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;

import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.Marker;
import com.google.android.gms.maps.model.MarkerOptions;
import com.google.android.gms.maps.model.MarkerOptionsCreator;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Thomas on 2014-11-16.
 */
public class ReviewDataSource {
    // Database fields
    private final SQLiteHelper dbHelper;
    private final String[] allColumns;
    private SQLiteDatabase database;

    {
        allColumns = new String[]
                {
                        SQLiteHelper.COLUMN_ID,
                        SQLiteHelper.COLUMN_LAT,
                        SQLiteHelper.COLUMN_LNG,
                        SQLiteHelper.COLUMN_TITLE,
                        SQLiteHelper.COLUMN_COMMENT,
                        SQLiteHelper.COLUMN_CATEGORY,
                        SQLiteHelper.COLUMN_RATING
                };
    }

    public ReviewDataSource(Context context)
    {
        dbHelper = new SQLiteHelper(context);
    }

    public void open()
            throws SQLException
    {
        database = dbHelper.getWritableDatabase();
    }

    public void close() {
        dbHelper.close();
    }

    public Review createReview(String title, short rating, String comment, String category, LatLng latLng)
    {
        Log.d("Creating", "Review " + category);
        final ContentValues values;
        Cursor cursor = null;
        Review newReview;
        long insertId;

        values = new ContentValues();
        values.put(SQLiteHelper.COLUMN_LAT, latLng.latitude);
        values.put(SQLiteHelper.COLUMN_LNG, latLng.longitude);
        values.put(SQLiteHelper.COLUMN_TITLE, title);
        values.put(SQLiteHelper.COLUMN_COMMENT, comment);
        values.put(SQLiteHelper.COLUMN_CATEGORY, category);
        values.put(SQLiteHelper.COLUMN_RATING, rating);

        insertId = database.insert(SQLiteHelper.TABLE_REVIEWS,
                null,
                values);
        cursor = database.query(SQLiteHelper.TABLE_REVIEWS,
                allColumns,
                SQLiteHelper.COLUMN_ID + " = " + insertId,
                null,
                null,
                null,
                null);
        cursor.moveToFirst();
        newReview = cursorToReview(cursor);

        cursor.close();
        return (newReview);
    }

    public void deleteReview(final Review review)
    {
        final long id;

        id = review.getId();
        System.out.println("Comment deleted with id: " + id);
        database.delete(SQLiteHelper.TABLE_REVIEWS,
                SQLiteHelper.COLUMN_ID + " = " + id,
                null);
    }

    public List<Review> getAllReviews()
    {
        final List<Review>    reviews;
        final Cursor        cursor;

        reviews = new ArrayList<Review>();
        cursor   = database.query(SQLiteHelper.TABLE_REVIEWS,
                allColumns,
                null,
                null,
                null,
                null,
                SQLiteHelper.COLUMN_TITLE + " ASC");

        try
        {
            cursor.moveToFirst();

            while(!(cursor.isAfterLast()))
            {
                final Review review;

                review = cursorToReview(cursor);
                reviews.add(review);
                cursor.moveToNext();
            }

        }
        finally
        {
            // make sure to close the cursor
            cursor.close();
        }

        return (reviews);
    }

    public List<Review> getReviews(String category)
    {
        final List<Review>    reviews;
        final Cursor        cursor;

        reviews = new ArrayList<Review>();
        cursor   = database.query(SQLiteHelper.TABLE_REVIEWS,
                allColumns,
                SQLiteHelper.COLUMN_CATEGORY + " LIKE '" + category + "'",
                null,
                null,
                null,
                SQLiteHelper.COLUMN_TITLE + " ASC");

        try
        {
            cursor.moveToFirst();

            while(!(cursor.isAfterLast()))
            {
                final Review review;

                review = cursorToReview(cursor);
                reviews.add(review);
                cursor.moveToNext();
            }
        }
        finally
        {
            // make sure to close the cursor
            cursor.close();
        }

        return (reviews);
    }

    private Review cursorToReview(final Cursor cursor)
    {
        final Review review;
        final MarkerOptions marker;

        marker = new MarkerOptions()
                .position(new LatLng(cursor.getFloat(1), cursor.getFloat(2)))
                .title(cursor.getString(3));
        review = new Review(cursor.getString(4), cursor.getString(5),
                cursor.getShort(6), marker);
        review.setId(cursor.getLong(0));

        return (review);
    }
}
