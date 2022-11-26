package com.example.ch13;

import android.os.Bundle;
import android.os.SystemClock;
import android.view.View;
import android.widget.Button;
import android.widget.ProgressBar;
import android.widget.SeekBar;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

public class ch13_2 extends AppCompatActivity {

    ProgressBar pb2_1, pb2_2;
    Button btn1;
    TextView tv1, tv2;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.ch13_2);
        setTitle("ch13_2 스레드");

        final ProgressBar pb1;
        Button btnInc, btnDec;

        pb1 = (ProgressBar) findViewById(R.id.progressBar1);
        btnInc = (Button) findViewById(R.id.btnInc);
        btnDec = (Button) findViewById(R.id.btnDec);

        btnInc.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                pb1.incrementProgressBy(10);
            }
        });

        btnDec.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                pb1.incrementProgressBy(-10);
            }
        });

        final TextView tvSeek = (TextView) findViewById(R.id.tvSeek);
        SeekBar seekBar1 = (SeekBar) findViewById(R.id.seekBar1);

        seekBar1.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {

            @Override
            public void onProgressChanged(SeekBar seekBar, int i, boolean b) {
                tvSeek.setText("진행률 : " + i + " %");
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {

            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {

            }
        });

        pb2_1 = (ProgressBar) findViewById(R.id.pb2_1);
        pb2_2 = (ProgressBar) findViewById(R.id.pb2_2);
        btn1 = (Button) findViewById(R.id.button1);
        tv1 = (TextView) findViewById(R.id.tv1);
        tv2 = (TextView) findViewById(R.id.tv2);

        btn1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                new Thread(){
                    public void run(){
                        for(int i=pb2_1.getProgress(); i<100; i=i+2){
                            runOnUiThread(new Runnable() {
                                @Override
                                public void run() {
                                    pb2_1.setProgress(pb2_1.getProgress() + 2);
                                    tv1.setText("1번 진행률 : " +pb2_1.getProgress() + "%");

                                }
                            });
                            SystemClock.sleep(100);
                        }
                    }
                }.start();

                new Thread(){
                    public void run(){
                        for(int i=pb2_2.getProgress(); i<100; i++){
                            runOnUiThread(new Runnable() {
                                @Override
                                public void run() {
                                    pb2_2.setProgress(pb2_2.getProgress() + 2);
                                    tv2.setText("2번 진행률 : " +pb2_2.getProgress() + "%");
                                }
                            });
                            SystemClock.sleep(100);
                        }
                    }
                }.start();
            }
        });

    }
}
