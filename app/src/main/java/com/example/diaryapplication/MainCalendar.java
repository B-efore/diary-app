package com.example.diaryapplication;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.CalendarView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.example.diaryapplication.dialog.CustomDialog;
import com.example.diaryapplication.dialog.CustomDialog2;
import com.google.android.material.appbar.MaterialToolbar;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.Calendar;
import java.util.Date;

public class MainCalendar extends AppCompatActivity implements View.OnClickListener {

    MaterialToolbar toolBar;

    FloatingActionButton editBtn;

    CalendarView calendarView;
    Calendar calendar;
    Date date;
    String userDate;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_calendar);

        editBtn = findViewById(R.id.edit_btn);
        calendarView = findViewById(R.id.calendarView);
        toolBar = findViewById(R.id.toolbar);

        calendar = Calendar.getInstance();
        date = new Date(calendarView.getDate());
        calendar.setTime(date);

        userDate = Integer.toString(calendar.get(Calendar.YEAR)) + "-"
                + Integer.toString(calendar.get(Calendar.MONTH)) + "-"
                + Integer.toString(calendar.get(Calendar.DATE));

        toolBar.setTitle("");
        setSupportActionBar(toolBar);

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
//        getSupportActionBar().setHomeAsUpIndicator(R.drawable.accounticon);

        Log.d("Date", "현재 날짜: " + userDate);

        //날짜 변경시 한번 더 클릭해야 디테일 캘린더로 들어가도록 함
        calendarView.setOnDateChangeListener(new CalendarView.OnDateChangeListener() {
            @Override
            public void onSelectedDayChange(@NonNull CalendarView view, int year, int month, int dayOfMonth) {

                if (userDate.equals(year + "-" + month + "-" + dayOfMonth)) {
                    startActivity(new Intent(getApplicationContext(), DetailCalendar.class).putExtra("Date", date));
                } else {
                    userDate = Integer.toString(year) + "-"
                            + Integer.toString(month) + "-"
                            + Integer.toString(dayOfMonth);
                }

                Log.d("Date", "현재 날짜: " + userDate);
            }
        });

        //편집 버튼
        editBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(MainCalendar.this, EditScheduleActivity.class));
            }
        });
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu){
        getMenuInflater().inflate(R.menu.top_app_bar, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
                startActivity(new Intent(this, Setting.class));
                return true;
            case R.id.swapIcon:
                startActivity(new Intent(this, DetailCalendar.class));
                return true;
            case R.id.marketIcon:
                CustomDialog2 customDialog = new CustomDialog2(MainCalendar.this);
                customDialog.callFunction("준비중!");
                return true;
            default:
                // If we got here, the user's action was not recognized.
                // Invoke the superclass to handle it.
                return super.onOptionsItemSelected(item);

        }
    }

    @Override
    public void onClick(View view) {

        Log.d("Date", "현재 날짜: " + userDate);

        switch (view.getId()) {
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