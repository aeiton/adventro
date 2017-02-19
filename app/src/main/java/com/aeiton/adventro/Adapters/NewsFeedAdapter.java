package com.aeiton.adventro.Adapters;

import android.content.Intent;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.aeiton.adventro.Activity.JournalView;
import com.aeiton.adventro.Fragments.LocationFragment;
import com.aeiton.adventro.R;

import java.util.List;

/**
 * Created by User on 8/3/2016.
 */

public class NewsFeedAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {

    private List<NewsFeedModel> feedList;



    public class ViewHolder1 extends RecyclerView.ViewHolder {


        private ImageView propic, image;
        private TextView name, caption, like, comment, location;
        ImageButton likeBtn;

        public ViewHolder1(View view) {
            super(view);

            propic = (ImageView) itemView.findViewById(R.id.pro_pic);
            image = (ImageView) itemView.findViewById(R.id.image);
            name = (TextView) itemView.findViewById(R.id.user_name);
            caption = (TextView) itemView.findViewById(R.id.caption);
            location = (TextView) itemView.findViewById(R.id.location);
            like = (TextView) itemView.findViewById(R.id.like_count);
            comment = (TextView) itemView.findViewById(R.id.comment_count);
            likeBtn = (ImageButton) itemView.findViewById(R.id.btn_like);



        }
    }

    public class ViewHolder2 extends RecyclerView.ViewHolder {


        private ImageView propic, image;
        private TextView name, title, like, comment;
        ImageButton likeBtn;


        public ViewHolder2(View view) {
            super(view);


            propic = (ImageView) itemView.findViewById(R.id.pro_pic);
            image = (ImageView) itemView.findViewById(R.id.image);
            name = (TextView) itemView.findViewById(R.id.user_name);
            title = (TextView) itemView.findViewById(R.id.title);
            like = (TextView) itemView.findViewById(R.id.like_count);
            comment = (TextView) itemView.findViewById(R.id.comment_count);
            likeBtn = (ImageButton) itemView.findViewById(R.id.btn_like);

        }
    }

    public class ViewHolder3 extends RecyclerView.ViewHolder {
        public TextView name,title;
        ImageView cover;
        LinearLayout main_view;
        private ImageView propic, image;

        public ViewHolder3(View view) {
            super(view);

            cover = (ImageView) view.findViewById(R.id.image);
            propic = (ImageView) itemView.findViewById(R.id.pro_pic);
            image = (ImageView) itemView.findViewById(R.id.image);
            name = (TextView) itemView.findViewById(R.id.user_name);




        }
    }


    public NewsFeedAdapter(List<NewsFeedModel> feedList) {
        this.feedList = feedList;
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {



        switch(viewType){

            case 0:
                View itemView = LayoutInflater.from(parent.getContext())
                        .inflate(R.layout.row_news_feed_item, parent, false);

            return new ViewHolder1(itemView);

            case 1:
                View journalView = LayoutInflater.from(parent.getContext())
                        .inflate(R.layout.row_news_feed_journal, parent, false);
                return new ViewHolder2(journalView);

            case 2:
                View InvitationView = LayoutInflater.from(parent.getContext())
                        .inflate(R.layout.row_news_feed_invitation, parent, false);
                return new ViewHolder3(InvitationView);

        }

        return null;

    }




    @Override
    public void onBindViewHolder(final RecyclerView.ViewHolder holder, final int position) {

                final NewsFeedModel feedModel = feedList.get(position);

        switch (holder.getItemViewType()){
            case 0:
                final ViewHolder1 vh1 = (ViewHolder1) holder;

                vh1.propic.setImageResource(feedModel.getPropic());
                vh1.image.setImageResource(feedModel.getImg());
                vh1.name.setText(feedModel.getName());
                vh1.caption.setText(feedModel.getCaption());
                vh1.location.setText(feedModel.getLocation());
                vh1.like.setText(""+feedModel.getLike());
                vh1.comment.setText(""+feedModel.getComment());



                if (feedModel.getLikeStatus()){
                    vh1.likeBtn.setImageResource(R.drawable.ic_like);
                }else {
                    vh1.likeBtn.setImageResource(R.drawable.ic_liked);
                }

                vh1.likeBtn.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {


                        if (feedModel.getLikeStatus()) {
                            final Animation myAnim = AnimationUtils.loadAnimation(v.getContext(), R.anim.button_bounce);
                            vh1.likeBtn.setImageResource(R.drawable.ic_liked);
                            vh1.likeBtn.startAnimation(myAnim);
                            feedModel.unlikePost();
                        }else {

                            final Animation myAnim = AnimationUtils.loadAnimation(v.getContext(), R.anim.button_bounce);
                            vh1.likeBtn.setImageResource(R.drawable.ic_like);
                            vh1.likeBtn.startAnimation(myAnim);
                            feedModel.likePost();
                        }
                    }
                });

                break;
            case 1:

                final ViewHolder2 vh2 = (ViewHolder2) holder;

                vh2.propic.setImageResource(feedModel.getPropic());
                vh2.image.setImageResource(feedModel.getImg());
                vh2.name.setText(feedModel.getName());
                vh2.title.setText(feedModel.getTitle());
                vh2.like.setText(""+feedModel.getLike());
                vh2.comment.setText(""+feedModel.getComment());

                vh2.image.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        v.getContext().startActivity(new Intent(v.getContext(), JournalView.class));
                    }
                });

                if (feedModel.getLikeStatus()){
                    vh2.likeBtn.setImageResource(R.drawable.ic_like);
                }else {
                    vh2.likeBtn.setImageResource(R.drawable.ic_liked);
                }

                vh2.likeBtn.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {


                        if (feedModel.getLikeStatus()) {
                            final Animation myAnim = AnimationUtils.loadAnimation(v.getContext(), R.anim.button_bounce);
                            vh2.likeBtn.setImageResource(R.drawable.ic_liked);
                            vh2.likeBtn.startAnimation(myAnim);
                            feedModel.unlikePost();
                        }else {

                            final Animation myAnim = AnimationUtils.loadAnimation(v.getContext(), R.anim.button_bounce);
                            vh2.likeBtn.setImageResource(R.drawable.ic_like);
                            vh2.likeBtn.startAnimation(myAnim);
                            feedModel.likePost();
                        }
                    }
                });

                break;
            case 2:

                final ViewHolder3 vh3 = (ViewHolder3) holder;

                vh3.propic.setImageResource(feedModel.getPropic());
                vh3.name.setText(feedModel.getName());
                vh3.image.setImageResource(feedModel.getImg());
//                vh3..setText(feedModel.getLocation());





                break;

        }





    }


    @Override
    public int getItemCount() {
        return feedList.size();
    }

    @Override
    public int getItemViewType(int position){

        int type = feedList.get(position).getType();
        return type;
    }
}

