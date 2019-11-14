package com.emxaple.app.drawermenuexample;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

public class ReviewActivity extends AppCompatActivity {

    String firstName;
    String lastName;
    String phone;
    String email;

    Button btnBack, btnSave;
    TextView txtFirstName, txtLastName, txtPhoneNumber, txtEmail;

    SharedPreferences preferences;
    SharedPreferences.Editor  editor;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_review);

        preferences = getSharedPreferences("Profile", MODE_PRIVATE);
        editor = preferences.edit();

        txtFirstName = findViewById(R.id.txt_first_name);
        txtLastName = findViewById(R.id.txt_last_name);
        txtPhoneNumber = findViewById(R.id.txt_phone);
        txtEmail = findViewById(R.id.txt_email);

        if (getIntent().getExtras() != null) {
            Bundle extras = getIntent().getExtras();
            firstName = extras.getString("FIRST_NAME");
            lastName = extras.getString("LAST_NAME");
            phone = extras.getString("PHONE");
            email = extras.getString("EMAIL");

            txtFirstName.setText(firstName);
            txtLastName.setText(lastName);
            txtPhoneNumber.setText(phone);
            txtEmail.setText(email);

        }

        btnBack = findViewById(R.id.btn_back);
        btnSave = findViewById(R.id.btn_save);

        btnBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });

        btnSave.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (!firstName.isEmpty() && !lastName.isEmpty() && !phone.isEmpty() && !email.isEmpty()) {
                    editor.putString("FIRST_NAME", firstName);
                    editor.putString("LAST_NAME", lastName);
                    editor.putString("PHONE", phone);
                    editor.putString("EMAIL", email);

                    editor.apply();
                    Toast.makeText(getApplicationContext(), "Saved Successfully!", Toast.LENGTH_SHORT).show();
                }
            }
        });


    }
}
