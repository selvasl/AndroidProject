package com.techtist.srikasiviswanathar.home.activities;

import android.content.Intent;
import android.graphics.Typeface;
import android.os.Bundle;
import android.view.View;
import androidx.annotation.NonNull;
import android.widget.TextView;
import android.view.MenuItem;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.res.ResourcesCompat;
import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.techtist.srikasiviswanathar.R;
import com.techtist.srikasiviswanathar.news.activities.activities.NewsActivity;
import com.techtist.srikasiviswanathar.pooja.activities.PoojaActivity;

public class HomeActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home); // Your layout resource

        // Find the BottomNavigationView
        BottomNavigationView bottomNavigationView = findViewById(R.id.bottom_navigation);

        // Use `setOnItemSelectedListener` to handle item clicks
        bottomNavigationView.setOnItemSelectedListener(new BottomNavigationView.OnItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                int itemId = item.getItemId();

                // Handling navigation based on item selection
                if (itemId == R.id.home) {
                    // Start the HomeActivity
                    startActivity(new Intent(HomeActivity.this, HomeActivity.class));
                    return true;
                } else if (itemId == R.id.pooja) {
                    // Start PoojaActivity
                    startActivity(new Intent(HomeActivity.this, PoojaActivity.class));
                    return true;
                } else if (itemId == R.id.news) {
                    // Start NewsActivity
                    startActivity(new Intent(HomeActivity.this, NewsActivity.class));
                    return true;
                } else if (itemId == R.id.menu) {
                    // Handle menu item
                    return true;
                }

                return false;
            }
        });

        // Set custom fonts for the BottomNavigationView items
        setCustomFontForMenuItems(bottomNavigationView);
    }

    private void setCustomFontForMenuItems(BottomNavigationView bottomNavigationView) {
        // Iterate through each menu item in the BottomNavigationView
        for (int i = 0; i < bottomNavigationView.getMenu().size(); i++) {
            final int index = i; // Use a final index for access inside post()

            bottomNavigationView.post(() -> {
                View menuItemView = bottomNavigationView.findViewById(
                        bottomNavigationView.getMenu().getItem(index).getItemId()
                );
                if (menuItemView != null) {
                    // Find the title TextView of the menu item
                    TextView textView = menuItemView.findViewById(com.google.android.material.R.id.navigation_bar_item_small_label_view);
                    if (textView == null) {
                        textView = menuItemView.findViewById(com.google.android.material.R.id.navigation_bar_item_large_label_view);
                    }

                    if (textView != null) {
                        // Apply custom font
                        Typeface typeface = ResourcesCompat.getFont(this, R.font.poppins_regular);
                        textView.setTypeface(typeface);
                    }
                }
            });
        }
    }
}
