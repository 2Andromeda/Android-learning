package com.example.proj8_1;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;

import android.Manifest;
import android.content.Context;
import android.os.Bundle;
import android.os.Environment;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.Toast;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.Arrays;
import java.util.Calendar;
import java.util.Date;


public class MainActivity extends AppCompatActivity {

    DatePicker dp;
    EditText edtDiary;
    Button btnWrite, btnInRead, btnSDconvert, btnSDwrite, btnInconvert;
    String fileName;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        setTitle("간단 일기장");

        dp = (DatePicker) findViewById(R.id.datePicer1);
        edtDiary = (EditText) findViewById(R.id.edtDiary);
        btnWrite = (Button) findViewById(R.id.btnWrite);
        btnInRead = (Button) findViewById(R.id.btnInRead);

        btnSDconvert = (Button) findViewById(R.id.btnSDconver);
        btnSDwrite = (Button) findViewById(R.id.btnSDwrite);
        btnInconvert = (Button) findViewById(R.id.btnIncover);


//        Calendar cal = Calendar.getInstance();
//        int cYear = cal.get(Calendar.YEAR);
//        int cMonth = cal.get(Calendar.MONTH);
//        int cDay = cal.get(Calendar.DAY_OF_MONTH);

        int cYear = Calendar.getInstance().get(Calendar.YEAR);
        int cMonth = Calendar.getInstance().get(Calendar.MONTH);
        int cDay = Calendar.getInstance().get(Calendar.DAY_OF_MONTH);
        ActivityCompat.requestPermissions(this, new String[] {Manifest.permission.WRITE_EXTERNAL_STORAGE}, MODE_PRIVATE);
        String SDPath = "/sdcard";
        File myDir = new File(SDPath + "/myDiary");

        fileName = Integer.toString(cYear) + "_" + Integer.toString(cMonth + 1) + "_"
                + Integer.toString(cDay) + ".txt";
        String str = readDiary(fileName);
        edtDiary.setText(str);
        btnWrite.setEnabled(true);

        dp.init(cYear, cMonth, cDay, new DatePicker.OnDateChangedListener() {//데이트 피커 init으로 초기화 onDatechangedListener 는 날짜가 변경되었을 시 적용되도록

            @Override
            public void onDateChanged(DatePicker datePicker, int year, int monthOfYear, int dayOfMonth) {

                fileName = Integer.toString(year) + "_" + Integer.toString(monthOfYear + 1) + "_"
                        + Integer.toString(dayOfMonth) + ".txt";
                if(btnSDwrite.getVisibility() == View.GONE) {
                    String str = readDiary(fileName);
                    edtDiary.setText(str);
                    btnWrite.setEnabled(true);
                } else if(btnSDwrite.getVisibility() == View.VISIBLE){

                    String str = readDiarySD(fileName);
                    edtDiary.setText(str);
                    btnSDwrite.setEnabled(true);
                }
            }
        });

        btnWrite.setOnClickListener(new View.OnClickListener(){
            public void onClick(View v){
                try{
                    FileOutputStream outFs = openFileOutput(fileName, Context.MODE_PRIVATE);
                    String str = edtDiary.getText().toString();
                    outFs.write(str.getBytes());
                    outFs.close();
                    Toast.makeText(getApplicationContext(), fileName+" 이 저장됨", Toast.LENGTH_SHORT).show();
                }catch (IOException e){

                }
            }
        });

        btnInRead.setOnClickListener(new View.OnClickListener(){
            public void onClick(View v){
                try{
                    InputStream inputS = getResources().openRawResource(R.raw.raw_test);
                    byte[] txt = new byte[inputS.available()];
                    inputS.read(txt);
                    edtDiary.setText(new String(txt));
                    inputS.close();
                }catch (IOException e){}
            }
        });

        btnSDconvert.setOnClickListener(new View.OnClickListener(){
            public void onClick(View v){
                btnSDwrite.setVisibility(View.VISIBLE);
                btnInconvert.setVisibility(View.VISIBLE);

                String[] sysFiles = new File(SDPath).list();

                if(Arrays.asList(sysFiles).contains("myDiary") == false){
                    myDir.mkdir();
                }

                String str = readDiarySD(fileName);
                edtDiary.setText(str);
                btnSDwrite.setEnabled(true);
            }
        });

        btnSDwrite.setOnClickListener(new View.OnClickListener(){
            public void onClick(View v){
                try{
                    FileOutputStream outFs = new FileOutputStream(myDir.toString() + "/" + fileName);
                    String str = edtDiary.getText().toString();
                    outFs.write(str.getBytes());
                    outFs.close();
                    Toast.makeText(getApplicationContext(), fileName + " 이 SD 카드에 저장됨", Toast.LENGTH_SHORT).show();
                }catch (IOException e){
                    Toast.makeText(MainActivity.this, e.toString(), Toast.LENGTH_SHORT).show();
                }
            }
        });

        btnInconvert.setOnClickListener(new View.OnClickListener(){
            public void onClick(View v){
                btnSDwrite.setVisibility(View.GONE);
                btnInconvert.setVisibility(View.GONE);

                String str = readDiary(fileName);
                edtDiary.setText(str);
                btnWrite.setEnabled(true);
            }
        });
    }

    String readDiary(String fName){
        String diaryStr = null;
        FileInputStream inFs;
        try{
            inFs = openFileInput(fName);
            byte[] txt = new byte[500];
            inFs.read(txt);
            inFs.close();
            diaryStr = (new String(txt)).trim();//앞뒤 공백 을 제거하는 함수 =trim()
            btnWrite.setText("수정하기");
        }catch (IOException e){
            edtDiary.setHint("일기 없음");
            btnWrite.setText("새로 저장");
        }
        return diaryStr;
    }

    String readDiarySD(String fName){
        String diaryStr = null;
        try{
            FileInputStream inFs = new FileInputStream("/sdcard/myDiary/" + fName);
            byte[] txt = new byte[inFs.available()];
            inFs.read(txt);
            inFs.close();
            diaryStr = (new String(txt)).trim();
            btnSDwrite.setText("수정하기(SD)");
        } catch (IOException e){
            edtDiary.setHint("일기 없음");
            btnWrite.setText("새로 저장");
        }
        return diaryStr;
    }
}

