package com.example.timer;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

//public class Txt_scr_activ extends AppCompatActivity {
public class Txt_scr_activ extends AppCompatActivity {
    //public Context context = this;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.txt_resource);

        Button btn = (Button) findViewById(R.id.back_home);
        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String toastMessage = "переходим на другую страницу";
                Toast.makeText(Txt_scr_activ.this, toastMessage, Toast.LENGTH_SHORT).show();
                onBackPressed();

            }
        });
    }
}
