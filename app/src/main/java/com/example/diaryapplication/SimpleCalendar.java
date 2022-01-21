package com.example.diaryapplication;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.example.diaryapplication.database.UserTodo;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class SimpleCalendar extends AppCompatActivity  {

    private FirebaseUser firebaseUser;
    private FirebaseAuth mAuth;
    private DatabaseReference mDatabase;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_simple_calendar);

        firebaseUser = mAuth.getCurrentUser();

        mAuth = FirebaseAuth.getInstance();
        mDatabase = FirebaseDatabase.getInstance().getReference();

//        Button editBtn = findViewById(R.id.editBtn);

    }

    //버튼 아이디 설정하기
//    @Override
//    public void onClick(View view) {
//
//        switch (view.getId()) {
//            case 체크리스트 수정 버튼 :
//                startActivity(new Intent(SimpleCalendar.this, EditCheckListActivity.class));
//                break ;
//            case 체크리스트 삭제 버튼 :
//                CustomDialog customDialog1 = new CustomDialog(SimpleCalendar.this);
//                customDialog1.callFunction("삭제하시겠습니까?");
//
//                mDatabase.child("users").child(firebaseUser.getUid()).child("1todo").child().setValue(null);
//                break ;
//            case 일정 수정 버튼 :
//                startActivity(new Intent(SimpleCalendar.this, EditScheduleActivity.class));
//                break ;
//            case 일정 삭제 버튼 :
//                CustomDialog customDialog2 = new CustomDialog(SimpleCalendar.this);
//                customDialog2.callFunction("삭제하시겠습니까?");
//
//                mDatabase.child("users").child(firebaseUser.getUid()).child("memo").child().setValue(null);
//                break;
//        }
//    }
}