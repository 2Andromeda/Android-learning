package com.proto.proj11_2;

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
        setTitle("갤러리와 스피너 연습");

        Button btnpr11_2 = (Button) findViewById(R.id.proj11_2);
        Button btnse11_3 = (Button) findViewById(R.id.self11_3);

        btnpr11_2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getApplicationContext(), proj11_2.class);
                startActivity(intent);
            }
        });

        btnse11_3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getApplicationContext(), self11_3.class);
                startActivity(intent);
            }
        });


    }
}