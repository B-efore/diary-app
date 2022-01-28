package com.example.diaryapplication;


import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.content.Intent;

import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;

import java.util.ArrayList;
import java.util.List;

import androidx.appcompat.app.AppCompatActivity;

import com.google.android.material.floatingactionbutton.FloatingActionButton;

public class TimelineActivity extends Activity implements View.OnClickListener{
    /*
    타임라인 담당 코드 작성 목록
      (1) 타임라인 편집버튼 인텐트로 화면 전환 (xml 아이디 임의로 정해서 오류 발생, 수정 예정)
      (2) 타임라인 내용 관리하는 TimelineData 파일 작성
      (3) 타임라인 어뎁터 파일 작성(아이디 오류 문제 있음, 수정 예정) - 일정 삭제 버튼 이벤트 처리
      (4) EditTimeline.java에 일정 입력 받기, 추가 버튼, 데이터 리스트, 어뎁터 설정 - 일정 추가 버튼 이벤트 처리

      -----------------------------------------------------------------------------------------------------------------
     @@ 남은 작업 목록@@
      (1) 프리셋 (DB 생성해서 시도해보기)
      */

    //편집 버튼 변수 선언
    private FloatingActionButton btn_edit;
    private Button btn_preset;

    public static ListView listView;
    public static TimelineAdapter adapter;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_timeline);


        listView = (ListView)  findViewById(R.id.listView);
//        //편집 버튼 아이디 찾기
        btn_edit = findViewById(R.id.btn_edit);
//        //편집 버튼 눌렀을 때 편집 화면으로 전환하게 하기
        btn_edit.setOnClickListener(this);

        adapter = new TimelineAdapter(TimelineActivity.this);
        listView.setAdapter(adapter);
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.btn_edit:
                Intent intent = new Intent(this, EditTimeline.class);
                startActivity(intent); //EditTimeLine Activity로 이동
                break;

        }

    }




}




