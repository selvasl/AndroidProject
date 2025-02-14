package com.techtist.srikasiviswanathar.pooja.activities;

import android.graphics.Typeface;
import android.os.Bundle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.content.res.ResourcesCompat;

import android.widget.TextView;

import com.techtist.srikasiviswanathar.R;

public class ChaturthiPoojaActivity extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_chaturthi_pooja);

        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setHomeAsUpIndicator(R.drawable.baseline_arrow_back);

        TextView titleTextView = (TextView) toolbar.getChildAt(0);  // Get the first child, which is the title TextView

        if (titleTextView != null) {
            // Set a custom font for the title from res/font directory
            Typeface customFont = ResourcesCompat.getFont(this, R.font.poppins_semibold); // Use the font from the res/font folder
            titleTextView.setTypeface(customFont);
        }
        TextView title = (TextView) toolbar.getChildAt(0);  // The title is the first child of the Toolbar
        if (title != null) {
            title.setTextSize(17);  // Adjust the text size here (in sp)
        }

        // Handle back navigation
        toolbar.setNavigationOnClickListener(view -> {
            finish(); // Navigate back to the previous activity
        });
    }
}
