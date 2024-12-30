package com.hfad.simongame;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.os.Handler;
import android.preference.PreferenceManager;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class SimonGamePage extends AppCompatActivity {

    // Declare buttons and views
    private Button buttonGreen, buttonRed, buttonYellow, buttonBlue, buttonStart, buttonViewScores;
    private TextView textDisplayUsername, scoreTextView;

    // Game variables
    private List<Button> sequence = new ArrayList<>();
    private int score = 0;
    private Random random = new Random();
    private Handler handler = new Handler();
    private int userIndex = 0;
    private boolean isUserTurn = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_simon_game_page);

        textDisplayUsername = findViewById(R.id.textDisplayUsername);
        scoreTextView = findViewById(R.id.textDisplayScore);
        buttonGreen = findViewById(R.id.buttonGreen);
        buttonRed = findViewById(R.id.buttonRed);
        buttonYellow = findViewById(R.id.buttonYellow);
        buttonBlue = findViewById(R.id.buttonBlue);
        buttonStart = findViewById(R.id.buttonStart);
        buttonViewScores = findViewById(R.id.buttonProceed);

        buttonViewScores.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // Replace "Scoreboard.class" with "ScoreboardActivity.class"
                Intent intent = new Intent(SimonGamePage.this, Scoreboard.class);
                startActivity(intent);
            }
        });

        String username = getIntent().getStringExtra("EXTRA_MESSAGE");
        if (username != null && !username.isEmpty()) {
            textDisplayUsername.setText("Welcome, " + username + "!");
        }

        disableGameButtons();
        buttonViewScores.setVisibility(View.GONE);

        buttonStart.setOnClickListener(view -> startGame());

        buttonGreen.setOnClickListener(view -> handleUserInput(buttonGreen));
        buttonRed.setOnClickListener(view -> handleUserInput(buttonRed));
        buttonYellow.setOnClickListener(view -> handleUserInput(buttonYellow));
        buttonBlue.setOnClickListener(view -> handleUserInput(buttonBlue));
    }

    private void disableGameButtons() {
        buttonGreen.setEnabled(false);
        buttonRed.setEnabled(false);
        buttonYellow.setEnabled(false);
        buttonBlue.setEnabled(false);
    }

    private void enableGameButtons() {
        buttonGreen.setEnabled(true);
        buttonRed.setEnabled(true);
        buttonYellow.setEnabled(true);
        buttonBlue.setEnabled(true);
    }

    private void startGame() {
        score = 0;
        sequence.clear();
        userIndex = 0;
        scoreTextView.setText("Score: " + score);
        buttonViewScores.setVisibility(View.GONE); // Hide the view scores button during the game
        addNewButtonToSequence();
    }

    private void addNewButtonToSequence() {
        int randomIndex = random.nextInt(4);
        Button nextButton = getButtonByIndex(randomIndex);
        sequence.add(nextButton);
        showSequence();
    }

    private Button getButtonByIndex(int index) {
        switch (index) {
            case 0: return buttonGreen;
            case 1: return buttonRed;
            case 2: return buttonYellow;
            case 3: return buttonBlue;
            default: return buttonGreen; // Fallback case
        }
    }


    private void showSequence() {
        disableGameButtons();
        isUserTurn = false;
        userIndex = 0;

        for (int i = 0; i < sequence.size(); i++) {
            final Button buttonToFlash = sequence.get(i);
            handler.postDelayed(() -> flashButton(buttonToFlash), i * 1000);
        }

        handler.postDelayed(() -> {
            enableGameButtons();
            isUserTurn = true;
        }, sequence.size() * 1000);
    }


    private void flashButton(Button button) {
        button.setAlpha(0.5f);
        handler.postDelayed(() -> button.setAlpha(1f), 500);
    }

    private void handleUserInput(Button buttonPressed) {
        if (!isUserTurn) return;

        if (buttonPressed == sequence.get(userIndex)) {
            userIndex++;
            if (userIndex == sequence.size()) {
                score++;
                scoreTextView.setText("Score: " + score);
                disableGameButtons();
                handler.postDelayed(this::addNewButtonToSequence, 1000);
            }
        } else {
            gameOver();
        }
    }

    private void gameOver() {
        disableGameButtons();
        scoreTextView.setText("Game Over! Final Score: " + score);

        String username = getIntent().getStringExtra("EXTRA_MESSAGE");
        if (username == null || username.isEmpty()) {
            username = "Guest";
        }
        saveScore(username, score);

        buttonViewScores.setVisibility(View.VISIBLE);
    }


    private void saveScore(String username, int score) {
        SharedPreferences sharedPreferences = PreferenceManager.getDefaultSharedPreferences(this);
        SharedPreferences.Editor editor = sharedPreferences.edit();

        String scoreEntry = username + " - " + score;
        String existingScores = sharedPreferences.getString("high_scores", "");
        String updatedScores = existingScores + scoreEntry + "\n";

        editor.putString("high_scores", updatedScores);
        editor.apply();
    }



}
