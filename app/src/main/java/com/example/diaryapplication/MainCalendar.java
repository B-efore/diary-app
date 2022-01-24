package com.example.diaryapplication;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.CalendarView;
import android.widget.ImageButton;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import java.util.Calendar;
import java.util.Date;

public class MainCalendar extends AppCompatActivity implements View.OnClickListener{

    CalendarView calendarView;
    Calendar calendar;
    Date date;
    ImageButton accountBtn, swapBtn, marketBtn;

    String userDate;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_calendar);


        calendarView = findViewById(R.id.calendarView);
        accountBtn = findViewById(R.id.accountIcon);
        swapBtn = findViewById(R.id.swapIcon);
        marketBtn = findViewById(R.id.marketIcon);

        calendar = Calendar.getInstance();
        date = new Date(calendarView.getDate());
        calendar.setTime(date);

        userDate = Integer.toString(calendar.get(Calendar.YEAR)) + "-"
                + Integer.toString(calendar.get(Calendar.MONTH)) + "-"
                + Integer.toString(calendar.get(Calendar.DATE));

        Log.d("Date", "현재 날짜: " + userDate);

        calendarView.setOnDateChangeListener(new CalendarView.OnDateChangeListener(){
            @Override
            public void onSelectedDayChange(@NonNull CalendarView view, int year, int month, int dayOfMonth){

                if(userDate.equals(year + "-" + month + "-" + dayOfMonth)){
                    startActivity(new Intent(getApplicationContext(), DetailCalendar.class).putExtra("Date", date));
                }
                else {
                    userDate = Integer.toString(year) + "-"
                            + Integer.toString(month) + "-"
                            + Integer.toString(dayOfMonth);
                }

                Log.d("Date", "현재 날짜: " + userDate);
            }
        });
    }

    @Override
    public void onClick(View view) {

        Log.d("Date", "현재 날짜: " + userDate);

        switch (view.getId()) {
            case R.id.accountIcon:
                startActivity(new Intent(this, Setting.class));
                break;
            case R.id.marketIcon:
                CustomDialog customDialog = new CustomDialog(MainCalendar.this);
                customDialog.callFunction("준비중입니다!");
                break;
            case R.id.swapIcon:
                startActivity(new Intent(this, DetailCalendar.class).putExtra("Date", userDate));
                break;
//            case 편집 버튼:
//                date = new Date(calendarView.getDate());
//                calendar.setTime(date);
//
//                String date = Integer.toString(calendar.get(Calendar.YEAR)) + "-"
//                        + Integer.toString(calendar.get(Calendar.MONTH)) + "-"
//                        + Integer.toString(calendar.get(Calendar.DATE));
//
//                startActivity(new Intent(this, EditCheckListActivity.class).putExtra("Date", userDate));
//                break;
        }
    }

    @Override
    public void onBackPressed(){
        //super.onBackPressed();
        CustomDialog customDialog = new CustomDialog(MainCalendar.this);
        customDialog.callFunction("다이어리 작성을 끝내겠습니까?");
    }
}