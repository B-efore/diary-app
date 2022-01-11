package com.example.diaryapplication;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.ListView;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {
    
    //타임라인 리스트뷰 변수 선언
    private ListView timelineListView;
    //타임라인 어뎁터 선언
    private TimelineAdapter timelineAdapter;
    //타임라인 데이터를 담는 리스트 선언
    private List<TimelineData> timelineList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //타임라인 리스트뷰 초기화 (아이디 이름은 수정예정)
        timelineListView = (ListView) findViewById(R.id.timelineListView);
        //타임라인 리스트 초기화
        timelineList = new ArrayList<TimelineData>();
        //어뎁터에 타임라인 리스트 내용 넣어주기
        timelineAdapter = new TimelineAdapter(getApplicationContext().timelineList);
        //타임라인 리스트뷰에 어뎁터 연결
        timelineListView.setAdapter(timelineAdapter);

    }
}