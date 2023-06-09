package com.example.timer;

import static android.app.job.JobInfo.PRIORITY_HIGH; //TestNotification

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.NotificationCompat; //TestNotification

import android.app.NotificationChannel; //TestNotification
import android.app.PendingIntent; //TestNotification
import android.content.Context; //TestNotification //SharedPreferences
import android.content.Intent; //TestNotification

import android.os.Build; //TestNotification //SharedPreferences
import android.app.NotificationManager; //TestNotification

import java.text.SimpleDateFormat; //Timer
import java.util.Calendar; //Timer
import java.util.Timer; //Timer
import java.util.TimerTask; //Timer

import android.os.Bundle; //Timer
import android.util.Log;
import android.view.View; //Timer //SharedPreferences
import android.view.View.OnClickListener; //Timer
import android.widget.Button; //Timer
import android.widget.CheckBox; //Timer
import android.widget.EditText; //Timer
import android.widget.TextView; //Timer //SharedPreferences
/*import android.app.Activity; //Timer

import android.content.SharedPreferences; //SharedPreferences
import android.support.v7.app.ActionBarActivity; //SharedPreferences*/

import android.widget.NumberPicker; //NumberPicker

import android.widget.Toast;//SharedPreferences всплывающее окно
import android.content.SharedPreferences;//SharedPreferences
public class MainActivity extends AppCompatActivity {
    private NotificationManager notificationManager; //TestNotification
    private static final int NOTIFY_ID = 1; //TestNotification
    private static final String CHANNEL_ID = "CHANNEL_ID"; //TestNotification
// ОБъявляем используемые объекты:
    CheckBox mCheck;
    Button mStart, mStop, btn_scr_txt;
    TextView mCount;
    Timer timer;
    TimerTask mTimerTask;

    private Button saveButton; //SharedPreferences
    private SharedPreferences sharedPreferences; //SharedPreferences
    private static final String SHARED_PREFS = "sharedPrefs"; //SharedPreferences
    private static final String TEXT = "text"; //SharedPreferences
    private static final String TEXT1 = "text1"; //SharedPreferences
    private static final String TEXT2 = "text2"; //SharedPreferences

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        startService(new Intent(this, BackgroundService.class));

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        TextView mtextView = findViewById(R.id.editTextTextMultiLine);//NumberPicker
        TextView mtextView2 = findViewById(R.id.textView2);//NumberPicker
        TextView mtextView3 = findViewById(R.id.textView3);//NumberPicker
        //SharedPreferences
        saveButton = findViewById(R.id.saveButton);

        sharedPreferences = getSharedPreferences(SHARED_PREFS, MODE_PRIVATE);

        // чтение сохраненного текста при запуске программы
        String savedText = sharedPreferences.getString(TEXT, "");
        String savedText1 = sharedPreferences.getString(TEXT1, "");
        String savedText2 = sharedPreferences.getString(TEXT2, "");
        mtextView.setText(savedText);
        mtextView2.setText(savedText1);
        mtextView3.setText(savedText2);

        saveButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // сохранение текста при нажатии на кнопку
                String text = mtextView.getText().toString();
                String text1 = mtextView2.getText().toString();
                String text2 = mtextView3.getText().toString();

                SharedPreferences.Editor editor = sharedPreferences.edit();
                editor.putString(TEXT, text)
                        .putString(TEXT1, text1)
                        .putString(TEXT2, text2)
                        .apply();

                String toastMessage = "Настройки сохранены";
                Toast.makeText(MainActivity.this, toastMessage, Toast.LENGTH_SHORT).show();
            }
          });


        //SharedPreferences


        NumberPicker numberPicker = findViewById(R.id.numberPicker);//NumberPicker
        //NumberPicker numberPicker = findViewById(R.id.numberPicker);
        numberPicker.setMaxValue(23);//NumberPicker
        numberPicker.setMinValue(0);//NumberPicker
        NumberPicker numberPicker2 = findViewById(R.id.numberPicker2);//NumberPicker
        numberPicker2.setMaxValue(59);//NumberPicker
        numberPicker2.setMinValue(0);//NumberPicker



       // numberPicker.descendantFocusability = NumberPicker.FOCUS_BLOCK_DESCENDANTS // блокируем появление клавиатуры

// Связываемся с элементами пользовательского интерфейса:
        mCheck = (CheckBox)findViewById(R.id.single_shot);
        mStart = (Button)findViewById(R.id.start);
        mStop = (Button)findViewById(R.id.stop);
        mCount = (TextView)findViewById(R.id.count);
        btn_scr_txt = (Button)findViewById(R.id.btn_screen_txt);
        notificationManager = (NotificationManager) getApplicationContext().getSystemService(Context.NOTIFICATION_SERVICE); //TestNotification
//NumberPicker
        numberPicker.setOnValueChangedListener(new NumberPicker.OnValueChangeListener() {
            @Override
            public void onValueChange(NumberPicker picker, int oldVal, int newVal) {
                mtextView2.setText(String.valueOf(newVal));
            }
        });
        numberPicker2.setOnValueChangedListener(new NumberPicker.OnValueChangeListener() {
            @Override
            public void onValueChange(NumberPicker picker, int oldVal, int newVal) {
                mtextView3.setText(String.valueOf(newVal));
            }
        });
//NumberPicker


      //  btn_scr_txt.setOnClickListener(new OnClick_Scr_txt(){
        //Button btn_scr_txt = (Button) findViewById(R.id.btn_screen_txt);
        btn_scr_txt = (Button) findViewById(R.id.btn_screen_txt);
        btn_scr_txt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String toastMessage = "переходим на другую страницу";
                Toast.makeText(MainActivity.this, toastMessage, Toast.LENGTH_SHORT).show();
                setContentView(R.layout.txt_resource);

            }
        });


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
                int number1 = Integer.parseInt(mtextView2.getText().toString()); //number часы из т
                int number2 = Integer.parseInt(mtextView3.getText().toString()); //number минуты
                if(mCheck.isChecked()){

// Выполняем действие с задержкой 1 секунда:
//                    timer.schedule(mTimerTask, 1000);
// Выполняем переход с задержкой 5 секунда:
                    timer.schedule(mTimerTask, 5000);


                }else{
// После задержки одна секунда, повторяем действие таймера каждую секунду:
                 //   timer.schedule(mTimerTask, 1000, 1000);
                    timer.schedule(mTimerTask, 1000, (number1*3600+number2*60)*1000); //number
                  //  startService(new Intent(this, BackgroundService.class));
                }
            }
        });

      //  Intent intent = new Intent(this, MyService.class);
        // Кнопка "Остановить" отменяет действие таймера:
        mStop.setOnClickListener(new OnClickListener(){
            @Override
            public void onClick(View v) {
                if (timer!=null){
                    timer.cancel();
                    timer = null;
                    //stopService(intent);

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
            EditText editText = findViewById(R.id.editTextTextMultiLine);
            String text = editText.getText().toString();
// Преобразуем информацию в строковые данные:
            final String strDate = simpleDateFormat.format(calendar.getTime());
            runOnUiThread(new Runnable(){
// Отображаем информацию в текстовом поле count:
                @Override
                public void run() {
                    mCount.setText(strDate);
                   // mCount.setText(editText);
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
                    //                .setContentText(strDate)
                                    .setContentText(text)
                                    .setPriority(PRIORITY_HIGH);
                    createChannelIfNeeded(notificationManager);
                    notificationManager.notify(NOTIFY_ID, notificationBuilder.build()); //*//TestNotification
                }

            });
        }
    }
}