package com.proto.ch10_1;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.os.SystemClock;
import android.view.View;
import android.widget.Button;
import android.widget.Chronometer;
import android.widget.ImageView;
import android.widget.RatingBar;
import android.widget.TextView;

import org.w3c.dom.Text;

import java.sql.Time;
import java.util.Timer;
import java.util.TimerTask;

public class SecondActivity extends Activity {

    int order = 1;

    @Override
    protected void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        setContentView(R.layout.second);
        setTitle("투표 결과");

        Intent intent = getIntent();
        int[] voteResult = intent.getIntArrayExtra("VoteCount");
        String[] imageName = intent.getStringArrayExtra("ImageName");
        Integer imageFileId[] = new Integer[9];

        TextView tv[] = new TextView[9];
        RatingBar rating[] = new RatingBar[9];
        Integer ratingOrder[] = new Integer[9];
        TextView resultText = (TextView) findViewById(R.id.resultText);
        ImageView resultImage = (ImageView) findViewById(R.id.resultImage);

        ratingOrder[0] = 0;
        int maxId = -1;
        int maxV = -1;

        for(int i = 0; i < 9; i++){
            int j = i + 1;
            int tvId = getResources().getIdentifier("tv" + j, "id", getPackageName());
            int ratingId = getResources().getIdentifier("rbar" + j, "id", getPackageName());
            imageFileId[i] = getResources().getIdentifier("pic" + j, "drawable", getPackageName());
            tv[i] = (TextView) findViewById(tvId);
            rating[i] = (RatingBar) findViewById(ratingId);

            tv[i].setText(imageName[i]);
            rating[i].setRating((float) voteResult[i]);

            if(voteResult[i] > maxV){
                maxId = i;
                maxV = voteResult[i];
            }

            Integer newRO[] = new Integer[j];
            newRO[0] = 0;
            int checked = 0;
            for(int k = 0; k < i; k++){
                if(voteResult[i] <= voteResult[ratingOrder[k]]){
                    newRO[k] = ratingOrder[k];
                }
                else if(voteResult[i] > voteResult[ratingOrder[k]] && checked == 0){
                    newRO[k] = i;
                    newRO[k+1] = ratingOrder[k];
                    checked = 1;
                }
                else{
                    newRO[k+1] = ratingOrder[k];
                }

                if(k == (i-1) && checked == 0){
                    newRO[k+1] = i;
                }
            }
            ratingOrder = newRO;

        }

        resultText.setText(imageName[maxId]);
        resultImage.setImageResource(imageFileId[maxId]);

        Button btnOriWindow = (Button) findViewById(R.id.btnOriWindow);
        btnOriWindow.setOnClickListener(new View.OnClickListener(){
            public void onClick(View v){
                finish();
            }
        });

        Button btnNewWindow2 = (Button) findViewById(R.id.btnNewWindow2);
        btnNewWindow2.setOnClickListener(new View.OnClickListener(){
            public void onClick(View v){
                Intent intent2 = new Intent(getApplicationContext(), ThirdActivity.class);
                startActivity(intent2);
            }
        });

    }


}
