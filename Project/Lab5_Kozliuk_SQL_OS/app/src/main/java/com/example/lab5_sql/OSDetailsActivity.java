package com.example.lab5_sql;

import android.content.Intent;
import android.os.Bundle;
import android.widget.TextView;
import androidx.appcompat.app.AppCompatActivity;

public class OSDetailsActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.info);

        // Get references to the TextViews in the layout
        TextView osNameTextView = findViewById(R.id.textViewOSName);
        TextView ownerCompanyTextView = findViewById(R.id.textViewOwnerCompany);
        TextView versionTextView = findViewById(R.id.textViewVersion);
        TextView featuresTextView = findViewById(R.id.textViewFeatures);
        TextView marketStatusTextView = findViewById(R.id.textViewMarketStatus);

        // Retrieve OS details from the intent
        Intent intent = getIntent();
        if (intent != null) {
            String osName = intent.getStringExtra("osName");
            String ownerCompany = intent.getStringExtra("ownerCompany");
            String version = intent.getStringExtra("version");
            String features = intent.getStringExtra("features");
            String marketStatus = intent.getStringExtra("marketStatus");

            // Set the TextViews with the retrieved information
            osNameTextView.setText(osName);
            ownerCompanyTextView.setText("Owner Company: " + ownerCompany);
            versionTextView.setText("Current Version: " + version);
            featuresTextView.setText("Architectural Features: " + marketStatus);
            marketStatusTextView.setText("Market Status: " + features);
        }
    }
}