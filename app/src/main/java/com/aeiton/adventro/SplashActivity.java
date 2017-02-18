package com.aeiton.adventro;

import android.content.Context;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.aeiton.adventro.Activity.RegistrationActivity;
import com.digits.sdk.android.AuthCallback;
import com.digits.sdk.android.AuthConfig;
import com.digits.sdk.android.Digits;
import com.digits.sdk.android.DigitsException;
import com.digits.sdk.android.DigitsSession;
import com.twitter.sdk.android.core.TwitterAuthConfig;
import com.twitter.sdk.android.core.TwitterCore;
import com.twitter.sdk.android.core.identity.TwitterAuthClient;

import io.fabric.sdk.android.Fabric;

public class SplashActivity extends AppCompatActivity {

    private TwitterAuthClient client;
    Button signIn;
    TextView mobile;
    Context context;

    // Note: Your consumer key and secret should be obfuscated in your source code before shipping.
    private static final String TWITTER_KEY = "qLqbl3IixfIPrX6CjFKwzkwDk";
    private static final String TWITTER_SECRET = "NzZC5FkGUBzvcvG4CA8nvlh1KmX7x2wJx3ebY7VECiJyd5PffQ";


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        TwitterAuthConfig authConfig = new TwitterAuthConfig(TWITTER_KEY, TWITTER_SECRET);
        Fabric.with(this, new TwitterCore(authConfig), new Digits.Builder().build());
        setContentView(R.layout.activity_splash);

        //initializing twitter digits
        Fabric.with(this, new TwitterCore(authConfig), new Digits.Builder().withTheme(R.style.AppTheme).build());
        client = new TwitterAuthClient();

        context = SplashActivity.this;

        signIn = (Button) findViewById(R.id.btn_sign_in);

//
//        signIn.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                startActivity(new Intent(SplashActivity.this,StudentHome.class));
//            }
//        });


        //calling twitter digits to verify mobile number
        signIn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {


//                startActivity(new Intent(SplashActivity.this,RegistrationActivity.class).putExtra("phone","9876543210"));
                AuthConfig.Builder builder = new AuthConfig.Builder();

                builder.withAuthCallBack(new AuthCallback() {
                    @Override
                    public void success(DigitsSession session, String phoneNumber) {
                        Intent i = new Intent(SplashActivity.this,RegistrationActivity.class);
                        i.putExtra("phone",phoneNumber);
                        Toast.makeText(SplashActivity.this,phoneNumber,Toast.LENGTH_SHORT).show();
                        startActivity(i);
                        // Do something
                    }

                    @Override
                    public void failure(DigitsException error) {
                        // Do something
                        error.printStackTrace();
                        Toast.makeText(SplashActivity.this,"Error",Toast.LENGTH_SHORT).show();
                    }
                });

                AuthConfig authConfig = builder.build();

                Digits.authenticate(authConfig);
            }
        });


    }
}
