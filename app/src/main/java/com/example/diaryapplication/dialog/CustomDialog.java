package com.example.diaryapplication.dialog;

import android.app.Dialog;
import android.content.Context;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.view.View;
import android.view.Window;
import android.widget.Button;
import android.widget.TextView;

import com.example.diaryapplication.R;

public class CustomDialog {

    private Context context;

    public CustomDialog(Context context){
        this.context = context;
    }

    public void callFunction(String message) {

        final Dialog dig = new Dialog(context);

        dig.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
        dig.requestWindowFeature(Window.FEATURE_NO_TITLE);
        dig.setContentView(R.layout.activity_custom_dialog);

        dig.show();

        final TextView text = (TextView) dig.findViewById(R.id.title);
        final Button okButton = (Button) dig.findViewById(R.id.okButton);
        final Button cancelButton = (Button) dig.findViewById(R.id.cancelButton);

        text.setText(message);

        //확인 버튼 이벤트 처리
        okButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                dig.dismiss();
                android.os.Process.killProcess(android.os.Process.myPid());
            }
        });
        //취소 버튼 이벤트 처리
        cancelButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                dig.dismiss();
            }
        });
    }
}
