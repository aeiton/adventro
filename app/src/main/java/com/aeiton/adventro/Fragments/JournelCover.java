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
public class JournelCover extends Fragment {


    public JournelCover() {
        // Required empty public constructor
    }

    public static JournelCover newInstace(){
        return new JournelCover();
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_journel_cover, container, false);
    }

}
