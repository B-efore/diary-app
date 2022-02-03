package com.example.diaryapplication;

import static com.example.diaryapplication.TimelineActivity.adapter;
import static com.example.diaryapplication.TimelineActivity.listView;

import androidx.activity.result.contract.ActivityResultContracts;
import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.TimePicker;

import com.example.diaryapplication.database.UserTimeline;
import com.example.diaryapplication.database.UserTodo;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.ArrayList;
import java.util.List;

public class EditTimeline extends Activity implements View.OnClickListener{

    private FirebaseAuth mAuth;
    private DatabaseReference mDatabase;

    private ListView listView2;

    private EditText ed_time;
    private EditText ed_schedule;
    private Button btn_add;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_timeline);

        //파이어베이스 데이터베이스 사용
        mDatabase = FirebaseDatabase.getInstance().getReference();
        mAuth = FirebaseAuth.getInstance();

        ed_time = (EditText) findViewById(R.id.ed_time);
        ed_schedule = (EditText) findViewById(R.id.ed_schedule);
        btn_add = (Button) findViewById(R.id.btn_add);

        btn_add.setOnClickListener(this);

        listView2 = (ListView) findViewById(R.id.listView2);
        listView2.setAdapter(adapter);

    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.btn_add:
                adapter.addData(ed_time.getText().toString(), ed_schedule.getText().toString());
                ed_time.setText("");
                ed_schedule.setText("");

                FirebaseUser firebaseUser = mAuth.getCurrentUser();
                UserTimeline userTimeline = new UserTimeline();

                String mUserID = mDatabase.push().getKey();

                //날짜 받아오기, TimePicker 자리에 timepicker 객체 만들어서 넣기
                userTimeline.settID(mUserID);
//                userTimeline.settDate();
                userTimeline.settContent(ed_schedule.getText().toString());
//                userTimeline.settHour(TimePicker.getCurrentHour());
//                userTimeline.settMin(TimePicker.getCurrentMinute());

                adapter.notifyDataSetChanged();
                break;
        }
    }
}