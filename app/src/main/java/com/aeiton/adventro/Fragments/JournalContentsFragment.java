package com.aeiton.adventro.Fragments;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.aeiton.adventro.JournalData;
import com.aeiton.adventro.R;
import com.squareup.picasso.Picasso;

/**
 * A simple {@link Fragment} subclass.
 */
public class JournalContentsFragment extends Fragment {

    int id;
    ImageView image;
    TextView location, content;

    public JournalContentsFragment() {
        // Required empty public constructor
    }

    public JournalContentsFragment newInstance(int id){
        this.id = id;
        return new JournalContentsFragment();
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View rootView = inflater.inflate(R.layout.fragment_journal_contents, container, false);

        image = (ImageView) rootView.findViewById(R.id.image);
        location = (TextView) rootView.findViewById(R.id.location);
        content = (TextView) rootView.findViewById(R.id.content);

        Picasso.with(getContext())
                .load(JournalData.pages.get(id).getImage())
                .into(image);


        return rootView;


    }

}
