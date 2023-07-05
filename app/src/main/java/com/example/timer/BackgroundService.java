package com.example.timer;

import android.app.Service;
import android.content.Context;
import android.content.Intent;
import android.os.Handler;
import android.os.IBinder;
import android.widget.Toast;

public class BackgroundService extends Service {
    public Context context = this;
    public Handler handler = null;
    public static Runnable runnable = null;

    @Override
    public IBinder onBind(Intent intent) {
        return null;
    }

    @Override
    public void onCreate() {
      //  Toast.makeText(this, "Сервис создан!", Toast.LENGTH_LONG).show();

        handler = new Handler();
        runnable = new Runnable() {
            public void run() {
            //    Toast.makeText(context, "Сервис все еще работает", Toast.LENGTH_LONG).show();
                handler.postDelayed(runnable, 10000);
            }
        };

        handler.postDelayed(runnable, 15000);
    }

    @Override
    public void onDestroy() {
        /* IF YOU WANT THIS SERVICE KILLED WITH THE APP THEN UNCOMMENT THE FOLLOWING LINE */
        //handler.removeCallbacks(runnable);
      //  Toast.makeText(this, "Сервис остановлен", Toast.LENGTH_LONG).show();
    }

    @Override
    //public void onStart(Intent intent, int startid) {
    public void onStart(Intent intent, int startid) {
        //Toast.makeText(this, "Сервис запущен пользователем.", Toast.LENGTH_LONG).show();
    }
}