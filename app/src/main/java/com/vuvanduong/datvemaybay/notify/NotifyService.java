package com.vuvanduong.datvemaybay.notify;

import android.app.Notification;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.app.Service;
import android.content.Context;
import android.content.Intent;
import android.os.IBinder;

import androidx.annotation.Nullable;
import androidx.core.app.NotificationCompat;

import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.vuvanduong.datvemaybay.R;

public class NotifyService  extends Service {

    private FirebaseDatabase database = FirebaseDatabase.getInstance();
    private DatabaseReference notify = database.getReference("notify");
    private ChildEventListener listener;
    private NotificationManager notifManager;

    @Override
    public void onCreate() {
        super.onCreate();

        notifManager=(NotificationManager) getSystemService(Context.NOTIFICATION_SERVICE);

        listener = notify.addChildEventListener(new ChildEventListener() {
            @Override
            public void onChildAdded(DataSnapshot dataSnapshot, String s) {
                // id = String.valueOf(dataSnapshot.child("id").getValue());
                String tieude = String.valueOf(dataSnapshot.child("tieuDe").getValue());
                String noidung = String.valueOf(dataSnapshot.child("noiDung").getValue());
                //System.out.println(id);
                //hien thong bao
                NotificationCompat.Builder builder = new NotificationCompat.Builder(getApplicationContext(), "123");
                Intent intent = new Intent(getApplicationContext(), XemActivity.class);
                intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_SINGLE_TOP);
                PendingIntent pendingIntent = PendingIntent.getActivity(getApplicationContext(), 0, intent, 0);
                builder.setContentTitle(tieude)  // required
                        .setSmallIcon(R.drawable.airtransport) // required
                        .setContentText(noidung)  // required
                        .setDefaults(Notification.DEFAULT_LIGHTS | Notification.DEFAULT_SOUND)
                        .setAutoCancel(true)
                        .setContentIntent(pendingIntent);
                Notification notification = builder.build();
                notifManager.notify(1111, notification);
            }
            @Override
            public void onChildChanged(DataSnapshot dataSnapshot, String s)
            {

            }
            @Override
            public void onChildRemoved(DataSnapshot dataSnapshot) {

            }

            @Override
            public void onChildMoved(DataSnapshot dataSnapshot, String s) {

            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });
    }

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        return super.onStartCommand(intent, flags, startId);
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        notify.removeEventListener(listener);
    }

    @Nullable
    @Override
    public IBinder onBind(Intent intent) {
        return null;
    }
}
