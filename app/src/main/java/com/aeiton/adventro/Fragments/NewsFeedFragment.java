package com.aeiton.adventro.Fragments;


import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.widget.NestedScrollView;
import android.support.v7.app.AlertDialog;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.aeiton.adventro.Adapters.NewsFeedAdapter;
import com.aeiton.adventro.Adapters.NewsFeedModel;
import com.aeiton.adventro.Adapters.TimeLineListAdapter;
import com.aeiton.adventro.Constants;
import com.aeiton.adventro.Model.FeedTimeLineModel;
import com.aeiton.adventro.NetworkSingleton;
import com.aeiton.adventro.R;
import com.android.volley.AuthFailureError;

import com.android.volley.DefaultRetryPolicy;

import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;

import com.android.volley.toolbox.Volley;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;


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

    NestedScrollView main_scroll;
    ProgressBar progress;


    RecyclerView timeline;

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

        main_scroll = (NestedScrollView) rootView.findViewById(R.id.scroll);


        mtAdapter = new TimeLineListAdapter(timelineMOdel);
        RecyclerView.LayoutManager mhLayoutManager = new LinearLayoutManager(rootView.getContext(), LinearLayoutManager.HORIZONTAL, false) {
            @Override
            public boolean canScrollVertically() {
                return false;
            }
        };
        timeline.setLayoutManager(mhLayoutManager);
        timeline.setAdapter(mtAdapter);




        newsFeed = (RecyclerView) rootView.findViewById(R.id.recycler);
        mAdapter = new NewsFeedAdapter(feed);
        RecyclerView.LayoutManager mLayoutManager = new LinearLayoutManager(rootView.getContext());
        newsFeed.setLayoutManager(mLayoutManager);
        newsFeed.setItemAnimator(new DefaultItemAnimator());
        newsFeed.setAdapter(mAdapter);

//        hideItems();
        loadOflline();



        return rootView;
    }

//    public void hideItems(){
//        main_scroll.setVisibility(View.GONE);
//        progress.setVisibility(View.VISIBLE);
//    }
//
//    public void showItems(){
//        main_scroll.setVisibility(View.VISIBLE);
//        progress.setVisibility(View.GONE);
//    }

    public void loadOflline(){

        timelineMOdel.add(new FeedTimeLineModel("Rumaan Khalandher",R.drawable.ic_profile_pic));
        timelineMOdel.add(new FeedTimeLineModel("Musthaq",R.drawable.img_propi_2));
        timelineMOdel.add(new FeedTimeLineModel("Shreyas ",R.drawable.ic_propic_2));
        timelineMOdel.add(new FeedTimeLineModel("Jhon a  ",R.drawable.ic_profile_pic));
        timelineMOdel.add(new FeedTimeLineModel("Adriel ",R.drawable.img_pro_pic_3));
        timelineMOdel.add(new FeedTimeLineModel("Rumaan Khalandher ",R.drawable.ic_profile_pic));
        timelineMOdel.add(new FeedTimeLineModel("Musthaq Ahamad ",R.drawable.img_propi_2));

        mtAdapter.notifyDataSetChanged();

        //type 0 for normal post, 1 for journal, 2 for invitation
        feed.add(new NewsFeedModel(0,"Rumaan Khalandher","Digital Valley","Digital representation of the nearest valley near us which can be visited in 325 km distance from homw location", R.drawable.ic_profile_pic,R.drawable.img_placeholder,467,655,23.4444,34.5555));
        feed.add(new NewsFeedModel(0,"Musthaq Ahamad","Spitti Valley","The Spiti Valley is a desert mountain valley located high in the Himalaya mountains in the north-eastern part of the Indian state of Himachal Pradesh. ", R.drawable.img_propi_2,R.drawable.img_placeholder_2,467,655,23.4444,34.5555));
        feed.add(new NewsFeedModel(1,"Shreyas B","The Amazing Views of the Spiti Valey, Heaven on Earth. ",R.drawable.ic_propic_2,R.drawable.img_placeholder_3,467,655));

        feed.add(new NewsFeedModel(0,"Musthaq Ahamad","3rd sem CSE","something related to this imagre as a caption", R.drawable.ic_profile_pic,R.drawable.img_placeholder,467,655,23.4444,34.5555));
        feed.add(new NewsFeedModel(2,"Musthaq Ahamad","Sahyadri College of engineering and management ",R.drawable.img_propi_2,R.drawable.img_placeholder_map,467,655));

        feed.add(new NewsFeedModel(0,"Karthik n","Adyar falls","something related to this imagre as a caption", R.drawable.ic_profile_pic,R.drawable.img_placeholder_5,467,655,23.4444,34.5555));
        feed.add(new NewsFeedModel(2,"Musthaq Ahamad","Sahyadri College of engineering and management ",R.drawable.ic_propic_2,R.drawable.img_placeholder_map,467,655));
        feed.add(new NewsFeedModel(0,"Prajuwal B","St. mary's island","One of the must Visit island in Manglore region.", R.drawable.ic_profile_pic,R.drawable.img_placeholder_6,467,655,13.3795261,74.6718896));
        feed.add(new NewsFeedModel(2,"Musthaq Ahamad","Sahyadri College of engineering and management ",R.drawable.ic_propic_2,R.drawable.img_placeholder_map,467,655));


        mAdapter.notifyDataSetChanged();

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


//    public void loadOnline(){
//
//        String url = "http://legall.co.in/adventro/get_news_feed.php";
//
//        StringRequest stringRequest = new StringRequest(Request.Method.POST, url,
//                new Response.Listener<String>() {
//                    @Override
//                    public void onResponse(String response) {
//                        //Success handle response
//
//                        Log.i("adv",response.toString());
//                        showItems();
//
//                        try {
//                            JSONObject obj = new JSONObject(response);
//                            JSONArray journalArray = obj.getJSONArray("journals");
//                            for (int i = 0; i < journalArray.length(); i++){
//                                JSONObject object = journalArray.getJSONObject(i);
//                                NewsFeedModel model;
//                                model = new NewsFeedModel(1, object.getInt("id"),object.getString("name"),"http://legall.co.in/"+object.getString("img_path"),object.getString("journal_image"),650,340);
//
//                            }
//                        }
//                        catch(JSONException ex) {
//                            ex.printStackTrace();
//                        }
//                    }
//                }, new Response.ErrorListener() {
//            @Override
//            public void onErrorResponse(VolleyError error) {
//                //Error handle error
//            }
//        }) {
//            @Override
//            protected Map<String, String> getParams() throws AuthFailureError {
//                Map<String, String> map = new HashMap<>();
//                map.put("user_id", "24");
//                map.put("page", "0");
//                return map;
//            }
//        };
//
//        // Add the request to the queue
//        stringRequest.setRetryPolicy(new DefaultRetryPolicy(20000, DefaultRetryPolicy.DEFAULT_MAX_RETRIES, DefaultRetryPolicy.DEFAULT_BACKOFF_MULT));
//        Volley.newRequestQueue(getContext()).add(stringRequest);
//
//    }

}
