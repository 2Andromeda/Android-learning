package com.example.ch13;

import android.Manifest;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.os.Environment;
import android.os.Handler;
import android.os.Message;
import android.os.SystemClock;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.ProgressBar;
import android.widget.SeekBar;
import android.widget.Switch;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;

import java.io.File;
import java.io.IOException;
import java.sql.Array;
import java.text.SimpleDateFormat;
import java.util.ArrayList;

public class ch13_1 extends AppCompatActivity {

    ListView listViewMP3;
    Button btnPlay, btnStop, btnPause;
    TextView tvMP3, tvTime;
    ProgressBar pbMP3;
    SeekBar pbHandler;

    ArrayList<String> mp3List;
    String selectedMP3;

    String mp3Path = Environment.getExternalStorageDirectory().getPath() + "/";
    MediaPlayer mPlayer;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.ch13_1);
        setTitle("ch13_1");

        final MediaPlayer mPlayer2;
        mPlayer2 = MediaPlayer.create(this, R.raw.song1);

        final Switch switch1 = (Switch) findViewById(R.id.switch1);
        switch1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (switch1.isChecked() == true){
                    mPlayer2.start();
                }
                else
                    mPlayer2.pause();
            }
        });

        mp3List = new ArrayList<String>();

        File[] listFiles = new File(mp3Path).listFiles();
        String fileName, extName;
        for(File file : listFiles){
            fileName = file.getName();
            extName = fileName.substring(fileName.length() - 3);
            if(extName.equals((String) "mp3"))
                mp3List.add(fileName);
        }

        listViewMP3 = (ListView) findViewById(R.id.lvMP3);
        ArrayAdapter<String> adapter = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_single_choice, mp3List);
        listViewMP3.setChoiceMode(ListView.CHOICE_MODE_SINGLE);
        listViewMP3.setAdapter(adapter);
        listViewMP3.setItemChecked(0, true);

        listViewMP3.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                selectedMP3 = mp3List.get(i);
            }
        });
        selectedMP3 = mp3List.get(0); //기본적으로는 첫번째 mp3 파일을 재생, 만약 선택이 되면, 위의 setonItem에서 고름

        btnPlay = (Button) findViewById(R.id.btnPlay);
        btnStop = (Button) findViewById(R.id.btnStop);
        btnPause = (Button) findViewById(R.id.btnPause);
        tvMP3 = (TextView) findViewById(R.id.tvMP3);
        pbMP3 = (ProgressBar) findViewById(R.id.pbMP3);
        tvTime = (TextView) findViewById(R.id.tvTime);
        pbHandler = (SeekBar) findViewById(R.id.pbHandler);

        btnPlay.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                try{// 시작 할때는 준비되지 않은채 시작되면 꺼지기 때문에 try and catch로 작성
                    mPlayer = new MediaPlayer();
                    mPlayer.setDataSource(mp3Path + selectedMP3);
                    String start = btnPlay.getText().toString();
                    if(start.equals("재생")){
                        mPlayer.prepare(); // 준비
                    }
                    mPlayer.start(); // 시작
                    btnPlay.setClickable(false);
                    btnPause.setClickable(true);
                    btnStop.setClickable(true);
                    tvMP3.setText("실행중인 음악 : " + selectedMP3);
                    pbMP3.setVisibility(View.VISIBLE);
                    new Thread(){
                        SimpleDateFormat timeFormat = new SimpleDateFormat("mm:ss");
                        public void run(){
                            if(mPlayer == null) return;
                            pbMP3.setMax(mPlayer.getDuration());
                            while(mPlayer.isPlaying()){
                                runOnUiThread(new Runnable() {
                                    @Override
                                    public void run() {
                                        pbMP3.setProgress(mPlayer.getCurrentPosition());
                                        tvTime.setText("진행 시간 : " + timeFormat.format(mPlayer.getCurrentPosition()));
                                    }
                                });
                                SystemClock.sleep(200);
                            }
                        }
                    }.start();

                    Handler mp3Handler = new Handler(){
                        public void handleMessage(Message msg){
                            pbHandler.setMax(mPlayer.getDuration());
                            pbHandler.setProgress(mPlayer.getCurrentPosition());
                            this.sendEmptyMessageDelayed(0, 200);
                        }
                    };

                    mp3Handler.sendEmptyMessage(0);

                }catch (IOException e){

                }
            }
        });

        btnPause.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){
                mPlayer.pause();
                btnPlay.setText("이어 듣기");
                btnPlay.setClickable(true);
                btnPause.setClickable(false);
                btnStop.setClickable(true);
                pbMP3.setVisibility(View.INVISIBLE);
            }
        });

        btnStop.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mPlayer.stop();//정지
                mPlayer.reset();//리셋
                btnPlay.setText("재생");
                btnPlay.setClickable(true);
                btnPause.setClickable(false);
                btnStop.setClickable(false);
                tvMP3.setText("실행중인 음악 : ");
                tvTime.setText("진행시간 : ");
                pbMP3.setProgress(0);
                pbMP3.setVisibility(View.INVISIBLE);
            }
        });
        btnPause.setClickable(false);
        btnStop.setClickable(false);



    }
}
