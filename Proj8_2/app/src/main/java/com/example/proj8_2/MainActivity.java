package com.example.proj8_2;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;

import android.Manifest;
import android.os.Bundle;
import android.os.Environment;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;

public class MainActivity extends AppCompatActivity {

    Button btnPrev, btnNext;
    TextView count;
    myPictureView myPicture;
    int curNum=1;
    File[] imageFiles;
    String imageFname;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        setTitle("이미지 뷰어");

        Button btnRead, btnMkdir, btnRmdir, btnFilelist;


        final EditText edtSD;
        btnRead = (Button) findViewById(R.id.btnRead);
        edtSD = (EditText) findViewById(R.id.edtSD);
        btnMkdir = (Button) findViewById(R.id.btnMkdir);
        btnRmdir = (Button) findViewById(R.id.btnRmdir);
        btnFilelist = (Button) findViewById(R.id.btnFilelist);
        btnPrev = (Button) findViewById(R.id.btnPrev);
        btnNext = (Button) findViewById(R.id.btnNext);
        myPicture = (myPictureView) findViewById(R.id.myPictureView1);
        count = (TextView) findViewById(R.id.count);

        final String strSDPath = Environment.getExternalStorageDirectory().getAbsolutePath();
        final File myDir = new File(strSDPath + "/mydir");
        ActivityCompat.requestPermissions(this, new String[] {Manifest.permission.WRITE_EXTERNAL_STORAGE},MODE_PRIVATE);



        btnRead.setOnClickListener(new View.OnClickListener(){
            public void onClick(View v){
                try{
                    FileInputStream inFs = new FileInputStream("/storage/emulated/0/SD_test.txt");
                    byte[] txt = new byte[inFs.available()];
                    inFs.read(txt);
                    edtSD.setText(new String(txt));
                    inFs.close();
                }catch (IOException e){
                    Toast.makeText(MainActivity.this, e.toString() , Toast.LENGTH_SHORT).show();

                }
            }
        });

        btnMkdir.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                myDir.mkdir();

            }
        });

        btnRmdir.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                myDir.delete();
            }
        });

        btnFilelist.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view){
                String sysDir = Environment.getRootDirectory().getAbsolutePath();
                File[] sysFiles = (new File(sysDir).listFiles());

                String strFname;
                for(int i=0; i<sysFiles.length; i++){
                    if(sysFiles[i].isDirectory() ==true)
                        strFname = "<폴더> " + sysFiles[i].toString();
                    else
                        strFname = "<파일> " + sysFiles[i].toString();

                    edtSD.setText(edtSD.getText() + "\n" + strFname);
                }
            }
        });

        imageFiles = new File(Environment.getExternalStorageDirectory().getAbsolutePath()+"/Pictures").listFiles();
        imageFname = imageFiles[1].toString();
        myPicture.imagePath = imageFname;
        count.setText(curNum + "/" + (imageFiles.length-1));

        btnPrev.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(curNum <= 1){
                    curNum = imageFiles.length-1;
                }else{
                    curNum--;
                }
                imageFname = imageFiles[curNum].toString();
                myPicture.imagePath=imageFname;
                myPicture.invalidate();
                count.setText(curNum + "/" + (imageFiles.length-1));
            }
        });

        btnNext.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(curNum >= imageFiles.length-1){
                    curNum =1;
                }else {
                    curNum++;
                }
                imageFname = imageFiles[curNum].toString();
                myPicture.imagePath=imageFname;
                myPicture.invalidate();
                count.setText(curNum + "/" + (imageFiles.length-1));
            }
        });
    }
}