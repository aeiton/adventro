package com.aeiton.adventro.Fragments;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.aeiton.adventro.Adapters.NearbyPlacesAdapter;
import com.aeiton.adventro.Model.NearbyPlacesModel;
import com.aeiton.adventro.R;

import java.util.ArrayList;

/**
 * A simple {@link Fragment} subclass.
 */
public class NearbyLocation extends Fragment {


    RecyclerView recyclerView;
    private NearbyPlacesAdapter mAdapter;
    private ArrayList<NearbyPlacesModel> nearbyList = new ArrayList<>();

    public NearbyLocation() {
        // Required empty public constructor
    }

    public static NearbyLocation newInstance() {
        NearbyLocation fragment = new NearbyLocation();
        return fragment;
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View rootView = inflater.inflate(R.layout.fragment_nearby_location, container, false);


        recyclerView = (RecyclerView) rootView.findViewById(R.id.recycler);

        mAdapter = new NearbyPlacesAdapter(nearbyList, getContext());
        RecyclerView.LayoutManager mLayoutManager = new LinearLayoutManager(rootView.getContext());
        recyclerView.setLayoutManager(mLayoutManager);
        recyclerView.setItemAnimator(new DefaultItemAnimator());
        recyclerView.setAdapter(mAdapter);

         nearbyList.add(new NearbyPlacesModel("Adyar Falls","Adyar Falls the waterfall can be easily approached from two sides – i.e one from Neermarga on the road leading to Volachil and the ...",R.drawable.img_placeholder_5,11.006655,55.446665));
         nearbyList.add(new NearbyPlacesModel("St Marys island","St. Mary's Islands, also known as Coconut Island and Thonsepar, are a set of four small islands in the Arabian Sea off the coast of Malpe in Udupi, Karnataka",R.drawable.img_placeholder_6,13.3795,74.6708113));
         nearbyList.add(new NearbyPlacesModel("Kadri Park","Kadri Park is a garden, 5 min from the city near AIR studios. It is located in Kadri gudde. It's the largest park within city limits of Mangalore. The park has beautifully laid garden and a toy train which is currently not operatinal.",R.drawable.img_placeholder_7,12.888945,74.856276));
         nearbyList.add(new NearbyPlacesModel("Bekal Fort","Bekal Fort, is the largest fort in Kerala, situated at Bekal village in Kasaragod district, North Kerala and it is 67 km from Mangalore spreading over 40 acres.",R.drawable.img_placeholder_8,12.3926134,75.0330579));

        mAdapter.notifyDataSetChanged();
        return rootView;
    }

}
