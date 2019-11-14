package com.emxaple.app.drawermenuexample;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

public class SendSmsActivity extends AppCompatActivity {


    private Button btnSendSms, btnBack;
    private EditText edt_phone, edt_content;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_sms);
        setTitle("Send SMS");

        btnBack = findViewById(R.id.btn_back);
        btnSendSms = findViewById(R.id.btn_send_sms);

        edt_content = findViewById(R.id.edt_content);
        edt_phone = findViewById(R.id.edt_phone);

        btnBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                SendSmsActivity.this.finish();
            }
        });

        btnSendSms.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String phone = edt_phone.getText().toString().trim();
                if (phone.isEmpty()) {
                    Toast.makeText(getApplicationContext(), "Please enter phone number", Toast.LENGTH_SHORT).show();
                    return;
                }

                String content = edt_content.getText().toString().trim();
                if (content.isEmpty()) {
                    Toast.makeText(getApplicationContext(), "Please enter content", Toast.LENGTH_SHORT).show();
                    return;
                }

                Intent intent = new Intent(Intent.ACTION_SENDTO, Uri.parse("smsto:" + phone));
                intent.putExtra("sms_body", content);
                startActivity(intent);

            }
        });


    }
}
