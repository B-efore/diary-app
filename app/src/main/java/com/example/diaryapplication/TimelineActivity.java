package com.example.diaryapplication;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;

import java.util.ArrayList;
import java.util.List;

public class TimelineActivity extends AppCompatActivity {
    /*
    타임라인 담당 이번주 코드 작성 목록....
      (1) 타임라인 편집버튼 인텐트로 화면 전환 (xml 아이디 임의로 정해서 오류 발생, 수정 예정)
      (2) 타임라인 내용 관리하는 TimelineData 파일 작성
      (3) 타임라인 어뎁터 파일 작성(아이디 오류 문제 있음, 수정 예정)
      (4) MainActivity에 타임라인 리스트, 어뎁터, 리스트뷰 객체 생성하고 초기화 시킴, 리스트 뷰에 어뎁터 연결 (이것도 아이디 문제있음,
      수정 예정)

      -----------------------------------------------------------------------------------------------------------------
     @@ 남은 작업 목록@@
      (1) 사용자 입력을 받는 부분 코드
      (2) 일정 수정, 삭제, 추가 버튼
      (3) 프리셋
      */

    //편집 버튼 변수 선언
    private Button btn_edit;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_timeline);
        
//        //편집 버튼 아이디 찾기
//        btn_edit = findViewById(R.id.btn_edit);
//        //편집 버튼 눌렀을 때 편집 화면으로 전환하게 하기
//        btn_edit = setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                Intent intent = new Intent(TimelineActivity.this, EditTimeline.class);
//                startActivity(intent); //EditTimeLine Activity로 이동
//            }
//        });


    }
}




