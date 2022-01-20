package com.example.diaryapplication;

import android.content.Context;
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
    private ArrayList<TimelineData> timelineList = null;
    
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
            view = inflater.inflate(R.layout.activity_edit_timeline, viewGroup, false);
        }

        //타임라인에 해당하는 view 생성
        View v = View.inflate(context, R.layout.activity_timeline, null);
        //시간 텍스트 표시하는 view 객체 생성
        TextView timeText = (TextView) view.findViewById(R.id.time_txt);
        //일정내용 텍스트 표시하는 view 객체 생성
        TextView scheduleText = (TextView) view.findViewById(R.id.schedule);
        //일정 삭제 버튼
        Button btn_delete = (Button)view.findViewById(R.id.btn_delete);


        //가져온 데이터를 텍스트 뷰에 저장
        timeText.setText(timelineList.get(i).getTime());
        scheduleText.setText(timelineList.get(i).getSchedule());

        //리스트 아이템 삭제
        btn_delete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                timelineList.remove(i);
                notifyDataSetChanged();
            }
        });
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
