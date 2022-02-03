package com.example.diaryapplication;

import android.app.DatePickerDialog;
import android.app.Dialog;
import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.View;
import android.view.Window;
import android.widget.Button;
import android.widget.CompoundButton;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.Switch;
import android.widget.TextView;
import android.widget.TimePicker;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;

import com.example.diaryapplication.database.UserTodo;
import com.example.diaryapplication.dialog.CustomDialog;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.Calendar;

public class EditScheduleActivity extends AppCompatActivity{

    private FirebaseAuth mAuth;
    private DatabaseReference mDatabase;

    boolean isAlarmChecked;
    EditText content;
    TextView todoDate;

    TimePicker alarmTime;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_schedule);

        //파이어베이스 데이터베이스 사용
        mDatabase = FirebaseDatabase.getInstance().getReference();
        mAuth = FirebaseAuth.getInstance();

        Button addBtn = findViewById(R.id.add_btn);

        Switch alarmBtn = findViewById(R.id.alarm);
        alarmTime = findViewById(R.id.time);

        isAlarmChecked = false;
        content = findViewById(R.id.content);
        todoDate = findViewById(R.id.date);

        Calendar calendar = Calendar.getInstance();
        int mYear = calendar.get(Calendar.YEAR);
        int mMonth = calendar.get(Calendar.MONTH);
        int mDay = calendar.get(Calendar.DAY_OF_MONTH);
        todoDate.setText(mYear + "-" + (mMonth+1) + "-" + mDay);

        //날짜 선택 다이얼로그 창
        DatePickerDialog datePickerDialog = new DatePickerDialog(this, new DatePickerDialog.OnDateSetListener() {
            @Override
            public void onDateSet(DatePicker datePicker, int year, int month, int day) {
                String date = year + "-" + (month+1) + "-" + day;
                todoDate.setText(date);
            }
        }, mYear, mMonth, mDay);

        //달력 최소, 최대 설정
        calendar.set(2000, 0, 1);
        datePickerDialog.getDatePicker().setMinDate(calendar.getTimeInMillis());
        calendar.set(2100, 11, 31);
        datePickerDialog.getDatePicker().setMaxDate(calendar.getTimeInMillis());

        //날짜 선택 버튼
        todoDate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (todoDate.isClickable()) {
                    datePickerDialog.show();
                }
            }
        });

        //알람 on/off 버튼
        alarmBtn.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean isChecked) {
                if (isChecked) {
                    isAlarmChecked = true;
                    alarmTime.setVisibility(View.VISIBLE);
                }
                else {
                    isAlarmChecked = false;
                    alarmTime.setVisibility(View.GONE);
                }
            }
        });

        //편집 버튼
        addBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                callFunction("일정을 추가하시겠습니까?");
            }
        });

        content.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {
            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
            }

            @Override
            public void afterTextChanged(Editable editable) {

                Log.d("Date:", todoDate.toString());

                if (content.length() > 0) {
                    addBtn.setEnabled(true);
                    addBtn.setTextColor(ContextCompat.getColor(getApplicationContext(), R.color.white));
                    addBtn.setBackground(ContextCompat.getDrawable(getApplicationContext(), R.drawable.round_button_ok));
                } else {
                    addBtn.setEnabled(false);
                    addBtn.setTextColor(ContextCompat.getColor(getApplicationContext(), R.color.deep_gray));
                    addBtn.setBackground(ContextCompat.getDrawable(getApplicationContext(), R.drawable.round_button));
                }
            }
        });
    }

    public void callFunction(String message) {

        final Dialog dig = new Dialog(this);

        dig.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
        dig.requestWindowFeature(Window.FEATURE_NO_TITLE);
        dig.setContentView(R.layout.activity_custom_dialog);

        dig.show();

        final TextView text = (TextView) dig.findViewById(R.id.title);
        final Button okButton = (Button) dig.findViewById(R.id.okButton);
        final Button cancelButton = (Button) dig.findViewById(R.id.cancelButton);

        text.setText(message);

        //확인 버튼 이벤트 처리, DB에 리스트 업데이트
        okButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                dig.dismiss();

                FirebaseUser firebaseUser = mAuth.getCurrentUser();

                //날짜 받아오기, 아이디 받아올 방법 생각
                UserTodo mUserTodo = new UserTodo();
                String mUserID = mDatabase.push().getKey();

                mUserTodo.setTodoID(mUserID);
                mUserTodo.setDate(todoDate.getText().toString());
                mUserTodo.setContent(content.getText().toString());
                mUserTodo.setCompleted(false);
                mUserTodo.setAlarm(isAlarmChecked);

                if (isAlarmChecked){
                    mUserTodo.setHour(alarmTime.getCurrentHour());
                    mUserTodo.setMin(alarmTime.getCurrentMinute());
                } else {
                    mUserTodo.setHour(00);
                    mUserTodo.setMin(00);
                }

                //DB에 저장
                mDatabase.child("users").child(firebaseUser.getUid()).child("todo").child(mUserID).setValue(mUserTodo);
                finish();
            }
        });

        //취소 버튼 이벤트 처리
        cancelButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                dig.dismiss();
            }
        });
    }
}