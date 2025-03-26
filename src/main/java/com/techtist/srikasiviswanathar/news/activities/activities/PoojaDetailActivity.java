package com.techtist.srikasiviswanathar.news.activities.activities;

import android.content.Intent;
import android.graphics.Typeface;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.content.res.ResourcesCompat;

import com.techtist.srikasiviswanathar.R;

public class PoojaDetailActivity extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pooja_detail);

        // Initialize toolbar
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true); // Show back button
        getSupportActionBar().setDisplayShowTitleEnabled(true);

        // Change back button color to black
        toolbar.getNavigationIcon().setTint(getResources().getColor(android.R.color.black));

        for (int i = 0; i < toolbar.getChildCount(); i++) {
            View view = toolbar.getChildAt(i);
            if (view instanceof TextView) {
                TextView titleTextView = (TextView) view;
                Typeface typeface = ResourcesCompat.getFont(this, R.font.poppins_semibold);
                titleTextView.setTypeface(typeface);
                break; // Exit loop once title is found
            }
        }

        // Initialize views
        TextView tvDescription = findViewById(R.id.tvDescription);

        // Get data from intent
        Intent intent = getIntent();
        if (intent != null) {
            String title = intent.getStringExtra("pooja_title");
            String description = intent.getStringExtra("pooja_description");

            // Set toolbar title
            getSupportActionBar().setTitle(title);

            // Set description text
            tvDescription.setText(description);
        }
    }

    @Override
    public boolean onSupportNavigateUp() {
        onBackPressed(); // Handle back button press
        return true;
    }
}
