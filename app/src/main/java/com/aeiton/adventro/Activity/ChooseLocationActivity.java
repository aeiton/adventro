package com.aeiton.adventro.Activity;

import android.app.Activity;
import android.content.Intent;
import android.location.Location;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.aeiton.adventro.R;
import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.location.LocationServices;

public class ChooseLocationActivity extends AppCompatActivity implements GoogleApiClient.ConnectionCallbacks, GoogleApiClient.OnConnectionFailedListener {

    // com.google.android.gms.location.places.Place place;
    static int GET_LOCATION_REQUEST = 2;
    Button chooseLocation;
    private GoogleApiClient mGoogleApiClient;
    private Location mLocation;

    @Override
    protected void onStart() {
        super.onStart();

        mGoogleApiClient.connect();
    }

    @Override
    protected void onStop() {
        if (mGoogleApiClient != null) {
            mGoogleApiClient.disconnect();
        }

        super.onStop();
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_choose_location);

        // set up google play api
        mGoogleApiClient = new GoogleApiClient.Builder(this)
                .addConnectionCallbacks(this)
                .addOnConnectionFailedListener(this)
                .addApi(LocationServices.API)
                .build();

        chooseLocation = (Button) findViewById(R.id.choose_location);
        chooseLocation.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                /*PlacePicker.IntentBuilder builder = new PlacePicker.IntentBuilder();

                try {
                    startActivityForResult(builder.build(ChooseLocationActivity.this), PLACE_PICKER_REQUEST);
                } catch (GooglePlayServicesNotAvailableException | GooglePlayServicesRepairableException e) {
                    e.printStackTrace();
                }*/
            }
        });
    }


    @Override
    public void onConnected(@Nullable Bundle bundle) {
        try {
            mLocation = LocationServices.FusedLocationApi.getLastLocation(mGoogleApiClient);
            if (mLocation == null) {
                Toast.makeText(this, "Unable to Access the Location", Toast.LENGTH_SHORT).show();
            } else {
                Intent data = new Intent();
                data.putExtra("lat", mLocation.getLatitude());
                data.putExtra("long", mLocation.getLongitude());

                Log.d("LOCATION", "LAT: " + mLocation.getLatitude() + " LONG: " + mLocation.getLongitude());
                setResult(Activity.RESULT_OK, data);
                ChooseLocationActivity.this.finish();
            }
        } catch (SecurityException e) {
            e.printStackTrace();
            Toast.makeText(this, "Permissions not granted", Toast.LENGTH_SHORT).show();
        }
    }

    @Override
    public void onConnectionSuspended(int i) {

    }

    @Override
    public void onConnectionFailed(@NonNull ConnectionResult connectionResult) {

    }

}
