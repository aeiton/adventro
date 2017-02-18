package com.aeiton.adventro.Activity;

import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.AsyncTask;
import android.os.Bundle;
import android.provider.MediaStore;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Base64;
import android.util.Log;
import android.util.Patterns;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.aeiton.adventro.Constants;
import com.aeiton.adventro.R;
import com.aeiton.adventro.UserDetails;
import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import de.hdodenhof.circleimageview.CircleImageView;

public class RegistrationActivity extends AppCompatActivity {


    CheckBox accept;
    Animation shake;
    ProgressDialog pd;


    CircleImageView propic;
    TextView title;
    Button register_btn;
    ImageButton img_upload;
    ArrayAdapter<String> gender_adapter;
    Spinner gender;


    HashMap<String, String> enteredData = new HashMap<String, String>();

    Bitmap bitmap;

    EditText name, email, phone;

    private int PICK_IMAGE_REQUEST = 1;
    private int GET_LOCATION_REQUEST = 2;
    private boolean isDecoded = false;

    private StringRequest stringRequest;
    private Context mContext;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registration);

        /* Set the current context */
        mContext = getApplicationContext();

        Intent intent = getIntent();

        if (intent != null) {
            Log.i("RegistrationActivity", intent.getStringExtra("phone"));
        }

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(false);
        getSupportActionBar().setDisplayShowHomeEnabled(false);
        getSupportActionBar().setTitle("Registration");

        // Log.d("PHONE", phoneNum);
        //   phone.setText(phoneNum);

        name = (EditText) findViewById(R.id.etxt_name);
        email = (EditText) findViewById(R.id.etxt_email);

        accept = (CheckBox) findViewById(R.id.accept);
        phone = (EditText) findViewById(R.id.etxt_phone);

        shake = AnimationUtils.loadAnimation(this, R.anim.shakeanim);

        // set the phone number
        phone.setText(intent.getStringExtra("phone"));


        final SharedPreferences.Editor profile = getSharedPreferences("profile", MODE_PRIVATE).edit();
        profile.clear();
        profile.commit();


        //initializing proffession spinner

        gender = (Spinner) findViewById(R.id.spnr_gender);
        final List<String> code = new ArrayList<String>();


        code.add("Choose Gender");
        code.add("Male");
        code.add("Female");
        code.add("Other");


        gender_adapter = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item, code);
        gender_adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        gender.setAdapter(gender_adapter);


        //Profile Picture
        propic = (CircleImageView) findViewById(R.id.profile_image);
        img_upload = (ImageButton) findViewById(R.id.btn_img_upload);
        img_upload.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(Intent.ACTION_PICK);
                i.setType("image/*");
                startActivityForResult(i, PICK_IMAGE_REQUEST);
            }
        });


        //Registration Button
        register_btn = (Button) findViewById(R.id.register);
        register_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {


                if (name.getText().toString().length() < 5 || !checkname(name.getText().toString())) {

                    name.setError("Full name required");
                    name.requestFocus();
                    register_btn.startAnimation(shake);
                    return;

                } else if (!Patterns.EMAIL_ADDRESS.matcher(email.getText()).matches()) {

                    email.setError("Invalid Email Address");
                    email.requestFocus();
                    register_btn.startAnimation(shake);
                    return;
                } else if (gender.getSelectedItemPosition() == 0) {

                    Toast.makeText(getApplicationContext(), "Please choose a gender", Toast.LENGTH_SHORT).show();
                    gender.startAnimation(shake);
                    return;

                } else if (!accept.isChecked()) {
                    Toast.makeText(getApplicationContext(), "Please accept the Terms and Conditions to continue", Toast.LENGTH_SHORT).show();
                    accept.startAnimation(shake);
                    return;
                } else if (bitmap == null) {
                    Toast.makeText(RegistrationActivity.this, "Please choose an image", Toast.LENGTH_SHORT).show();
                    img_upload.performClick();
                    return;
                }

                enteredData.put("name", name.getText().toString().trim());
                enteredData.put("email", email.getText().toString().trim());
                enteredData.put("mobile_no", "" + phone.getText().toString());

                //jonathan added begins

                // set the gender
                switch (gender.getSelectedItemPosition()) {
                    case 1:
                        enteredData.put("sex", "M");
                        break;
                    case 2:
                        enteredData.put("sex", "F");
                        break;
                    case 3:
                        enteredData.put("sex", "O");
                        break;
                }

                //code below this is not in this activity
                //TODO: get latitude and logitude from 'ChooseLocationActivity'
                enteredData.put("lat", "" + "12.554554554559");//TODO: latitude
                enteredData.put("long", "" + "12.995995995994");//TODO: longitude
                enteredData.put("address", "" + "Test address"); //TODO: address
                // address is retrived from the obtained location
                //jonathan added ends

                // send the entered data
                sendEnteredData();
            }
        });
    }


    private void sendEnteredData() {
        final ProgressDialog progressDialog = new ProgressDialog(this);
        progressDialog.setTitle("Loading...");
        progressDialog.setIndeterminate(true);
        progressDialog.show();
        stringRequest = new StringRequest(Request.Method.POST, Constants.REG_URL, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                Log.i("Registration", response);
                if (progressDialog.isShowing()) {
                    progressDialog.dismiss();
                }

                // parse the response
                try {
                    JSONObject jsonObject = new JSONObject(response);
                    String resp = jsonObject.getString("status");
                    String msg = jsonObject.getString("message");
                    if (resp.equals("3")) {
                        Toast.makeText(RegistrationActivity.this, msg, Toast.LENGTH_SHORT).show();

                        // we are supposed to get user_id if successful
                        String userId = jsonObject.getString("user_id");
                        UserDetails.getInstance().setUser_id(userId);

                    } else {
                        // Ignore other cases
                        Toast.makeText(RegistrationActivity.this, msg, Toast.LENGTH_SHORT).show();
                    }
                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Toast.makeText(RegistrationActivity.this, "Some Error Occured", Toast.LENGTH_SHORT).show();
                if (progressDialog.isShowing()) {
                    progressDialog.dismiss();
                }
            }
        }) {
            @Override
            protected Map<String, String> getParams() throws AuthFailureError {
                return enteredData;
            }
        };

        //startActivity(new Intent(RegistrationActivity.this, ChooseLocationActivity.class));
    }


    public boolean checkname(String id) {
        Pattern p = Pattern.compile("[a-zA-Z\\s]*");
        Matcher m = p.matcher(id);
        return m.matches();
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        /* PickImage request goes here */
        if (requestCode == PICK_IMAGE_REQUEST && resultCode == RESULT_OK && data != null && data.getData() != null) {
            Uri filePath = data.getData();
            try {
                //Getting the Bitmap from Gallery
                bitmap = MediaStore.Images.Media.getBitmap(getContentResolver(), filePath);

                SharedPreferences.Editor profile = getSharedPreferences("profile", MODE_PRIVATE).edit();
                profile.putString("propic", filePath.toString());
                profile.commit();

                // Setting the Bitmap to ImageView
                propic.setImageBitmap(bitmap);

                // decode the bitmap as soon as you get it
                new DecodeImageTask().execute(bitmap);

            } catch (IOException e) {
                e.printStackTrace();
            }
        }

        /* Get location activity goes here */
        if (requestCode == GET_LOCATION_REQUEST && resultCode == RESULT_OK && data != null && data.getData() != null) {
            // get the location and set it into the HashMap<>
            return;
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
        protected void onPostExecute(String encodedImage) {
            super.onPostExecute(encodedImage);
            enteredData.put("img", encodedImage); // upload image in the form of Base64 string

            // send the request after decode complete
            Volley.newRequestQueue(mContext).add(stringRequest);
        }
    }
}
