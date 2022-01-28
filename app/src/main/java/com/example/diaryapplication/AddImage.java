package com.example.diaryapplication;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.view.View;
import android.widget.ImageView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.google.android.gms.tasks.OnFailureListener;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;
import com.google.firebase.storage.UploadTask;

import java.io.InputStream;

public class AddImage extends AppCompatActivity {

    private final int GALLERY_CODE = 10; //휴대폰 갤러리에 접근하기 위해 선언한 코드 번호
    private FirebaseStorage storage; //파이어베이스 스토리지에 접근하기 위해 사용

    ImageView photo;
    private int i = 1;

    public int getI() {
        return i;
    }

    public int getGALLERY_CODE() {
        return GALLERY_CODE;
    }

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_image);

//        findViewById(R.id.Img).setOnClickListener(onClickListener);
//        photo=(ImageView)findViewById(R.id.Img);
//        storage=FirebaseStorage.getInstance();
    }

    View.OnClickListener onClickListener = new View.OnClickListener() {//이미지뷰 클릭시 loadAlbum 실행
        @Override
        public void onClick(View v) {
//            if(v.getId() == R.id.Img)
//                loadAlbum();
        }
    };

    private void loadAlbum() { //휴대폰 갤러리 실행할 수 있게 하는 함수
        Intent intent = new Intent(Intent.ACTION_PICK);
        intent.setType(MediaStore.Images.Media.CONTENT_TYPE);
        startActivityForResult(intent, GALLERY_CODE);
    }

    @Override
    protected void onActivityResult(int requestCode, final int resultCode, final Intent data) {
        //갤러리에서 사용자가 선택한 사진을 파이어 스토어에 올라가도록 하는 함수
        super.onActivityResult(requestCode, resultCode, data);
        if(requestCode == GALLERY_CODE) {
            Uri file = data.getData();
            StorageReference storageRef = storage.getReference();
            StorageReference riversRef = storageRef.child("photo/" + i + ".png");
            i++;
            UploadTask uploadTask = riversRef.putFile(file);

            try {
                InputStream in = getContentResolver().openInputStream(data.getData());
                Bitmap img = BitmapFactory.decodeStream(in);
                in.close();
                photo.setImageBitmap(img);
            }
            catch(Exception e) {
                e.printStackTrace();
            }

//            uploadTask.addOnFailureListener(new OnFailureListener(){
//                @Override
//                public void onFailure (@NonNull Exception exeption){
//                    Toast.makeText(ImgActivity.this, "사진이 정상적으로 업로드 되지 않았습니다.", Toast.LENGTH_SHORT).show();
//                }
//            }).addOnSuccessListener(new OnSuccesListener<UploadTask.TaskSnapshot>(){
//               @Override
//               public void onSuccess(UploadTask.TaskSnapshot taskSnapshot) {
//                   Toast.makeText(AddImage.this, "사진이 정상적으로 업로드 되었습니다.", Toast.LENGTH_SHORT).show();
//               }
//            });
        }
    }
}
