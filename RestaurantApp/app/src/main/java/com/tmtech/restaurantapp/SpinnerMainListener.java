package com.tmtech.restaurantapp;

import android.app.Activity;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Toast;

/**
 * Created by MrChimick on 2014-11-10.
 */
public class SpinnerMainListener implements AdapterView.OnItemSelectedListener
{
    private HomePage activity;
    @Override
    public void onItemSelected(AdapterView<?> parent, View view, int position, long id)
    {
        /*Intent intent = new Intent(getApplicationContext(), MapsActivity.class);
        intent.putExtra("category", parent.getItemIdAtPosition(position));
        startActivity(intent);*/

        activity.setCategory(parent.getItemAtPosition(position).toString());
    }

    public void setActivity(HomePage act) {
        activity = act;
    }

    @Override
    public void onNothingSelected(AdapterView<?> parent)
    {

    }
}
