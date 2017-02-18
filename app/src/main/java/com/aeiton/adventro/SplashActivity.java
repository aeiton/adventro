package com.aeiton.adventro;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import com.digits.sdk.android.Digits;
import com.twitter.sdk.android.core.TwitterAuthConfig;
import com.twitter.sdk.android.core.TwitterCore;
import io.fabric.sdk.android.Fabric;

public class SplashActivity extends AppCompatActivity {

    // Note: Your consumer key and secret should be obfuscated in your source code before shipping.
    private static final String TWITTER_KEY = "qLqbl3IixfIPrX6CjFKwzkwDk";
    private static final String TWITTER_SECRET = "NzZC5FkGUBzvcvG4CA8nvlh1KmX7x2wJx3ebY7VECiJyd5PffQ";


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        TwitterAuthConfig authConfig = new TwitterAuthConfig(TWITTER_KEY, TWITTER_SECRET);
        Fabric.with(this, new TwitterCore(authConfig), new Digits.Builder().build());
        setContentView(R.layout.activity_splash);


    }
}
