package com.example.prac7_6;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CompoundButton;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    RadioGroup rdoGroup1;
    RadioButton rdoButton[] = new RadioButton[4];
    Button button1, btClose;
    View dlgview;
    TextView dlgEdtTitle;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        rdoGroup1 = (RadioGroup) findViewById(R.id.rdoGroup1);
        rdoButton[0] = (RadioButton) findViewById(R.id.dog);
        rdoButton[1] = (RadioButton) findViewById(R.id.cat);
        rdoButton[2] = (RadioButton) findViewById(R.id.rabbit);
        rdoButton[3] = (RadioButton) findViewById(R.id.horse);

        button1 = (Button) findViewById(R.id.button);

        for (int i = 0; i< rdoButton.length; i++){
            final int index;
            index=i;
            rdoButton[index].setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    button1.setVisibility(View.VISIBLE);
                }
            });
        }


        button1.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){

                dlgview = (View) View.inflate(MainActivity.this, R.layout.dialog1, null);
                AlertDialog.Builder builder = new AlertDialog.Builder(MainActivity.this);
                AlertDialog dlg = builder.create();
                dlgEdtTitle = (TextView) dlgview.findViewById(R.id.tvHead);
                btClose = (Button) dlgview.findViewById(R.id.btClose);

                switch (rdoGroup1.getCheckedRadioButtonId()) {
                    case R.id.dog:
                        dlgEdtTitle.setText("강아지");
                        break;
                    case R.id.cat:
                        dlgEdtTitle.setText("고양이");
                        break;
                    case R.id.rabbit:
                        dlgEdtTitle.setText("토끼");
                        break;
                    case R.id.horse:
                        dlgEdtTitle.setText("말");
                        break;
                }
                dlg.setView(dlgview);
                dlg.show();

                btClose.setOnClickListener(new View.OnClickListener(){
                    @Override
                    public void onClick(View v){
                        dlg.dismiss();
                    }
                });


            }
        });

    }
}