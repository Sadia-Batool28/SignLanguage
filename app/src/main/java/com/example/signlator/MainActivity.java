package com.example.signlator;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // Find views
        ImageView aslImage = findViewById(R.id.asl_image);
        ImageView homeIcon = findViewById(R.id.home_icon);
        ImageView handIcon = findViewById(R.id.hand_icon);
        ImageView cameraIcon = findViewById(R.id.camera_icon); // Camera icon to go to MainActivity3
        ImageView alphabetIcon = findViewById(R.id.bc_icon);

        // Click listener for the "real time camera" image
        aslImage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, MainActivity3.class);
                startActivity(intent);
            }
        });

        // Click listener for the "ASL Alphabets" image
        handIcon.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, MainActivity2.class);
                startActivity(intent);
            }
        });

        homeIcon.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, MainActivity.class);
                startActivity(intent);
            }
        });

        // Click listener for the camera icon (real-time camera)
        cameraIcon.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Navigate to MainActivity3 for real-time camera
                Intent intent = new Intent(MainActivity.this, MainActivity3.class); // This redirects to MainActivity3
                startActivity(intent);
            }
        });

        // Click listener for the "ASL Alphabets" image
        alphabetIcon.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Navigate to MainActivity3 for real-time camera
                Intent intent = new Intent(MainActivity.this, MainActivity2.class); // This redirects to MainActivity3
                startActivity(intent);
            }
        });
    }
}
