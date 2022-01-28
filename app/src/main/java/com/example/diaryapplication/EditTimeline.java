package com.example.diaryapplication;

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

import java.util.ArrayList;
import java.util.List;

public class EditTimeline extends Activity {

    private ListView listView;
    private TimelineAdapter adapter;

    private EditText ed_time;
    private EditText ed_schedule;
    private Button btn_add;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_timeline);

//        ed_time = (EditText) findViewById(R.id.ed_time);
//        ed_schedule = (EditText) findViewById(R.id.ed_schedule);
//        btn_add = (Button) findViewById(R.id.btn_add);
//        listView = (ListView) findViewById(R.id.listview);

        adapter = new TimelineAdapter(EditTimeline.this);
        listView.setAdapter(adapter);

        //일정 추가하기
//        btn_add.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                adapter.addData(ed_time.getText().toString(), ed_schedule.getText().toString());
//                ed_time.setText("");
//                ed_schedule.setText("");
//
//                adapter.notifyDataSetChanged();
//            }
//        });
    }

}