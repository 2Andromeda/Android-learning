package com.example.prac7_4;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.widget.ImageView;

public class MainActivity extends AppCompatActivity {

    ImageView centerImage;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        setTitle("연습문제 7-4");

        centerImage = (ImageView) findViewById(R.id.centerImage);



    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        super.onCreateOptionsMenu(menu);
        MenuInflater mInflater = getMenuInflater();
        mInflater.inflate(R.menu.menu1, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        switch (item.getItemId()){
            case R.id.dog:
                centerImage.setImageResource(R.drawable.dog);
                return true;
            case R.id.cat :
                centerImage.setImageResource(R.drawable.cat);
                return true;
            case R.id.rabbit:
                centerImage.setImageResource(R.drawable.rabbit);
                return true;
        }
        return false;
    }
}