package com.emxaple.app.drawermenuexample;

import android.content.Intent;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

public class CameraActivity extends AppCompatActivity {

    int CAMERA_PIC_REQUEST = 512;
    Button btn_open_camera, btn_back;
    ImageView img_picture;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_camera);
        setTitle("Camera Picture");

        btn_open_camera = findViewById(R.id.btn_open_camera);
        btn_back = findViewById(R.id.btn_back);
        img_picture = findViewById(R.id.img_picture);

        btn_back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                CameraActivity.this.finish();
            }
        });
        btn_open_camera.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(android.provider.MediaStore.ACTION_IMAGE_CAPTURE);
                startActivityForResult(intent, CAMERA_PIC_REQUEST);
            }
        });

    }


    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (requestCode == CAMERA_PIC_REQUEST) {
            if(data!=null && data.getExtras()!=null) {
                Bitmap image = (Bitmap) data.getExtras().get("data");
                img_picture.setImageBitmap(image);
            }
        }
    }
}
