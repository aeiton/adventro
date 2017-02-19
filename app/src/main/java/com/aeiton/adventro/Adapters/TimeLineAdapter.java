package com.aeiton.adventro.Adapters;


import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.TextView;

import com.aeiton.adventro.Model.FeedTimeLineModel;
import com.aeiton.adventro.Model.TimeLineModel;
import com.aeiton.adventro.R;

import java.util.List;


/**
 * Created by User on 8/3/2016.
 */

public class TimeLineAdapter extends RecyclerView.Adapter<TimeLineAdapter.MyViewHolder> {

    private List<TimeLineModel> timeLineList;
    private FrameLayout content;



    public class MyViewHolder extends RecyclerView.ViewHolder {

        private TextView caption, location, time;
        private ImageView propic, image;


        public MyViewHolder(View view) {
            super(view);

            propic = (ImageView) view.findViewById(R.id.pro_pic);
            location = (TextView) view.findViewById(R.id.location);
            time = (TextView) view.findViewById(R.id.time);
            image = (ImageView) view.findViewById(R.id.image);
            caption = (TextView) view.findViewById(R.id.caption);

            propic.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {

                }
            });
        }
    }


    public TimeLineAdapter(List<TimeLineModel> timeLineList) {
        this.timeLineList = timeLineList;
    }

    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.row_timeline_item, parent, false);

        return new MyViewHolder(itemView);
    }



    @Override
    public void onBindViewHolder(final MyViewHolder holder, final int position) {
        final TimeLineModel timelineModel = timeLineList.get(position);

        holder.propic.setImageResource(timelineModel.getProPic());
        holder.location.setText(timelineModel.getLocation());
        holder.time.setText(timelineModel.getTime());
        holder.image.setImageResource(timelineModel.getImage());
        holder.caption.setText(timelineModel.getCaption());



    }


    @Override
    public int getItemCount() {
        return timeLineList.size();
    }
}

