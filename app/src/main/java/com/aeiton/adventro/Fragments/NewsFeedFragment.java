package com.aeiton.adventro.Fragments;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.aeiton.adventro.Activity.RegistrationActivity;
import com.aeiton.adventro.Adapters.NewsFeedAdapter;
import com.aeiton.adventro.Adapters.NewsFeedModel;
import com.aeiton.adventro.R;

import java.util.ArrayList;

/**
 * A simple {@link Fragment} subclass.
 */
public class NewsFeedFragment extends Fragment {



    RecyclerView newsFeed;
    private NewsFeedAdapter mAdapter;
    private ArrayList<NewsFeedModel> feed = new ArrayList<>();

    public NewsFeedFragment() {
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
        View rootView = inflater.inflate(R.layout.fragment_news_feed, container, false);

        newsFeed = (RecyclerView) rootView.findViewById(R.id.recycler);

        mAdapter = new NewsFeedAdapter(feed);
        RecyclerView.LayoutManager mLayoutManager = new LinearLayoutManager(rootView.getContext());
        newsFeed.setLayoutManager(mLayoutManager);
        newsFeed.setItemAnimator(new DefaultItemAnimator());
        newsFeed.setAdapter(mAdapter);

        //type 0 for normal post, 1 for journal, 2 for invitation
        feed.add(new NewsFeedModel(0,"Musthaq Ahamad","3rd sem CSE","something related to this imagre as a caption", R.drawable.ic_profile_pic,R.drawable.img_placeholder,467,655,23.4444,34.5555));
        feed.add(new NewsFeedModel(0,"Musthaq Ahamad","3rd sem CSE","something related to this imagre as a caption", R.drawable.ic_profile_pic,R.drawable.img_placeholder,467,655,23.4444,34.5555));
        feed.add(new NewsFeedModel(1,"Musthaq Ahamad","This is a travel Journal, and i have travelled like a lot of places ",R.drawable.ic_profile_pic,R.drawable.img_placeholder,467,655));

        feed.add(new NewsFeedModel(0,"Musthaq Ahamad","3rd sem CSE","something related to this imagre as a caption", R.drawable.ic_profile_pic,R.drawable.img_placeholder,467,655,23.4444,34.5555));
        feed.add(new NewsFeedModel(2,"Musthaq Ahamad","Sahyadri College of engineering and management ",R.drawable.ic_profile_pic,R.drawable.img_placeholder_map,467,655));

        feed.add(new NewsFeedModel(0,"Musthaq Ahamad","3rd sem CSE","something related to this imagre as a caption", R.drawable.ic_profile_pic,R.drawable.img_placeholder,467,655,23.4444,34.5555));
        feed.add(new NewsFeedModel(0,"Musthaq Ahamad","3rd sem CSE","something related to this imagre as a caption", R.drawable.ic_profile_pic,R.drawable.img_placeholder,467,655,23.4444,34.5555));
        feed.add(new NewsFeedModel(2,"Musthaq Ahamad","Sahyadri College of engineering and management ",R.drawable.ic_profile_pic,R.drawable.img_placeholder_map,467,655));
        feed.add(new NewsFeedModel(0,"Musthaq Ahamad","3rd sem CSE","something related to this imagre as a caption", R.drawable.ic_profile_pic,R.drawable.img_placeholder,467,655,23.4444,34.5555));
        feed.add(new NewsFeedModel(2,"Musthaq Ahamad","Sahyadri College of engineering and management ",R.drawable.ic_profile_pic,R.drawable.img_placeholder_map,467,655));





        return rootView;
    }

}
