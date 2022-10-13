package com.example.self7_1;

import androidx.appcompat.app.AppCompatActivity;

import android.graphics.Color;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.SubMenu;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;

import org.w3c.dom.Text;

public class MainActivity extends AppCompatActivity {

    LinearLayout baseLayout;
    //Button button;
    ImageView imageView;
    EditText editText;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        setTitle("배경색 바꾸기");
        baseLayout =(LinearLayout) findViewById(R.id.baseLayout);
        imageView = (ImageView) findViewById(R.id.image);
        editText = (EditText) findViewById(R.id.angle);
        //button= (Button) findViewById(R.id.button);


    }

    @Override
    /* project 7_1 start
    public boolean onCreateOptionsMenu(Menu menu) {
        super.onCreateOptionsMenu(menu);
        MenuInflater mInflater = getMenuInflater();
        mInflater.inflate(R.menu.menu1, menu);
        return true;
    }
    @Override
    public boolean onOptionsItemSelected(MenuItem item){
        switch (item.getItemId()){
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
                button.setRotation(45);
                return true;
            case R.id.subSize:
                button.setScaleX(2);
                return true;
        }
        return false;
    }
    //project 7.1 end
    */

    /* ex. 7_2 start
    public boolean onCreateOptionsMenu(Menu menu){
        super.onCreateOptionsMenu(menu);

        menu.add(0, 1, 0, "배경색(빨강)"); // (그룹 아이디, 항목 아이디, 순번, 제목)
        menu.add(0, 2, 0, "배경색(파랑)");
        menu.add(0, 3 , 0, "배경색(초록)");

        SubMenu sMenu = menu.addSubMenu("버튼 변경>>");
        sMenu.add(0, 4, 0, "버튼 45도 회전");
        sMenu.add(0, 5, 0, "버튼 2배 확대");

        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item){
        switch (item.getItemId()){
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
                button.setRotation(45);
                return true;
            case 5:
                button.setScaleX(2);
                return true;
        }
        return false;
    }

    //ex. 7_2 end
    */

    //self 7.1 start
    public boolean onCreateOptionsMenu(Menu menu) {
        super.onCreateOptionsMenu(menu);
        MenuInflater mInflater = getMenuInflater();
        mInflater.inflate(R.menu.menu1, menu);
        return true;
    }
    @Override
    public boolean onOptionsItemSelected(MenuItem item){
        switch (item.getItemId()){
            case R.id.rotate:
                Integer angle;
                angle = Integer.parseInt(editText.getText().toString());
                imageView.setRotation(angle);
                return true;
            case R.id.jeju1:
                imageView.setImageResource(R.drawable.jeju1);
                return true;
            case R.id.jeju2:
                imageView.setImageResource(R.drawable.jeju2);
                return true;
            case R.id.jeju3:
                imageView.setImageResource(R.drawable.jeju3);
                return true;
        }
        return false;
    }

    //self 7.1 end


}