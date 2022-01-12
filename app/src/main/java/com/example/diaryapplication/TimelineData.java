package com.example.diaryapplication;

//타임라인 리스트에 들어가는 내용 관리 클래스
public class TimelineData {

    //타임라인 시간 변수 선언
    String time;
    //타임라인 일정내용 변수 선언
    String schedule;
    
    //생성자 생성
    public TimelineData(String time, String schedule) {
        this.time = time;
        this.schedule = schedule;
    }

    //시간 얻기
    public String getTime() {
        return time;
    }

    //시간 초기화
    public void setTime(String time) {
        this.time = time;
    }

    //일정내용 얻기
    public String getSchedule() {
        return schedule;
    }

    //일정내용 초기화
    public void setSchedule(String schedule) {
        this.schedule = schedule;
    }


}
