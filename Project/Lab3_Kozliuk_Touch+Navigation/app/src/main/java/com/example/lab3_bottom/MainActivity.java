package com.example.lab3_bottom;



import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.MenuItem;
import android.widget.TextView;

import com.google.android.material.badge.BadgeDrawable;
import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.android.material.navigation.NavigationBarView;

public class MainActivity extends AppCompatActivity {

    BottomNavigationView bottomNavigationView;
    TextView textView;
    SingleTouch homeFragment = new SingleTouch();
    MultiTouch multiTouchTest = new MultiTouch();
    StudentForm studentForm = new StudentForm();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        setTitle("Kozliuk_KI44_Lab3");

        // Find the TextView in the fragment's layout

        bottomNavigationView  = findViewById(R.id.nav_view);
        getSupportFragmentManager().beginTransaction().replace(R.id.container,homeFragment).commit();

        bottomNavigationView.setOnItemSelectedListener(new NavigationBarView.OnItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(MenuItem item)
            {
                if(item.getItemId() == R.id.navigation_home)
                {
                    getSupportFragmentManager().beginTransaction().replace(R.id.container,homeFragment).commit();
                    return true;
                }
                if(item.getItemId() == R.id.navigation_dashboard)
                {
                    getSupportFragmentManager().beginTransaction().replace(R.id.container,multiTouchTest).commit();
                    return true;
                }

                if(item.getItemId() == R.id.navigation_notifications)
                {
                    getSupportFragmentManager().beginTransaction().replace(R.id.container,studentForm).commit();
                    return true;
                }
                return false;
            }
        });

    }
}