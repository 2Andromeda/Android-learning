package com.proto.proj11_2;

import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.Spinner;

import androidx.appcompat.app.AppCompatActivity;

public class self11_3 extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.self11_3);
        setTitle("스피너 테스트");

        final String[] movie = {"쿵푸팬더", "짱구", "아저씨"};

        Spinner spinner = (Spinner) findViewById(R.id.spinner1);

        ArrayAdapter<String> adapter;
        adapter = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item, movie);
        spinner.setAdapter(adapter);

        spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int position, long id) {
                Integer[] posterID = new Integer[3];
                for(int i = 1; i < 4; i++){
                    int k = 80 + i;
                    String j = Integer.toString(k);
                    posterID[i-1] = getResources().getIdentifier("mov" + j, "drawable", getPackageName());
                }

                ImageView imageView = (ImageView) findViewById(R.id.ivPoster2);
                imageView.setImageResource(posterID[position]);
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });
    }
}
