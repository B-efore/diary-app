package com.example.diaryapplication;

import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.ContextWrapper;
import android.content.Intent;
import android.os.Build;

import androidx.core.app.NotificationCompat;

public class NotificationHelper extends ContextWrapper {

    private static final String CHANNEL_ID="channelID";
    private static final String CHANNEL_NAME="channelNAME";

    private NotificationManager notimanager;

    NotificationCompat.Builder builder = null;

    public NotificationHelper(Context base) {
        super(base);
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            notimanager.createNotificationChannel(new NotificationChannel(
                    CHANNEL_ID, CHANNEL_NAME, NotificationManager.IMPORTANCE_DEFAULT
            ));
            builder = new NotificationCompat.Builder(this, CHANNEL_ID);
        } else {
            builder = new NotificationCompat.Builder(this);
        }//안드로이드 버전에 따라 나눴음

        Intent intent= new Intent(this, MainActivity.class);
        PendingIntent pendingIntent=PendingIntent.getActivity(this,101,intent,PendingIntent.FLAG_UPDATE_CURRENT);

        builder.setAutoCancel(true);
        builder.setContentIntent(pendingIntent);//알림 클릭하면 MainActivity화면으로 이동
    }


    public NotificationManager getManager() {
        if (notimanager == null){
            notimanager = (NotificationManager) getSystemService(Context.NOTIFICATION_SERVICE);
        }

        return notimanager;
    }

    public NotificationCompat.Builder getChannelNotification(){
        return new NotificationCompat.Builder(getApplicationContext(),CHANNEL_ID)

                .setContentTitle("알림 제목")
        .setContentText("알림 내용")
       .setSmallIcon(android.R.drawable.ic_menu_view);
    }


}



