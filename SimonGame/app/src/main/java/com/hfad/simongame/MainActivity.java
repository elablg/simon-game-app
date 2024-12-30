package com.hfad.simongame;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;

import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);  // Ensure this is set correctly to your initial layout
    }

    public void onSendMessage(View view) {
        EditText usernameEditText = findViewById(R.id.usernameEnterText);
        String username = usernameEditText.getText().toString();
        Intent intent = new Intent(this, SimonGamePage.class);
        intent.putExtra("EXTRA_MESSAGE", username);
        startActivity(intent);
    }
}

