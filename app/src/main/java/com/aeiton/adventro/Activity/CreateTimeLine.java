package com.aeiton.adventro.Activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.aeiton.adventro.Constants;
import com.aeiton.adventro.NetworkSingleton;
import com.aeiton.adventro.R;
import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.google.android.gms.common.GooglePlayServicesNotAvailableException;
import com.google.android.gms.common.GooglePlayServicesRepairableException;
import com.google.android.gms.location.places.Place;
import com.google.android.gms.location.places.ui.PlacePicker;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;

public class CreateTimeLine extends AppCompatActivity implements View.OnClickListener {

    private static final int SOURCE_REQUEST_CODE = 32;
    private static final int DESTINATION_REQUEST_CODE = 31;
    Button create_timeline;
    private Place sourcePlace, destinationPlace;
    private LinearLayout source, destination;

    private TextView srcLoc, dstLoc;

    private double sourceLat, sourceLong;
    private double destLat, destLong;

    private String srcAddress, destAddress;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_create_time_line);

        source = (LinearLayout) findViewById(R.id.starting_location);
        destination = (LinearLayout) findViewById(R.id.destination_location);
        srcLoc = (TextView) findViewById(R.id.source_location_text);
        dstLoc = (TextView) findViewById(R.id.destination_location_text);

        /* Hook up on click listeners */
        source.setOnClickListener(this);
        destination.setOnClickListener(this);

        create_timeline = (Button) findViewById(R.id.create_timeline);
        create_timeline.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(CreateTimeLine.this, EditTimeLine.class).putExtra("access", 1));
            }
        });
    }


    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.starting_location:
                // get the starting location
                PlacePicker.IntentBuilder intentBuilder = new PlacePicker.IntentBuilder();
                try {
                    startActivityForResult(intentBuilder.build(CreateTimeLine.this), SOURCE_REQUEST_CODE);
                } catch (GooglePlayServicesRepairableException | GooglePlayServicesNotAvailableException e) {
                    e.printStackTrace();
                }
                break;
            case R.id.destination_location:
                // get the destination location
                PlacePicker.IntentBuilder builder = new PlacePicker.IntentBuilder();
                try {
                    startActivityForResult(builder.build(CreateTimeLine.this), DESTINATION_REQUEST_CODE);
                } catch (GooglePlayServicesRepairableException | GooglePlayServicesNotAvailableException e) {
                    e.printStackTrace();
                }
                break;
        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        // check for the starting activity location result
        if (requestCode == SOURCE_REQUEST_CODE) {
            if (resultCode == RESULT_OK && data != null) {
                // get the data
                sourcePlace = PlacePicker.getPlace(this, data);

                srcAddress = sourcePlace.getName().toString();
                sourceLat = sourcePlace.getLatLng().latitude;
                sourceLong = sourcePlace.getLatLng().longitude;

                setTheSourceLocation();
            }
        }

        // check for the starting activity location result
        if (requestCode == DESTINATION_REQUEST_CODE) {
            if (resultCode == RESULT_OK && data != null) {
                // get the data
                destinationPlace = PlacePicker.getPlace(this, data);

                destAddress = destinationPlace.getName().toString();
                destLat = destinationPlace.getLatLng().latitude;
                destLong = destinationPlace.getLatLng().longitude;

                setTheDestinationLocation();
            }
        }
    }

    private void setTheDestinationLocation() {
        dstLoc.setText(destAddress);
        // presume this will be called in the end
        sendDataToBackEnd();
    }

    private void setTheSourceLocation() {

        srcLoc.setText(srcAddress);
    }

    private void sendDataToBackEnd() {
        StringRequest request = new StringRequest(Request.Method.POST, Constants.CREATE_TIMELINE, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                Log.d("CreateTimeLine:", response);
                try {
                    if (new JSONObject(response).getString("status").equals("1")) {
                        // leaoisjo
                    }
                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {

            }
        }) {
            @Override
            protected Map<String, String> getParams() throws AuthFailureError {
                Map<String, String> params = new HashMap<>();
                params.put("user_id", 24 + "");
                params.put("start_lat", String.valueOf(sourceLat));
                params.put("start_long", String.valueOf(sourceLong));
                params.put("dest_lat", String.valueOf(destLat));
                params.put("dest_long", String.valueOf(destLong));
                params.put("start_address", srcAddress);
                params.put("dest_address", destAddress);
                return params;
            }
        };

        NetworkSingleton.getInstance(this).addToRequestQueue(request);
    }
}
