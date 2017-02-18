package com.aeiton.adventro.Activity;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.aeiton.adventro.R;
import com.google.android.gms.common.GooglePlayServicesNotAvailableException;
import com.google.android.gms.common.GooglePlayServicesRepairableException;
import com.google.android.gms.location.places.ui.PlacePicker;

public class ChooseLocationActivity extends AppCompatActivity {

    int PLACE_PICKER_REQUEST = 1;
    com.google.android.gms.location.places.Place place;

    Button chooseLocation;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_choose_location);

        chooseLocation = (Button) findViewById(R.id.choose_location);
        chooseLocation.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                PlacePicker.IntentBuilder builder = new PlacePicker.IntentBuilder();

                try {
                    startActivityForResult(builder.build(ChooseLocationActivity.this), PLACE_PICKER_REQUEST);
                } catch (GooglePlayServicesNotAvailableException | GooglePlayServicesRepairableException e) {
                    e.printStackTrace();
                }
            }
        });
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (requestCode == PLACE_PICKER_REQUEST) {
            if (resultCode == RESULT_OK) {
                com.google.android.gms.location.places.Place place = PlacePicker.getPlace(data, this);
                this.place = place;
                String toastMsg = String.format("Place: %s", place.getName());
                Toast.makeText(this, toastMsg, Toast.LENGTH_LONG).show();
            }
        }
    }
}
