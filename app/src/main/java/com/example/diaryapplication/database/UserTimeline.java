package com.example.diaryapplication.database;

public class UserTimeline {

    private String tID;
    private String tContent;
    private int tHour;
    private int tMin;

    public String gettID() {
        return tID;
    }

    public void settID(String tID) {
        this.tID = tID;
    }

    public String gettContent() {
        return tContent;
    }

    public void settContent(String tContent) {
        this.tContent = tContent;
    }

    public int gettHour() {
        return tHour;
    }

    public void settHour(int tHour) {
        this.tHour = tHour;
    }

    public int gettMin() {
        return tMin;
    }

    public void settMin(int tMin) {
        this.tMin = tMin;
    }
}
