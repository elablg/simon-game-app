package com.hfad.week10_sounds;

import android.media.AudioAttributes;
import android.media.SoundPool;
import android.os.Bundle;
import android.view.View;
import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {

    private SoundPool soundPool;
    private int sound1, sound2, sound3, sound4, sound5, sound6;
    private int currentStreamId = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // Configure SoundPool with AudioAttributes
        AudioAttributes audioAttributes = new AudioAttributes.Builder()
                .setUsage(AudioAttributes.USAGE_MEDIA)
                .setContentType(AudioAttributes.CONTENT_TYPE_SONIFICATION)
                .build();

        soundPool = new SoundPool.Builder()
                .setMaxStreams(6)
                .setAudioAttributes(audioAttributes)
                .build();

        // Load sounds
        sound1 = soundPool.load(this, R.raw.rain, 1);
        sound2 = soundPool.load(this, R.raw.thunder, 1);
        sound3 = soundPool.load(this, R.raw.kitten, 1);
        sound4 = soundPool.load(this, R.raw.violin, 1);
        sound5 = soundPool.load(this, R.raw.guitar, 1);
        sound6 = soundPool.load(this, R.raw.pinao, 1); // Corrected typo
    }

    public void playRain(View view) {
        playSound(sound1);
    }

    public void playThunder(View view) {
        playSound(sound2);
    }

    public void playKitten(View view) {
        playSound(sound3);
    }

    public void playViolin(View view) {
        playSound(sound4);
    }

    public void playGuitar(View view) {
        playSound(sound5);
    }

    public void playPiano(View view) {
        playSound(sound6);
    }

    public void playLoopRain(View view) {
        playLoop(sound1);
    }

    public void pauseSound(View view) {
        if (currentStreamId != 0) {
            soundPool.pause(currentStreamId);
        }
    }

    public void stopSound(View view) {
        stopCurrentStream();
    }

    private void playSound(int soundId) {
        stopCurrentStream();
        currentStreamId = soundPool.play(soundId, 1f, 1f, 1, 0, 1f);
    }

    private void playLoop(int soundId) {
        stopCurrentStream();
        currentStreamId = soundPool.play(soundId, 1f, 1f, 1, -1, 1f);
    }

    private void stopCurrentStream() {
        if (currentStreamId != 0) {
            soundPool.stop(currentStreamId);
            currentStreamId = 0;
        }
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        if (soundPool != null) {
            soundPool.release();
            soundPool = null;
        }
    }
}
