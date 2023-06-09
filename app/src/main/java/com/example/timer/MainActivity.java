package com.example.timer;

import static android.app.job.JobInfo.PRIORITY_HIGH; //TestNotification

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.NotificationCompat; //TestNotification

import android.app.NotificationChannel; //TestNotification
import android.app.PendingIntent; //TestNotification
import android.content.Context; //TestNotification
import android.content.Intent; //TestNotification
import android.os.Build; //TestNotification
import android.app.NotificationManager; //TestNotification

import java.text.SimpleDateFormat; //Timer
import java.util.Calendar; //Timer
import java.util.Timer; //Timer
import java.util.TimerTask; //Timer

import android.content.Intent;
import android.os.Bundle; //Timer
import android.view.View; //Timer
import android.view.View.OnClickListener; //Timer
import android.widget.Button; //Timer
import android.widget.CheckBox; //Timer
import android.widget.TextView; //Timer
import android.app.Activity; //Timer

public class MainActivity extends AppCompatActivity {
    private NotificationManager notificationManager; //TestNotification
    private static final int NOTIFY_ID = 1; //TestNotification
    private static final String CHANNEL_ID = "CHANNEL_ID"; //TestNotification
// ОБъявляем используемые объекты:
    CheckBox mCheck;
    Button mStart, mStop;
    TextView mCount;
    Timer timer;
    TimerTask mTimerTask;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

// Связываемся с элементами пользовательского интерфейса:
        mCheck = (CheckBox)findViewById(R.id.single_shot);
        mStart = (Button)findViewById(R.id.start);
        mStop = (Button)findViewById(R.id.stop);
        mCount = (TextView)findViewById(R.id.count);
        notificationManager = (NotificationManager) getApplicationContext().getSystemService(Context.NOTIFICATION_SERVICE); //TestNotification
// Настраиваем слушателя нажатий по кнопке "Старт":
        mStart.setOnClickListener(new OnClickListener(){
            @Override
            public void onClick(View arg0) {
                if(timer != null){
                    timer.cancel();
                }
                // Задаем поведение таймера при включенном и выключенном переключателе,
// выполняем задачу MyTimerTask, описание которой будет ниже:
                timer = new Timer();
                mTimerTask = new MyTimerTask();
            /*    Intent intent = new Intent(getApplicationContext(), MainActivity.class); //*TestNotification
                intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK | Intent.FLAG_ACTIVITY_NEW_TASK);
                PendingIntent pendingIntent =
                        PendingIntent.getActivity(getApplicationContext(), 0, intent, PendingIntent.FLAG_UPDATE_CURRENT);
                NotificationCompat.Builder notificationBuilder =
                        new NotificationCompat.Builder(getApplicationContext(), CHANNEL_ID)
                                .setAutoCancel(false)
                                .setSmallIcon(R.drawable.ic_launcher_foreground)
                                .setWhen(System.currentTimeMillis())
                                .setContentIntent(pendingIntent)
                                .setContentTitle("text1")
                                .setContentText("text1")
                                .setPriority(PRIORITY_HIGH);
                createChannelIfNeeded(notificationManager);
                notificationManager.notify(NOTIFY_ID, notificationBuilder.build()); //*///TestNotification

                if(mCheck.isChecked()){

// Выполняем действие с задержкой 1 секунда:
//                    timer.schedule(mTimerTask, 1000);
// Выполняем переход с задержкой 5 секунда:
                    timer.schedule(mTimerTask, 5000);


                }else{
// После задержки одна секунда, повторяем действие таймера каждую секунду:
                    timer.schedule(mTimerTask, 5000, 5000);
                }
            }
        });

// Кнопка "Остановить" отменяет действие таймера:
        mStop.setOnClickListener(new OnClickListener(){
            @Override
            public void onClick(View v) {
                if (timer!=null){timer.cancel();timer = null;
                }
            }
        });
    }

    public static void createChannelIfNeeded(NotificationManager manager) { //*TestNotification
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            NotificationChannel notificationChannel = new NotificationChannel(CHANNEL_ID, CHANNEL_ID, NotificationManager.IMPORTANCE_DEFAULT);
            manager.createNotificationChannel(notificationChannel);
        }
    } // *//TestNotification

// Метод для описания того, что будет происходить при работе таймера (задача для таймера):
    class MyTimerTask extends TimerTask {
        @Override
        public void run() {
           /* Intent intent = new Intent(MainActivity.this,Second.class);
            startActivity(intent);*/

// Берем дату и время с системного календаря:
            Calendar calendar = Calendar.getInstance();
            SimpleDateFormat simpleDateFormat = new SimpleDateFormat("HH:mm:ss a");

// Преобразуем информацию в строковые данные:
            final String strDate = simpleDateFormat.format(calendar.getTime());
            runOnUiThread(new Runnable(){
// Отображаем информацию в текстовом поле count:
                @Override
                public void run() {
                    mCount.setText(strDate);
                    Intent intent = new Intent(getApplicationContext(), MainActivity.class); //*TestNotification
                    intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK | Intent.FLAG_ACTIVITY_NEW_TASK);
                    PendingIntent pendingIntent =
                            PendingIntent.getActivity(getApplicationContext(), 0, intent, PendingIntent.FLAG_UPDATE_CURRENT);
                    NotificationCompat.Builder notificationBuilder =
                            new NotificationCompat.Builder(getApplicationContext(), CHANNEL_ID)
                                    .setAutoCancel(false)
                                    .setSmallIcon(R.drawable.ic_launcher_foreground)
                                    .setWhen(System.currentTimeMillis())
                                    .setContentIntent(pendingIntent)
                                    .setContentTitle(strDate)
                                    .setContentText(strDate)
                                    .setPriority(PRIORITY_HIGH);
                    createChannelIfNeeded(notificationManager);
                    notificationManager.notify(NOTIFY_ID, notificationBuilder.build()); //*//TestNotification
                }

            });
        }
    }
}