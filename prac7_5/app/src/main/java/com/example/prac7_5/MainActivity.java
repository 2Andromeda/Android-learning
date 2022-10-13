package com.example.prac7_5;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.Display;
import android.view.Gravity;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    Button buttonToast;
    View toastView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        setTitle("연습문제7-5");

        buttonToast = (Button) findViewById(R.id.toastButton);

        buttonToast.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view) {
                Toast toast = new Toast(MainActivity.this);
                toastView = (View) View.inflate(MainActivity.this, R.layout.dialog1, null);
                toast.setView(toastView);

                Display display = ((WindowManager) getSystemService(WINDOW_SERVICE)).getDefaultDisplay();
                int xOffset = (int) (Math.random()*display.getWidth());
                int yOffset = (int) (Math.random()*display.getHeight());
                toast.setGravity(Gravity.TOP| Gravity.LEFT, xOffset, yOffset);
                toast.show();
            }
        });
    }
}