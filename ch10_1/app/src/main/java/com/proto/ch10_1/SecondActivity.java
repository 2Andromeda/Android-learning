package com.proto.ch10_1;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.RatingBar;
import android.widget.TextView;

import org.w3c.dom.Text;

public class SecondActivity extends Activity {

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
        TextView resultText = (TextView) findViewById(R.id.resultText);
        ImageView resultImage = (ImageView) findViewById(R.id.resultImage);

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
        }
        resultText.setText(imageName[maxId]);
        resultImage.setImageResource(imageFileId[maxId]);

        Button btnOriWindow = (Button) findViewById(R.id.btnOriWindow);
        btnOriWindow.setOnClickListener(new View.OnClickListener(){
            public void onClick(View v){
                finish();
            }
        });
    }
}
