package com.tea.alarm;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.util.Log;

public class AlarmReceiver extends BroadcastReceiver {
    @Override
    public void onReceive(Context context, Intent intent) {
        // bradcast usse receiver inten fron system or exchange data between 2 or more app
        Log.e("Talama", "Xin chao");
        String string_chuoi = intent.getExtras().getString("extra");
        Log.e("Key", string_chuoi);

        Intent myInten = new Intent(context, Music.class);
        myInten.putExtra("extra", string_chuoi);
        context.startService(myInten);
    }
}
