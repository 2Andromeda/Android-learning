package com.proto.ch10_1;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        setTitle("영화 선호도 투표");

        final int voteCount[] = new int[9];
        for (int i = 0; i < 9; i++)
            voteCount[i] = 0;

        ImageView image[] = new ImageView[9];
        final String imgName[] = {"독서하는 소녀", "꽃장식 모자 소녀",
                "부채를 든 소녀", "이레느깡 단 베르양", "잠자는 소녀", "테라스의 두 자매",
                "피아노 레슨", "피아노 앞의 소녀들", "해변에서"};

        for(int i = 0; i < 9; i++ ){
            final int index = i;
            final int j = i + 1;
            int imageId = getResources().getIdentifier("iv" + j, "id", getPackageName());
            image[index] = (ImageView) findViewById(imageId);
            image[index].setOnClickListener(new View.OnClickListener(){
                public void onClick(View v){
                    voteCount[index]++;
                    Toast.makeText(getApplicationContext(), imgName[index] + ": 총 " + voteCount[index] + " 표", Toast.LENGTH_SHORT).show();
                }
            });
        }

        Button btnNewWindow = (Button) findViewById(R.id.btnResult);
        btnNewWindow.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v){
                Intent intent = new Intent(getApplicationContext(), SecondActivity.class);
                intent.putExtra("VoteCount", voteCount);
                intent.putExtra("ImageName", imgName);
                startActivity(intent);
            }
        });
    }
}