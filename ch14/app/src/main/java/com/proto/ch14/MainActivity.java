package com.proto.ch14;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class MainActivity extends AppCompatActivity {


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        setTitle("ch14 메인 화면");

        Button btn14_1 = (Button) findViewById(R.id.ch14_1);
        Button btn14_2 = (Button) findViewById(R.id.ch14_2);
        Button btn14_3 = (Button) findViewById(R.id.ch14_3);

        btn14_1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getApplicationContext(), ch14_1.class);
                startActivity(intent);
            }
        });

        btn14_2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getApplicationContext(), ch14_2.class);
                startActivity(intent);
            }
        });

        btn14_3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getApplication(), ch14_3.class);
                startActivity(intent);
            }
        });

    }
}