package com.example.diaryapplication;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.example.diaryapplication.database.UserDB;
import com.example.diaryapplication.database.UserTodo;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class EditCheckListActivity extends AppCompatActivity {

    private FirebaseAuth mAuth;
    private DatabaseReference mDatabase;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_check_list);

        //파이어베이스 데이터베이스 사용
        mDatabase = FirebaseDatabase.getInstance().getReference();
        mAuth = FirebaseAuth.getInstance();

        EditText content = findViewById(R.id.투두내용);
        Button editBtn = findViewById(R.id.editBtn);;


        //편집 버튼
        editBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                FirebaseUser firebaseUser = mAuth.getCurrentUser();

                //날짜 받아오기, 아이디 받아올 방법 생각
                int userDate;
                String userContent = content.getText().toString();

                UserTodo mUserTodo = new UserTodo();
//                mUserTodo.setTodoID();
//                mUserTodo.setDate(userDate);
                mUserTodo.setContent(userContent);
                mUserTodo.setCompleted(false);

                //DB에 저장
                mDatabase.child("users").child(firebaseUser.getUid()).child("todo").setValue(mUserTodo);

            }
        });
    }
}