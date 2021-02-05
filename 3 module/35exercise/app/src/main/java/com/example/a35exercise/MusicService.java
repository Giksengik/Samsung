package com.example.a35exercise;

import android.app.IntentService;
import android.app.Service;
import android.content.Intent;
import android.media.MediaPlayer;
import android.os.IBinder;

import androidx.annotation.Nullable;

public class MusicService extends IntentService {
    private static MediaPlayer mediaPlayer;
    public static final int MUSIC_PLAY = 1;
    public static final int MUSIC_STOP = 2;
    public MusicService() {
        super("name");
    }

    @Override
    public void onCreate(){
        super.onCreate();
        mediaPlayer = MediaPlayer.create(getApplicationContext(), R.raw.mystic_music);
    }

    @Nullable
    @Override
    public IBinder onBind(Intent intent) {
        return null;
    }

    @Override
    protected synchronized void onHandleIntent(@Nullable Intent intent) {
        switch (intent.getIntExtra("status" , 0)) {
            case MUSIC_PLAY:
                if (!mediaPlayer.isPlaying()) mediaPlayer.start();
                break;
            case MUSIC_STOP:
                if (mediaPlayer.isPlaying()) {
                    mediaPlayer.stop();
                }
        }
    }
}
