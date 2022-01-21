package com.example.diaryapplication.database;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;

import java.util.List;

//사용할지 안할지 모르겠음
@Dao
public interface UserTodoDao {

    @Query("SELECT * FROM UserTodo ORDER BY todoID DESC")
    List<UserTodo> select();

    @Query("INSERT INTO UserTodo (content) VALUES(:content)")
    void insert(String content);

    @Insert
    void insert(UserTodo todo);

    @Query("DELETE FROM UserTodo WHERE todoID = :id")
    void delete(int id);

    @Delete
    void delete(UserTodo todo);

    @Query("UPDATE UserTodo SET completed = 1 WHERE todoID = :id")
    void complete(int id);
}
