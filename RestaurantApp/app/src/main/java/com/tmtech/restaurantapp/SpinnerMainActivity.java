package com.tmtech.restaurantapp;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;

/**
 * Created by MrChimick on 2014-11-10.
 */
public class SpinnerMainActivity extends Activity implements AdapterView.OnItemSelectedListener
{
    /*public void initSpinner()
    {
        Spinner spinner = (Spinner) findViewById(R.id.spinnerMain);
        // Create an ArrayAdapter using the string array and a default spinner layout
        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(this,
                R.array.categories, android.R.layout.simple_spinner_item);
        // Specify the layout to use when the list of choices appears
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        // Apply the adapter to the spinner
        spinner.setAdapter(adapter);

        spinner.setOnItemSelectedListener(this);
    }*/

    @Override
    public void onItemSelected(AdapterView<?> parent, View view, int position, long id)
    {
        Intent intent = new Intent(getApplicationContext(), MapsActivity.class);
        Bundle b = new Bundle();
        b.putLong("category", parent.getItemIdAtPosition(position));
        b.putBoolean("all", false);
        intent.putExtras(b);
        startActivity(intent);
    }

    @Override
    public void onNothingSelected(AdapterView<?> parent)
    {

    }
}
