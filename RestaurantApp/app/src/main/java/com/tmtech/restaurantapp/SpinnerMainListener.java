package com.tmtech.restaurantapp;

import android.view.View;
import android.widget.AdapterView;
import android.widget.Toast;

/**
 * Created by MrChimick on 2014-11-10.
 */
public class SpinnerMainListener implements AdapterView.OnItemSelectedListener
{
    @Override
    public void onItemSelected(AdapterView<?> parent, View view, int position, long id)
    {
        //Intent intent = new Intent(getApplicationContext(), MapsActivity.class);
        //intent.putExtra("category", parent.getItemIdAtPosition(position));
        //startActivity(intent);

        Toast.makeText(parent.getContext(),
                "On Item Select : \n" + parent.getItemAtPosition(position).toString(),
                Toast.LENGTH_LONG).show();
    }

    @Override
    public void onNothingSelected(AdapterView<?> parent)
    {

    }
}
