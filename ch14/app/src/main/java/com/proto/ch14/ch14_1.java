package com.proto.ch14;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;

public class ch14_1 extends AppCompatActivity {
    Intent mintent;
    Button btnStart, btnStop;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.ch14_1);
        setTitle("ch14_1");

        mintent = new Intent(this, MusicService.class);
        btnStart = (Button) findViewById(R.id.btnStart);
        btnStop = (Button) findViewById(R.id.btnStop);

        btnStart.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startService(mintent);
                android.util.Log.i("서비스 테스트", "startService()");
                finish();
            }
        });

        btnStop.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                stopService(mintent);
                android.util.Log.i("서비스 테스트", "stopService()");
            }
        });

    }

}
