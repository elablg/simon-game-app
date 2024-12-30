package com.hfad.simongame;

import android.os.Bundle;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;
import android.widget.ListView;
import android.widget.ArrayAdapter;

import androidx.appcompat.app.AppCompatActivity;

public class Scoreboard extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_scoreboard);

        // Initialize ListView
        ListView listViewScores = findViewById(R.id.listViewScores);

        // Retrieve scores from SharedPreferences
        SharedPreferences sharedPreferences = PreferenceManager.getDefaultSharedPreferences(this);
        String scoresString = sharedPreferences.getString("high_scores", "");

        // Split the scores by line breaks to create a list
        String[] scoresArray = scoresString.split("\n");

        // Set up ArrayAdapter to display scores in the ListView
        ArrayAdapter<String> adapter = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, scoresArray);
        listViewScores.setAdapter(adapter);
    }
}

