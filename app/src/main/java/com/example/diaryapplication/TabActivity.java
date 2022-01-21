package com.example.diaryapplication;

import android.app.ActivityGroup;
import android.content.Intent;
import android.os.Bundle;
import android.widget.TabHost;

public class TabActivity extends ActivityGroup {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tab);

        TabHost tabHost = (TabHost) findViewById(R.id.host);
        tabHost.setup(getLocalActivityManager());

        // 캘린더 액티비티
        TabHost.TabSpec spec = tabHost.newTabSpec("tab1")
                .setIndicator(null,getResources().getDrawable(R.drawable.ic_baseline_event_note_24))
                .setContent(new Intent(this, MainCalendar.class));
        tabHost.addTab(spec);

        // 타임라인 액티비티
        spec = tabHost.newTabSpec("tab2")
                .setIndicator(null, getResources().getDrawable(R.drawable.ic_baseline_timer_24))
                .setContent(new Intent(this,TimelineActivity.class).addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP));
        tabHost.addTab(spec);

        // SNS 액티비티 (준비중)
        spec = tabHost.newTabSpec("tab3")
                .setIndicator(null, getResources().getDrawable(R.drawable.ic_baseline_favorite_24))
                .setContent(new Intent(this,SocialActivity.class).addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP));
        tabHost.addTab(spec);

        tabHost.setCurrentTabByTag("tab1");
    }

    //앱 종료버튼 띄우기
    @Override
    public void onBackPressed(){
        //super.onBackPressed();
        CustomDialog customDialog = new CustomDialog(TabActivity.this);
        customDialog.callFunction("다이어리 작성을 끝내겠습니까?");
    }

}