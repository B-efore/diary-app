package com.example.diaryapplication;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import java.util.List;

public class TimelineAdapter extends BaseAdapter {

    private Context context;
    //타임라인 내용을 저장할 리스트 선언
    private List<TimelineData> timelineList;
    
    //생성자 선언
    public TimelineAdapter(Context context, List<TimelineData> timelineList) {
        this.context = context;
        this.timelineList = timelineList;
    }

    @Override
    public int getCount() {
        return timelineList.size();
    }

    @Override
    public Object getItem(int i) {
        return timelineList.get(i);
    }

    @Override
    public long getItemId(int i) {
        return i;
    }

    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {
        //타임라인에 해당하는 view 생성
        View v = View.inflate(context, R.layout.activity_timeline, null);
        //시간 텍스트 표시하는 view 객체 생성
        TextView timeText = (TextView) v.findViewById(R.id.time);
        //일정내용 텍스트 표시하는 view 객체 생성
//        TextView scheduleText = (TextView) v.findViewById(R.id.schedule);

        //객체값들을 리스트에 저장
        timeText.setText(timelineList.get(i).getTime());
//        scheduleText.setText(timelineList.get(i).getSchedule());

        //v의 태그(고유 이름) 설정
        v.setTag(timelineList.get(i).getTime());
        return v;

    }
}
