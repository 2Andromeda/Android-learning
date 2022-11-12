package com.proto.ch10_1;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.ViewFlipper;

import androidx.appcompat.app.AppCompatActivity;

import java.util.Timer;
import java.util.TimerTask;

public class prac10_6 extends AppCompatActivity {

    int order = 1;

    @Override
    protected void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        setContentView(R.layout.prac10_6);
        setTitle("투표 결과");

        Intent intent = getIntent();
        int[] voteResult = intent.getIntArrayExtra("VoteCount");
        String[] imageName = intent.getStringArrayExtra("ImageName");
        Integer imageFileId[] = new Integer[9];

        ImageView iv[] = new ImageView[9];
        Integer ratingOrder[] = new Integer[9]; // 평가순서

        final ViewFlipper vFlipper;

        ratingOrder[0] = 0;
        int maxId = -1;
        int maxV = -1;

        vFlipper = (ViewFlipper) findViewById(R.id.viewFlipper1);
        vFlipper.setFlipInterval(1000);

        for(int i = 0; i < 9; i++) {
            int j = i + 1;
            int ivId = getResources().getIdentifier("v" + j, "id", getPackageName());
            imageFileId[i] = getResources().getIdentifier("pic" + j, "drawable", getPackageName());
            iv[i] = (ImageView) findViewById(ivId);

            if (voteResult[i] > maxV) {
                maxId = i;
                maxV = voteResult[i];
            }

            Integer newRO[] = new Integer[j];
            newRO[0] = 0;
            int checked = 0;
            for (int k = 0; k < i; k++) {
                if (voteResult[i] <= voteResult[ratingOrder[k]]) {
                    newRO[k] = ratingOrder[k];
                } else if (voteResult[i] > voteResult[ratingOrder[k]] && checked == 0) {
                    newRO[k] = i;
                    newRO[k + 1] = ratingOrder[k];
                    checked = 1;
                } else {
                    newRO[k + 1] = ratingOrder[k];
                }

                if (k == (i - 1) && checked == 0) {
                    newRO[k + 1] = i;
                }
            }
            ratingOrder = newRO;
        }
        for(int i = 0; i < 9; i++) {
            iv[i].setImageResource(imageFileId[ratingOrder[i]]);
        }

        Button btnAutoStart = (Button) findViewById(R.id.btnAutoStart);
        Button btnAutoStop = (Button) findViewById(R.id.btnAutoStop);

        btnAutoStart.setOnClickListener(new View.OnClickListener(){
            public void onClick(View v){
                vFlipper.startFlipping();
            }
        });

        btnAutoStop.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                vFlipper.stopFlipping();
            }
        });
    }


}
