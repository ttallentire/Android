package com.tmtech.restaurantapp;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.TextView;

/**
 * Created by MrChimick on 2014-11-10.
 */
public class SpinnerFragmentActivity extends Activity implements AdapterView.OnItemSelectedListener
{
    private MakeMarker activity;
    @Override
    public void onItemSelected(AdapterView<?> parent, View view, int position, long id)
    {
        activity.setCategory(parent.getItemAtPosition(position).toString());
    }

    public void setActivity(MakeMarker act) {
        activity = act;
    }

    @Override
    public void onNothingSelected(AdapterView<?> parent)
    {

    }
}
