package com.aeiton.adventro.Activity;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.aeiton.adventro.R;

public class CreateTimeLine extends AppCompatActivity {

    Button create_timeline;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_create_time_line);


        create_timeline = (Button) findViewById(R.id.create_timeline);
        create_timeline.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(CreateTimeLine.this,EditTimeLine.class).putExtra("access",1));
            }
        });
    }


}
