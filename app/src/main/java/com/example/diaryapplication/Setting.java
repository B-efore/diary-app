package com.example.diaryapplication;


import android.app.AlarmManager;
import android.app.PendingIntent;
import android.app.TimePickerDialog;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.widget.TimePicker;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.DialogFragment;

import java.text.DateFormat;
import java.util.Calendar;
import java.util.Objects;



public class Setting extends AppCompatActivity implements TimePickerDialog.OnTimeSetListener {
    String timeText;




        @Override
        protected void onCreate(Bundle savedInstanceState) {
            super.onCreate(savedInstanceState);
            setContentView(R.layout.activity_main);
            DialogFragment timePicker = new TimePickerFragment();

        }

        @Override
        public void onTimeSet(TimePicker view, int hourOfDay, int minute) {
            Calendar c = Calendar.getInstance();
            c.set(Calendar.HOUR_OF_DAY, hourOfDay);
            c.set(Calendar.MINUTE, minute);
            updateTimeText(c);
            startAlarm(c);
        }

        private void updateTimeText(Calendar c) {
            timeText = "Alarm set for: ";
            timeText += DateFormat.getTimeInstance(DateFormat.SHORT).format(c.getTime());
        }

        private void startAlarm(Calendar c) {
            AlarmManager alarmManager = (AlarmManager) getSystemService(Context.ALARM_SERVICE);
            Intent intent = new Intent(this, NotificationHelper.class);
            PendingIntent pendingIntent = PendingIntent.getBroadcast(this, 1, intent, 0);
            if (c.before(Calendar.getInstance())) {
                c.add(Calendar.DATE, 1);
            }
            Objects.requireNonNull(alarmManager).setExact(AlarmManager.RTC_WAKEUP,
                    c.getTimeInMillis(), pendingIntent);
        }
    }

