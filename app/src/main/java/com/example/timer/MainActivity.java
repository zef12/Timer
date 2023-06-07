package com.example.timer;

import androidx.appcompat.app.AppCompatActivity;

import java.text.SimpleDateFormat; //Timer
import java.util.Calendar; //Timer
import java.util.Timer; //Timer
import java.util.TimerTask; //Timer
import android.os.Bundle; //Timer
import android.view.View; //Timer
import android.view.View.OnClickListener; //Timer
import android.widget.Button; //Timer
import android.widget.CheckBox; //Timer
import android.widget.TextView; //Timer
import android.app.Activity; //Timer



public class MainActivity extends AppCompatActivity {

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

                if(mCheck.isChecked()){

// Выполняем действие с задержкой 1 секунда:
                    timer.schedule(mTimerTask, 1000);

                }else{
// После задержки одна секунда, повторяем действие таймера каждую секунду:
                    timer.schedule(mTimerTask, 1000, 1000);
                }
            }});

// Кнопка "Остановить" отменяет действие таймера:
        mStop.setOnClickListener(new OnClickListener(){
            @Override
            public void onClick(View v) {
                if (timer!=null){timer.cancel();timer = null;
                }
            }
        });
    }
    // Метод для описания того, что будет происходить при работе таймера (задача для таймера):
    class MyTimerTask extends TimerTask {
        @Override
        public void run() {

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
                }});
        }
    }
}