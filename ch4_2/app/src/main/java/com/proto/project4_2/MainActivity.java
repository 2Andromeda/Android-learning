package com.proto.project4_2;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Switch;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    LinearLayout red_lay, yellow_lay, black_lay;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //setContentView(R.layout.activity_main);

        LinearLayout.LayoutParams params1 = new LinearLayout.LayoutParams(
                LinearLayout.LayoutParams.MATCH_PARENT,
                LinearLayout.LayoutParams.MATCH_PARENT);

        LinearLayout.LayoutParams params2 = new LinearLayout.LayoutParams(
                LinearLayout.LayoutParams.MATCH_PARENT,
                LinearLayout.LayoutParams.WRAP_CONTENT, 1);
        LinearLayout.LayoutParams params21 = new LinearLayout.LayoutParams(
                LinearLayout.LayoutParams.MATCH_PARENT,
                LinearLayout.LayoutParams.WRAP_CONTENT, 1);

        LinearLayout.LayoutParams params31 = new LinearLayout.LayoutParams(
                LinearLayout.LayoutParams.WRAP_CONTENT,
                LinearLayout.LayoutParams.MATCH_PARENT, 1);
        LinearLayout.LayoutParams params32 = new LinearLayout.LayoutParams(
                LinearLayout.LayoutParams.WRAP_CONTENT,
                LinearLayout.LayoutParams.MATCH_PARENT, 1);

        LinearLayout.LayoutParams params41 = new LinearLayout.LayoutParams(
                LinearLayout.LayoutParams.MATCH_PARENT,
                LinearLayout.LayoutParams.WRAP_CONTENT, 1);
        LinearLayout.LayoutParams params42 = new LinearLayout.LayoutParams(
                LinearLayout.LayoutParams.MATCH_PARENT,
                LinearLayout.LayoutParams.WRAP_CONTENT, 1);



        LinearLayout baselayout1 = new LinearLayout(this);
        baselayout1.setOrientation(LinearLayout.VERTICAL);
        baselayout1.setBackgroundColor(Color.rgb(0, 0, 255));


        LinearLayout baselayout2 = new LinearLayout(this);
        baselayout2.setOrientation(LinearLayout.HORIZONTAL);
        baselayout2.setBackgroundColor(Color.rgb(255, 0, 0));

        LinearLayout baselayout21 = new LinearLayout(this);


        LinearLayout baselayout31 = new LinearLayout(this);
        baselayout31.setOrientation(LinearLayout.VERTICAL);

        LinearLayout baselayout32=new LinearLayout(this);
        baselayout32.setOrientation(LinearLayout.VERTICAL);

        LinearLayout baselayout41 = new LinearLayout(this);
        baselayout41.setBackgroundColor(Color.rgb(255, 255, 0));

        LinearLayout baselayout42 = new LinearLayout(this);
        baselayout42.setBackgroundColor(Color.rgb(0, 0, 0));


        baselayout1.addView(baselayout2, params2);
        baselayout1.addView(baselayout21, params21);

        baselayout2.addView(baselayout31, params31);
        baselayout2.addView(baselayout32, params32);

        baselayout32.addView(baselayout41, params41);
        baselayout32.addView(baselayout42, params42);

        setContentView(baselayout1, params1);




    }


}