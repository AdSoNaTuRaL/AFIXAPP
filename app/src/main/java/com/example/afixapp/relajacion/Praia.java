package com.example.afixapp.relajacion;

import androidx.appcompat.app.AppCompatActivity;

import android.media.MediaPlayer;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.example.afixapp.R;

public class Praia extends AppCompatActivity {

    Button play_pause;
    MediaPlayer mediaPlayer;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_praia);

        play_pause = findViewById(R.id.btn_praia);
        mediaPlayer = MediaPlayer.create(this, R.raw.praia);
    }

    @Override
    protected void onResume() {
        super.onResume();
        play_pause.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (mediaPlayer.isPlaying()) {
                    mediaPlayer.pause();
                    play_pause.setBackgroundResource(R.drawable.ic_play_arrow_black_24dp);
                }else{
                    mediaPlayer.start();
                    play_pause.setBackgroundResource(R.drawable.ic_pause_black_24dp);
                }
            }
        });
    }

    @Override
    protected void onPause() {
        super.onPause();
        mediaPlayer.stop();
    }

    @Override
    protected void onStop() {
        super.onStop();
        mediaPlayer.stop();
    }
}
