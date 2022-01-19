package com.example.diaryapplication;

import static android.content.Intent.FLAG_ACTIVITY_NEW_TASK;
import static android.view.View.INVISIBLE;
import static androidx.constraintlayout.helper.widget.MotionEffect.TAG;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;

import android.app.Dialog;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.widget.ListView;

import java.util.ArrayList;
import java.util.List;
import android.util.Log;
import android.view.View;
import android.view.Window;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.auth.api.signin.GoogleSignIn;
import com.google.android.gms.auth.api.signin.GoogleSignInAccount;
import com.google.android.gms.auth.api.signin.GoogleSignInClient;
import com.google.android.gms.auth.api.signin.GoogleSignInOptions;
import com.google.android.gms.common.SignInButton;
import com.google.android.gms.common.api.ApiException;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthCredential;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.auth.GoogleAuthProvider;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class MainActivity extends AppCompatActivity implements View.OnClickListener{

    private static final int RC_SIGN_IN = 9001;

    private GoogleSignInClient mGoogleSignInClient;
    private FirebaseAuth mAuth;
    private DatabaseReference mDatabase;

    private Button registerBtn;
    private EditText userName, userMessage;

    Dialog login_dialog;
    
    //타임라인 리스트뷰 변수 선언
    private ListView timelineListView;
    //타임라인 어뎁터 선언
    private TimelineAdapter timelineAdapter;
    //타임라인 데이터를 담는 리스트 선언
    private List<TimelineData> timelineList;

    SignInButton signBtn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
  
//        //타임라인 리스트뷰 초기화 (아이디 이름은 수정예정)
//        timelineListView = (ListView) findViewById(R.id.timelineListView);
//        //타임라인 리스트 초기화
//        timelineList = new ArrayList<TimelineData>();
//        //어뎁터에 타임라인 리스트 내용 넣어주기
//        timelineAdapter = new TimelineAdapter(getApplicationContext().timelineList);
//        //타임라인 리스트뷰에 어뎁터 연결
//        timelineListView.setAdapter(timelineAdapter);
//
        signBtn = findViewById(R.id.sign_in_button);
        signBtn.setOnClickListener(this);

        //사용자 데이터 요청을 위해 로그인에 필요한 옵션을 설정, 빨간색 뜨는거 무시해도 됨
        GoogleSignInOptions gso = new GoogleSignInOptions.Builder(GoogleSignInOptions.DEFAULT_SIGN_IN)
                .requestIdToken(getString(R.string.default_web_client_id))
                .requestEmail()
                .build();

        mGoogleSignInClient = GoogleSignIn.getClient(this, gso);
        mDatabase = FirebaseDatabase.getInstance().getReference();
        mAuth = FirebaseAuth.getInstance();

        Log.d("Test", "리턴값");
    }

    @Override
    public void onClick(View view)
    {
        switch (view.getId()){
            case R.id.sign_in_button:
                signIn();
                break;
        }
    }

    @Override
    public void onStart() {
        super.onStart();
        //유저가 로그인 된 상태인지 확인
        FirebaseUser currentUser = mAuth.getCurrentUser();
        updateUI(currentUser);
    }

    //로그인 상태에 따라 UI 변경
    private void updateUI(FirebaseUser user){
        if (user==null){
//            Toast.makeText(this.getApplicationContext(), "로그인 실패...", Toast.LENGTH_SHORT).show();
            signBtn.setVisibility(View.VISIBLE);

        }
        else
        {
//            Toast.makeText(this.getApplicationContext(), "로그인 성공...", Toast.LENGTH_SHORT).show();
            signBtn.setVisibility(View.INVISIBLE);

            startActivity(new Intent(getApplicationContext(), TabActivity.class));
            finish();

//            mDatabase.child("users").child(user.getUid()).get();
//            Log.d("Test", "리턴값" + mDatabase.child("users").child(user.getUid()).get());
        }
    }

    private void signIn() {
        Intent signInIntent = mGoogleSignInClient.getSignInIntent();
        startActivityForResult(signInIntent, RC_SIGN_IN);
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (requestCode == RC_SIGN_IN) {
            Task<GoogleSignInAccount> task = GoogleSignIn.getSignedInAccountFromIntent(data);
            try {
                //구글 로그인 성공
                GoogleSignInAccount account = task.getResult(ApiException.class);
                Log.d(TAG, "firebaseAuthWithGoogle:" + account.getId());
                firebaseAuthWithGoogle(account.getIdToken());
            } catch (ApiException e) {
                //구글 로그인 실패
                Log.w(TAG, "Google sign in failed", e);
            }
        }
    }

    //구글 로그인 인증
    private void firebaseAuthWithGoogle(String idToken) {
        AuthCredential credential = GoogleAuthProvider.getCredential(idToken, null);
        mAuth.signInWithCredential(credential)
                .addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if (task.isSuccessful()) {
                            //로그인 성공 시, 유저 정보와 함께 UI 업데이트
                            Log.d(TAG, "signInWithCredential:success");

//                            FirebaseUser user = mAuth.getCurrentUser();
//                            updateUI(user);

                            Intent registerIntent = new Intent(MainActivity.this, LoginActivity.class);
                            startActivity(registerIntent);
                            finish();

                        } else {
                            //로그인 실패
                            Log.w(TAG, "signInWithCredential:failure", task.getException());
                            updateUI(null);
                        }
                    }
                });
    }
}