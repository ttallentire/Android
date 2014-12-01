package com.tmtech.restaurantapp;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;


public class MakeMarker extends Activity {

    private String category;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_make_marker);
        initSpinner();
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.make_marker, menu);
        return true;
    }

    public void initSpinner()
    {
        SpinnerFragmentActivity spinnerFragmentActivity = new SpinnerFragmentActivity();
        Spinner spinner = (Spinner)findViewById(R.id.spinnerMain);
        // Create an ArrayAdapter using the string array and a default spinner layout
        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(getApplicationContext(),
                R.array.categories, R.layout.spinner_dark);
        // Specify the layout to use when the list of choices appears
        adapter.setDropDownViewResource(R.layout.spinner_item_dark);
        // Apply the adapter to the spinner
        spinner.setAdapter(adapter);

        spinnerFragmentActivity.setActivity(this);

        spinner.setOnItemSelectedListener(spinnerFragmentActivity);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();
        if (id == R.id.action_settings) {
            return true;
        }
        return super.onOptionsItemSelected(item);
    }

    public void setCategory(String cat) {
        category = cat;
    }

    public void saveMarker(View view) {
        String title = ((EditText)findViewById(R.id.title)).getText().toString();
        String ratingString = ((EditText)findViewById(R.id.rating)).getText().toString();
        String comment = ((EditText)findViewById(R.id.comment)).getText().toString();
        short rating = -1;
        try {
            rating = Short.parseShort(ratingString);
        } catch(NumberFormatException e) {
            Toast.makeText(getApplicationContext(), "Invalid rating", Toast.LENGTH_SHORT).show();
        }
        if (rating < 0 || rating > 5)
            Toast.makeText(getApplicationContext(), "Invalid rating", Toast.LENGTH_SHORT).show();
        else {
            Intent intent = new Intent(getApplicationContext(), MapsActivity.class);
            Bundle b = new Bundle();
            b.putString("title", title);
            b.putShort("rating", rating);
            b.putString("comment", comment);
            b.putString("category", category);
            intent.putExtras(b);
            setResult(RESULT_OK, intent);
            finish();
        }
    }
}
