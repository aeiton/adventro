package com.aeiton.adventro.Activity;

import android.app.ProgressDialog;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Bitmap;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.util.Patterns;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.LinearLayout;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.aeiton.adventro.R;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import de.hdodenhof.circleimageview.CircleImageView;

public class RegistrationActivity extends AppCompatActivity {


    CheckBox accept;
    Animation shake;
    ProgressDialog pd;

    String url;

    LinearLayout tandc;

    Integer status;
    String status1;




    CircleImageView propic;
    TextView title;
    Button register_btn;
    Spinner spnr_profession, spnr_city, spnr_lawyer_loc;
    ImageButton img_upload;

    LinearLayout lawyerPart;

    HashMap<String, String> enteredData = new HashMap<String, String>();

    Bitmap bitmap;

    EditText name, email, password, lawyerId, officeAddress, exp, phone, lawyer_id_a, lawyer_id_b;

    private int PICK_IMAGE_REQUEST = 1;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registration);

        Toolbar toolbar =(Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);
        getSupportActionBar().setTitle("Registration");

        name = (EditText) findViewById(R.id.etxt_name);
        email = (EditText) findViewById(R.id.etxt_email);

        accept = (CheckBox) findViewById(R.id.accept);
        phone = (EditText) findViewById(R.id.etxt_phone);

        shake = AnimationUtils.loadAnimation(this, R.anim.shakeanim);


        final SharedPreferences.Editor profile = getSharedPreferences("profile", MODE_PRIVATE).edit();
        profile.clear();
        profile.commit();





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
                } else if (phone.getText().length() != 10) {
                    phone.setError("Invalid phone number", null);
                    phone.requestFocus();
                    phone.startAnimation(shake);
                    return;
                } else if (password.getText().length() < 6) {

                    password.setError("Minimum 6 characters", null);
                    password.requestFocus();
                    register_btn.startAnimation(shake);
                    return;

                } else if (spnr_city.getSelectedItemPosition() == 0) {
                    TextView errorText = (TextView) spnr_city.getSelectedView();
                    errorText.setError("Select a City");
                    errorText.requestFocus();
                    register_btn.startAnimation(shake);
                    return;
                } else if (spnr_profession.getSelectedItemPosition() == 0){

                    TextView errorText = (TextView) spnr_profession.getSelectedView();
                    errorText.setError("Choose Profession");
                    register_btn.startAnimation(shake);
                    return;
                } else if (spnr_profession.getSelectedItemPosition() == 2){


                } else if (!accept.isChecked()) {

                    Toast.makeText(getApplicationContext(), "Please accept the Terms and Conditions to continue", Toast.LENGTH_SHORT).show();
                    accept.startAnimation(shake);
                    return;

                }


                {

//
//                    pd = new ProgressDialog(RegistrationActivity.this);
//                    pd.setMessage("Creating Account..");
//                    pd.show();

                    //adding data to the hashmap cz it'll be easy to get a json data out of it


                    enteredData.put("Name", name.getText().toString().trim());
                    enteredData.put("EmailId", email.getText().toString().trim());
                    enteredData.put("Password", password.getText().toString());
                    enteredData.put("Mobile", "" + phone.getText().toString());

//                    register();

                    //save everything in a singleton and then we can upload everything in a go after getting location in the next ACTIVITY
                }


            }
        });


    }


    public boolean checkname(String id) {
        Pattern p = Pattern.compile("[a-zA-Z\\s]*");
        Matcher m = p.matcher(id);
        return m.matches();
    }

}
