package com.aeiton.adventro.Fragments;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.aeiton.adventro.R;

/**
 * A simple {@link Fragment} subclass.
 */
public class LocationFragment extends Fragment {


    public LocationFragment() {
        // Required empty public constructor
    }

    public static NewsFeedFragment newInstance(){
        NewsFeedFragment fragment = new NewsFeedFragment();
        return fragment;
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_location, container, false);
    }

}
