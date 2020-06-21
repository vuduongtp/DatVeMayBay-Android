package com.vuvanduong.datvemaybay.message;

import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.media.RingtoneManager;
import android.net.Uri;
import android.util.Log;

import androidx.core.app.NotificationCompat;

import com.google.firebase.messaging.FirebaseMessagingService;
import com.google.firebase.messaging.RemoteMessage;
import com.vuvanduong.datvemaybay.R;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;

public class MyFirebaseMessagingService extends FirebaseMessagingService {

    private static final String TAG = "MyFirebaseMsgService";
    ThongBaoDB thongBaoDB;

    @Override
    public void onMessageReceived(RemoteMessage remoteMessage) {
        thongBaoDB = new ThongBaoDB(this);
        //Displaying data in log
        //It is optional
        Log.d(TAG, "From: " + remoteMessage.getFrom());
        Log.d(TAG, "Notification Message Body: " + remoteMessage.getNotification().getBody());

        //Calling method to generate notification
        sendNotification(remoteMessage.getNotification().getBody(),remoteMessage.getNotification().getTitle());
    }

    //This method is only generating push notification
    //It is same as we did in earlier posts
    private void sendNotification(String messageBody,String messageTitle) {
        Intent intent = new Intent(getApplicationContext(), XemActivity.class);
        intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_SINGLE_TOP);
        PendingIntent pendingIntent = PendingIntent.getActivity(getApplicationContext(), 0, intent, 0);

        ThongBao thongBao = new ThongBao();
        thongBao.setId(String.valueOf(System.currentTimeMillis()).substring(0,13));
        thongBao.setTieuDe(messageTitle);
        thongBao.setNoiDung(messageBody);
        thongBao.setNgayBatDau(getStringDateTime());
        //thongBaoDB.xoa();
        thongBaoDB.them(thongBao);

        Uri defaultSoundUri= RingtoneManager.getDefaultUri(RingtoneManager.TYPE_NOTIFICATION);
        NotificationCompat.Builder notificationBuilder = new NotificationCompat.Builder(this)
                .setSmallIcon(R.drawable.airtransport)
                .setContentTitle(messageTitle)
                .setContentText(messageBody)
                .setAutoCancel(true)
                .setSound(defaultSoundUri)
                .setContentIntent(pendingIntent);

        NotificationManager notificationManager =
                (NotificationManager) getSystemService(Context.NOTIFICATION_SERVICE);

        notificationManager.notify(0, notificationBuilder.build());
    }

    public static String getStringDateTime(){
        String result="";
        Date currentTime = Calendar.getInstance().getTime();
        SimpleDateFormat formatterNew = new SimpleDateFormat("dd-MM-yyyy | HH:mm", Locale.getDefault());
        result = formatterNew.format(currentTime);
        return result;
    }
}
