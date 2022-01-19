package com.example.diaryapplication;

public class UserDB {

    private String _id;
    private String name;
    private String message;

    public UserDB(){}
    public UserDB(String name, String message){
        this.name = name;
        this.message = message;
    }

    public String get_id() {
        return _id;
    }

    public void set_id(String _id) {
        this._id = _id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
    
}
