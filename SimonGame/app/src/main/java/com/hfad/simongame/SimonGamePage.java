package com.hfad.simongame;

import android.content.Intent;
import android.os.Bundle;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

public class SimonGamePage extends AppCompatActivity {

    public static final String EXTRA_MESSAGE = "message"; // Ensure consistency with MainActivity

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_simon_game_page);

        // Get the intent that started this activity
        Intent intent = getIntent();
        String username = intent.getStringExtra(EXTRA_MESSAGE); // Use the correct key

        // Display the username in the text view
        TextView messageView = findViewById(R.id.textView);
        if (username != null && !username.isEmpty()) {
            messageView.setText("Welcome, " + username + "!");
        } else {
            messageView.setText("Welcome, Guest!");  // Fallback message if username is null
        }
    }
}
