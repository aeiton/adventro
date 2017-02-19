package com.aeiton.adventro.Adapters;


import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.aeiton.adventro.Model.FeedTimeLineModel;
import com.aeiton.adventro.R;

import java.util.List;


/**
 * Created by User on 8/3/2016.
 */

public class TimeLineListAdapter extends RecyclerView.Adapter<TimeLineListAdapter.MyViewHolder> {

    private List<FeedTimeLineModel> timeLineList;
    private FrameLayout content;



    public class MyViewHolder extends RecyclerView.ViewHolder {

        private TextView name;
        private ImageView propic;


        public MyViewHolder(View view) {
            super(view);

            propic = (ImageView) view.findViewById(R.id.pro_pic);
            name = (TextView) view.findViewById(R.id.user_name);

            Toast.makeText(view.getContext(),"here",Toast.LENGTH_SHORT).show();
            propic.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {

                }
            });
        }
    }


    public TimeLineListAdapter(List<FeedTimeLineModel> timeLineList) {
        this.timeLineList = timeLineList;
    }

    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.item_feed_timeline, parent, false);

        return new MyViewHolder(itemView);
    }



    @Override
    public void onBindViewHolder(final MyViewHolder holder, final int position) {
        final FeedTimeLineModel timelineModel = timeLineList.get(position);

        holder.propic.setImageResource(timelineModel.getProPic());
        holder.name.setText(timelineModel.getTitle());



    }


    @Override
    public int getItemCount() {
        return timeLineList.size();
    }
}

