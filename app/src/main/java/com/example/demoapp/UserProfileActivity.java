package com.example.demoapp;

import android.os.Bundle;
import android.widget.TextView;
import androidx.appcompat.app.AppCompatActivity;
import com.example.demoapp.R;

public class UserProfileActivity extends AppCompatActivity {

    private TextView userProfileTextView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user_profile);

        // Initialize the TextView
        userProfileTextView = findViewById(R.id.user_profile_text);

        // Set a simple paragraph of text for testing
        String userProfileText = "Welcome to your profile! Here you can view and edit your personal information, manage your recipes, and customize your meal preferences. Enjoy exploring the features of MealMate!";
        userProfileTextView.setText(userProfileText);
    }
}