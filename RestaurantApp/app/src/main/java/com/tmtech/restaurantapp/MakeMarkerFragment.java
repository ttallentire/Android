package com.tmtech.restaurantapp;

import android.app.Activity;
import android.app.FragmentManager;
import android.net.Uri;
import android.os.Bundle;
import android.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;


/**
 * A simple {@link Fragment} subclass.
 * Activities that contain this fragment must implement the
 * {@link MakeMarkerFragment.OnFragmentInteractionListener} interface
 * to handle interaction events.
 * Use the {@link MakeMarkerFragment#newInstance} factory method to
 * create an instance of this fragment.
 *
 */
public class MakeMarkerFragment extends Fragment {

    private OnFragmentInteractionListener mListener;
    private static MapsActivity activity;

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @return A new instance of fragment MakeMarkerFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static MakeMarkerFragment newInstance(MapsActivity act) {
        MakeMarkerFragment fragment = new MakeMarkerFragment();
        activity = act;
        return fragment;
    }
    public MakeMarkerFragment() {
        // Required empty public constructor
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        FragmentManager fm = getFragmentManager();
        fm.beginTransaction()
                .setCustomAnimations(android.R.animator.fade_in, android.R.animator.fade_out)
                .hide(this)
                .commit();
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_make_marker, container, false);
    }

    // TODO: Rename method, update argument and hook method into UI event
    public void onButtonPressed(Uri uri) {
        if (mListener != null) {
            mListener.onFragmentInteraction(uri);
        }
    }

    @Override
    public void onAttach(Activity activity) {
        super.onAttach(activity);
        try {
            mListener = (OnFragmentInteractionListener) activity;
        } catch (ClassCastException e) {
            throw new ClassCastException(activity.toString()
                    + " must implement OnFragmentInteractionListener");
        }
    }

    @Override
    public void onDetach() {
        super.onDetach();
        mListener = null;
    }

    public void initSpinner()
    {
        SpinnerFragmentActivity spinnerFragmentActivity = new SpinnerFragmentActivity();
        Spinner spinner = (Spinner)getView().findViewById(R.id.spinnerMain);
        // Create an ArrayAdapter using the string array and a default spinner layout
        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(getView().getContext(),
                R.array.categories, android.R.layout.simple_spinner_item);
        // Specify the layout to use when the list of choices appears
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        // Apply the adapter to the spinner
        spinner.setAdapter(adapter);

        spinner.setOnItemSelectedListener(spinnerFragmentActivity);
    }

    /**
     * This interface must be implemented by activities that contain this
     * fragment to allow an interaction in this fragment to be communicated
     * to the activity and potentially other fragments contained in that
     * activity.
     * <p>
     * See the Android Training lesson <a href=
     * "http://developer.android.com/training/basics/fragments/communicating.html"
     * >Communicating with Other Fragments</a> for more information.
     */
    public interface OnFragmentInteractionListener {
        // TODO: Update argument type and name
        public void onFragmentInteraction(Uri uri);
    }

    public void save(View view) {
        String title = ((EditText)getView().findViewById(R.id.title)).getText().toString();
        short rating = Short.parseShort(((EditText)getView().findViewById(R.id.rating)).getText().toString());
        String comment = ((EditText)getView().findViewById(R.id.comment)).getText().toString();
        String category;

        if (rating > 5 || rating < 0) {
            Toast.makeText(null, "Rating invalid", Toast.LENGTH_SHORT).show();
        } else {
            FragmentManager fm = getFragmentManager();
            fm.beginTransaction()
                    .setCustomAnimations(android.R.animator.fade_in, android.R.animator.fade_out)
                    .hide(this)
                    .commit();
            category = ((TextView)getView().findViewById(R.id.category)).getText().toString();
            activity.createMarker(title, rating, comment, category);
        }
    }
}
