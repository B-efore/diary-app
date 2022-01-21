package com.example.diaryapplication;

import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;

public class MainCalendar extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_calendar);
    }

    //앱 종료버튼 띄우기
    @Override
    public void onBackPressed(){
        //super.onBackPressed();
        CustomDialog customDialog = new CustomDialog(MainCalendar.this);
        customDialog.callFunction();
    }
}