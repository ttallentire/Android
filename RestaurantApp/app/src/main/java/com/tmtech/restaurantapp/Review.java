package com.tmtech.restaurantapp;

import com.google.android.gms.maps.model.MarkerOptions;

/**
 * Created by Thomas on 2014-11-16.
 */
public class Review {
    private long id;
    private String comment;
    private String category;
    private short rating;
    private MarkerOptions marker;

    public Review(String com, String cat, short r, MarkerOptions m) {
        comment = com;
        category = cat;
        rating = r;
        marker = m;
    }

    public Review() {

    }

    public long getId() {
        return id;
    }

    public void setId(long idl) {
        id = idl;
    }

    public String getComment() {
        return comment;
    }

    public void setComment(String c) {
        comment = c;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String c) {
        category = c;
    }

    public short getRating() {
        return rating;
    }

    public void setRating(short r) {
        rating = r;
    }

    public MarkerOptions getMarker() {
        return marker;
    }

    public void setMarker(MarkerOptions m) {
        marker = m;
    }

    @Override
    public String toString() {
        String s;
        s = "Name: " + getMarker().getTitle();
        return s;
    }
}
