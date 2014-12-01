package com.tmtech.restaurantapp;

import android.app.Activity;
import android.app.AlertDialog;
import android.app.ListActivity;
import android.content.DialogInterface;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Spinner;

import java.util.List;


public class DeleteActivity extends ListActivity {

    private String category = "Any";
    private ReviewDataSource datasource;
    public DeleteActivity activity = this;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        final List<Review>         values;
        final ArrayAdapter<Review> adapter;

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_delete);

        datasource = new ReviewDataSource(this);
        datasource.open();
        values  = datasource.getAllReviews();
        populateList(values);
        initSpinner();
        ((ListView)findViewById(android.R.id.list)).setOnItemClickListener(new deleteListener());
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.delete, menu);
        return true;
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

    public void initSpinner() {
        SpinnerDeleteListener spinnerDeleteListener = new SpinnerDeleteListener();
        spinnerDeleteListener.setActivity(this);
        Spinner spinner = (Spinner) findViewById(R.id.spinnerDelete);
        // Create an ArrayAdapter using the string array and a default spinner layout
        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(getApplicationContext(),
                        R.array.categories, R.layout.spinner_dark);
        // Specify the layout to use when the list of choices appears
        adapter.setDropDownViewResource(R.layout.spinner_item_dark);
        // Apply the adapter to the spinner
        spinner.setAdapter(adapter);

        spinner.setOnItemSelectedListener(spinnerDeleteListener);
    }

    public void setCategory(String cat) {
        List<Review> reviews;
        category = cat;
        if (category.equals("Any"))
            reviews = datasource.getAllReviews();
        else
            reviews = datasource.getReviews(category);
        populateList(reviews);
    }

    private void populateList(List<Review> list) {
        final Review               review;
        final int                   nextInt;
        final ArrayAdapter<Review> adapter;

        adapter = new ArrayAdapter<Review>(this,
                android.R.layout.simple_list_item_1);
        for (Review r : list) {
            if (!r.getMarker().getTitle().equals(""))
                adapter.add(r);
        }
        setListAdapter(adapter);
        adapter.notifyDataSetChanged();
    }

    public class deleteListener implements AdapterView.OnItemClickListener {
        public Review review;
        @Override
        public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
            review = (Review)parent.getItemAtPosition(position);
            showDialog(activity, "Remove", "Would you like to delete this review?");
        }

        public void showDialog(Activity activity, String title, CharSequence message) {
            AlertDialog.Builder builder = new AlertDialog.Builder(activity);

            if (title != null) builder.setTitle(title);

            builder.setMessage(message);
            builder.setPositiveButton("Remove", new DialogInterface.OnClickListener() {
                public void onClick(DialogInterface dialog, int which) {
                    datasource.deleteReview(review);
                }
            });
            builder.setNegativeButton("Cancel", null);
            builder.show();
        }
    }
}
