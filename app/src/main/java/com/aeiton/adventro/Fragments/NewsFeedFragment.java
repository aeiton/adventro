package com.aeiton.adventro.Fragments;


import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.aeiton.adventro.Adapters.NewsFeedAdapter;
import com.aeiton.adventro.Adapters.NewsFeedModel;
import com.aeiton.adventro.Adapters.TimeLineListAdapter;
import com.aeiton.adventro.Constants;
import com.aeiton.adventro.Model.FeedTimeLineModel;
import com.aeiton.adventro.NetworkSingleton;
import com.aeiton.adventro.R;
import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

/**
 * A simple {@link Fragment} subclass.
 */
public class NewsFeedFragment extends Fragment {


    RecyclerView newsFeed;
    RecyclerView timeline;
    private NewsFeedAdapter mAdapter;
    private ArrayList<NewsFeedModel> feed = new ArrayList<>();
    private TimeLineListAdapter mtAdapter;
    private ArrayList<FeedTimeLineModel> timelineMOdel = new ArrayList<>();

    public NewsFeedFragment() {
        // Required empty public constructor
    }

    public static NewsFeedFragment newInstance() {
        NewsFeedFragment fragment = new NewsFeedFragment();
        return fragment;
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        sendData();
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View rootView = inflater.inflate(R.layout.fragment_news_feed, container, false);

        timeline = (RecyclerView) rootView.findViewById(R.id.timeline_recycler);

        mtAdapter = new TimeLineListAdapter(timelineMOdel);
        RecyclerView.LayoutManager mhLayoutManager = new LinearLayoutManager(rootView.getContext(), LinearLayoutManager.HORIZONTAL, false) {
            @Override
            public boolean canScrollVertically() {
                return false;
            }
        };
        timeline.setLayoutManager(mhLayoutManager);
        timeline.setAdapter(mtAdapter);

        timelineMOdel.add(new FeedTimeLineModel("Musthaq Ahamad", R.drawable.ic_profile_pic));
        timelineMOdel.add(new FeedTimeLineModel("SomeOther ", R.drawable.ic_profile_pic));
        timelineMOdel.add(new FeedTimeLineModel("SomeOther ", R.drawable.ic_profile_pic));
        timelineMOdel.add(new FeedTimeLineModel("SomeOther ", R.drawable.ic_profile_pic));
        timelineMOdel.add(new FeedTimeLineModel("SomeOther ", R.drawable.ic_profile_pic));
        timelineMOdel.add(new FeedTimeLineModel("SomeOther ", R.drawable.ic_profile_pic));
        timelineMOdel.add(new FeedTimeLineModel("SomeOther ", R.drawable.ic_profile_pic));

        mtAdapter.notifyDataSetChanged();


        newsFeed = (RecyclerView) rootView.findViewById(R.id.recycler);
        mAdapter = new NewsFeedAdapter(feed);
        RecyclerView.LayoutManager mLayoutManager = new LinearLayoutManager(rootView.getContext());
        newsFeed.setLayoutManager(mLayoutManager);
        newsFeed.setItemAnimator(new DefaultItemAnimator());
        newsFeed.setAdapter(mAdapter);

        //type 0 for normal post, 1 for journal, 2 for invitation
        feed.add(new NewsFeedModel(0, "Musthaq Ahamad", "3rd sem CSE", "something related to this imagre as a caption", R.drawable.ic_profile_pic, R.drawable.img_placeholder, 467, 655, 23.4444, 34.5555));
        feed.add(new NewsFeedModel(0, "Musthaq Ahamad", "3rd sem CSE", "something related to this imagre as a caption", R.drawable.ic_profile_pic, R.drawable.img_placeholder, 467, 655, 23.4444, 34.5555));
        feed.add(new NewsFeedModel(1, "Musthaq Ahamad", "This is a travel Journal, and i have travelled like a lot of places ", R.drawable.ic_profile_pic, R.drawable.img_placeholder, 467, 655));

        feed.add(new NewsFeedModel(0, "Musthaq Ahamad", "3rd sem CSE", "something related to this imagre as a caption", R.drawable.ic_profile_pic, R.drawable.img_placeholder, 467, 655, 23.4444, 34.5555));
        feed.add(new NewsFeedModel(2, "Musthaq Ahamad", "Sahyadri College of engineering and management ", R.drawable.ic_profile_pic, R.drawable.img_placeholder_map, 467, 655));

        feed.add(new NewsFeedModel(0, "Musthaq Ahamad", "3rd sem CSE", "something related to this imagre as a caption", R.drawable.ic_profile_pic, R.drawable.img_placeholder, 467, 655, 23.4444, 34.5555));
        feed.add(new NewsFeedModel(0, "Musthaq Ahamad", "3rd sem CSE", "something related to this imagre as a caption", R.drawable.ic_profile_pic, R.drawable.img_placeholder, 467, 655, 23.4444, 34.5555));
        feed.add(new NewsFeedModel(2, "Musthaq Ahamad", "Sahyadri College of engineering and management ", R.drawable.ic_profile_pic, R.drawable.img_placeholder_map, 467, 655));
        feed.add(new NewsFeedModel(0, "Musthaq Ahamad", "3rd sem CSE", "something related to this imagre as a caption", R.drawable.ic_profile_pic, R.drawable.img_placeholder, 467, 655, 23.4444, 34.5555));
        feed.add(new NewsFeedModel(2, "Musthaq Ahamad", "Sahyadri College of engineering and management ", R.drawable.ic_profile_pic, R.drawable.img_placeholder_map, 467, 655));


        return rootView;
    }

    private void sendData() {
        StringRequest stringRequest = new StringRequest(Request.Method.POST, Constants.ADD_NODE_TIMELINE, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                Log.d("REsponse", response);
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {

            }
        }) {
            @Override
            protected Map<String, String> getParams() throws AuthFailureError {
                Map<String, String> pa
                        = new HashMap<>();
                pa.put("user_id", 24 + "");
                pa.put("page", "" + 0);
                return pa;
            }
        };

        NetworkSingleton.getInstance(getContext()).addToRequestQueue(stringRequest);
    }

}
