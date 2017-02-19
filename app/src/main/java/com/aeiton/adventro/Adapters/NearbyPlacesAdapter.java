package com.aeiton.adventro.Adapters;


import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;

import com.aeiton.adventro.Model.NearbyPlacesModel;
import com.aeiton.adventro.R;

import java.util.List;


/**
 * Created by User on 8/3/2016.
 */

public class NearbyPlacesAdapter extends RecyclerView.Adapter<NearbyPlacesAdapter.MyViewHolder> {

    private List<NearbyPlacesModel> placesList;
    private Context context;



    public class MyViewHolder extends RecyclerView.ViewHolder {

        private ImageView image;
        private TextView title,caption;
        private ImageButton showLocation;

        public MyViewHolder(View view) {
            super(view);

            title = (TextView) view.findViewById(R.id.txt_title);
            image = (ImageView) view.findViewById(R.id.iv);
            caption = (TextView) view.findViewById(R.id.caption);
            showLocation = (ImageButton) view.findViewById(R.id.show_location);

        }
    }


    public NearbyPlacesAdapter(List<NearbyPlacesModel> placesList, Context context) {
        this.placesList = placesList;
        this.context = context;
    }

    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.row_nearby_places, parent, false);

        return new MyViewHolder(itemView);
    }



    @Override
    public void onBindViewHolder(final MyViewHolder holder, final int position) {
        final NearbyPlacesModel placesModel = placesList.get(position);

        holder.image.setImageResource(placesModel.getImgId());
        holder.title.setText(placesModel.getLocation());
        holder.caption.setText(placesModel.getCaption());
        final Double lat = Double.parseDouble(placesModel.getLat().toString());
        final Double lng = Double.parseDouble(placesModel.getLng().toString());

        holder.showLocation.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Uri gmmIntentUri = Uri.parse("geo:"+lat.toString()+","+lng.toString());
                Intent mapIntent = new Intent(Intent.ACTION_VIEW,gmmIntentUri);
                mapIntent.setPackage("com.google.android.apps.maps");
                context.startActivity(mapIntent);
            }
        });


    }


    @Override
    public int getItemCount() {
        return placesList.size();
    }
}

