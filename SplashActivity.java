package com.example.signlatorapp;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.Button;
import android.widget.ImageView;
import androidx.appcompat.app.AppCompatActivity;

import com.example.signlator.MainActivity;
import com.example.signlator.R;

@SuppressLint("CustomSplashScreen")
public class SplashActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // Setting the content view to the splash activity layout
        setContentView(R.layout.splash_activity);

        // Find the ImageView in the layout and apply the animation
        ImageView splashImage = findViewById(R.id.splash_image);
        Animation animation = AnimationUtils.loadAnimation(this, R.anim.splash_animation);
        splashImage.startAnimation(animation);

        // Find the Start Button in the layout
        Button startButton = findViewById(R.id.start_button);

        // Set a click listener on the Start Button to transition to MainActivity
        startButton.setOnClickListener(v -> {
            Intent intent = new Intent(SplashActivity.this, MainActivity.class);
            startActivity(intent);
            finish(); // Close the splash activity so it doesn't reappear when back is pressed
        });

        // Optional: Automatically move to MainActivity after a delay if no button click
        int SPLASH_SCREEN_TIMEOUT = 5000; // 5 seconds delay
        new Handler().postDelayed(() -> {
            if (!isFinishing()) {
                Intent intent = new Intent(SplashActivity.this, MainActivity.class);
                startActivity(intent);
                finish();  // Close the splash screen
            }
        }, SPLASH_SCREEN_TIMEOUT);
    }
}
