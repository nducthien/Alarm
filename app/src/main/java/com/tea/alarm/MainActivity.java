package com.tea.alarm;

import android.app.AlarmManager;
import android.app.PendingIntent;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.TimePicker;

import java.util.Calendar;

public class MainActivity extends AppCompatActivity {
    private Button btnPut;
    private Button btnDismiss;
    private TextView tvDisplay;
    private TimePicker tp_Time;

    Calendar calendar;
    AlarmManager alarmManager;
    PendingIntent pendingIntent;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        tp_Time = findViewById(R.id.tp_Time);
        tvDisplay = findViewById(R.id.tvDisplay);
        btnPut = findViewById(R.id.btnPut);
        btnDismiss = findViewById(R.id.btnDismiss);

        calendar = Calendar.getInstance();
        alarmManager = (AlarmManager) getSystemService(ALARM_SERVICE);

        final Intent intent = new Intent(MainActivity.this, AlarmReceiver.class);

        btnPut.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                calendar.set(Calendar.HOUR_OF_DAY, tp_Time.getCurrentHour());
                calendar.set(Calendar.MINUTE, tp_Time.getCurrentMinute());

                int hour = tp_Time.getCurrentHour();
                int minute = tp_Time.getCurrentMinute();

                String string_hour = String.valueOf(hour);
                String string_minute = String.valueOf(minute);

                if (hour > 12) {
                    string_hour = String.valueOf(hour - 12);
                }
                if (minute < 10) {
                    string_minute = "0" + String.valueOf(minute);
                }

                intent.putExtra("extra", "on");

                pendingIntent = PendingIntent.getBroadcast(MainActivity.this,
                        0, intent, PendingIntent.FLAG_UPDATE_CURRENT);
                alarmManager.set(AlarmManager.RTC_WAKEUP, calendar.getTimeInMillis(), pendingIntent);

                tvDisplay.setText("Put is: " + string_hour + ":" + string_minute);
            }
        });

        btnDismiss.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                tvDisplay.setText("Have a nice day");
                pendingIntent.cancel();
                intent.putExtra("extra", "off");
                sendBroadcast(intent);
            }
        });
    }
}
