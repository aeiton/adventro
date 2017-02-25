package com.aeiton.adventro.Activity;

import android.app.ProgressDialog;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Base64;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.aeiton.adventro.R;

import java.io.ByteArrayOutputStream;
import java.util.HashMap;

public class SpotsActivity extends AppCompatActivity implements View.OnClickListener {

    private static final int PICK_IMAGE_REQUEST = 44;
    private static final int LOCATION_REQUEST_CODE = 40;
    private final String TAG = "SpotsActivity";
    private EditText journalTitle;
    private TextView locationText;
    private LinearLayout locationRootView;
    private ImageButton addImageButton;
    private ImageView imageView;
    private Button continueButton;
    private Bitmap bitmap;
    private ProgressDialog progressDialog;
    private HashMap<String, String> params = new HashMap<>();
    private String imageText;
    private String description;
    private EditText descriptionText;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_spots);

        journalTitle = (EditText) findViewById(R.id.edit_journal_title);
        locationText = (TextView) findViewById(R.id.location_text);
        locationRootView = (LinearLayout) findViewById(R.id.choose_location_view);
        addImageButton = (ImageButton) findViewById(R.id.add_cover_picture);
        imageView = (ImageView) findViewById(R.id.image);
        continueButton = (Button) findViewById(R.id.continue_button);
        descriptionText = (EditText) findViewById(R.id.edit_spot_desc);


        addImageButton.setOnClickListener(this);
        locationRootView.setOnClickListener(this);
        continueButton.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            // on add cover picture
            case R.id.add_cover_picture:
                Log.d("ADD COVER", "Cover pic");
                Intent i = new Intent(Intent.ACTION_PICK);
                i.setType("image/*");
                startActivityForResult(i, PICK_IMAGE_REQUEST);
                break;
            // on click choose location
            case R.id.choose_location_view:
                startActivityForResult(new Intent(SpotsActivity.this, PlacePickerActivity.class), LOCATION_REQUEST_CODE);
                break;
            // on click continue button
            case R.id.continue_button:
                if (validateData()) {
                    sendData();
                }
                break;
        }
    }

    private void sendData() {
        // send the data to backend
    }

    private boolean validateData() {
        /* Validate the data and then proceed */
        if (journalTitle.getText().toString().isEmpty() || journalTitle.getText() == null) {
            journalTitle.setError("Invalid Title");
            journalTitle.requestFocus();
            return false;
        } else if (descriptionText.getText().toString().isEmpty()) {
            descriptionText.setError("Some Error occurred!");
            descriptionText.requestFocus();
            return false;
        }
        return true;
    }

    /**
     * Class to Decode Bitmap image to a base64 String
     */
    class DecodeImageTask extends AsyncTask<Bitmap, Void, String> {
        @Override
        protected String doInBackground(Bitmap... params) {
            ByteArrayOutputStream baos = new ByteArrayOutputStream();
            params[0].compress(Bitmap.CompressFormat.WEBP, 10, baos);
            final String encodedImage = Base64.encodeToString(baos.toByteArray(), Base64.DEFAULT);

            //to decode image
            byte[] decodeString = Base64.decode(encodedImage, Base64.DEFAULT);
            //encodeImage is the string to be decoded
            //encodeImage is in Base64 format
            params[0] = BitmapFactory.decodeByteArray(decodeString, 0, decodeString.length);
            return encodedImage;
        }

        @Override
        protected void onProgressUpdate(Void... values) {
            super.onProgressUpdate(values);
        }

        @Override
        protected void onPostExecute(String encodedImage) {
            super.onPostExecute(encodedImage);
            imageText = encodedImage;
        }
    }
}
