package com.example.media_player;

import android.media.MediaPlayer;
import android.net.Uri;
import android.os.Bundle;
import android.widget.*;
import android.content.Intent;
import android.widget.MediaController;

import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {

    MediaPlayer mediaPlayer;
    VideoView videoView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Button openAudioBtn = findViewById(R.id.openAudioBtn);
        Button openVideoBtn = findViewById(R.id.openVideoBtn);
        Button playBtn = findViewById(R.id.playBtn);
        Button pauseBtn = findViewById(R.id.pauseBtn);
        Button stopBtn = findViewById(R.id.stopBtn);
        Button restartBtn = findViewById(R.id.restartBtn);

        EditText videoUrl = findViewById(R.id.videoUrl);
        videoView = findViewById(R.id.videoView);

        MediaController mediaController = new MediaController(this);
        mediaController.setAnchorView(videoView);
        videoView.setMediaController(mediaController);

        openAudioBtn.setOnClickListener(v -> {
            Intent intent = new Intent(Intent.ACTION_GET_CONTENT);
            intent.setType("audio/*");
            startActivityForResult(intent, 1);
        });

        openVideoBtn.setOnClickListener(v -> {
            String url = videoUrl.getText().toString();

            if(!url.isEmpty()){
                Uri uri = Uri.parse(url);
                videoView.setVideoURI(uri);

                videoView.setOnPreparedListener(mp -> {
                    videoView.start();
                });

                videoView.setOnErrorListener((mp, what, extra) -> {
                    Toast.makeText(this, "Video Error!", Toast.LENGTH_SHORT).show();
                    return true;
                });

            } else {
                Toast.makeText(this, "Enter URL", Toast.LENGTH_SHORT).show();
            }
        });

        playBtn.setOnClickListener(v -> {
            if(mediaPlayer != null) mediaPlayer.start();
            if(videoView != null) videoView.start();
        });

        pauseBtn.setOnClickListener(v -> {
            if(mediaPlayer != null && mediaPlayer.isPlaying()) mediaPlayer.pause();
            if(videoView != null && videoView.isPlaying()) videoView.pause();
        });

        stopBtn.setOnClickListener(v -> {
            if(mediaPlayer != null){
                mediaPlayer.stop();
                mediaPlayer.release();
                mediaPlayer = null;
            }
            if(videoView != null){
                videoView.stopPlayback();
            }
        });

        restartBtn.setOnClickListener(v -> {
            if(mediaPlayer != null){
                mediaPlayer.seekTo(0);
                mediaPlayer.start();
            }
            if(videoView != null){
                videoView.seekTo(0);
                videoView.start();
            }
        });
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if(requestCode == 1 && resultCode == RESULT_OK && data != null){
            Uri audioUri = data.getData();
            mediaPlayer = MediaPlayer.create(this, audioUri);
            mediaPlayer.start();
        }
    }
}