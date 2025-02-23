package com.example.demoapp;

import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;

import com.google.android.material.navigation.NavigationView;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser ;

public class MainActivity extends AppCompatActivity {
    private FirebaseAuth auth;
    private Button logoutButton;
    private TextView userDetailsTextView;
    private FirebaseUser  user;
    private DrawerLayout drawerLayout;
    private ActionBarDrawerToggle toggle;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // Initialize Firebase Auth
        auth = FirebaseAuth.getInstance();
        logoutButton = findViewById(R.id.logout);
        userDetailsTextView = findViewById(R.id.user_details);
        drawerLayout = findViewById(R.id.drawer_layout);
        NavigationView navigationView = findViewById(R.id.nav_view);

        // Set up the ActionBarDrawerToggle
        toggle = new ActionBarDrawerToggle(this, drawerLayout, R.string.open_nav, R.string.close_nav);
        drawerLayout.addDrawerListener(toggle);
        toggle.syncState();

        // Check if the user is logged in
        user = auth.getCurrentUser ();
        if (user == null) {
            // If user is not logged in, redirect to LoginActivity
            Intent intent = new Intent(getApplicationContext(), LoginActivity.class);
            startActivity(intent);
            finish();
        } else {
            // Display the user's email
            userDetailsTextView.setText(user.getEmail());
        }

        // Set up the logout button click listener
        logoutButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Sign out the user and redirect to LoginActivity
                auth.signOut();
                Intent intent = new Intent(getApplicationContext(), LoginActivity.class);
                startActivity(intent);
                finish();
            }
        });

        // Set up the navigation item selection listener
        navigationView.setNavigationItemSelectedListener(new NavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                // Handle navigation item selections using if-else
                if (item.getItemId() == R.id.nav_home) {
                    startActivity(new Intent(MainActivity.this, HomeActivity.class));
                } else if (item.getItemId() == R.id.nav_user_profile) {
                    startActivity(new Intent(MainActivity.this, UserProfileActivity.class));
                } else if (item.getItemId() == R.id.nav_grocery_list) {
                    startActivity(new Intent(MainActivity.this, GroceryListActivity.class));
                } else if (item.getItemId() == R.id.nav_sms_delegation) {
                    startActivity(new Intent(MainActivity.this, SMSDelegationActivity.class));
                } else if (item.getItemId() == R.id.nav_logout) {
                    auth.signOut();
                    startActivity(new Intent(MainActivity.this, LoginActivity.class));
                    finish();
                }

                drawerLayout.closeDrawer(GravityCompat.START); // Close the drawer after selection
                return true;
            }
        });
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        if (toggle.onOptionsItemSelected(item)) {
            return true;
        }
        return super.onOptionsItemSelected(item);
    }
}