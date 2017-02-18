package com.aeiton.adventro;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.aeiton.adventro.Activity.HomeActivity;
import com.aeiton.adventro.Activity.RegistrationActivity;
import com.android.volley.AuthFailureError;
import com.android.volley.DefaultRetryPolicy;
import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.digits.sdk.android.AuthCallback;
import com.digits.sdk.android.AuthConfig;
import com.digits.sdk.android.Digits;
import com.digits.sdk.android.DigitsException;
import com.digits.sdk.android.DigitsSession;
import com.twitter.sdk.android.core.TwitterAuthConfig;
import com.twitter.sdk.android.core.TwitterCore;
import com.twitter.sdk.android.core.identity.TwitterAuthClient;

import java.util.HashMap;
import java.util.Map;

import io.fabric.sdk.android.Fabric;

public class SplashActivity extends AppCompatActivity {

    // Note: Your consumer key and secret should be obfuscated in your source code before shipping.
    private static final String TWITTER_KEY = "qLqbl3IixfIPrX6CjFKwzkwDk";
    private static final String TWITTER_SECRET = "NzZC5FkGUBzvcvG4CA8nvlh1KmX7x2wJx3ebY7VECiJyd5PffQ";
    /* Tag of Activity */
    private final String TAG = "SplashActivity";
    private TwitterAuthClient client;
    private Button signIn;
    private TextView mobile;
    private Context context;
    private EditText phoneNumberEdit;
    private ImageView appIcon;
    private String phoneNumber;
    private SharedPreferences sharedPreferences;
    private SharedPreferences.Editor editor;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        TwitterAuthConfig authConfig = new TwitterAuthConfig(TWITTER_KEY, TWITTER_SECRET);
        Fabric.with(this, new TwitterCore(authConfig), new Digits.Builder().build());
        setContentView(R.layout.activity_splash);
        signIn = (Button) findViewById(R.id.btn_sign_in);
        phoneNumberEdit = (EditText) findViewById(R.id.edit_phonenumber);
        appIcon = (ImageView) findViewById(R.id.app_image);


        /*  Check for Previous login here
            Check SharedPrefs
            If Previously logged in, goto HomePage
            Else Goto registration
          */
        if (checkForPreviousLogin()) {
            Toast.makeText(this, "Test: Already Logged in", Toast.LENGTH_SHORT).show();
            SplashActivity.this.finish();
            startActivity(new Intent(SplashActivity.this, HomeActivity.class));
        } else {

            //initializing twitter digits
            Fabric.with(this, new TwitterCore(authConfig), new Digits.Builder().withTheme(R.style.AppTheme).build());
            client = new TwitterAuthClient();

            context = SplashActivity.this;

            //calling twitter digits to verify mobile number
            signIn.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    // get the phone number from text field
                    if (phoneNumberEdit.getText().toString().isEmpty() || phoneNumberEdit.getText() == null) {
                        phoneNumberEdit.setError("Invalid Phone number");
                        phoneNumberEdit.requestFocus();
                    } else {
                        phoneNumber = phoneNumberEdit.getText().toString();


                    /* Authentication here */
                        AuthConfig.Builder builder = new AuthConfig.Builder()
                                .withPhoneNumber(phoneNumber);
                        builder.withAuthCallBack(new AuthCallback() {
                            @Override
                            public void success(DigitsSession session, String phoneNumber) {

                                // set the phone number
                                UserDetails.getInstance().setPhone(phoneNumber);
                                Log.d(TAG, "Phone: " + phoneNumber);
                                Intent i = new Intent(SplashActivity.this, RegistrationActivity.class);
                                i.putExtra("phone", phoneNumber);

                                Toast.makeText(SplashActivity.this, phoneNumber, Toast.LENGTH_SHORT).show();
                                startActivity(i);
                            }

                            @Override
                            public void failure(DigitsException error) {
                                // Do something
                                error.printStackTrace();
                                Toast.makeText(SplashActivity.this, "Error", Toast.LENGTH_SHORT).show();
                            }
                        });

                        AuthConfig authConfig = builder.build();
                        Digits.authenticate(authConfig);
                    }
                }
            });
        }
    }

    private boolean checkForPreviousLogin() {
        sharedPreferences = getSharedPreferences(Constants.SHARED_PREF_LOGIN_KEY, Context.MODE_PRIVATE);
        if (sharedPreferences.contains(Constants.LOGIN_KEY)) {
            // user has previously logged in
            // get the phone number and send it to server for validation
            sendPhoneNumber(sharedPreferences.getString(Constants.LOGIN_KEY, ""));
            return true;
        }
        return false;
    }

    private void sendPhoneNumber(final String phoneNumber) {
        StringRequest stringRequest = new StringRequest(Request.Method.POST, Constants.PHONENUM_VERIFY_URL, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                Log.d(TAG, "Response: " + response);
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Toast.makeText(context, "Some Error Occurred!", Toast.LENGTH_SHORT).show();
            }
        }) {
            @Override
            protected Map<String, String> getParams() throws AuthFailureError {
                Map<String, String> params = new HashMap<>();
                params.put("mobile_no", phoneNumber);
                return params;
            }
        };

        stringRequest.setRetryPolicy(new DefaultRetryPolicy(DefaultRetryPolicy.DEFAULT_TIMEOUT_MS, DefaultRetryPolicy.DEFAULT_MAX_RETRIES, DefaultRetryPolicy.DEFAULT_BACKOFF_MULT));
        Volley.newRequestQueue(this).add(stringRequest);
    }

}
