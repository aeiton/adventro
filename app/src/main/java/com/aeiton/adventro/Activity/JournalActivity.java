package com.aeiton.adventro.Activity;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.aeiton.adventro.R;

public class JournalActivity extends AppCompatActivity implements View.OnClickListener {

    private EditText journalTitle;
    private TextView locationText;
    private LinearLayout locationRootView;
    private ImageButton addImageButton;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_journal);

    }

    @Override
    public void onClick(View v) {

    }
}
