package com.aeiton.adventro.Fragments;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.aeiton.adventro.Adapters.PostListAdapter;
import com.aeiton.adventro.Model.PostsModel;
import com.aeiton.adventro.R;

import java.util.ArrayList;

/**
 * A simple {@link Fragment} subclass.
 */
public class PostsFragment extends Fragment {


    RecyclerView myPostsRecycler;
    private PostListAdapter myPostListAdapter;
    private ArrayList<PostsModel> myGroupList = new ArrayList<>();

    public PostsFragment() {
        // Required empty public constructor
    }

    public static PostsFragment newInstance(){
        PostsFragment fragment = new PostsFragment();
        return fragment;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View rootView = inflater.inflate(R.layout.fragment_posts, container, false);
        myPostsRecycler = (RecyclerView) rootView.findViewById(R.id.recycler);

        //my groups
        myPostListAdapter = new PostListAdapter(myGroupList);
        RecyclerView.LayoutManager mLayoutManager = new LinearLayoutManager(rootView.getContext()) {
            @Override
            public boolean canScrollVertically() {
                return false;
            }
        };

        myPostsRecycler.setLayoutManager(mLayoutManager);
        myPostsRecycler.setItemAnimator(new DefaultItemAnimator());
        myPostsRecycler.setAdapter(myPostListAdapter);

        myGroupList.add(new PostsModel("Ranipuram Trekking",R.drawable.ic_profile_pic));
        myGroupList.add(new PostsModel("Mumbai tour",R.drawable.ic_profile_pic));

        myPostListAdapter.notifyDataSetChanged();

        return rootView;
    }

}
