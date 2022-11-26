package com.example.ch13;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;

import android.Manifest;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        setTitle("ch13 메인 화면");
        ActivityCompat.requestPermissions(this, new String[]{Manifest.permission.WRITE_EXTERNAL_STORAGE}, MODE_PRIVATE);

        Button btn13_1 = (Button) findViewById(R.id.btnCh13_1);
        Button btn13_2 = (Button) findViewById(R.id.btnCh13_2);
        Button btn13_3 = (Button) findViewById(R.id.btnCh13_3);

        btn13_1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getApplicationContext(), ch13_1.class);
                startActivity(intent);
            }
        });

        btn13_2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getApplicationContext(), ch13_2.class);
                startActivity(intent);
            }
        });

        btn13_3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getApplicationContext(), ch13_3.class);
                startActivity(intent);
            }
        });


    }
}