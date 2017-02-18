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

     public static NearbyLocation newInstance(){
         NearbyLocation fragment = new NearbyLocation();
         return fragment;
     }



     @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View rootView = inflater.inflate(R.layout.fragment_nearby_location, container, false);


         recyclerView = (RecyclerView) rootView.findViewById(R.id.recycler);

         mAdapter = new NearbyPlacesAdapter(nearbyList,getContext());
         RecyclerView.LayoutManager mLayoutManager = new LinearLayoutManager(rootView.getContext());
         recyclerView.setLayoutManager(mLayoutManager);
         recyclerView.setItemAnimator(new DefaultItemAnimator());
         recyclerView.setAdapter(mAdapter);

         nearbyList.add(new NearbyPlacesModel("Example place name","this place is really awesome",R.drawable.img_placeholder,11.006655,55.446665));
         nearbyList.add(new NearbyPlacesModel("Example place name","this place is really awesome",R.drawable.img_placeholder,11.006655,55.446665));
         nearbyList.add(new NearbyPlacesModel("Example place name","this place is really awesome",R.drawable.img_placeholder,11.006655,55.446665));
         nearbyList.add(new NearbyPlacesModel("Example place name","this place is really awesome",R.drawable.img_placeholder,11.006655,55.446665));
         nearbyList.add(new NearbyPlacesModel("Example place name","this place is really awesome",R.drawable.img_placeholder,11.006655,55.446665));

         mAdapter.notifyDataSetChanged();
         return rootView;
    }

}
