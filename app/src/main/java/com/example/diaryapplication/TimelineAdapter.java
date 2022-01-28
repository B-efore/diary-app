package com.example.diaryapplication;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

public class TimelineAdapter extends BaseAdapter {

    private Context context;
    //타임라인 내용을 저장할 리스트 선언
    public static ArrayList<TimelineData> timelineList = new ArrayList<TimelineData>();
    private int position;
    
    //생성자 선언
    public TimelineAdapter(Context context) {
        this.context = context;
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
        //activity_edit_timeline.xml 참조획득
        if(view == null) {
            LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            view = inflater.inflate(R.layout.activity_timeline_data,null);
        }


        TextView timeText;
        timeText = (TextView) view.findViewById(R.id.time_txt);


        TextView scheduleText;
        scheduleText = (TextView) view.findViewById(R.id.schedule_txt);
        //일정 삭제 버튼
        Button btn_delete;
        btn_delete = (Button)view.findViewById(R.id.btn_delete);

        btn_delete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                 switch (view.getId()) {
                case R.id.btn_delete:
                 timelineList.remove(i);
                notifyDataSetChanged();
                break;
                }
            }
        });

        //가져온 데이터를 텍스트 뷰에 저장
        TimelineData data = timelineList.get(i);

        timeText.setText(data.getTime());
        scheduleText.setText(data.getSchedule());

        return view;
    }

    //리스트에 데이터 추가
    public void addData(String time, String schedule) {
        TimelineData timelineData = new TimelineData();

        timelineData.setTime(time);
        timelineData.setSchedule(schedule);

        timelineList.add(timelineData);
    }

}
