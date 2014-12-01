package com.tmtech.restaurantapp;

import android.view.View;
import android.widget.AdapterView;

/**
 * Created by MrChimick on 2014-11-10.
 */
public class SpinnerDeleteListener implements AdapterView.OnItemSelectedListener
{
    private DeleteActivity activity;
    @Override
    public void onItemSelected(AdapterView<?> parent, View view, int position, long id)
    {
        activity.setCategory(parent.getItemAtPosition(position).toString());
    }

    public void setActivity(DeleteActivity act) {
        activity = act;
    }

    @Override
    public void onNothingSelected(AdapterView<?> parent)
    {

    }
}
