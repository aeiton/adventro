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

import com.aeiton.adventro.Activity.EditTimeLine;
import com.aeiton.adventro.Model.PostsModel;
import com.aeiton.adventro.R;

import java.util.List;


/**
 * Created by User on 8/3/2016.
 */

public class PostListAdapter extends RecyclerView.Adapter<PostListAdapter.MyViewHolder> {

    private List<PostsModel> myPostList;
    private FrameLayout content;


    public PostListAdapter(List<PostsModel> myGroupList) {
        this.myPostList = myGroupList;
    }

    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.row_my_groups_list, parent, false);

        return new MyViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(final MyViewHolder holder, final int position) {
        final PostsModel groupModel = myPostList.get(position);

        holder.image.setImageResource(groupModel.getProPic());
        holder.title.setText(groupModel.getTitle());

        if (position == myPostList.size() - 1) {
            holder.margin.setVisibility(View.INVISIBLE);
        }

    }

    @Override
    public int getItemCount() {
        return myPostList.size();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder {

        private TextView title;
        private ImageView image;
        private View margin;
        private LinearLayout main;

        public MyViewHolder(View view) {
            super(view);

            image = (ImageView) view.findViewById(R.id.pro_pic);
            title = (TextView) view.findViewById(R.id.post_name);
            margin = (View) view.findViewById(R.id.margin);
            main = (LinearLayout) view.findViewById(R.id.main_content);
            main.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    v.getContext().startActivity(new Intent(v.getContext(), EditTimeLine.class).putExtra("access", 1));
                }
            });

        }
    }
}

