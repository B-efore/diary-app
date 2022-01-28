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

import java.util.ArrayList;
import java.util.List;

public class EditTimeline extends Activity implements View.OnClickListener{


    private ListView listView2;

    private EditText ed_time;
    private EditText ed_schedule;
    private Button btn_add;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_timeline);

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

                adapter.notifyDataSetChanged();
                break;
        }
    }
}