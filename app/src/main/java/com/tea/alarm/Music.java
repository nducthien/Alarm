package com.tea.alarm;

import android.app.Service;
import android.content.Intent;
import android.media.MediaPlayer;
import android.os.IBinder;
import android.support.annotation.Nullable;
import android.util.Log;

public class Music extends Service {
    MediaPlayer mediaPlayer;
    int id;

    @Nullable
    @Override
    public IBinder onBind(Intent intent) {
        return null;
    }

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {

        Log.e("That Girl", "I keep saying no");

        String nhan_key = intent.getExtras().getString("extra");
        Log.e("Music Nhan Key", nhan_key);

        if (nhan_key.equals("on")) {
            id = 1;
        } else if (nhan_key.equals("off")) {
            id = 0;
        }

        if (id == 1) {
            mediaPlayer = MediaPlayer.create(this, R.raw.thatgirl);
            mediaPlayer.start();
        } else if (id == 0) {
            mediaPlayer.stop();
            mediaPlayer.reset();
        }

        return START_NOT_STICKY;
    }
}
