package com.techtist.srikasiviswanathar.pooja.activities;

import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Spinner;
import android.widget.AdapterView;
import android.widget.Toast;
import android.graphics.Typeface;
import androidx.core.content.res.ResourcesCompat;
import androidx.appcompat.widget.Toolbar;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.techtist.srikasiviswanathar.R;

public class PoojaActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pooja);

        // Setup Toolbar
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setHomeAsUpIndicator(R.drawable.baseline_arrow_back);

        // Enable back navigation
        if (getSupportActionBar() != null) {
            getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        }

        // Set the title text
        getSupportActionBar().setTitle("Pooja");

        // Setup Spinner
        Spinner messagesSpinner = findViewById(R.id.messages_spinner);

        // Create an ArrayAdapter using a string array from resources
        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(
                this,
                R.array.pooja_messages, // Define this array in strings.xml
                android.R.layout.simple_spinner_item
        );

        // Specify the layout to use when the list of choices appears
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

        // Apply the adapter to the spinner
        messagesSpinner.setAdapter(adapter);

        // Set a listener for the spinner item selection
        messagesSpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                // Get the selected item
                String selectedMessage = parent.getItemAtPosition(position).toString();

                // Display a Toast or perform some action
                Toast.makeText(PoojaActivity.this, "Selected: " + selectedMessage, Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {
                // Optional: Handle the case when no item is selected
            }
        });

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
