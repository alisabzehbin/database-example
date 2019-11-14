package com.emxaple.app.drawermenuexample;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import java.util.prefs.Preferences;

public class ProfileActivity extends AppCompatActivity {


    EditText edtFirstName, edtLastName, edtPhoneNumber,edtEmail;
    Button btnReview, btnBack;

    SharedPreferences preferences;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_prfile);
        setTitle("Profile");

        preferences = getSharedPreferences("Profile", MODE_PRIVATE);

        edtFirstName = findViewById(R.id.edt_first_name);
        edtLastName = findViewById(R.id.edt_last_name);
        edtPhoneNumber = findViewById(R.id.edt_phone);
        edtEmail = findViewById(R.id.edt_email);


        String str_first_name  = preferences.getString("FIRST_NAME", null );
        if(str_first_name!=null)
        {
            edtFirstName.setText(str_first_name);
        }

        String str_last_name  = preferences.getString("LAST_NAME", null );
        if(str_last_name!=null)
        {
            edtLastName.setText(str_last_name);
        }

        String str_phone  = preferences.getString("PHONE", null );
        if(str_phone!=null)
        {
            edtPhoneNumber.setText(str_phone);
        }

        String str_email  = preferences.getString("EMAIL", null );
        if(str_email!=null)
        {
            edtEmail.setText(str_email);
        }


        btnBack = findViewById(R.id.btn_back);
        btnBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });

        btnReview = findViewById(R.id.btn_review);
        btnReview.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                String firstName = edtFirstName.getText().toString().trim();
                String lastName = edtLastName.getText().toString().trim();
                String phone = edtPhoneNumber.getText().toString().trim();
                String email = edtEmail.getText().toString().trim();

                if (firstName.isEmpty()) {
                    edtFirstName.setError("Please Enter First Name");
                    return;
                }
                if (lastName.isEmpty()) {
                    edtLastName.setError("Please Enter last Name");
                    return;
                }
                if (phone.isEmpty()) {
                    edtPhoneNumber.setError("Please Enter Phone Number");
                    return;
                }
                if (email.isEmpty()) {
                    edtEmail.setError("Please Enter Email Address");
                    return;
                }

                Intent intent = new Intent(ProfileActivity.this, ReviewActivity.class);
                intent.putExtra("FIRST_NAME", firstName);
                intent.putExtra("LAST_NAME", lastName);
                intent.putExtra("PHONE", phone);
                intent.putExtra("EMAIL", email);
                startActivity(intent);
            }
        });



    }
}
