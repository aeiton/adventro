package com.aeiton.adventro.Activity;

import android.app.ProgressDialog;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.AsyncTask;
import android.os.Bundle;
import android.provider.MediaStore;
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
import android.widget.Toast;

import com.aeiton.adventro.Constants;
import com.aeiton.adventro.NetworkSingleton;
import com.aeiton.adventro.R;
import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

public class JournalActivity extends AppCompatActivity implements View.OnClickListener {

    private static final int PICK_IMAGE_REQUEST = 44;
    private static final int LOCATION_REQUEST_CODE = 40;
    private final String TAG = "JournalActivity";
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

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_journal);

        journalTitle = (EditText) findViewById(R.id.edit_journal_title);
        locationText = (TextView) findViewById(R.id.location_text);
        locationRootView = (LinearLayout) findViewById(R.id.choose_location_view);
        addImageButton = (ImageButton) findViewById(R.id.add_cover_picture);
        imageView = (ImageView) findViewById(R.id.image);
        continueButton = (Button) findViewById(R.id.continue_button);
        addImageButton.setOnClickListener(this);
        locationRootView.setOnClickListener(this);
        continueButton.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.add_cover_picture:
                Log.d("ADD COVER", "Cover pic");
                Intent i = new Intent(Intent.ACTION_PICK);
                i.setType("image/*");
                startActivityForResult(i, PICK_IMAGE_REQUEST);
                break;
            case R.id.choose_location_view:
                startActivityForResult(new Intent(JournalActivity.this, PlacePickerActivity.class), LOCATION_REQUEST_CODE);
                break;
            case R.id.continue_button:
                if (validateData()) {
                    sendData();
                }
                break;
        }
    }

    private boolean validateData() {
        if (journalTitle.getText().toString().isEmpty() || journalTitle.getText() == null) {
            journalTitle.setError("Invalid Title");
            journalTitle.requestFocus();
            return false;
        }
        return true;
    }

    private void sendData() {
        params.put("user_id", 24 + "");
        params.put("title", journalTitle.getText().toString());
        params.put("img", imageText);

        Log.d("user_id", 24 + "");
        Log.d("title", journalTitle.getText().toString());
        Log.d("img", imageText);
        StringRequest stringRequest = new StringRequest(Request.Method.POST, Constants.JOURNAL_URL, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                Log.d(TAG, response);
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Toast.makeText(JournalActivity.this, "Some Error occured!", Toast.LENGTH_SHORT).show();
            }
        }) {
            @Override
            protected Map<String, String> getParams() throws AuthFailureError {
                return params;
            }
        };

        NetworkSingleton.getInstance(this).addToRequestQueue(stringRequest);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == LOCATION_REQUEST_CODE) {
            if (resultCode == RESULT_OK && data != null) {
                double lat = data.getDoubleExtra("latitude", 12.9666666);
                double lon = data.getDoubleExtra("longitude", 12.6964644);
                String address = data.getStringExtra("address");

                Log.d("lat", String.valueOf(lat));
                Log.d("long", String.valueOf(lon));
                Log.d("address", address);

                locationText.setText(address);

                params.put("lat", String.valueOf(lat));
                params.put("long", String.valueOf(lon));
                params.put("address", address);
            }
        }
        /* PickImage request goes here */
        if (requestCode == PICK_IMAGE_REQUEST) {
            if (resultCode == RESULT_OK && data != null && data.getData() != null) {
                Uri filePath = data.getData();
                try {
                    //Getting the Bitmap from Gallery
                    bitmap = MediaStore.Images.Media.getBitmap(getContentResolver(), filePath);

                    // Setting the Bitmap to ImageView
                    imageView.setImageBitmap(bitmap);

                    new DecodeImageTask().execute(bitmap);

                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }


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
