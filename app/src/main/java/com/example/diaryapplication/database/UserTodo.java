package com.example.diaryapplication.database;

public class UserTodo {

//    private int todoID;
    private String date;
    private String content;
    private boolean completed;

//    public int getTodoID() {
//        return todoID;
//    }
//
//    public void setTodoID(int todoID) {
//        this.todoID = todoID;
//    }


    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public boolean isCompleted() {
        return completed;
    }

    public void setCompleted(boolean completed) {
        this.completed = completed;
    }
}
