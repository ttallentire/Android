package com.tmtech.restaurantapp;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.TextView;

/**
 * Created by MrChimick on 2014-11-10.
 */
public class SpinnerFragmentActivity extends Activity implements AdapterView.OnItemSelectedListener
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
        long catNo;
        String category;
        catNo = parent.getItemIdAtPosition(position);
        category = getResources().getStringArray(R.array.categories)[(int)catNo];
        ((TextView)findViewById(R.id.category)).setText(category);
    }

    @Override
    public void onNothingSelected(AdapterView<?> parent)
    {

    }
}
