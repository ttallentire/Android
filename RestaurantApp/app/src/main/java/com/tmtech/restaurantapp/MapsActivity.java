package com.tmtech.restaurantapp;

import android.app.FragmentManager;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.util.Log;

import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.LatLng;

import java.util.List;

public class MapsActivity extends FragmentActivity implements GoogleMap.OnMapLongClickListener
{
    private ReviewDataSource datasource;
    SharedPreferences prefs = null;
    private String category;
    private boolean all;
    private GoogleMap mMap; // Might be null if Google Play services APK is not available.
    private MakeMarkerFragment markerFragment;
    private LatLng curMarkerPos;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_maps);
        prefs = getSharedPreferences("com.tmtech.restaurantapp", MODE_PRIVATE);
        datasource = new ReviewDataSource(this);
        datasource.open();

        if (prefs.getBoolean("firstrun", true)) {
            addFirstCats();
            prefs.edit().putBoolean("firstrun", false).apply();
        }

        if (savedInstanceState != null)
        {
            category = savedInstanceState.getString("category");
        } else {
            category = "Italian";
        }
        setUpMapIfNeeded();

        try
        {
            mMap.setOnMapLongClickListener(this);
            markerFragment = MakeMarkerFragment.newInstance(this);
        }
        catch (Exception e)
        {
            Log.d("MapsActivity", " OnCreate error");
        }
    }

    @Override
    protected void onResume()
    {
        datasource.open();
        super.onResume();

        setUpMapIfNeeded();
    }

    @Override
    protected void onPause() {
        datasource.close();
        super.onPause();
    }

    private void addFirstCats() {
        String[] category;
        LatLng latLng = new LatLng(0, 0);
        category = getResources().getStringArray(R.array.categories);

        for (String s : category) {
            Log.d("Category", s);
            datasource.createReview("", (short)0, "", s, latLng);
        }
    }

    /**
     * Sets up the map if it is possible to do so (i.e., the Google Play services APK is correctly
     * installed) and the map has not already been instantiated.. This will ensure that we only ever
     * call {@link #setUpMap()} once when {@link #mMap} is not null.
     * <p>
     * If it isn't installed {@link SupportMapFragment} (and
     * {@link com.google.android.gms.maps.MapView MapView}) will show a prompt for the user to
     * install/update the Google Play services APK on their device.
     * <p>
     * A user can return to this FragmentActivity after following the prompt and correctly
     * installing/updating/enabling the Google Play services. Since the FragmentActivity may not
     * have been completely destroyed during this process (it is likely that it would only be
     * stopped or paused), {@link #onCreate(Bundle)} may not be called again so we should call this
     * method in {@link #onResume()} to guarantee that it will be called.
     */
    private void setUpMapIfNeeded()
    {
        // Do a null check to confirm that we have not already instantiated the map.
        if (mMap == null) {
            // Try to obtain the map from the SupportMapFragment.
            mMap = ((SupportMapFragment) getSupportFragmentManager().findFragmentById(R.id.map))
                    .getMap();
            // Check if we were successful in obtaining the map.
            if (mMap != null) {
                setUpMap();
            }
        }
    }

    /**
     * This is where we can add markers or lines, add listeners or move the camera. In this case, we
     * just add a marker near Africa.
     * <p>
     * This should only be called once and when we are sure that {@link #mMap} is not null.
     */
    private void setUpMap()
    {
        List<Review> reviews;
        if (!category.equals("Any")) {
            reviews = datasource.getReviews(category);
        } else {
            reviews = datasource.getAllReviews();
        }
        if (reviews != null) {
            for (Review r : reviews)
                mMap.addMarker(r.getMarker());
        }
    }

    @Override
    public void onMapLongClick(LatLng latLng) {
        curMarkerPos = latLng;
        FragmentManager fm = getFragmentManager();
        fm.beginTransaction()
                .setCustomAnimations(android.R.animator.fade_in, android.R.animator.fade_out)
                .show(markerFragment)
                .commit();
    }

    public void createMarker(String title, short rating, String comment, String category) {
        datasource.createReview(title, rating, comment, category, curMarkerPos);
    }
}
