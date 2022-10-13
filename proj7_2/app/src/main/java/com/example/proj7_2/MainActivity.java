package com.example.proj7_2;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.graphics.Color;
import android.os.Bundle;
import android.view.ContextMenu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;

public class MainActivity extends AppCompatActivity {

    LinearLayout baseLayout;
    Button backChange, buttonChange;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        setTitle("컨텍스트 메뉴");
        baseLayout = (LinearLayout) findViewById(R.id.backlayout);
        backChange = (Button) findViewById(R.id.back_change);
        buttonChange = (Button) findViewById(R.id.button_change);

        registerForContextMenu(backChange);
        registerForContextMenu(buttonChange);

    }

    @Override
    public void onCreateContextMenu(ContextMenu menu, View v, ContextMenu.ContextMenuInfo menuInfo) {
        super.onCreateContextMenu(menu, v, menuInfo);

        MenuInflater mInflater = getMenuInflater();
        if(v==backChange){
            menu.setHeaderTitle("배경색 변경");
            menu.add(0,1,0, "배경색(빨강)");
            menu.add(0, 2, 0, "배경색(파랑)");
            menu.add(0, 3, 0, "배경색(초록)");

            //mInflater.inflate(R.menu.menu1, menu); //prac7_2
        }

        if(v==buttonChange){
            menu.setHeaderTitle("버튼 변경");
            menu.add(0, 4, 0, "버튼 회전");
            menu.add(0, 5, 0, "버튼 확장");

            //mInflater.inflate(R.menu.menu2, menu); //prac7_1
        }
    }

    @Override
    public boolean onContextItemSelected(@NonNull MenuItem item) {
        super.onContextItemSelected(item);

        switch(item.getItemId()) {

            case 1:
                baseLayout.setBackgroundColor(Color.RED);
                return true;
            case 2:
                baseLayout.setBackgroundColor(Color.BLUE);
                return true;
            case 3:
                baseLayout.setBackgroundColor(Color.GREEN);
                return true;
            case 4:
                backChange.setRotation(45);
                buttonChange.setRotation(45);
                return true;
            case 5:
                backChange.setScaleX(2);
                buttonChange.setScaleX(2);
                return true;

            /* //prac7_1
            case R.id.itemRed:
                baseLayout.setBackgroundColor(Color.RED);
                return true;
            case R.id.itemBlue:
                baseLayout.setBackgroundColor(Color.BLUE);
                return true;
            case R.id.itemGreen:
                baseLayout.setBackgroundColor(Color.GREEN);
                return true;
            case R.id.subRotate:
                backChange.setRotation(45);
                buttonChange.setRotation(45);
                return true;
            case R.id.subSize:
                backChange.setScaleX(2);
                buttonChange.setScaleX(2);
                return true;
             */
        }
        return false;

    }
}