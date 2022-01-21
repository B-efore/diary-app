package com.example.diaryapplication;

<<<<<<< HEAD
import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;
=======
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;

import android.content.Intent;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
>>>>>>> 78cfe5f92c1cb2a70fceaaa49682205f71c1e959

import com.example.diaryapplication.database.UserDB;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class LoginActivity extends AppCompatActivity {

    private FirebaseAuth mAuth;
    private DatabaseReference mDatabase;

    private Button registerBtn;
    private EditText userName, userMessage;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

//        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
//        ActionBar actionBar = getSupportActionBar();
//        actionBar.hide();

        //파이어베이스 데이터베이스 사용
        mDatabase = FirebaseDatabase.getInstance().getReference();
        mAuth = FirebaseAuth.getInstance();

        registerBtn = findViewById(R.id.registerBtn);
        userName = findViewById(R.id.userName);
        userMessage = findViewById(R.id.userMessage);

        registerBtn.setEnabled(false);

        userName.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {
            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
            }

            @Override
            public void afterTextChanged(Editable editable) {
                if (userName.length() > 0 && userMessage.length() > 0) {
                    registerBtn.setEnabled(true);
                    registerBtn.setTextColor(ContextCompat.getColor(getApplicationContext(), R.color.white));
                    registerBtn.setBackground(ContextCompat.getDrawable(getApplicationContext(), R.drawable.round_button_ok));
                } else {
                    registerBtn.setEnabled(false);
                    registerBtn.setTextColor(ContextCompat.getColor(getApplicationContext(), R.color.deep_gray));
                    registerBtn.setBackground(ContextCompat.getDrawable(getApplicationContext(), R.drawable.round_button));
                }
            }
        });

        userMessage.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
            }

            @Override
            public void afterTextChanged(Editable editable) {
                if (userName.length() > 0 && userMessage.length() > 0) {
                    registerBtn.setEnabled(true);
                    registerBtn.setTextColor(ContextCompat.getColor(getApplicationContext(), R.color.white));
                    registerBtn.setBackground(ContextCompat.getDrawable(getApplicationContext(), R.drawable.round_button_ok));
                } else {
                    registerBtn.setEnabled(false);
                    registerBtn.setTextColor(ContextCompat.getColor(getApplicationContext(), R.color.deep_gray));
                    registerBtn.setBackground(ContextCompat.getDrawable(getApplicationContext(), R.drawable.round_button));
                }
            }
        });

        //확인 버튼, 유저 DB 업데이트!
        registerBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                String strName = userName.getText().toString();
                String strMessage = userMessage.getText().toString();

                FirebaseUser firebaseUser = mAuth.getCurrentUser();
                UserDB mUser = new UserDB();

                mUser.set_id(firebaseUser.getUid());
                mUser.setName(strName);
                mUser.setMessage(strMessage);

                mDatabase.child("users").child(firebaseUser.getUid()).child("info").setValue(mUser);

                Intent registerIntent = new Intent(LoginActivity.this, TabActivity.class);
                startActivity(registerIntent);
                finish();
            }
        });
    }

    // DB에 유저정보 업데이트
//    public void writeNewUser(String userId, String name, String message) {
//        UserDB user = new UserDB(name, message);
//
//        mDatabase.child("users").child(userId).setValue(user);
//    }

}