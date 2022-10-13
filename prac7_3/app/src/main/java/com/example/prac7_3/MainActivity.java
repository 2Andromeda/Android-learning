package com.example.prac7_3;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.DialogInterface;
import android.os.Bundle;
import android.view.Display;
import android.view.Gravity;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    TextView tvName, tvEmail;
    Button button1;
    EditText edName, edEmail;
    TextView toastText;
    View dlglogView, toastView;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        setTitle("사용자 정보 입력");

        tvName = (TextView) findViewById(R.id.tvName);
        tvEmail = (TextView) findViewById(R.id.tvEmail);
        button1 = (Button) findViewById(R.id.button1);


        button1.setOnClickListener(new View.OnClickListener(){
            public void onClick(View v){

                dlglogView = (View) View.inflate(MainActivity.this, R.layout.dialog1, null);
                AlertDialog.Builder dlg = new AlertDialog.Builder(MainActivity.this);
                dlg.setTitle("사용자 정보 입력");
                dlg.setIcon(R.drawable.btn_star_big_on);
                dlg.setView(dlglogView);
                dlg.setPositiveButton("확인", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        edName = (EditText) dlglogView.findViewById(R.id.etName);
                        edEmail = (EditText) dlglogView.findViewById(R.id.etEmail);

                        tvName.setText(edName.getText().toString());
                        tvEmail.setText(edEmail.getText().toString());
                    }
                });
                dlg.setNegativeButton("취소", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        Toast toast = new Toast(MainActivity.this);
                        toastView = (View) View.inflate(MainActivity.this, R.layout.toast1, null);
                        toastText = (TextView) toastView.findViewById(R.id.toastText1);
                        toastText.setText("취소했습니다.");
                        toast.setView(toastView);
                        toast.show();

                    }
                });
                dlg.show();

                /* //대화 상자 연습
                final String[] versionArray = new String[] { "파이", "Q(10)", "R(11)"};
                final boolean[] checkArray = new boolean[] {true, false, false};
                AlertDialog.Builder dlg=new AlertDialog.Builder(MainActivity.this);
                dlg.setTitle("제목입니다.");
                //dlg.setMessage("이곳이 내용입니다.");
                dlg.setIcon(R.mipmap.ic_launcher);
                dlg.setMultiChoiceItems(versionArray, checkArray, new DialogInterface.OnMultiChoiceClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i, boolean isChecked) {
                        button.setText(versionArray[i]);
                    }
                });
                dlg.setPositiveButton("확인", null);
                dlg.show();
                //대화 상자 연습 끝
                */


                /*
                Toast tMsg = Toast.makeText(MainActivity.this, "토스트 연습", Toast.LENGTH_SHORT);


                Display display = ((WindowManager) getSystemService(WINDOW_SERVICE)).getDefaultDisplay();

                int xOffset = (int) (Math.random() * display.getWidth());
                int yOffset = (int) (Math.random() * display.getHeight());

                tMsg.setGravity(Gravity.TOP | Gravity.LEFT, xOffset, yOffset);
                tMsg.show();
                //토스트 메시지
                 */
            }
        });
    }
}